package com.fredaas.handlers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.fredaas.handlers.GameKeys.Key;

public class KeyInputProcessor extends InputAdapter {

    public KeyInputProcessor() {}

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Keys.LEFT) {
            GameKeys.setKey(Key.LEFT, true);
        }
        if (keyCode == Keys.RIGHT) {
            GameKeys.setKey(Key.RIGHT, true);
        }
        if (keyCode == Keys.UP) {
            GameKeys.setKey(Key.UP, true);
        }
        if (keyCode == Keys.DOWN) {
            GameKeys.setKey(Key.DOWN, true);
        }

        return true;
    }

    @Override
    public boolean keyUp(int keyCode) {
        if (keyCode == Keys.LEFT) {
            GameKeys.setKey(Key.LEFT, false);
        }
        if (keyCode == Keys.RIGHT) {
            GameKeys.setKey(Key.RIGHT, false);
        }
        if (keyCode == Keys.UP) {
            GameKeys.setKey(Key.UP, false);
        }
        if (keyCode == Keys.DOWN) {
            GameKeys.setKey(Key.DOWN, false);
        }

        return true;
    }

}
