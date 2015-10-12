package com.fredaas.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.fredaas.states.PlayState;

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
    protected float tx = 0;
    protected float ty = 0;
    protected final float PI = 3.141592654f;
    protected float rad = PI / 2;
    protected int numPoints;
    protected float xSpeed;
    protected float ySpeed;
    protected float radOffset;
    protected float distance;
    public static enum Force {
        REPEL,
        ATTRACT;
    }
    
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
    
    protected void setGravity(float x, float y, float strength, Force force, float dt) {
        setAngle(x, y);
        
        switch (force) {
            case REPEL:
                setForce(-1, strength);
                break;
            case ATTRACT:
                setForce(1, strength);
                break;
        }
        
        this.x += tx * dt;
        this.y += ty * dt;
        
        dx = 0;
        dy = 0;
        tx = 0;
        ty = 0; 
    }
    
    protected void setAngle(float x, float y) {
        float deltaX = this.x - x;
        float deltaY = this.y - y;
        
        rad = MathUtils.atan2(deltaY, deltaX);
        
        if (rad < PI) {
            rad += PI;
        }
        
        distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
    
    private void setForce(float direction, float strength) {
        float delta = PlayState.map.getRadius();
        
        tx = MathUtils.cos(rad) * (delta / distance) * direction * strength;
        ty = MathUtils.sin(rad) * (delta / distance) * direction * strength;
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
    
    public void resetColor(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);
    }
    
    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw(ShapeRenderer sr);
    
}
