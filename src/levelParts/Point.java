package levelParts;

import game.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Point {

    public Float[] position;
    public float radius;
    public Color renderColor;

    public Point(Float[] position, float radius, Color renderColor){
        this.position = position;
        this.radius = radius;
        this.renderColor = renderColor;

    }


    public BufferedImage renderOnImage(BufferedImage image){
        Graphics2D g2d = image.createGraphics();

        g2d.setColor(renderColor);
        g2d.fillOval(Math.round(Main.scene.toRenderCoords(position)[0]-radius),  Math.round(Main.scene.toRenderCoords(position)[1]-radius), Math.round(radius*2), Math.round(radius*2));
        g2d.dispose();

        return image;
    }

    public float pyth(float a, float b){
        return (float) Math.sqrt(a*a+b*b);
    }

    public boolean isColiding(Float[] position, float radius){
        float a = position[0] - this.position[0];
        float b = position[1] - this.position[1];
        float dist = (float) Math.sqrt(a*a + b*b);

        return dist < radius+this.radius;
    }




}
