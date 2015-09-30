package com.fredaas.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.fredaas.main.Game;
import com.fredaas.states.PlayState;

public class BlackHole extends SpaceObject {
    
    public BlackHole() {
        init();
    }

    @Override
    public void init() {
        radius = 150;
        numPoints = 8;
        radOffset = 2 * PI / numPoints;
        posx = new float[numPoints];
        posy = new float[numPoints];
        LineMap map = PlayState.map;
        float angle = MathUtils.random(0, 2 * PI);
        distance = MathUtils.random(map.getRadius() / 1.5f, map.getRadius() / 1.2f);
        x = map.getX() + MathUtils.cos(angle) * distance;
        y = map.getY() + MathUtils.sin(angle) * distance;
        
        for (int i = 0; i < numPoints; i++) {
            posx[i] = x + MathUtils.cos(rad) * radius;
            posy[i] = y + MathUtils.sin(rad) * radius;
            rad += radOffset;
        }
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void draw(ShapeRenderer sr) {
        sr.begin(ShapeType.Line);
            for (int i = 0, j = numPoints - 1; i < numPoints; j = i++) {
                sr.line(posx[i], posy[i], posx[j], posy[j]);
            }
        sr.end();
    }
    
}
