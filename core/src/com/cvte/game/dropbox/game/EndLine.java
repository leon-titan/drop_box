package com.cvte.game.dropbox.game;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Created by CVTEr on 2014/10/27.
 */
public class EndLine {

    private static final int END_LINE_NUM = 13;
    private static final float END_LINE_WIDTH = 55.3F;

    private Group group;
    private PassEffect passEffect;

    public EndLine() {
        group = new Group();
        for (int i = 0; i < END_LINE_NUM; ++i) {
            LittleBlock block = new LittleBlock();
            group.addActor(block);

            block.setPosition(i * END_LINE_WIDTH, 0);
            block.setColor(1, 1, 1, 0);

            block.addAction(sequence(delay(i * 0.07f), forever(sequence(alpha(0.3f, 0.5f, Interpolation.sine), delay(0.02f), alpha(0.7f, 0.5f, Interpolation.sine), delay(0.02f)))));
        }
        passEffect = new PassEffect();
        passEffect.start();
        group.addActor(passEffect);
    }

    public void setPosition(float x, float y){
        group.setPosition(x, y);
    }

    public Actor getActor(){
        return group;
    }
}
