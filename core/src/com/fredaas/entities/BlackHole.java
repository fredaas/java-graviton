package com.fredaas.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.fredaas.states.PlayState;

public class BlackHole extends SpaceObject {
    
    private ArrayList<Particles> particles;
    private final int NUM_PARTICLES = 30;
    
    public BlackHole() {
        init();
    }

    @Override
    public void init() {
        particles = new ArrayList<Particles>();
        rotationSpeed = 1;
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
        createparticles(NUM_PARTICLES);
        setOrientation();
    }
    
    private void createparticles(int num) {
        for (int i = 0; i < num; i++) {
            createParticle(MathUtils.random(0, radius));
        }
    }
    
    private void createParticle(float dist) {
        particles.add(new Particles(x, y, dist));
    }
    
    private void setOrientation() {
        for (int i = 0; i < numPoints; i++) {
            posx[i] = x + MathUtils.cos(rad) * radius;
            posy[i] = y + MathUtils.sin(rad) * radius;
            rad += radOffset;
        }
    }

    @Override
    public void update(float dt) {
        rad += rotationSpeed * dt;
        
        for (int i = 0; i < particles.size(); i++) {
            Particles p = particles.get(i);
            p.update(dt);
            if (p.ready()) {
                particles.remove(i);
            }
        }
        
        if (particles.size() < NUM_PARTICLES) {
            createParticle(radius);
        }
        
        setOrientation();
    }

    @Override
    public void draw(ShapeRenderer sr) {
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).draw(sr);
        }
        
        sr.begin(ShapeType.Line);
            for (int i = 0, j = numPoints - 1; i < numPoints; j = i++) {
                sr.line(posx[i], posy[i], posx[j], posy[j]);
            }
        sr.end();
    }
    
}
