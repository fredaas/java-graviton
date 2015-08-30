package com.fredaas.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.fredaas.main.Game;

public class Asteroid extends SpaceObject {
    
    private int numPoints;
    private float delta[];
    private float angleOffset;
    
    public Asteroid (float radius) {
        this.x = (float) Math.random() * Game.WIDTH;
        this.y = (float) Math.random() * Game.HEIGHT;
        this.radius = radius;
        init();
    }

    @Override
    public void init() {
        numPoints = 12;
        angleOffset = (2 * PI) / numPoints;
        posx = new float[numPoints];
        posy = new float[numPoints];
        delta = new float[numPoints];
        rotationSpeed = MathUtils.random(-1, 1);
        
        for (int i = 0; i < numPoints; i++) {
            delta[i] = MathUtils.random(radius / 2, radius);
            posx[i] = x + MathUtils.cos(rad) * delta[i];
            posy[i] = y + MathUtils.sin(rad) * delta[i];
            rad += angleOffset;
        }
    }
    
    private void setOrientation() {
        for (int i = 0; i < numPoints; i++) {
            posx[i] = x + MathUtils.cos(rad) * delta[i];
            posy[i] = y + MathUtils.sin(rad) * delta[i];
            rad += angleOffset;
        }
    }

    @Override
    public void update(float dt) {
        rad += rotationSpeed * dt;
        setOrientation();
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
