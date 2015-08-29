package com.fredaas.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class Player extends SpaceObject {
    
    public Player(float x, float y) {
        super(x, y, 0);
        init();
    }

    @Override
    public void init() {
        posx = new float[4];
        posy = new float[4];
        float scale = 1.1f;
        posx[0] = x + MathUtils.cos(PI / 2) * 10 * scale;
        posy[0] = y + MathUtils.sin(PI / 2) * 10 * scale;
        posx[1] = x + MathUtils.cos(-2 * PI / 6) * 15 * scale;
        posy[1] = y + MathUtils.sin(-2 * PI / 6) * 15 * scale;
        posx[2] = x + MathUtils.cos(-PI / 2) * 10 * scale;
        posy[2] = y + MathUtils.sin(-PI / 2) * 10 * scale;
        posx[3] = x + MathUtils.cos(8 * PI / 6) * 15 * scale;
        posy[3] = y + MathUtils.sin(8 * PI / 6) * 15 * scale;
    }
    
    @Override
    public void update(float dt) {
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
