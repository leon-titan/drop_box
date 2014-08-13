package com.cvte.game.dropbox.game;

import com.badlogic.gdx.Screen;

import java.util.Vector;

/**
 * Created by cvtpc on 2014/8/13.
 */
public class BlockManager {
    private Vector<Block> blocks;
    private GameScreen screen;

    public BlockManager() {
        blocks = new Vector<Block>();
    }

    public void addBlock() {
        Block block = new Block();
        blocks.add(block);
        screen.getGameStage().addActor(block.getActor());
    }

    public GameScreen getScreen() {
        return screen;
    }

    public void setScreen(GameScreen screen) {
        this.screen = screen;
    }
}
