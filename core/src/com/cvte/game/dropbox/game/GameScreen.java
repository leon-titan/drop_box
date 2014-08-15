package com.cvte.game.dropbox.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.cvte.game.dropbox.BoxGame;

/**
 * Created by cvtpc on 2014/8/5.
 */
public class GameScreen implements Screen {

    private Stage gameStage;
    private Actor bgActor;

    private PhysicsWorld physicsWorld;

    private BlockManager blockManager;

    private GameInput input;

    private Block slideBlock;

    public GameScreen() {

        gameStage = new Stage(new StretchViewport(BoxGame.GAME_SCREEN_WIDTH, BoxGame.GAME_SCREEN_HEIGHT));

        bgActor = new BackgroundActor();
        bgActor.setSize(gameStage.getWidth(), gameStage.getHeight());
        bgActor.setPosition(0, 0);

        gameStage.addActor(bgActor);

        physicsWorld = new PhysicsWorld();

        blockManager = new BlockManager();
        blockManager.setScreen(this);
        blockManager.setPhysicsWorld(physicsWorld);

        addSlideBlock();

        input = new GameInput(this);
        Gdx.input.setInputProcessor(input);
    }

    public void addSlideBlock() {
        if (slideBlock != null) {
            return;
        }
        slideBlock = new Block(BoxGame.GAME_SCREEN_WIDTH / 2, BoxGame.GAME_SCREEN_HEIGHT * 3 / 4);
        slideBlock.getActor().addAction(forever(sequence(
                moveTo(0, BoxGame.GAME_SCREEN_HEIGHT * 3 / 4, 1.5f),
                moveTo(BoxGame.GAME_SCREEN_WIDTH - slideBlock.getActor().getWidth(), BoxGame.GAME_SCREEN_HEIGHT * 3 / 4, 1.5f))));
        slideBlock.getActor().setColor(1, 1, 1, 0.5f);
        gameStage.addActor(slideBlock.getActor());
    }

    public void dropBlock() {
        if (slideBlock == null) {
            return;
        }
//        slideBlock.getActor().clearActions();
//        blockManager.addBlock(slideBlock);
//        slideBlock = null;
        blockManager.addBlock(slideBlock.getActor().getX(), slideBlock.getActor().getY());

//        //TODO TEST
//        addSlideBlock();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.act(delta);
        gameStage.draw();

        physicsWorld.render();
    }

    @Override
    public void resize(int width, int height) {

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

    public void setGameStage(Stage gameStage) {
        this.gameStage = gameStage;
    }
}
