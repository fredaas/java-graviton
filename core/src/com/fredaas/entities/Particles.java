package com.fredaas.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class Particles extends SpaceObject {
    
    private boolean remove;
    private float delta;
    private float alpha;
    
    public Particles(float x, float y, float r) {
        init();
        this.distance = r * 1.3f;
        this.tx = x;
        this.ty = y;
        this.x = tx + MathUtils.cos(rad) * distance;
        this.y = ty + MathUtils.sin(rad) * distance;
    }

    @Override
    public void init() {
        rad = MathUtils.random(0, 2 * PI);
        delta = MathUtils.random(0.90f, 0.95f);
        radius = 2;
        remove = false;
        alpha = 1;
    }
    
    public boolean ready() {
        return remove;
    }

    @Override
    public void update(float dt) {
        distance *= delta;
        alpha -= 0.02;
        x = tx + MathUtils.cos(rad) * distance;
        y = ty + MathUtils.sin(rad) * distance;
        
        if (distance < 1 || alpha < 0) {
            remove = true;
        }
    }

    @Override
    public void draw(ShapeRenderer sr) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        sr.setColor(1, 0, 0, alpha);
        sr.begin(ShapeType.Filled);
            sr.circle(x, y, radius);
        sr.end();
        resetColor(sr);
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

}
