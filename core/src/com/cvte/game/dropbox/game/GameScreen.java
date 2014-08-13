package com.cvte.game.dropbox.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
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

    public GameScreen() {

        gameStage = new Stage(new StretchViewport(BoxGame.GAME_SCREEN_WIDTH, BoxGame.GAME_SCREEN_HEIGHT));

        bgActor = new BackgroundActor();
        bgActor.setSize(gameStage.getWidth(), gameStage.getHeight());
        bgActor.setPosition(0, 0);

        gameStage.addActor(bgActor);

        physicsWorld = new PhysicsWorld();

        blockManager = new BlockManager();
        blockManager.setScreen(this);
        blockManager.addBlock();

        Gdx.input.setInputProcessor(physicsWorld);
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
