package com.fredaas.states;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fredaas.entities.Player;
import com.fredaas.handlers.GameKeys;
import com.fredaas.handlers.GameKeys.Key;
import com.fredaas.handlers.GameStateManager;
import com.fredaas.main.Main;

public class PlayState extends GameState {
    
    private Player player;

    public PlayState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }
    
    @Override
    public void init() {
        player = new Player(Main.WIDTH / 2, Main.HEIGHT / 2);        
    }
    
    @Override
    public void update(float dt) {
        player.update(dt);
        handleInput();
    }

    @Override
    public void draw(ShapeRenderer sr) {
        player.draw(sr);
    }
    
    public void handleInput() {
        player.setLeft(GameKeys.isDown(Key.LEFT));
        player.setRight(GameKeys.isDown(Key.RIGHT));
        player.setUp(GameKeys.isDown(Key.UP));
    }
    
}
