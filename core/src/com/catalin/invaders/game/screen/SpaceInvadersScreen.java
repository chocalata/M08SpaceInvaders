package com.catalin.invaders.game.screen;

import com.badlogic.gdx.Screen;
import com.catalin.invaders.game.Assets;
import com.catalin.invaders.game.SpaceInvaders;

public class SpaceInvadersScreen implements Screen {
    SpaceInvaders game;
    Assets assets;

    public SpaceInvadersScreen(SpaceInvaders game){
        this.game = game;
        this.assets = game.assets;
    }

    void setScreen(Screen screen){
        game.setScreen(screen);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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
