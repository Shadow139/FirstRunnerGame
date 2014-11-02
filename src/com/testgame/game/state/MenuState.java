package com.testgame.game.state;

import com.testgame.game.main.Resources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by cloud99_9 on 27.10.14.
 */
public class MenuState extends State {
    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        //System.out.println("drawing!");
        g.drawImage(Resources.welcome,0,0,null);
    }


    @Override
    public void onClick(MouseEvent e) {

    }

    @Override
    public void onKeyPress(KeyEvent e) {

    }

    @Override
    public void onKeyRelease(KeyEvent e) {

    }
}
