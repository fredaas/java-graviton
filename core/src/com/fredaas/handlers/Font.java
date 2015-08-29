package com.fredaas.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Font {

    public static BitmapFont HYPERSPACE_BOLD_H1;
    public static BitmapFont HYPERSPACE_BOLD_H2;
    public static BitmapFont HYPERSPACE_BOLD_H3;
    public static BitmapFont HYPERSPACE_BOLD_H4;
    public static BitmapFont HYPERSPACE_BOLD_H5;

    public static void init() {
        HYPERSPACE_BOLD_H1 = loadFont("fonts/Hyperspace-Bold.ttf", 70);
        HYPERSPACE_BOLD_H2 = loadFont("fonts/Hyperspace-Bold.ttf", 60);
        HYPERSPACE_BOLD_H3 = loadFont("fonts/Hyperspace-Bold.ttf", 50);
        HYPERSPACE_BOLD_H4 = loadFont("fonts/Hyperspace-Bold.ttf", 40);
        HYPERSPACE_BOLD_H5 = loadFont("fonts/Hyperspace-Bold.ttf", 30);
    }

    private static BitmapFont loadFont(String path, int size) {
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
        parameter.size = size;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        return font;
    }

    public static void dispose() {
        HYPERSPACE_BOLD_H1.dispose();
        HYPERSPACE_BOLD_H2.dispose();
        HYPERSPACE_BOLD_H3.dispose();
        HYPERSPACE_BOLD_H4.dispose();
        HYPERSPACE_BOLD_H5.dispose();
    }

}
