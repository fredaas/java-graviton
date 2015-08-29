package com.fredaas.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fredaas.handlers.Font;
import com.fredaas.handlers.GameStateManager;
import com.fredaas.handlers.KeyInputProcessor;

public class Main implements ApplicationListener {

    public static int WIDTH;
    public static int HEIGHT;
    public static OrthographicCamera cam;
    private Viewport viewport;
    private GameStateManager gsm;
    private ShapeRenderer sr;

    public Main() {
    }

    private void init() {
        Font.init();
        gsm = new GameStateManager();
    }

    @Override
    public void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        cam = new OrthographicCamera(WIDTH, HEIGHT);
        cam.translate(WIDTH / 2, HEIGHT / 2);
        viewport = new StretchViewport(WIDTH, HEIGHT, cam);
        Gdx.input.setInputProcessor(new KeyInputProcessor());
        init();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.draw(sr);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

}
