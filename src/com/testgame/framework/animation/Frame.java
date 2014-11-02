package com.testgame.framework.animation;

import java.awt.*;

/**
 * Created by Hollow on 02.11.14.
 */
public class Frame {
    private Image image;
    private double duration;

    public Frame(Image image, double duration){
        this.image = image;
        this.duration = duration;
    }

    public Image getImage() {
        return image;
    }

    public double getDuration() {
        return duration;
    }
}
