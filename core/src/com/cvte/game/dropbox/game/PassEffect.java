package com.cvte.game.dropbox.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by CVTEr on 2014/10/27.
 */
public class PassEffect extends Actor {

    private ParticleEffect passEffect;

    public PassEffect() {
        passEffect = new ParticleEffect();
        passEffect.load(Gdx.files.internal("pass.p"), Gdx.files.internal(""));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        passEffect.draw(batch, Gdx.graphics.getDeltaTime());
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        passEffect.setPosition(x, y);
    }

    public void start(){
        passEffect.start();
    }
}
