package com.testgame.game.state;

import com.testgame.game.main.Resources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by cloud99_9 on 27.10.14.
 */
public class LoadState extends State {
    @Override
    public void init() {
        System.out.println("LoadState: Initializing Resources!");
        Resources.load();

    }

    @Override
    public void update() {
        System.out.println("LoadState: Changing to MenuState!");
        setCurrentState(new MenuState());
    }

    @Override
    public void render(Graphics g) {

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
