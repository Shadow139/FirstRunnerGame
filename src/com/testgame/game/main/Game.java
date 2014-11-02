package com.testgame.game.main;

import com.testgame.framework.util.InputHandler;
import com.testgame.game.state.LoadState;
import com.testgame.game.state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by cloud99_9 on 27.10.14.
 */
public class Game extends JPanel implements Runnable {
    private int gameWidth;
    private int gameHeight;
    private Image gameImage;

    private Thread gameThread;
    private volatile boolean running;
    private volatile State currentState;

    private InputHandler inputHandler;

    public Game(int gameHeight, int gameWidth){
        System.out.println("Creating Game!");

        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;
        setPreferredSize(new Dimension(gameWidth,gameHeight));
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocus();

        System.out.println("Game created sucessfully!");

    }

    private void initInput(){
        inputHandler = new InputHandler();
        addKeyListener(inputHandler);
        addMouseListener(inputHandler);
    }

    public void setCurrentState(State newState) {
        System.out.println("Changing State!");
        System.gc();
        currentState = newState;
        newState.init();
        inputHandler.setCurrentState(currentState);
        System.out.println("State changed sucessfully!");
    }
    @Override
    public void addNotify(){
        super.addNotify();
        initInput();
        setCurrentState(new LoadState());
        initGame();

    }

    private void initGame() {
        System.out.println("Initializing Game!");
        running = true;
        gameThread = new Thread(this, "Game Thread");
        gameThread.start();
        System.out.println("Game initialized sucessfully!");
    }

    @Override
    public void run() {
        while (running){
            //System.out.println("" + currentState.getClass());
            currentState.update();
            prepareGameImage();
            currentState.render(gameImage.getGraphics());
            repaint();
            try {
                Thread.sleep(14);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.exit(0);

    }

    private void prepareGameImage() {
        if(gameImage == null){
            gameImage = createImage(gameWidth,gameHeight);
        }
        Graphics g = gameImage.getGraphics();
        g.clearRect(0,0,gameWidth,gameHeight);
    }
    public void exit(){
        running = false;
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(gameImage == null){
            return;
        }

        g.drawImage(gameImage,0,0,null);
    }
}
