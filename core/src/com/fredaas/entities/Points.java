package com.fredaas.entities;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fredaas.handlers.Font;

public class Points {
    
    private float x;
    private float y;
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
        alpha = 1;
    }
    
    public boolean ready() {
        return ready;
    }
    
    public void update(float dt) {
        alpha -= 0.01;
        
        if (alpha < 0) {
            ready = true;
        }
    }
    
    public void draw(ShapeRenderer sr) {
        sb.begin();
        text.setText(bmf, score);
        bmf.setColor(1, 1, 1, alpha);
        bmf.draw(sb, text, x, y);
        sb.end();
    }
    
}
