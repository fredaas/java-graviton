package com.fredaas.states;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.fredaas.entities.Asteroid;
import com.fredaas.entities.Asteroid.Type;
import com.fredaas.entities.BlackHole;
import com.fredaas.entities.LineMap;
import com.fredaas.entities.Player;
import com.fredaas.entities.Points;
import com.fredaas.entities.StandardTracker;
import com.fredaas.entities.Star;
import com.fredaas.handlers.Font;
import com.fredaas.handlers.GameKeys;
import com.fredaas.handlers.GameKeys.Key;
import com.fredaas.handlers.GameStateManager;
import com.fredaas.main.Game;

public class PlayState extends GameState {
    
    public static Player player;
    public static ArrayList<Asteroid> asteroids;
    public static ArrayList<Star> stars;
    public static LineMap map;
    public static ArrayList<StandardTracker> trackers;
    public static ArrayList<BlackHole> blackHoles;
    public static ArrayList<Points> points;

    public PlayState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }
    
    @Override
    public void init() {
        Font.init(); // This should be done some other place
        player = new Player(Game.WIDTH / 2, Game.HEIGHT / 2);     
        asteroids = new ArrayList<Asteroid>();
        trackers = new ArrayList<StandardTracker>();
        blackHoles = new ArrayList<BlackHole>();
        stars = new ArrayList<Star>();
        map = new LineMap(12, 2000);
        points = new ArrayList<Points>();
        createAsteroids(10);
        createStars(500);
        createTrackers(50);
        createBlackHoles(4);
    }
    
    @Override
    public void update(float dt) {
        Game.cam.position.set(player.getX(), player.getY(), 0);
        Game.cam.update();
        
        player.update(dt);
        
        for (int i = 0; i < points.size(); i++) {
            Points current = points.get(i);
            current.update(dt);
            if (current.ready()) {
                points.remove(i);
                i--;
            }
        }
        
        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).update(dt);
        }
        
        for (int i = 0; i < trackers.size(); i++) {
            trackers.get(i).update(dt);
        }
        
        for (int i = 0; i < blackHoles.size(); i++) {
            blackHoles.get(i).update(dt);
        }
        
        /*
         * Check for collision between player and asteroids
         */
        for (int i = 0; i < asteroids.size(); i++) {
            Asteroid a = asteroids.get(i);
            float[] posx = a.getPosX();
            float[] posy = a.getPosY();
            for (int j = 0; j < a.numPoints(); j++) {
                if (player.contains(posx[j], posy[j])) {
                    asteroids.remove(i);
                    i--;
                    break;
                }
            }
        }
        
        /*
         * Check for collision between player line map
         */
        float[] px = player.getPosX();
        float[] py = player.getPosY();
        for (int i = 0; i < player.numPoints(); i++) {
            if (!map.contains(px[i], py[i])) {
                player.isDead(true);
            }
        }
        
        /*
         * Check for collision between asteroid and map
         */
        for (int i = 0; i < asteroids.size(); i++) {
            Asteroid a = asteroids.get(i);
            for (int j = 0; j < a.numPoints(); j++) {
                if (!map.contains(a.getPosX()[j], a.getPosY()[j])) {
                    if (a.getType() != Type.SMALL) {
                        float delta = 1;
                        for (int k = 0; k < 2; k++) {
                            asteroids.add(new Asteroid(
                                    a.getX(),
                                    a.getY(),
                                    delta *= -1,
                                    a.getType()));
                        }
                    }
                    asteroids.remove(i);
                    i--;
                    break;
                }
            }
        }
        
        handleInput();
    }
    
    @Override
    public void draw(ShapeRenderer sr) {
        player.draw(sr);
        map.draw(sr);
        
        for (int i = 0; i < points.size(); i++) {
            points.get(i).draw(sr);
        }
        
        for (int i = 0; i < blackHoles.size(); i++) {
            blackHoles.get(i).draw(sr);
        }
        
        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).draw(sr);
        }
        
        for (int i = 0; i < trackers.size(); i++) {
            trackers.get(i).draw(sr);
        }
        
        for (int i = 0; i < stars.size(); i++) {
            stars.get(i).draw(sr);
        }
        
        sr.setProjectionMatrix(Game.cam.combined);
    }
    
    private void createBlackHoles(int num) {
        for (int i = 0; i < num; i++) {
            blackHoles.add(new BlackHole());
        }
    }
    
    private void createAsteroids(int num) {
        for (int i = 0; i < num; i++) {
            asteroids.add(new Asteroid(Type.LARGE));
        }
    }
    
    private void createTrackers(int num) {    
        for (int i = 0; i < num; i++) {
            float x = MathUtils.random(0, Game.WIDTH);
            float y = MathUtils.random(0, Game.HEIGHT);
            trackers.add(new StandardTracker(x, y));
        }
    }
    
    private void createStars(int num) {
        float x;
        float y;
        
        for (int i = 0; i < num; i++) {
            do {
                x = MathUtils.random(map.getX() - map.getRadius(), 
                        map.getX() + map.getRadius());
                y = MathUtils.random(map.getY() - map.getRadius(), 
                        map.getY() + map.getRadius());
            }
            while (!map.contains(x, y));
            stars.add(new Star(x, y));
        }
    }
    
    private void handleInput() {
        player.setLeft(GameKeys.isDown(Key.LEFT));
        player.setRight(GameKeys.isDown(Key.RIGHT));
        player.setUp(GameKeys.isDown(Key.UP));
        player.setFire(GameKeys.isDown(Key.F));
    }
    
}
