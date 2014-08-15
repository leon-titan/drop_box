package com.cvte.game.dropbox.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by cvtpc on 2014/8/12.
 */
public class Block {

    private Image blockImage;

    public Block(float x, float y) {
        blockImage = new Image(Assets.blockTexture);
        blockImage.setOrigin(blockImage.getWidth() / 2, blockImage.getHeight() / 2);
        blockImage.setPosition(x, y);
    }

    public void setPosition(float x, float y) {
        blockImage.setPosition(x, y);
    }

    public void setRotation(float rotation) {
        blockImage.setRotation(rotation);
    }

    public Actor getActor(){
        return blockImage;
    }
}
