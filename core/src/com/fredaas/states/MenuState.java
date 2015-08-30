package com.fredaas.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fredaas.handlers.Font;
import com.fredaas.handlers.GameStateManager;
import com.fredaas.main.Game;

public class MenuState extends GameState {

    private SpriteBatch sb;
    private BitmapFont bmfTitle;
    private BitmapFont bmfOptions;
    private GlyphLayout text;
    private final String menuTitle = "graviton";
    private final String[] menuOptions = { "play", "options", "quit" };

    public MenuState(GameStateManager gsm) {
        super.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        sb = new SpriteBatch();
        text = new GlyphLayout();
        bmfTitle = Font.HYPERSPACE_BOLD_H1;
        bmfOptions = Font.HYPERSPACE_BOLD_H3;
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void draw(ShapeRenderer sr) {
        sb.begin();
        text.setText(bmfTitle, menuTitle);
        bmfTitle.draw(sb, text, (Game.WIDTH - text.width) / 2, Game.HEIGHT * 0.70f);
        for (int i = 0; i < menuOptions.length; i++) {
            text.setText(bmfOptions, menuOptions[i]);
            bmfOptions.draw(sb, text, (Game.WIDTH - text.width) / 2, Game.HEIGHT * 0.50f - i * 60);
        }
        sb.end();
    }

}
