package com.fredaas.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.fredaas.main.Game;

public class Asteroid extends SpaceObject {
    
    private int numPoints;
    
    public Asteroid (float radius) {
        this.x = (float) Math.random() * Game.WIDTH;
        this.y = (float) Math.random() * Game.HEIGHT;
        this.radius = radius;
        init();
    }

    @Override
    public void init() {
        numPoints = 10;
        float angleOffset = (2 * PI) / numPoints;
        posx = new float[numPoints];
        posy = new float[numPoints];
        
        
        for (int i = 0; i < numPoints; i++) {
            float delta = (float) Math.random() * radius / 2 + radius / 2;
            posx[i] = x + MathUtils.cos(rad) * delta;
            posy[i] = y + MathUtils.sin(rad) * delta;
            rad += angleOffset;
        }
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
