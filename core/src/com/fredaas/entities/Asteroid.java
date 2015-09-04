package com.fredaas.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.fredaas.main.Game;

public class Asteroid extends SpaceObject {
    
    private float delta[];
    private float angleOffset;
    private float angle;
    private float xspeed;
    private float yspeed;
    
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
        xspeed = MathUtils.random(-1, 1);
        yspeed = MathUtils.random(-1, 1);
        angle = MathUtils.random(0, 2 * PI);
        dx = MathUtils.cos(angle) * xspeed;
        dy = MathUtils.sin(angle) * yspeed;
        
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
        x += dx;
        y += dy;
        setOrientation();
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
