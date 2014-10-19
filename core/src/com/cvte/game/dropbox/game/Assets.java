package com.cvte.game.dropbox.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by cvtpc on 2014/8/15.
 */
public class Assets {
    public static Texture blockTexture;

    public static void  loadTexture(){
        blockTexture = new Texture("item_chick_green.png");
        blockTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public static void dispose(){
        if(blockTexture!=null){
            blockTexture.dispose();
        }
    }
}
