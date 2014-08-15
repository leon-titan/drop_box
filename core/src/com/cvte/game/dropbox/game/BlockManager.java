package com.cvte.game.dropbox.game;

import com.badlogic.gdx.Screen;

import java.util.Vector;

/**
 * Created by cvtpc on 2014/8/13.
 */
public class BlockManager {
    private Vector<Block> blocks;
    private GameScreen screen;
    private PhysicsWorld physicsWorld;

    public BlockManager() {
        blocks = new Vector<Block>();
    }

    public void addBlock(float x, float y) {
        Block block = new Block(x, y);
        blocks.add(block);
        screen.getGameStage().addActor(block.getActor());

        physicsWorld.createBox((x + block.getActor().getWidth() / 2) / PhysicsWorld.PXTM,
                (y + block.getActor().getHeight() / 2) / PhysicsWorld.PXTM, block);
    }

    public void addBlock(Block block) {
        physicsWorld.createBox((block.getActor().getX() + block.getActor().getWidth() / 2) / PhysicsWorld.PXTM,
                (block.getActor().getY() + block.getActor().getHeight() / 2) / PhysicsWorld.PXTM, block);
    }

    public GameScreen getScreen() {
        return screen;
    }

    public void setScreen(GameScreen screen) {
        this.screen = screen;
    }

    public PhysicsWorld getPhysicsWorld() {
        return physicsWorld;
    }

    public void setPhysicsWorld(PhysicsWorld physicsWorld) {
        this.physicsWorld = physicsWorld;
    }
}
