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
        long updateDuration = 0;
        long sleepDuration = 0;

        while (running){
            long beforeUpdateRender = System.nanoTime();
            long delta = updateDuration + sleepDuration;

            updateAndRender(delta);

            updateDuration = (System.nanoTime() - beforeUpdateRender)/ 1000000L;
            sleepDuration = Math.max(2,17-updateDuration);

            try {
                Thread.sleep(sleepDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.exit(0);

    }

    private void updateAndRender(long delta){
        currentState.update(delta / 1000f);
        prepareGameImage();
        currentState.render(gameImage.getGraphics());
        renderGameImage(getGraphics());
    }

    private void renderGameImage(Graphics g) {
        if(gameImage != null){
            g.drawImage(gameImage,0,0,null);
        }
        g.dispose();
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
