package com.fredaas.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends SpaceObject {

    private float angle;
    private float ttl;
    private float timerStart;
    private float timerDiff;
    
    public Bullet(float x, float y, float angle) {    
        this.x = x;
        this.y = y;
        this.angle = angle;
        init();
    }
    
    @Override
    public void init() {
        ttl = 500;
        timerStart = System.nanoTime();
        radius = 2;
        dx = MathUtils.cos(angle) * 400;
        dy = MathUtils.sin(angle) * 400;
    }
    
    public boolean remove() {
        return timerDiff > ttl;
    }
    
    @Override
    public void update(float dt) {
        x += dx * dt;
        y += dy * dt;
        timerDiff = (System.nanoTime() - timerStart) / 1000000;
    }

    @Override
    public void draw(ShapeRenderer sr) {
        sr.begin(ShapeType.Filled);
            sr.circle(x, y, radius);
        sr.end();
    }
    
}
