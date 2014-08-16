package com.cvte.game.dropbox.game;

import java.util.Vector;

/**
 * Created by cvtpc on 2014/8/13.
 */
public class BlockManager {
    private Vector<Block> blocks;
    private GameScreen screen;
    private PhysicsWorld physicsWorld;

    private Block lastBlock;

    public BlockManager() {
        blocks = new Vector<Block>();
    }

    public void addBlock(float x, float y) {
        Block block = new Block(x, y);
        addBlock(block);
    }

    public void addBlock(Block block) {
        blocks.add(block);
        screen.getGameStage().addActor(block.getActor());
        physicsWorld.createBox((block.getActor().getX() + block.getActor().getWidth() / 2) / PhysicsWorld.PXTM,
                (block.getActor().getY() + block.getActor().getHeight() / 2) / PhysicsWorld.PXTM, block);

        lastBlock = block;
    }

    public void clearBlock() {
        physicsWorld.clearBox();

        for (Block block : blocks) {
            screen.getGameStage().getRoot().removeActor(block.getActor());
        }
        blocks.removeAllElements();

        lastBlock = null;
    }

    public void setScreen(GameScreen screen) {
        this.screen = screen;
    }

    public void setPhysicsWorld(PhysicsWorld physicsWorld) {
        this.physicsWorld = physicsWorld;
    }

    public Block getLastBlock() {
        return lastBlock;
    }
}
