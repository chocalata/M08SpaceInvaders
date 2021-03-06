package com.catalin.invaders.game.objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.catalin.invaders.game.Assets;
import com.catalin.invaders.game.Timer;

public class Weapon {

    Array<Shoot> shoots;

    Timer shootTimer;
    float shootRate = 0.4f;

    Sound shootSound;

    Weapon(Sound shootSound){
        this.shootSound = shootSound;
        shoots = new Array<Shoot>();
        shootTimer = new Timer(shootRate);
    }

    void render(SpriteBatch batch){
        for (Shoot shoot: shoots) {
            shoot.render(batch);
        }
    }

    public void update(float delta, Assets assets) {
        shootTimer.update(delta);
        for(Shoot shoot: shoots){
            shoot.update(delta, assets);
        }
        removeShoots();
    }

    public void shoot(float position){
        if(shootTimer.check()){
            shoots.add(new Shoot(position));
            shootSound.play();
        }
    }

    public void removeShoots(){
        Array<Shoot> shootsToRemove = new Array<Shoot>();
        for(Shoot shoot:shoots){
            if(shoot.state == Shoot.State.TO_REMOVE){
                shootsToRemove.add(shoot);
            }
        }

        for (Shoot shoot: shootsToRemove){
            shoots.removeValue(shoot, true);
        }
    }
}
