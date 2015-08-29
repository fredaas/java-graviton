package com.fredaas.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class Player extends SpaceObject {
    
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    
    public Player(float x, float y) {
        super(x, y, 0);
        init();
    }

    @Override
    public void init() {
        posx = new float[4];
        posy = new float[4];
    }
    
    public void setOrientation() {
        float scale = 1.1f;
        posx[0] = x + MathUtils.cos(rad) * 10 * scale;
        posy[0] = y + MathUtils.sin(rad) * 10 * scale;
        posx[1] = x + MathUtils.cos(rad + 5 * PI / 6) * 15 * scale;
        posy[1] = y + MathUtils.sin(rad + 5 * PI / 6) * 15 * scale;
        posx[2] = x + MathUtils.cos(rad + PI) * 10 * scale;
        posy[2] = y + MathUtils.sin(rad + PI) * 10 * scale;
        posx[3] = x + MathUtils.cos(rad - 5 * PI / 6) * 15 * scale;
        posy[3] = y + MathUtils.sin(rad - 5 * PI / 6) * 15 * scale;
    }
    
    public void setLeft(boolean b){    
        left = b;
    }
    
    public void setRight(boolean b) {
        right = b;
    }
    
    public void setUp(boolean b) {
        up = b;
    }
    
    
    @Override
    public void update(float dt) {
        if (left) {
            rad += rotationSpeed * dt;
        }
        if (right) {
            rad -= rotationSpeed * dt;
        }
        if(up) {
            dx += MathUtils.cos(rad) * accelerationSpeed * dt;
            dy += MathUtils.sin(rad) * accelerationSpeed * dt;
        }
        
        float speed = (float) Math.sqrt(dx * dx + dy * dy);
        if(speed > 0) {
            dx -= (dx / speed) * resistance * dt;
            dy -= (dy / speed) * resistance * dt;
        }
        if(speed > maxSpeed) {
            dx = (dx / speed) * maxSpeed;
            dy = (dy / speed) * maxSpeed;
        }
        
        x += dx * dt;
        y += dy * dt;
        
        setOrientation();
    }

    @Override
    public void draw(ShapeRenderer sr) {
        sr.begin(ShapeType.Line);
            int length = posx.length;
            for (int i = 0, j = length - 1; i < length; j = i++) {
                sr.line(posx[i], posy[i], posx[j], posy[j]);
            }
        sr.end();
    }
    
}
