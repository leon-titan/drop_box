package com.cvte.game.dropbox.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.cvte.game.dropbox.BoxGame;

import java.util.Random;
import java.util.Vector;

/**
 * Created by cvtpc on 2014/8/8.
 */
public class PhysicsWorld implements InputProcessor {

    public static final float PXTM = 200;

    private static final float BOX_SIZE = 83;

    private OrthographicCamera debugCamera;

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Body groundBody;

    private Vector<Body> bodies;

    /**
     * our mouse joint *
     */
    private MouseJoint mouseJoint = null;

    private Vector2 lastTouch = new Vector2();

    public PhysicsWorld() {

        debugCamera = new OrthographicCamera(BoxGame.GAME_SCREEN_WIDTH / PXTM, BoxGame.GAME_SCREEN_HEIGHT / PXTM);
        debugCamera.position.x = BoxGame.GAME_SCREEN_WIDTH / 2 / PXTM;
        debugCamera.position.y = BoxGame.GAME_SCREEN_HEIGHT / 2 / PXTM;
//        debugCamera.position.x = 0;
//        debugCamera.position.y = 0;
        debugCamera.update();

        bodies = new Vector<Body>();

        creatWorld();
    }

    private void creatWorld() {

        // we instantiate a new World with a proper gravity vector
        // and tell it to sleep when possible.
        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();

        // next we create a static ground platform. This platform
        // is not moveable and will not react to any influences from
        // outside. It will however influence other bodies. First we
        // create a PolygonShape that holds the form of the platform.
        // it will be 100 meters wide and 2 meters high, centered
        // around the origin
        PolygonShape groundPoly = new PolygonShape();
        groundPoly.setAsBox(BoxGame.GAME_SCREEN_WIDTH / PXTM / 2, 10 / PXTM / 2);
        // next we create the body for the ground platform. It's
        // simply a static body.
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.x = BoxGame.GAME_SCREEN_WIDTH / PXTM / 2;
        groundBodyDef.position.y = 0;
        groundBody = world.createBody(groundBodyDef);

        // finally we add a fixture to the body using the polygon
        // defined above. Note that we have to dispose PolygonShapes
        // and CircleShapes once they are no longer used. This is the
        // only time you have to care explicitely for memomry managment.
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundPoly;
        fixtureDef.filter.groupIndex = 0;
        groundBody.createFixture(fixtureDef);

        groundPoly.dispose();
    }

    private void createCircle(float x, float y) {

        CircleShape wheelShape = new CircleShape();
        wheelShape.setRadius(10 / PXTM);

        BodyDef wheelBodyDef = new BodyDef();
        wheelBodyDef.type = BodyDef.BodyType.DynamicBody;
        wheelBodyDef.position.x = x;
        wheelBodyDef.position.y = y;
        Body wheelBody = world.createBody(wheelBodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = wheelShape;
        fixtureDef.density = 1.0f;
        fixtureDef.filter.groupIndex = 0;
        fixtureDef.restitution = 0.8f;
        wheelBody.createFixture(fixtureDef);

        wheelShape.dispose();
    }

    public void createBox(float x, float y, Block block) {

        PolygonShape boxPoly = new PolygonShape();
        boxPoly.setAsBox(BOX_SIZE / PXTM, BOX_SIZE / PXTM);

        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = BodyDef.BodyType.DynamicBody;
        boxBodyDef.position.x = x;
        boxBodyDef.position.y = y;
        Body boxBody = world.createBody(boxBodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = boxPoly;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 5.0f;
        fixtureDef.filter.groupIndex = 0;
        fixtureDef.restitution = 0.1f;
        boxBody.createFixture(fixtureDef);

        boxBody.setUserData(block);

        bodies.add(boxBody);

        boxPoly.dispose();
    }

    public void render(){
        world.step(Gdx.graphics.getDeltaTime(), 8, 3);
        for (Body body : bodies) {
            Block block = (Block) body.getUserData();
            block.setPosition(body.getPosition().x * PXTM - block.getActor().getWidth() / 2, body.getPosition().y * PXTM - block.getActor().getHeight() / 2);
            block.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        }
//        debugRenderer.render(world, debugCamera.combined);
    }

    /**
     * we instantiate this vector and the callback here so we don't irritate the GC *
     */
    Vector3 testPoint = new Vector3();
    Body hitBody = null;
    QueryCallback callback = new QueryCallback() {
        @Override
        public boolean reportFixture(Fixture fixture) {
            // if the hit fixture's body is the ground body
            // we ignore it
            if (fixture.getBody() == groundBody) return true;

            // if the hit point is inside the fixture of the body
            // we report it
            if (fixture.testPoint(testPoint.x, testPoint.y)) {
                hitBody = fixture.getBody();
                return false;
            } else {
                return true;
            }
        }
    };

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            Vector2 p = screenToWorld(screenX, screenY);
            lastTouch.set(p);
            return false;
        }
//		System.out.println("x = " + screenX + " y = " + screenY);
        // translate the mouse coordinates to world coordinates
        testPoint.set(screenX, screenY, 0);
        debugCamera.unproject(testPoint);
//		testPoint.x /= PXTM;
//		testPoint.y /= PXTM;

        // ask the world which bodies are within the given
        // bounding box around the mouse pointer
        hitBody = null;
        world.QueryAABB(callback, testPoint.x - 0.1f, testPoint.y - 0.1f, testPoint.x + 0.1f, testPoint.y + 0.1f);

        // if we hit something we create a new mouse joint
        // and attach it to the hit body.
        if (hitBody != null) {
            MouseJointDef def = new MouseJointDef();
            def.bodyA = groundBody;
            def.bodyB = hitBody;
            def.collideConnected = true;
            def.target.set(testPoint.x, testPoint.y);
            def.maxForce = 1000.0f * hitBody.getMass();

            mouseJoint = (MouseJoint) world.createJoint(def);
            hitBody.setAwake(true);
        } else {
//            if (new Random().nextInt(2) == 0) {
//                createCircle(testPoint.x, testPoint.y);
//            } else {
//                createBox(testPoint.x, testPoint.y);
//            }
        }

        return false;
    }

    public Vector2 screenToWorld(int x, int y) {
        Vector3 v3 = new Vector3(x, y, 0);
        debugCamera.unproject(v3);
        return new Vector2(v3.x, v3.y);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // if a mouse joint exists we simply destroy it
        if (mouseJoint != null) {
            world.destroyJoint(mouseJoint);
            mouseJoint = null;
        }
        return false;
    }

    /**
     * another temporary vector *
     */
    Vector2 target = new Vector2();

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        // if a mouse joint exists we simply update
        // the target of the joint based on the new
        // mouse coordinates
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            if (mouseJoint != null) {
                debugCamera.unproject(testPoint.set(screenX, screenY, 0));
                mouseJoint.setTarget(target.set(testPoint.x, testPoint.y));
            }
        } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            Vector2 p = screenToWorld(screenX, screenY);
            Vector2 delta = new Vector2(p).sub(lastTouch);
            debugCamera.translate(-delta.x, -delta.y, 0);
            debugCamera.update();
            lastTouch.set(screenToWorld(screenX, screenY));
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if (amount > 0) {
            debugCamera.zoom *= 1.1f;
        } else {
            debugCamera.zoom *= 0.9f;
        }
        debugCamera.update();
        return false;
    }
}
