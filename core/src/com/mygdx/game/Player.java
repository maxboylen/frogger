package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.Canvas;

public class Player {
/*
    //frog animation constants
    private static final int FRAME_COLS = 4;
    private static final int FRAME_ROWS = 2;
    Texture frogSheet;
    TextureRegion[] frogFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS]; //this might not be the right way to declare

    Animation hopAnimation;
    TextureRegion currentFrame;
    int frameIndex;
    float stateTime;


    public void draw(Game game) {
        //import frog sprite
        frogSheet = new Texture(Gdx.files.internal("froggy.png"));

        //split frog sprite
        TextureRegion[][] temp = TextureRegion.split(frogSheet,
                frogSheet.getWidth() / FRAME_COLS,
                frogSheet.getHeight() / FRAME_ROWS);
        frogFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                frogFrames[index++] = temp[i][j];
            }
        }

        //initialize animation variables
        hopAnimation = new Animation(0.033f, frogFrames);
        stateTime= 0.0f;

    }
    public void update(Game game) {
        //frog animation
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = (TextureRegion) hopAnimation.getKeyFrame(stateTime, true);
        frameIndex = hopAnimation.getKeyFrameIndex(stateTime);
        Gdx.app.log("current time",Float.toString(stateTime));
        Gdx.app.log("current frame index",Integer.toString(frameIndex));
        game.batch.begin();
        this.game.batch.draw(currentFrame,1,1);
        this.game.batch.end();

    }
 */


//test commit
}
