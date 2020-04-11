package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MyGdxGame;

import java.util.List;

import Screens.PlayScreen;

public class Frog extends Sprite {
    //frog animation variables
    private static final int FRAME_COLS = 4;
    private static final int FRAME_ROWS = 2;
    Texture frogSheet = new Texture(Gdx.files.internal("froggy.png"));
    Texture splat = new Texture(Gdx.files.internal("splat.png"));
    TextureRegion[] frogFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS]; //this might not be the right way to declare
    Animation hopAnimation;
    TextureRegion currentFrame;
    int frameIndex;
    float stateTime;
    Rectangle frogObject;

    public Frog(float x, float y) {
        //set texture
        this.setTexture(frogSheet);
        //set location
        setX(x);
        setY(y);
        //set collision rectangle
        frogObject = new Rectangle(x,y, 64, 64);


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

    public void render() {
        //frog animation
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = (TextureRegion) hopAnimation.getKeyFrame(stateTime, true);
        frameIndex = hopAnimation.getKeyFrameIndex(stateTime);
        Gdx.app.log("current time",Float.toString(stateTime));
        Gdx.app.log("current frame index",Integer.toString(frameIndex));
        MyGdxGame.batch.draw(currentFrame, getX(), getY());
    }

    public void update() {
        //update collision box
        frogObject.set(getX(), getY(), 64, 64);

        //check for collision
        checkCollision (PlayScreen.carList);

    }

    public void checkCollision(List<Car> carList) {
        for (int i =0; i<carList.size(); i++) {
            if (isColliding(carList.get(i), this) == true) {
                //death screen
                setTexture(splat);
                setX(0);
            }


        }
    }

    public boolean isColliding(Car car, Frog player) {
        if (player.frogObject.overlaps(car.carObject)) {
            return true;
        } else {
            return false;
        }
    }





/*
    private void defineFrog() {
         ****from box2d video, might not use
        BodyDef bdef = new BodyDef();
        bdef.position.set(32, 32);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape(); //might want to change this to a square
        shape.setRadius(5);

        fdef.shape = shape;
        b2Body.createFixture(fdef);
    }
*/


//test commit 2
}
