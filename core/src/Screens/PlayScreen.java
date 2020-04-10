package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MyGdxGame;

import java.util.List;

import Sprites.Car;
import Sprites.Frog;

public class PlayScreen implements Screen {
    private MyGdxGame game;
    Texture texture;
    private OrthographicCamera gameCam;
    private FitViewport gamePort;

    //tiled map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //frog animation variables
    private static final int FRAME_COLS = 4;
    private static final int FRAME_ROWS = 2;
    Texture frogSheet;
    TextureRegion[] frogFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS]; //this might not be the right way to declare
    Animation hopAnimation;
    TextureRegion currentFrame;
    int frameIndex;
    float stateTime;

    //player variables
    Sprite playerSprite;
    Frog player;
    public static final float speed = 300f; //world units/second
    private final Vector2 moveVector = new Vector2();
    private Vector3 touchPos;

    //car variables
    Car car1 = new Car(200, 200);
    static public List<Car> carList;


    public PlayScreen(MyGdxGame game) {
        this.game = game;



        //create camera
        gameCam = new OrthographicCamera();

        //create viewport, maintains aspect ratio
        gamePort = new FitViewport(800, 480, gameCam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("street.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0); //centres screen

/*
        //import frog sprite
        frogSheet = new Texture(Gdx.files.internal("froggy.png"));
        playerSprite = new Sprite(frogSheet);
        //frog starting point
        playerSprite.setX(300);
        playerSprite.setY(300);
        */

        touchPos = new Vector3(); //initialize movement vector

        player = new Frog(200,200);

        /*
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


         */

        //create cars
        car1 = new Car(600,200);



    }

    @Override
    public void show() {

    }

    public void update (float dt) {

//    world.step(1/60f, 6, 2); //from box2d video, might not use

        gameCam.update();
        //draw what camera can see
        renderer.setView(gameCam);
        car1.update();


    }

    @Override
    public void render(float delta) {
        update(delta); //updates delta time
        //clear screen
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(gameCam.combined); //tells game to only render what camera can see
        renderer.render(); //tiled map renderer



        //movement input
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            gameCam.unproject(touchPos); //sets vector relative to camera
            //how far the player can move this frame (distance = speed * time):
            float maxDistance = speed * Gdx.graphics.getDeltaTime();
            //a vector from the player to the touch point:
            moveVector.set((touchPos.x - player.getX()), (touchPos.y - player.getY()));
            //normalise vector to max distance it can travel per frame
            moveVector.nor().scl(maxDistance);


            //update vector coordinates
            player.setX(player.getX()+moveVector.x);
            player.setY(player.getY()+moveVector.y);

            //keep sprite within game borders
            if (player.getX() < 1) {
                player.setX(1);
            }
            if (player.getX() > 740) {
                player.setX(740);
            }
            if (player.getY() < -10) {
                player.setY(-10);
            }
            if (player.getY() > 435) {
                player.setY(435);
            }
        }



/*
        //frog animation
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = (TextureRegion) hopAnimation.getKeyFrame(stateTime, true);
        frameIndex = hopAnimation.getKeyFrameIndex(stateTime);
        Gdx.app.log("current time",Float.toString(stateTime));
        Gdx.app.log("current frame index",Integer.toString(frameIndex));
      */



        this.game.batch.begin();
//        this.game.batch.draw(currentFrame,playerSprite.getX(),playerSprite.getY());
        player.render();
        car1.render();
        this.game.batch.end();


    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height); //updates game when window is resized

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
