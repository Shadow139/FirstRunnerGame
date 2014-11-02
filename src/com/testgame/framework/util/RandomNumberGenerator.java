package com.testgame.framework.util;

import java.util.Random;

/**
 * Created by Hollow on 01.11.14.
 */
public class RandomNumberGenerator {

    private static Random random = new Random();

    public static int getRandomIntBetween(int lowerBound,int upperBound){
        return random.nextInt(upperBound - lowerBound) - lowerBound;
    }

    public static int getRandomInt(int upperBound){
        return random.nextInt(upperBound);
    }
}
