package com.srinathv.sunnyfoggy;

/**
 * Created by Srinath on 03-10-2017.
 */

public class Circle {
    public int radius;
    public int x;
    public int y;

    public Circle(int r, int a, int b){
        radius = r;
        x = a;
        y=b;
    }

    public double getArea(){
        return Math.PI*radius*radius;
    }

}
