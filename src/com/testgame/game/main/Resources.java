package com.testgame.game.main;

import com.testgame.framework.animation.Animation;
import com.testgame.framework.animation.Frame;


import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by cloud99_9 on 27.10.14.
 */
public class Resources {

    public static BufferedImage welcome, icon, block, cloud1, cloud2,duck,grass,jump,run1,run2,run3,run4,run5,selector;
    public static AudioClip hit,onJump;

    public static Animation runAnimation;

    public static Color skyBlue;

    public static void load(){
        System.out.println("Loading Resources!");
        welcome = loadImage("welcome.png");
        icon = loadImage("iconimage.png");
        block = loadImage("block.png");
        cloud1 = loadImage("cloud1.png");
        cloud2 = loadImage("cloud2.png");
        duck = loadImage("duck.png");
        grass = loadImage("grass.png");
        jump = loadImage("jump.png");
        run1 = loadImage("run_anim1.png");
        run2 = loadImage("run_anim2.png");
        run3 = loadImage("run_anim3.png");
        run4 = loadImage("run_anim4.png");
        run5 = loadImage("run_anim5.png");
        selector = loadImage("selector.png");

        hit = loadSound("hit.wav");
        onJump = loadSound("onjump.wav");

        skyBlue = new Color(208,244,247);

        double temp = 0.1;
        Frame f1 = new Frame(run1, .1f);
        Frame f2 = new Frame(run2, .1f);
        Frame f3 = new Frame(run3, .1f);
        Frame f4 = new Frame(run4, .1f);
        Frame f5 = new Frame(run5, .1f);

        runAnimation = new Animation(f1,f2,f3,f4,f5,f3,f2);


    }

    public static AudioClip loadSound(String file){
        URL fileUrl = Resources.class.getResource("/resources/" + file);
        return Applet.newAudioClip(fileUrl);
    }

    private static BufferedImage loadImage(String file){
        BufferedImage img = null;
        try {
            img = ImageIO.read(Resources.class.getResourceAsStream("/resources/" + file));
        } catch (IOException e) {
            System.out.println("ERROR: Error while loading image!");
            e.printStackTrace();
        }
        return img;
    }
}
