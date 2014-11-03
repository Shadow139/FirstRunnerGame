package com.testgame.game.state;

import com.testgame.game.main.GameMain;
import com.testgame.game.main.Resources;
import com.testgame.game.state.PlayState;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by cloud99_9 on 27.10.14.
 */
public class MenuState extends State {
    private int currentSelection = 0;

    @Override
    public void init() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Graphics g) {
        //System.out.println("drawing!");
        g.drawImage(Resources.welcome,0,0,null);

        if(currentSelection == 0){
            g.drawImage(Resources.selector,335,241,null);
        }else{
            g.drawImage(Resources.selector,335,291,null);
        }
    }


    @Override
    public void onClick(MouseEvent e) {

    }

    @Override
    public void onKeyPress(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_ENTER){
            if(currentSelection == 0){
                setCurrentState(new PlayState());
            }else if(currentSelection == 1){
                GameMain.game.exit();
            }
        }else if(key == KeyEvent.VK_UP){
            currentSelection = 0;
        }else if(key == KeyEvent.VK_DOWN){
            currentSelection = 1;
        }
    }

    @Override
    public void onKeyRelease(KeyEvent e) {

    }
}
