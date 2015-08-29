package com.fredaas.handlers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fredaas.states.GameState;
import com.fredaas.states.MenuState;
import com.fredaas.states.PlayState;

public class GameStateManager {

    private GameState gameState;

    public static enum State {
        MENU(0),
        PLAY(1),
        GAMEOVER(2),
        HIGHSCORES(3);

        int current;

        private State(int current) {
            this.current = current;
        }
    }

    public GameStateManager() {
        loadState(State.PLAY);
    }

    private void loadState(State state) {
        switch (state) {
            case MENU:
                gameState = new MenuState(this);
                break;
            case PLAY:
                gameState = new PlayState(this);
                break;
            default:
                break;
        }
    }

    public void update(float dt) {
        gameState.update(dt);
    }

    public void draw(ShapeRenderer sr) {
        gameState.draw(sr);
    }

}
