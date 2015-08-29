package com.fredaas.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class SpaceObject {

    protected float x;
    protected float y;
    protected float r;
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

    public SpaceObject(float x, float y, float r) {
        this.x = x;
        this.y = y;
    }
    
    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw(ShapeRenderer sr);
    
}
