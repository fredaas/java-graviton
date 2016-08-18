package com.fredaas.entities;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.fredaas.main.Game;
import com.fredaas.states.PlayState;

public class Player extends SpaceObject {
    
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean dead = false;
    private boolean fire = false;
    private Line2D.Float[] hitLines;
    private Point2D.Float[] hitPoints;
    private ArrayList<Bullet> bullets;
    private float shootingTimer;
    private float shootingTimerDelay;
    private float shootingTimerDiff;
    private long thrustTimer;
    private long thrustTimerDiff;
    private long thrustTimerDelay;
    private ArrayList<Thrust> thrust;
    
    public Player(float x, float y) {
        this.x = Game.WIDTH / 2;
        this.y = Game.HEIGHT / 2;
        init();
    }

    @Override
    public void init() {
        thrust = new ArrayList<Thrust>();
        bullets = new ArrayList<Bullet>();
        shootingTimer = System.nanoTime();
        shootingTimerDelay = 100;
        thrustTimerDelay = 10;
        numPoints = 4;
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
    
    public void setFire(boolean b) {
        fire = b;
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
        ArrayList<BlackHole> blackHoles = PlayState.blackHoles;
        
        if (!dead) {
            for (int i = 0; i < blackHoles.size(); i++) {
                BlackHole h = blackHoles.get(i);
                setGravity(h.getX(), h.getY(), 20, Force.ATTRACT, dt);
            }
        }
        
        if (rad < 0) {
            rad += 2 * PI;
        }
        
        /*
         * Update thrust particles
         */
        for (int i = 0; i < thrust.size(); i++) {
            Thrust t = thrust.get(i);
            t.update(dt);
            if (t.ready()) {
                thrust.remove(i);
            }
        }
         
        /*
         * Break player into vector lines if dead
         */
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
        
        /*
         * Fire bullets
         */
        if (fire) {
            shootingTimerDiff = (System.nanoTime() - shootingTimer) / 1000000;
        }
        if (shootingTimerDiff > shootingTimerDelay) {
            bullets.add(new Bullet(x, y, rad));
            shootingTimerDiff = 0;
            shootingTimer = System.nanoTime();
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update(dt);
            if (bullets.get(i).remove()) {
                bullets.remove(i);
                i--;
            }
        }
        
        /*
         * Update position
         */
        if (left) {
            rad += rotationSpeed * dt;
        }
        if (right) {
            rad -= rotationSpeed * dt;
        }
        if(up) {
            dx += MathUtils.cos(rad) * accelerationSpeed * dt;
            dy += MathUtils.sin(rad) * accelerationSpeed * dt;
            thrustTimerDiff = (System.nanoTime() - thrustTimer) / 1000000;
            
            if (thrustTimerDiff > thrustTimerDelay) {
                thrust.add(new Thrust(x, y, rad));
                thrustTimerDiff = 0;
                thrustTimer = System.nanoTime();
            }
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
        
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(sr);
        }
        
        for (int i = 0; i < thrust.size(); i++) {
            thrust.get(i).draw(sr);
        }
    }
    
}
