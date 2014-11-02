package com.testgame.framework.animation;

import java.awt.*;

/**
 * Created by Hollow on 02.11.14.
 */
public class Animation {
    private Frame[] frames;
    private double[] durations;
    private int frameIndex;

    private double totalDuration = 0;
    private double currentTime = 0;

    public Animation(Frame... frames){
        this.frames = frames;
        durations = new double[frames.length];

        for (int i = 0; i < frames.length; i++) {
            Frame f = frames[i];
            totalDuration += f.getDuration();
            durations[i] = totalDuration;
        }
    }

    public synchronized void update(float increment) {
        currentTime += increment;

        if (currentTime > totalDuration) {
            wrapAnimation();
        }

        while (currentTime > durations[frameIndex]) {
            frameIndex++;
        }
    }

    private synchronized void wrapAnimation() {
        frameIndex = 0;
        currentTime %= totalDuration; // equal to cT = cT % tD
    }

    public synchronized void render(Graphics g, int x, int y) {
        g.drawImage(frames[frameIndex].getImage(), x, y, null);
    }

    public synchronized void render(Graphics g, int x, int y, int width,
                                    int height) {
        g.drawImage(frames[frameIndex].getImage(), x, y, width, height,
                null);
    }
}
