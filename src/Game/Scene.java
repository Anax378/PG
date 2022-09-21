package Game;

import LevelParts.LineSegment;
import LevelParts.Player;
import LevelParts.Triangle;

import javax.sound.sampled.Line;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Scene {
    public Player player;
    public List<LineSegment> lineSegments = new ArrayList<>();
    public List<Triangle> triangles = new ArrayList<>();

    public Scene(Player player){
        this.player = player;
    }

    public void tickUpdate(){
        player.tickUpdate();
        for(int i = 0; i < triangles.size(); i++){triangles.get(0).tickUpdate();}
    }

    public Float[] toRenderCoords(Float[] position){
        return position;
    }

    public BufferedImage getFrame(){
        BufferedImage image = new BufferedImage(Main.width, Main.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setPaint(Color.WHITE);
        g2d.fillRect(0, 0, Main.width, Main.height);
        g2d.dispose();

        image = player.renderOnImage(image);

        for(int i = 0; i < lineSegments.size(); i++){image = lineSegments.get(i).renderOnImage(image);}
        for(int i = 0; i < triangles.size(); i++){image = triangles.get(i).renderOnImage(image);}

        return image;
    }

    public void add(Object o)
    {
        if(o instanceof LineSegment){lineSegments.add((LineSegment) o);}
        if(o instanceof Triangle){triangles.add((Triangle) o);}
    }

}
