package com.catalin.invaders.game.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.catalin.invaders.game.SpaceInvaders;

public class LoadingScreen extends SpaceInvadersScreen {

    private Texture texture;

    SpriteBatch batch;

    public LoadingScreen(SpaceInvaders si) {
        super(si);
        batch = new SpriteBatch();

        Pixmap pixmap = new Pixmap(50, 50, Pixmap.Format.RGBA8888);
        pixmap.setColor(1,1,0,1);
        pixmap.fillRectangle(0, 0, 50, 50);
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void render(float delta) {
        if(!assets.update()){
            /////HACER BARRA DE CARGA
            System.out.println(assets.getProgress());
            batch.begin();
            batch.draw(texture,0,50, 650*assets.getProgress(),50);
            batch.end();
            return;
        }

        setScreen(new GameScreen(game));
    }
}
