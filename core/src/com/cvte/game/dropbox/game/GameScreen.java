package com.cvte.game.dropbox.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.cvte.game.dropbox.BoxGame;

/**
 * Created by cvtpc on 2014/8/5.
 */
public class GameScreen implements Screen {

    public enum State{
        READY,
        START,
        END,
    }

    private Stage gameStage;
    private Actor bgActor;

    private PhysicsWorld physicsWorld;

    private BlockManager blockManager;

    private GameInput input;

    private Block slideBlock;

    private State state;

    public GameScreen() {

        gameStage = new Stage(new StretchViewport(BoxGame.GAME_SCREEN_WIDTH, BoxGame.GAME_SCREEN_HEIGHT));

//        bgActor = new BackgroundActor();
//        bgActor.setSize(gameStage.getWidth(), gameStage.getHeight());
//        bgActor.setPosition(0, 0);
        bgActor = new Image(new Texture("bg_01.png"));
//        gameStage.addActor(bgActor);

        EndLine endLine = new EndLine();
        endLine.setPosition(20, Block.SIZE * 6);//1020
        gameStage.addActor(endLine.getActor());

        physicsWorld = new PhysicsWorld();

        blockManager = new BlockManager();
        blockManager.setScreen(this);
        blockManager.setPhysicsWorld(physicsWorld);

        addSlideBlock();

        input = new GameInput(this);
        Gdx.input.setInputProcessor(input);

        state = State.READY;
    }

    public void start() {
        if (state != State.READY) {
            return;
        }
        state = State.START;
        riseGroup(170);
    }

    public void end() {
        if (state != State.START) {
            return;
        }
        state = State.END;

        restart();
    }

    public void restart() {
        if (state != State.END) {
            return;
        }

        blockManager.clearBlock();

        gameStage.getRoot().setY(0);
        gameStage.getRoot().clearActions();

        slideBlock.getActor().setPosition(0, BoxGame.GAME_SCREEN_HEIGHT * 3 / 4 + 25);
        slideBlock.getActor().clearActions();
        slideBlock.getActor().addAction(forever(sequence(
                moveBy(BoxGame.GAME_SCREEN_WIDTH - slideBlock.getActor().getWidth(), 0, 1.5f),
                moveBy(-(BoxGame.GAME_SCREEN_WIDTH - slideBlock.getActor().getWidth()), 0, 1.5f))));

        state = State.READY;
    }

    public void riseGroup(final float height) {
//        gameStage.getRoot().addAction(moveBy(0, -height, 2));
//        slideBlock.getActor().addAction(moveBy(0, height, 2));
//        bgActor.setSize(bgActor.getWidth(), bgActor.getHeight() + height);
        final float time = 4f;
        bgActor.setSize(bgActor.getWidth(), bgActor.getHeight() + height);
        gameStage.getRoot().addAction(forever(sequence(moveBy(0, -height, time), run(new Runnable() {
            @Override
            public void run() {
                bgActor.setSize(bgActor.getWidth(), bgActor.getHeight() + height);
                slideBlock.getActor().addAction(moveBy(0, height, time));
            }
        }))));
        slideBlock.getActor().addAction(moveBy(0, height, time));
    }

    public void addSlideBlock() {
        if (slideBlock != null) {
            return;
        }
        slideBlock = new Block(0, BoxGame.GAME_SCREEN_HEIGHT * 3 / 4 + 25);
        slideBlock.getActor().addAction(forever(sequence(
                moveBy(BoxGame.GAME_SCREEN_WIDTH - slideBlock.getActor().getWidth(), 0, 1.5f),
                moveBy(-(BoxGame.GAME_SCREEN_WIDTH - slideBlock.getActor().getWidth()), 0, 1.5f))));
        slideBlock.getActor().setColor(1, 1, 1, 0.5f);
        gameStage.addActor(slideBlock.getActor());
    }

    public void dropBlock() {
        if (slideBlock == null) {
            return;
        }
        blockManager.addBlock(slideBlock.getActor().getX(), slideBlock.getActor().getY());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.388235f, 0.6745098f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.act(delta);
        gameStage.draw();

        physicsWorld.render();

        Block block = blockManager.getLastBlock();
        if (block != null) {
            if (block.getActor().getY() + gameStage.getRoot().getY() < -170) {
                end();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    public Stage getGameStage() {
        return gameStage;
    }
}
