package LevelParts;

import Game.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ball {

    public Float[] position;
    public float radius;
    public Color renderColor;

    public Ball(Float[] position, float radius, Color renderColor){
        this.position = position;
        this.radius = radius;
        this.renderColor = renderColor;

    }


    public BufferedImage renderOnImage(BufferedImage image){
        Graphics2D g2d = image.createGraphics();

        g2d.setColor(renderColor);
        g2d.fillOval(Math.round(Main.scene.toRenderCoords(position)[0]),  Math.round(Main.scene.toRenderCoords(position)[1]), Math.round(radius), Math.round(radius));
        g2d.dispose();

        return image;
    }

    public float cross(Float[] a, Float[] b){
        return (a[0]*b[0]+a[1]*b[1]);
    }
    public float pyth(float a, float b){
        return (float) Math.sqrt(a*a+b*b);
    }




}
