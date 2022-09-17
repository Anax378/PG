package LevelParts;

import Game.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LineSegment {
    public Float[] p1;
    public Float[] p2;
    public Color renderColor;


    public LineSegment(Float[] p1, Float[] p2, Color renderColor){
        this.p1 = p1;
        this.p2 = p2;
        this.renderColor = renderColor;
    }

    public BufferedImage renderOnImage(BufferedImage image){
        Graphics2D g2d = image.createGraphics();
        g2d.setPaint(renderColor);
        g2d.drawLine(Math.round(Main.scene.toRenderCoords(p1)[0]), Math.round(Main.scene.toRenderCoords(p1)[1]), Math.round(Main.scene.toRenderCoords(p2)[0]), Math.round(Main.scene.toRenderCoords(p2)[1]));
        g2d.dispose();
        return image;
    }



}
