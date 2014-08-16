package com.cvte.game.dropbox.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.cvte.game.dropbox.BoxGame;

/**
 * Created by cvtpc on 2014/8/15.
 */
public class GameInput extends InputAdapter {

    private GameScreen game;

    public GameInput(GameScreen game) {
        this.game = game;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            game.end();
        } else {
            game.start();
            game.dropBlock();
        }
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        Group group = game.getGameStage().getRoot();
        group.setOriginX(BoxGame.GAME_SCREEN_WIDTH / 2);
        group.setOriginY(-group.getY());
        if (amount > 0) {
            group.setScale(group.getScaleX() * 1.1f);
        } else {
            group.setScale(group.getScaleX() * 0.9f);
        }
        return true;
    }
}
