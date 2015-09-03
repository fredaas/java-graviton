package com.fredaas.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.fredaas.main.Game;

public class LineMap {
    
    private float centerX;
    private float centerY;
    private float[] x;
    private float[] y;
    private int numPoints;
    private float radius;
    private final float PI = 3.141592654f;
    private float rad = PI / 2;

    public LineMap(int numPoints, float radius) {
        this.numPoints = numPoints;
        this.radius = radius;
        init(); 
    }
    
    private void init() {
        centerX = Game.WIDTH / 2;
        centerY = Game.HEIGHT / 2;
        x = new float[numPoints];
        y = new float[numPoints];
        createMap();
    }
    
    private void createMap() {
        float radOffset = 2 * PI / numPoints;
        
        for (int i = 0; i < numPoints; i++) {
            x[i] = centerX + MathUtils.cos(rad) * radius;
            y[i] = centerY + MathUtils.sin(rad) * radius;
            rad += radOffset;
        }
    }
    
    public void draw(ShapeRenderer sr) {
        sr.begin(ShapeType.Line);
            for (int i = 0, j = numPoints - 1; i < numPoints; j = i++) {
                sr.line(x[i], y[i], x[j], y[j]);
            }
        sr.end();
    }
    
}
