package com.catalin.invaders.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.prism.image.ViewPort;

public class Controls {
    static Viewport viewport;
    static Vector3 touch = new Vector3();

    private static final int MAX_DEDOS_PULSADOS = 2;

    public static void init(Viewport viewport){
        Controls.viewport = viewport;

    }
    public static boolean isLeftPressed() {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)){
            return true;
        }

        for (int i = 0; i < MAX_DEDOS_PULSADOS; i++) {
            if(Gdx.input.isTouched(i)) {
                viewport.unproject(touch.set(Gdx.input.getX(i), Gdx.input.getY(i), 0));

                if ((Gdx.input.isTouched() && touch.x > 0 && touch.x < 30 && touch.y > 50 && touch.y < 80)
                        && Gdx.app.getType() == Application.ApplicationType.Android) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isRightPressed() {
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)){
            return true;
        }


        for (int i = 0; i < MAX_DEDOS_PULSADOS; i++) {
            if(Gdx.input.isTouched(i)) {

                viewport.unproject(touch.set(Gdx.input.getX(i), Gdx.input.getY(i), 0));

                if ((Gdx.input.isTouched() && touch.x > 50 && touch.x < 80 && touch.y > 50 && touch.y < 80)
                        && Gdx.app.getType() == Application.ApplicationType.Android) {
                    return true;
                }
            }
        }

        return false;

    }

    public static boolean isShootPressed() {
        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)){
            return true;
        }

        for (int i = 0; i < MAX_DEDOS_PULSADOS; i++) {
            if(Gdx.input.isTouched(i)) {

                viewport.unproject(touch.set(Gdx.input.getX(i), Gdx.input.getY(i), 0));

                if ((Gdx.input.isTouched() && touch.x > 350 && touch.x < 380 && touch.y > 50 && touch.y < 80)
                        && Gdx.app.getType() == Application.ApplicationType.Android) {
                    return true;
                }
            }
        }

        return false;

    }

    public static void render(SpriteBatch spriteBatch, Assets assets){
        spriteBatch.begin();
        spriteBatch.draw(assets.izquierda, 0,50,30,30);
        spriteBatch.draw(assets.derecha, 50,50,30,30);
        spriteBatch.draw(assets.disparo, 350,50,30,30);
        spriteBatch.end();
    }
}
