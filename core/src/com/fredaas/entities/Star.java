package com.fredaas.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Star {
    
    private float x;
    private float y;
    private final Color COLOR = new Color(1, 1, 1, 0.5f);
    
    public Star(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void draw(ShapeRenderer sr) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        sr.setColor(COLOR);
        sr.begin(ShapeType.Filled);
            sr.circle(x, y, 2);
        sr.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

}
