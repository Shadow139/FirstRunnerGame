package com.testgame.game.main;

import javax.swing.*;

/**
 * Created by cloud99_9 on 27.10.14.
 */
public class GameMain {
    private static final String GAME_TITLE = "Runner Game";
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT= 450;
    public static Game game;

    public static void main(String[] args){
        System.out.println("Main started!");
        JFrame frame = new JFrame(GAME_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);

        game = new Game(GAME_HEIGHT,GAME_WIDTH);
        frame.add(game);
        frame.pack();

        frame.setVisible(true);
        frame.setIconImage(Resources.icon);

        System.out.println("Main finished!");

    }


}
