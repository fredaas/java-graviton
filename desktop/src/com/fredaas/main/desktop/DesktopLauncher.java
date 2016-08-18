package com.fredaas.main.desktop;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fredaas.main.Game;

public class DesktopLauncher {

    public static void main(String[] arg) {
        final Dimension DEVICE = Toolkit.getDefaultToolkit().getScreenSize();
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Graviton";
        config.width = (int) DEVICE.getWidth();
        config.height = (int) DEVICE.getHeight();
        config.resizable = false;
        config.fullscreen = true;
        new LwjglApplication(new Game(), config);
    }

}
