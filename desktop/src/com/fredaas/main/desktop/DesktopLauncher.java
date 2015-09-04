package com.fredaas.main.desktop;

import java.awt.Toolkit;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fredaas.main.Game;

public class DesktopLauncher {

    public static void main(String[] arg) {
        final int DEVICE_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
        final int DEVICE_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Graviton";
        config.width = DEVICE_WIDTH;
        config.height = DEVICE_HEIGHT;
        config.resizable = false;
        config.fullscreen = true;
        new LwjglApplication(new Game(), config);
    }

}
