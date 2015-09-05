package com.fredaas.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Tracker {
    
    protected float x;
    protected float y;
    protected float tx;
    protected float ty;
    protected float dx; 
    protected float dy;
    protected float xSpeed;
    protected float ySpeed;
    protected int numPoints;
    protected final float PI = 3.141502654f;
    protected float rad = PI / 2;
    protected float radius;
    protected float posx[];
    protected float posy[];
    
    public Tracker() {}
    
    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw(ShapeRenderer sr);
    
}
