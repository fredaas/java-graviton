package com.fredaas.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.fredaas.main.Game;

public class LineMap extends SpaceObject {
    
    public LineMap(int numPoints, float radius) {
        this.numPoints = numPoints;
        this.radius = radius;
        init(); 
    }
    
    public void init() {
        x = Game.WIDTH / 2;
        y = Game.HEIGHT / 2;
        posx = new float[numPoints];
        posy = new float[numPoints];
        createMap();
    }
    
    private void createMap() {
        float radOffset = 2 * PI / numPoints;
        
        for (int i = 0; i < numPoints; i++) {
            posx[i] = x + MathUtils.cos(rad) * radius;
            posy[i] = y + MathUtils.sin(rad) * radius;
            rad += radOffset;
        }
    }
    
    @Override
    public void update(float dt) {}
    
    @Override
    public void draw(ShapeRenderer sr) {
        sr.begin(ShapeType.Line);
            for (int i = 0, j = numPoints - 1; i < numPoints; j = i++) {
                sr.line(posx[i], posy[i], posx[j], posy[j]);
            }
        sr.end();
    }
    
}
