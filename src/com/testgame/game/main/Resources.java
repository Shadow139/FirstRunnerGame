package com.testgame.game.main;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by cloud99_9 on 27.10.14.
 */
public class Resources {

    public static BufferedImage welcome, icon;

    public static void load(){
        System.out.println("Loading Resources!");
        welcome = loadImage("welcome.png");
        icon = loadImage("iconimage.png");

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
