package com.fredaas.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.fredaas.states.PlayState;

public class StandardTracker extends SpaceObject {
    
    private double id;
    private float dist[];
    
    public StandardTracker(float x, float y) {
        this.x = x;
        this.y = y;
        init();
    }
    
    @Override
    public void init() {
        id = System.nanoTime();
        xSpeed = MathUtils.random(0.5f, 1);
        ySpeed = MathUtils.random(0.5f, 1);
        radius = 10;
        dist = new float[2];
        dist[0] = radius;
        dist[1] = radius / 2;
        numPoints = 12;
        radOffset = 2 * PI / numPoints;
        posx = new float[numPoints];
        posy = new float[numPoints];
        
        for (int i = 0; i < numPoints; i++) {
            radius = i % 2 != 0 ? dist[0] : dist[1];
            posx[i] = x + MathUtils.cos(rad) * radius;
            posy[i] = y + MathUtils.sin(rad) * radius;
            rad += radOffset;
        }
    }
    
    private void setDirection() {
        for (int i = 0; i < numPoints; i++) {
            radius = i % 2 != 0 ? dist[0] : dist[1];
            posx[i] = x + MathUtils.cos(rad) * radius;
            posy[i] = y + MathUtils.sin(rad) * radius;
            rad += radOffset;
        }
    }
    
    private void follow(float x, float y) {
        setAngle(x, y);
        
        dx = MathUtils.cos(rad) * (distance + 50) * xSpeed;
        dy = MathUtils.sin(rad) * (distance + 50) * ySpeed;
    }
    
    @Override
    public void update(float dt) {
        Player player = PlayState.player;
        ArrayList<Asteroid> asteroids = PlayState.asteroids;
        ArrayList<StandardTracker> enemies = PlayState.trackers;
        
        for (int i = 0; i < asteroids.size(); i++) {
            Asteroid a = asteroids.get(i);
            setGravity(a.getX(), a.getY(), 5, Force.REPEL, dt);
        }
        
        for (int i = 0; i < enemies.size(); i++) {
            StandardTracker t = enemies.get(i);
            if (t.id != this.id) {
                setGravity(t.getX(), t.getY(), 0.25f, Force.REPEL, dt);
            }
        }
        
        follow(player.getX(), player.getY());
        setDirection();
        
        x += dx * dt;
        y += dy * dt;
    }

    @Override
    public void draw(ShapeRenderer sr) {
        
        sr.begin(ShapeType.Line);
        sr.setColor(0, 1, 0, 1);
            for (int i = 0, j = numPoints - 1; i < numPoints; j = i++) {
                sr.line(posx[i], posy[i], posx[j], posy[j]);
            }
        sr.end();
    }
    
}
