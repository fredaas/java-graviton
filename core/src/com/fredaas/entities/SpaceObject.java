package com.fredaas.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class SpaceObject {

    protected float x;
    protected float y;
    protected float radius;
    protected float posx[];
    protected float posy[];
    protected float rotationSpeed = 3;
    protected float accelerationSpeed = 200;
    protected float resistance = 10;
    protected float maxSpeed = 300;
    protected float dx = 0;
    protected float dy = 0;
    protected final float PI = 3.141592654f;
    protected float rad = PI / 2;
    protected int numPoints;
    protected float xSpeed;
    protected float ySpeed;
    protected float radOffset;
    protected float distance;

    public SpaceObject() {}
    
    /*
     * Check if polygon shape contains the point (x, y)
     */
    public boolean contains(float x, float y) {
        int length = posx.length;
        boolean hit = false;
        
        for (int i = 0, j = length - 1; i < length; j = i++) {
            if (((posy[i] > y) != (posy[j] > y)) && 
                    (x < (posx[j] - posx[i]) * 
                    (y - posy[i]) / (posy[j] - posy[i]) + posx[i])) { 
                hit = !hit;
            }
        }
        
        return hit;
    }
    
    public float[] getPosX() {
        return posx;
    }
    
    public float[] getPosY() {
        return posy;
    }
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
    
    public float getRadius() {
        return radius;
    }
    
    public int numPoints() {
        return numPoints;
    }
    
    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw(ShapeRenderer sr);
    
}
