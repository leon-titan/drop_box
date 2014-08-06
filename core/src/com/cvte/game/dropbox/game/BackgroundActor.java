package com.cvte.game.dropbox.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by cvtpc on 2014/8/5.
 */
public class BackgroundActor extends Actor {

    private TextureRegion bgTexture;

    public BackgroundActor() {
        bgTexture = new TextureRegion(new Texture("marble.png"));
        bgTexture.getTexture().setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(bgTexture.getTexture(), getX(), getY(), getWidth(), getHeight(), 0, 0, getWidth()/64, getHeight()/64);
    }
}
