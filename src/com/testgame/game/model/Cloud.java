package com.testgame.game.model;

import com.testgame.framework.util.RandomNumberGenerator;

/**
 * Created by cloud99_9 on 03.11.14.
 */
public class Cloud {
    private float x,y;
    private static final int VEL_X = -15;

    public Cloud(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void update(float delta){
        x += VEL_X * delta;

        if(x <= -200){
            x += 1000;
            y = RandomNumberGenerator.getRandomIntBetween(20, 100);
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
