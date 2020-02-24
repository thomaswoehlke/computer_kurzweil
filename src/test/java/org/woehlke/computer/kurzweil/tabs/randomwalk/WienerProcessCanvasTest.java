package org.woehlke.computer.kurzweil.tabs.randomwalk;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

@Log
public class WienerProcessCanvasTest {

    @Test
    public void colorForAgeTest() {
        log.info("colorForAgeTest");
        long age;
        int red = 0;
        int green = 0;
        int blue = 0;
        long myage;
        long mybyte;
        for(long n=0; n < Long.MAX_VALUE; n++){
            age = n % (256 * 256 * 256);
            if(age == 0){
                red = 0;
                green = 0;
                blue = 0;
            } else {
                myage = (256 * 256 * 256) - age;
                mybyte = myage % 256;
                red = (int)(mybyte);
                myage /= 256;
                mybyte = myage % 256;
                green = (int)(mybyte);
                myage /= 256;
                mybyte = myage % 256;
                blue = (int)(mybyte);
            }
            log.info("age: "+age+" n: "+n+" RGB = ("+red+","+green+","+blue+")");
            Assertions.assertTrue(red<256);
            Assertions.assertTrue(green<256);
            Assertions.assertTrue(blue<256);
            Color colorForAge = new Color(red, green, blue);
            Assertions.assertTrue(colorForAge.getRed()==red);
            Assertions.assertTrue(colorForAge.getGreen()==green);
            Assertions.assertTrue(colorForAge.getBlue()==blue);

        }
        Assertions.assertTrue(true);
        log.info("colorForAgeTest Done");
    }
}