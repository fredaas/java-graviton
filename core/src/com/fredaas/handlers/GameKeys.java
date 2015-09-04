package com.fredaas.handlers;

public class GameKeys {

    public static enum Key {
        LEFT(0),
        RIGHT(1),
        DOWN(2),
        UP(3),
        F(4);

        int value;

        private Key(int value) {
            this.value = value;
        }
    }

    private static final int NUM_KEYS = Key.values().length;
    private static boolean prev[] = new boolean[NUM_KEYS];
    private static boolean curr[] = new boolean[NUM_KEYS];
    private static boolean keyCurr;
    private static boolean keyPrev;

    public GameKeys() {
    }

    public static void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            prev[i] = curr[i];
        }
        keyPrev = keyCurr;
    }

    public static void setKey(Key k, boolean b) {
        curr[k.value] = b;
    }
    
    public static boolean isDown(Key k) {
        return curr[k.value];
    }

}
