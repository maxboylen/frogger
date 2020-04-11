package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MyGdxGame;

import org.w3c.dom.css.Rect;

import Screens.PlayScreen;

public class Car extends Sprite {
    public static final float carSpeed = 300f;
    Texture carTexture = new Texture(Gdx.files.internal("car.png"));
    Rectangle carObject;

    public Car (int lane, int pos) {
//        this.setX(lane * (find lane dimension));
//        this.setY(pos * (find pos dimension));

        this.setX(lane);
        this.setY(pos);
        carObject = new Rectangle(getX(), getY(), 75, 32);


    }

    public void render() {

        MyGdxGame.batch.draw(carTexture, getX(), getY());
    }

    public void update() {
        //car animations
        float carMovement = carSpeed *  Gdx.graphics.getDeltaTime();
        setX(getX() - carMovement);

        //check for screen edge
        if (getX() < -100) {
            setX(900);
        }

        //update collision box
        carObject.setX(getX());
        carObject.setY(getY());


    }
}
