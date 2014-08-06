package com.cvte.game.dropbox;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.cvte.game.dropbox.game.GameScreen;

/**
 * Created by cvtpc on 2014/8/5.
 */
public class BoxGame extends Game {

    private Screen curScreen;

    @Override
    public void create() {
        curScreen = new GameScreen();
        setScreen(curScreen);
    }
}
