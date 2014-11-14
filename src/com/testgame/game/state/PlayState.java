package com.testgame.game.state;

import com.testgame.game.main.GameMain;
import com.testgame.game.main.Resources;
import com.testgame.game.model.Block;
import com.testgame.game.model.Cloud;
import com.testgame.game.model.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by cloud99_9 on 03.11.14.
 */
public class PlayState extends State {
    private Player player;
    private ArrayList<Block> blocks;
    private Cloud cloud1,cloud2;

    private Font scoreFont;
    private int playerScore = 0;

    private static final int BLOCK_HEIGHT = 50;
    private static final int BLOCK_WIDTH =20;
    private int blockSpeed = -200;

    private static final int PLAYER_HEIGHT = 92;
    private static final int PLAYER_WIDTH = 66;

    @Override
    public void init() {
        System.out.println("Initializing player y: " + (GameMain.GAME_HEIGHT - 45 - PLAYER_HEIGHT));

        player = new Player(160, GameMain.GAME_HEIGHT - 45 - PLAYER_HEIGHT,PLAYER_WIDTH,PLAYER_HEIGHT);

        System.out.println("Initialized player: " + player.getY());

        blocks = new ArrayList<Block>();
        cloud1 = new Cloud(100,100);
        cloud2 = new Cloud(500,50);
        scoreFont = new Font("SansSerif", Font.BOLD, 25);

        for(int i = 0; i < 5; i++){
            Block b = new Block(i * 200,GameMain.GAME_HEIGHT - 95, BLOCK_WIDTH,BLOCK_HEIGHT);

            blocks.add(b);
        }

    }

    @Override
    public void update(float delta) {
        //System.out.println("X: " + player.getX());
        //System.out.println("Y: " + player.getY());
        System.out.println("Ducked: " + player.isDucked());
        System.out.println("isGrounded: " + player.isGrounded());
       // System.out.println("PlayerRectangleJumping : \n" +
       //         "x: " + player.getRectangleJumping().getX() + "  :  y: " + player.getRectangleJumping().getY() +
       //         "width: " + player.getRectangleJumping().getWidth() + "  :  height: " + player.getRectangleJumping().getHeight());

       // System.out.println("GroundRectangle : \n" +
        //        "x: " + player.getGround().getX() + "  :  y: " + player.getGround().getY() +
        //        "width: " + player.getGround().getWidth() + "  :  height: " + player.getGround().getHeight());

        //System.out.println("Width: " + player.getWidth());
        //System.out.println("Height: " + player.getHeight());


        if(!player.isAlive()){
            setCurrentState(new GameOverState(playerScore/100));
        }
        playerScore += 1;

        if(playerScore % 500 == 0 && blockSpeed > -280){
            blockSpeed -= 10;
        }

        cloud1.update(delta);
        cloud2.update(delta);

        Resources.runAnimation.update(delta);
        player.update(delta);
        updateBlocks(delta);

    }

    private void updateBlocks(float delta) {
        for(Block b : blocks) {
            b.update(delta, blockSpeed);

            if (b.isVisible()){
                if(player.isDucked() && b.getRectangle().intersects(player.getRectangleDucking())){
                    b.onCollision(player);
                }else if(!player.isDucked() && b.getRectangle().intersects(player.getRectangleJumping())){
                    b.onCollision(player);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Resources.skyBlue);
        g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
        renderPlayer(g);
        renderBlocks(g);
        renderSun(g);
        renderClouds(g);
        g.drawImage(Resources.grass, 0, 405, null);
        renderScore(g);

    }

    private void renderClouds(Graphics g) {
        g.drawImage(Resources.cloud1,(int) cloud1.getX(),(int) cloud1.getY(), 100, 60,null);
        g.drawImage(Resources.cloud2,(int) cloud2.getX(),(int) cloud2.getY(), 100, 60,null);
    }

    private void renderSun(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval(715,-85,170,170);
        g.setColor(Color.YELLOW);
        g.fillOval(725,-75,150,150);

    }

    private void renderBlocks(Graphics g) {
        for(Block b: blocks){
            if(b.isVisible()){
                g.drawImage(Resources.block,(int) b.getX(), (int) b.getY(), BLOCK_WIDTH,BLOCK_HEIGHT,null);
            }
        }
    }

    private void renderPlayer(Graphics g) {
        if(player.isGrounded()){
            if(player.isDucked()){
                g.drawImage(Resources.duck,(int) player.getX(),(int) player.getY(),null);
            }else{
                Resources.runAnimation.render(g,(int) player.getX(),(int) player.getY(), player.getWidth(), player.getHeight());
            }
        }else{
            g.drawImage(Resources.jump,(int) player.getX(),(int) player.getY(), player.getWidth(), player.getHeight(),null);
        }
    }

    private void renderScore(Graphics g) {
        g.setFont(scoreFont);
        g.setColor(Color.GRAY);
        g.drawString("" + playerScore/ 100, 20,30);
    }

    @Override
    public void onClick(MouseEvent e) {

    }

    @Override
    public void onKeyPress(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            setCurrentState(new MenuState());
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP){
            System.out.println("jump pressed");
            player.jump();
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            System.out.println("duck pressed");
            player.duck();
        }
    }

    @Override
    public void onKeyRelease(KeyEvent e) {

    }
}
