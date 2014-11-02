package com.testgame.game.state;

import com.testgame.game.main.GameMain;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by cloud99_9 on 27.10.14.
 */
public abstract class State {

    public abstract void init();

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void onClick(MouseEvent e);

    public abstract void onKeyPress(KeyEvent e);

    public abstract void onKeyRelease(KeyEvent e);

    public void setCurrentState(State newState) {
        GameMain.game.setCurrentState(newState);
    }
}
