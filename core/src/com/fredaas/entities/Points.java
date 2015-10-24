package com.fredaas.entities;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.fredaas.handlers.Font;
import com.fredaas.main.Game;

public class Points {
    
    private float x;
    private float y;
    private float dx;
    private float dy;
    private float speed;
    private float angle;
    private float alpha;
    private final float PI = 3.141592654f;
    public boolean ready = false;
    private SpriteBatch sb;
    private BitmapFont bmf;
    private GlyphLayout text;
    private String score = "1000";
    
    public Points(float x, float y) {
        init();
        this.x = x;
        this.y = y;
    }
    
    private void init() {
        sb = new SpriteBatch();
        text = new GlyphLayout();
        bmf = Font.HYPERSPACE_BOLD_H5;
        speed = 100;
        angle = MathUtils.random(0, 2 * PI);
        dx = MathUtils.cos(angle) * speed;
        dy = MathUtils.sin(angle) * speed;
        alpha = 1;
    }
    
    public boolean ready() {
        return ready;
    }
    
    public void update(float dt) {
        alpha -= 0.01;
        
        if (alpha < 0) {
            alpha = 0;
            ready = true;
        }
        
        x += dx * dt;
        y += dy * dt;
    }
    
    public void draw(ShapeRenderer sr) {
        sb.setProjectionMatrix(Game.cam.combined);
        sb.begin();
        bmf.setColor(1, 1, 1, alpha);
        text.setText(bmf, score);
        bmf.draw(sb, text, x, y);
        sb.end();
    }
    
}
