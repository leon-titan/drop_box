package com.cvte.game.dropbox.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by cvtpc on 2014/8/5.
 */
public class GameScreen implements Screen {

    private Stage gameStage;
    private Actor bgActor;

    public GameScreen() {

        gameStage = new Stage(new StretchViewport(640, 480));

        bgActor = new BackgroundActor();
        bgActor.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bgActor.setPosition(0, 0);

        gameStage.addActor(bgActor);

        Gdx.input.setInputProcessor(gameStage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameStage.act(delta);
        gameStage.draw();
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
}
