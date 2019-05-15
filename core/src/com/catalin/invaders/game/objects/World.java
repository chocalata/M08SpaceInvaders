package com.catalin.invaders.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.catalin.invaders.game.Assets;

public class World {
    Space space;
    Ship ship;
    AlienArmy alienArmy;

    int WORLD_WIDTH, WORLD_HEIGHT;

    public World(int WORLD_WIDTH, int WORLD_HEIGHT, Assets assets){
        this.WORLD_WIDTH = WORLD_WIDTH;
        this.WORLD_HEIGHT = WORLD_HEIGHT;

        space = new Space();
        ship = new Ship(WORLD_WIDTH/2, assets);
        alienArmy = new AlienArmy(WORLD_WIDTH, WORLD_HEIGHT);
    }

    public void render(float delta, SpriteBatch batch, Assets assets){

        update(delta, assets);

        batch.begin();
        space.render(batch);
        ship.render(batch);
        if(alienArmy.aliens.size != 0 || alienArmy.shoots.size != 0) {
            alienArmy.render(batch);
        }
        batch.end();
    }

    void update(float delta, Assets assets){
        space.update(delta, assets);
        ship.update(delta, assets);

        if(alienArmy.aliens.size != 0 || alienArmy.shoots.size != 0) {
            alienArmy.update(delta, assets);
        }

        checkCollisions(assets);
    }

    private void checkCollisions(Assets assets) {
        checkNaveInWorld();
        checkShootsInWorld();
        if (alienArmy.aliens.size != 0) {
            checkShootsToAlien(assets);
        }
        checkShootsToShip();

    }
    private void checkShootsToShip() {
        Rectangle shipRectangle = new Rectangle(ship.position.x, ship.position.y, ship.frame.getRegionWidth(), ship.frame.getRegionHeight());

        for(AlienShoot shoot: alienArmy.shoots){
            Rectangle shootRectangle = new Rectangle(shoot.position.x, shoot.position.y, shoot.frame.getRegionWidth(), shoot.frame.getRegionHeight());

            if (Intersector.overlaps(shootRectangle, shipRectangle)) {
                ship.damage();
                shoot.remove();
            }
        }
    }

    private void checkShootsToAlien(Assets assets) {
        for(Shoot shoot: ship.weapon.shoots){
            Rectangle shootRectangle = new Rectangle(shoot.position.x, shoot.position.y, shoot.frame.getRegionWidth(), shoot.frame.getRegionHeight());
            for(Alien alien: alienArmy.aliens){
                if(alien.isAlive()) {
                    Rectangle alienRectangle = new Rectangle(alien.position.x, alien.position.y, alien.frame.getRegionWidth(), alien.frame.getRegionHeight());

                    if (Intersector.overlaps(shootRectangle, alienRectangle)) {
                        alien.kill();
                        shoot.remove();
                        assets.aliendieSound.play();
                    }
                }
            }
        }
    }

    private void checkShootsInWorld() {
        for(Shoot shoot: ship.weapon.shoots){
            if(shoot.position.y > WORLD_HEIGHT){
                shoot.remove();
            }
        }

        for(AlienShoot shoot: alienArmy.shoots){
            if(shoot.position.y < 0){
                shoot.remove();
            }
        }
    }

    private void checkNaveInWorld() {
        if(ship.position.x > WORLD_WIDTH-32){
            ship.position.x = WORLD_WIDTH-32;
        } else if(ship.position.x < 0){
            ship.position.x = 0;
        }
    }
}
