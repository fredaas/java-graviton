package com.fredaas.states;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fredaas.handlers.GameStateManager;

public abstract class GameState {

    protected GameStateManager gsm;

    public abstract void update(float dt);

    public abstract void draw(ShapeRenderer sr);

    public abstract void init();

}
