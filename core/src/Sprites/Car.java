package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.MyGdxGame;

import Screens.PlayScreen;

public class Car extends Sprite {
    public static final float carSpeed = 300f;
    Texture carTexture = new Texture(Gdx.files.internal("car.png"));

    public Car (int lane, int pos) {
//        this.setX(lane * (find lane dimension));
//        this.setY(pos * (find pos dimension));

        this.setX(lane);
        this.setY(pos);


    }

    public void render() {
        MyGdxGame.batch.draw(carTexture, getX(), getY());
    }

    public void update() {
        //car animations
        float carMovement = carSpeed *  Gdx.graphics.getDeltaTime();
        setX(getX() - carMovement);

    }
}
