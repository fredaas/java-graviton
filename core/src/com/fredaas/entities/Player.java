package com.fredaas.entities;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.fredaas.main.Game;

public class Player extends SpaceObject {
    
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean dead = false;
    private int numPoints = 4;
    private Line2D.Float[] hitLines;
    private Point2D.Float[] hitPoints;
    
    public Player(float x, float y) {
        this.x = Game.WIDTH / 2;
        this.y = Game.HEIGHT / 2;
        init();
    }

    @Override
    public void init() {
        posx = new float[numPoints];
        posy = new float[numPoints];
        hitLines = new Line2D.Float[numPoints]; 
        hitPoints = new Point2D.Float[numPoints];
        
        for (int i = 0; i < numPoints; i++) {
            hitPoints[i] = new Point2D.Float();
            hitLines[i] = new Line2D.Float();
        }
    }
    
    public void calculateHitlines() {
        if (dead) {
            return;
        }
        
        for (int i = 0, j = numPoints - 1; i < numPoints; j = i++) {
            hitLines[i].setLine(posx[i], posy[i], posx[j], posy[j]);
        }
        
        hitPoints[0].setLocation(
                MathUtils.cos(rad - PI / 2),
                MathUtils.sin(rad - PI / 2));
        hitPoints[1].setLocation(
                MathUtils.cos(rad + PI / 2),
                MathUtils.sin(rad + PI / 2));
        hitPoints[2].setLocation(
                MathUtils.cos(rad + 5 * PI / 6),
                MathUtils.sin(rad + 5 * PI / 6));
        hitPoints[3].setLocation(
                MathUtils.cos(rad - 5 * PI / 6),
                MathUtils.sin(rad - 5 * PI / 6));
    }
    
    public void setOrientation() {
        float scale = 1.1f;
        posx[0] = x + MathUtils.cos(rad) * 10 * scale;
        posy[0] = y + MathUtils.sin(rad) * 10 * scale;
        posx[1] = x + MathUtils.cos(rad + 5 * PI / 6) * 15 * scale;
        posy[1] = y + MathUtils.sin(rad + 5 * PI / 6) * 15 * scale;
        posx[2] = x + MathUtils.cos(rad + PI) * 10 * scale;
        posy[2] = y + MathUtils.sin(rad + PI) * 10 * scale;
        posx[3] = x + MathUtils.cos(rad - 5 * PI / 6) * 15 * scale;
        posy[3] = y + MathUtils.sin(rad - 5 * PI / 6) * 15 * scale;
    }
    
    public void setLeft(boolean b){    
        left = b;
    }
    
    public void setRight(boolean b) {
        right = b;
    }
    
    public void setUp(boolean b) {
        up = b;
    }
    
    public void isDead(boolean b) {
        dead = b;
    }
    
    public float getX() {
        return x; 
    }
    
    public float getY() {    
        return y;
    }
    
    @Override
    public void update(float dt) {
        if (dead) {
            for (int i = 0; i < numPoints; i++) {
                hitLines[i].setLine(
                        hitLines[i].x1 + hitPoints[i].x * 10 * dt,
                        hitLines[i].y1 + hitPoints[i].y * 10 * dt, 
                        hitLines[i].x2 + hitPoints[i].x * 10 * dt, 
                        hitLines[i].y2 + hitPoints[i].y * 10 * dt);
            }
            return;
        }
        
        if (left) {
            rad += rotationSpeed * dt;
        }
        if (right) {
            rad -= rotationSpeed * dt;
        }
        if(up) {
            dx += MathUtils.cos(rad) * accelerationSpeed * dt;
            dy += MathUtils.sin(rad) * accelerationSpeed * dt;
        }
        
        float speed = (float) Math.sqrt(dx * dx + dy * dy);
        if(speed > 0) {
            dx -= (dx / speed) * resistance * dt;
            dy -= (dy / speed) * resistance * dt;
        }
        if(speed > maxSpeed) {
            dx = (dx / speed) * maxSpeed;
            dy = (dy / speed) * maxSpeed;
        }
        
        x += dx * dt;
        y += dy * dt;
        
        setOrientation();
        calculateHitlines();
    }

    @Override
    public void draw(ShapeRenderer sr) {
        sr.begin(ShapeType.Line);
            if (dead) {
                for (int i = 0; i < numPoints; i++) {
                    sr.line(
                            hitLines[i].x1, 
                            hitLines[i].y1, 
                            hitLines[i].x2, 
                            hitLines[i].y2);
                }
                sr.end();
                return;
            }
        
            for (int i = 0, j = numPoints - 1; i < numPoints; j = i++) {
                sr.line(posx[i], posy[i], posx[j], posy[j]);
            }
        sr.end();
    }
    
}
