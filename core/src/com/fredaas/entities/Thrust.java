package com.fredaas.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class Thrust extends SpaceObject {
    
    private boolean ready = false;
    private float alpha = 1;
    
    public Thrust(float x, float y, float angle) {
        init();
        this.rad = angle + PI;
        this.x = x + MathUtils.cos(rad) * distance;
        this.y = y + MathUtils.sin(rad) * distance;
    }

    @Override
    public void init() {
        radius = 2;
        distance = 25;
        xSpeed = 3;
        ySpeed = 3;
    }
    
    public boolean ready() {
        return ready;
    }

    @Override
    public void update(float dt) {
        alpha -= 0.03;
        
        if (alpha < 0) {
            ready = true;
        }
        
        dx = MathUtils.cos(rad) * xSpeed;
        dy = MathUtils.sin(rad) * ySpeed;
        x += dx;
        y += dy;
    }

    @Override
    public void draw(ShapeRenderer sr) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        sr.setColor(1, 1, 1, alpha);
        sr.begin(ShapeType.Filled);
            sr.circle(x, y, radius);
        sr.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

}
