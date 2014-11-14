package com.testgame.game.model;

import com.testgame.game.main.Resources;

import java.awt.*;

/**
 * Created by cloud99_9 on 03.11.14.
 */
public class Player {
    private float x,y;
    private int width,height,velY;
    private Rectangle rectangleJumping,rectangleDucking,ground;

    private boolean isAlive;
    private boolean isDucked;
    private float duckDuration = .6f;

    private static final int JUMP_VELOCITY = -600;
    private static final int ACCEL_GRAVITY = 1800;

    public Player(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        ground = new Rectangle(0,404,800,45);
        rectangleJumping = new Rectangle();
        rectangleDucking = new Rectangle();
        isAlive = true;
        isDucked = false;
        updateRectangles();
    }

    public void update(float delta){
        if(duckDuration > 0 && isDucked){
            duckDuration -= delta;
        }else{
            isDucked = false;
            duckDuration = .6f;
        }

        if(!isGrounded()){
            velY += (int)(ACCEL_GRAVITY * delta);
        }else{
            y = 406-height;
            velY = 0;

        }

        y += velY * delta;
        System.out.println("" + (velY * delta));
        updateRectangles();
    }

    public void jump(){
        if(isGrounded()){
            Resources.onJump.play();
            isDucked = false;
            duckDuration = .6f;
            y -= 10;
            velY = JUMP_VELOCITY;
            updateRectangles();
        }
    }

    public void duck(){
        if(isGrounded()){
            isDucked = true;
        }
    }

    public boolean isGrounded() {
        return rectangleJumping.intersects(ground);
    }

    private void updateRectangles() {
        rectangleJumping.setBounds((int)x + 10,(int)y,width - 20, height);
        rectangleDucking.setBounds((int)x,(int)y + 20,width,height - 20);
    }

    public void pushBack(int dx){
        Resources.hit.play();
        x -= dx;

        if(x < -width/2){
            isAlive = false;
        }
        rectangleJumping.setBounds((int)x,(int)y,width,height);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getVelY() {
        return velY;
    }

    public Rectangle getRectangleJumping() {
        return rectangleJumping;
    }

    public Rectangle getRectangleDucking() {
        return rectangleDucking;
    }

    public Rectangle getGround() {
        return ground;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isDucked() {
        return isDucked;
    }
}
