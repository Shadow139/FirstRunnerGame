package com.testgame.game.model;

import com.testgame.framework.util.RandomNumberGenerator;

import java.awt.*;

/**
 * Created by cloud99_9 on 03.11.14.
 */
public class Block {
    private float x,y;
    private int width,height;
    private Rectangle rectangle;
    private boolean visible;

    private static final int UPPER_Y = 275;
    private static final int LOWER_Y = 355;

    public Block(float x,float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        rectangle = new Rectangle((int) x, (int) y, width, height);
        visible = false;
    }

    public void update(float delta, float velX){
        x += velX * delta;

        if(x <= -50){
            reset();
        }
        updateRectangle();
    }

    private void updateRectangle() {
        rectangle.setBounds((int) x ,(int) y, width, height);
    }

    private void reset() {
        visible = true;

        if(RandomNumberGenerator.getRandomInt(3)  == 0){
            y = UPPER_Y;
        }else{
            y = LOWER_Y;
        }

        x += 1000;
    }

    public void onCollision(Player p){
        visible = false;
        p.pushBack(30);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean isVisible() {
        return visible;
    }
}
