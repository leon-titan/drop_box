package com.cvte.game.dropbox;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.cvte.game.dropbox.game.Assets;
import com.cvte.game.dropbox.game.GameScreen;

/**
 * Created by cvtpc on 2014/8/5.
 */
public class BoxGame extends Game {

    public static final float GAME_SCREEN_WIDTH = 720;
    public static final float GAME_SCREEN_HEIGHT = 1280;

    private Screen curScreen;

    @Override
    public void create() {
        Assets.loadTexture();
        curScreen = new GameScreen();
        setScreen(curScreen);
    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose();
    }
}
