package com.fredaas.states;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fredaas.entities.Asteroid;
import com.fredaas.entities.Player;
import com.fredaas.handlers.GameKeys;
import com.fredaas.handlers.GameKeys.Key;
import com.fredaas.handlers.GameStateManager;
import com.fredaas.main.Game;

public class PlayState extends GameState {
    
    private Player player;
    private ArrayList<Asteroid> asteroids;

    public PlayState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }
    
    @Override
    public void init() {
        player = new Player(Game.WIDTH / 2, Game.HEIGHT / 2);     
        asteroids = new ArrayList<Asteroid>();
        createAsteroids(10);
    }
    
    @Override
    public void update(float dt) {
        Game.cam.position.set(player.getX(), player.getY(), 0);
        Game.cam.update();
        
        player.update(dt);
        
        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).update(dt);
        }
        
        handleInput();
    }

    @Override
    public void draw(ShapeRenderer sr) {
        sr.setProjectionMatrix(Game.cam.combined);
        
        player.draw(sr);
        
        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).draw(sr);
        }
    }
    
    private void createAsteroids(int num) {
        for (int i = 0; i < num; i++) {
            asteroids.add(new Asteroid(20));
        }
    }
    
    private void handleInput() {
        player.setLeft(GameKeys.isDown(Key.LEFT));
        player.setRight(GameKeys.isDown(Key.RIGHT));
        player.setUp(GameKeys.isDown(Key.UP));
    }
    
}
