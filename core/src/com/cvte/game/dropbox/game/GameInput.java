package com.cvte.game.dropbox.game;

import com.badlogic.gdx.InputAdapter;
import com.cvte.game.dropbox.BoxGame;

/**
 * Created by cvtpc on 2014/8/15.
 */
public class GameInput extends InputAdapter {

    private GameScreen game;
    private PhysicsWorld physicsWorld;

    private BlockManager blockManager;

    public GameInput(BlockManager blockManager) {
        this.blockManager = blockManager;
    }

    public GameInput(GameScreen game) {
        this.game = game;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        game.dropBlock();
//        blockManager.addBlock(BoxGame.GAME_SCREEN_WIDTH / 2, BoxGame.GAME_SCREEN_HEIGHT * 4 / 5);
//        blockManager.addBlock(screenX, BoxGame.GAME_SCREEN_HEIGHT - screenY);
        return true;
    }
}
