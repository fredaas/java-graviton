package com.fredaas.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class SpaceObject {

    protected float x;
    protected float y;
    protected float r;
    protected float speed;
    protected float posx[];
    protected float posy[];
    protected final float PI = 3.141592654f;
    protected float radians = PI / 2;

    public SpaceObject(float x, float y, float r) {
        this.x = x;
        this.y = y;
    }
    
    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw(ShapeRenderer sr);
    
}
