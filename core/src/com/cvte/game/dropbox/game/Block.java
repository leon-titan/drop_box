package com.cvte.game.dropbox.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by cvtpc on 2014/8/12.
 */
public class Block {
    private Image blockImage;

    public Block() {
//        blockSp = new Image(new Texture("block.png"));
//        blockSp.setOrigin(blockSp.getWidth() / 2, blockSp.getHeight() / 2);
        blockImage = new Image(new Texture("block.png"));
        blockImage.setOrigin(blockImage.getImageWidth() / 2, blockImage.getImageHeight() / 2);
        blockImage.setPosition(100, 100);
    }

    public void setPosition(float x, float y) {
        blockImage.setPosition(x, y);
    }

    public void setRotation(int rotation) {
        blockImage.setRotation(rotation);
    }
}
