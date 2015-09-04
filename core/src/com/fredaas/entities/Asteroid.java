package com.fredaas.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.fredaas.main.Game;
import com.fredaas.states.PlayState;

public class Asteroid extends SpaceObject {
    
    private float delta[];
    private float angleOffset;
    private float angle;
    private float xspeed;
    private float yspeed;
    private Type type;
    public static enum Type {
        SMALL,
        MEDIUM,
        LARGE;
    }
    
    public Asteroid (Type type) {
        this.type = type;
        x = MathUtils.random(0, Game.WIDTH);
        y = MathUtils.random(0, Game.HEIGHT);
        
        /*
         * Initial projection angle
         */
        xspeed = MathUtils.random(-1, 1);
        yspeed = MathUtils.random(-1, 1);
        angle = MathUtils.random(0, 2 * PI);
        dx = MathUtils.cos(angle) * xspeed;
        dy = MathUtils.sin(angle) * yspeed;
        
        init();
    }
    
    public Asteroid (float x, float y, float delta, Type type) {
        this.x = x;
        this.y = y;
        
        /*
         * When an asteroid collides with the map it gets
         * split into two smaller asteroids. The angle of
         * projection is an offset range from the center
         * of the map.
         */
        float mapCenterX = PlayState.map.getX();
        float mapCenterY = PlayState.map.getY();
        angle = MathUtils.atan2(y - mapCenterY, x - mapCenterX) + PI;
        xspeed = 1;
        yspeed = 1;
        float angleOffset = angle + delta * PI / 6;
        dx = MathUtils.cos(angleOffset) * xspeed;
        dy = MathUtils.sin(angleOffset) * yspeed;
        
        switch (type) {
            case LARGE:
                this.type = Type.MEDIUM;
                break;
            case MEDIUM:
                this.type = Type.SMALL;
                break;
            case SMALL:
                this.type = type;
        }
        
        init();
    }
    
    public Type getType() {
        return type;
    }
    
    public float getAngle() {
        return angle;
    }

    @Override
    public void init() {
        switch (type) {
            case SMALL:
                radius = 10;
                break;
            case MEDIUM:
                radius = 15;
                break;
            case LARGE:
                radius = 25;
                break;
        }
        
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
