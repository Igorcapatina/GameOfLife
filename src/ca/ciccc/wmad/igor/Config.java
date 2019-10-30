package ca.ciccc.wmad.igor;

import java.awt.*;

public class Config {

    public static final int SIZE = 10;
    public static final int WIDTH = 80;
    public static final int HEIGHT = 60;
    public static final int SLEEPMS = 200;


    public static Color getColor (Status status)
    {
        switch (status)
        {
            default:
            case NONE: return Color.BLACK;
                //break;
            case BORN: return  Color.green;
                //break;
            case LIVE: return  Color.yellow;
                //break;
            case DIED: return  Color.getHSBColor(0,75,65);
                //break;

        }
    }
}
