package com.fredaas.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.fredaas.states.PlayState;

public class StandardTracker extends Tracker {
    
    private float radOffset;
    private float distance;
    
    public StandardTracker(float x, float y) {
        this.x = x;
        this.y = y;
        init();
    }
    
    @Override
    public void init() {
        xSpeed = MathUtils.random(0.5f, 1);
        ySpeed = MathUtils.random(0.5f, 1);
        radius = 20;
        numPoints = 3;
        radOffset = 2 * PI / numPoints;
        posx = new float[numPoints];
        posy = new float[numPoints];
        
        for (int i = 0; i < numPoints; i++) {
            posx[i] = x + MathUtils.cos(rad) * radius;
            posy[i] = y + MathUtils.sin(rad) * radius;
            rad += radOffset;
        }
    }
    
    private void setDirection() {
        float delta = PlayState.map.getRadius();
        float fieldStrength = 20;
        float direction = -1;
        
        dx = x - PlayState.player.getX();
        dy = y - PlayState.player.getY();
        rad = MathUtils.atan2(dy, dx);
        
        if (rad < PI) {
            rad += PI;
        }
        
        distance = (float) Math.sqrt(dx * dx + dy * dy);
        dx = MathUtils.cos(rad) * distance * xSpeed;
        dy = MathUtils.sin(rad) * distance * ySpeed;
        tx = MathUtils.cos(rad) * (delta / distance) * fieldStrength * direction;
        ty = MathUtils.sin(rad) * (delta / distance) * fieldStrength * direction;
        
        for (int i = 0; i < numPoints; i++) {
            posx[i] = x + MathUtils.cos(rad) * radius;
            posy[i] = y + MathUtils.sin(rad) * radius;
            rad += radOffset;
        }
    }

    @Override
    public void update(float dt) {
        x += (dx + tx) * dt;
        y += (dy + ty) * dt;
        
        setDirection();
    }

    @Override
    public void draw(ShapeRenderer sr) {
        
        sr.begin(ShapeType.Line);
        sr.setColor(0, 1, 0, 1);
            for (int i = 0, j = numPoints - 1; i < numPoints; j = i++) {
                sr.line(posx[i], posy[i], posx[j], posy[j]);
            }
        sr.end();
    }
    
}
