package com.catalin.invaders.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.catalin.invaders.game.Assets;
import com.catalin.invaders.game.Controls;

public class Ship {

    enum State {
        IDLE, LEFT, RIGHT, SHOOT;
    }

    Vector2 position;

    State state;
    float stateTime;
    float speed = 5;

    TextureRegion frame;

    Weapon weapon;

    Ship(int initialPosition, Assets assets){
        position = new Vector2(initialPosition, 10);
        state = State.IDLE;
        stateTime = 0;

        weapon = new Weapon(assets.shootSound);
    }


    void setFrame(Assets assets){
        switch (state){
            case IDLE:
                frame = assets.naveidle.getKeyFrame(stateTime, true);
                break;
            case LEFT:
                frame = assets.naveleft.getKeyFrame(stateTime, true);
                break;
            case RIGHT:
                frame = assets.naveright.getKeyFrame(stateTime, true);
                break;
            case SHOOT:
                frame = assets.naveshoot.getKeyFrame(stateTime, true);
                break;
            default:
                frame = assets.naveidle.getKeyFrame(stateTime, true);
                break;
        }
    }

    void render(SpriteBatch batch){
        batch.draw(frame, position.x, position.y);

        weapon.render(batch);
    }

    public void update(float delta, Assets assets) {
        stateTime += delta;

        if(Controls.isLeftPressed()){
            moveLeft();
        } else if(Controls.isRightPressed()){
            moveRight();
        } else {
            idle();
        }

        if(Controls.isShootPressed()) {
            shoot();
        }

        setFrame(assets);

        weapon.update(delta, assets);
    }

    void idle(){
        state = State.IDLE;
    }

    void moveLeft(){
        position.x -= speed;
        state = State.LEFT;
    }

    void moveRight(){
        position.x += speed;
        state = State.RIGHT;
    }

    void shoot(){
        state = State.SHOOT;
        weapon.shoot(position.x +16);
    }

    public void damage() {

    }
}
