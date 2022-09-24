package LevelParts;
import Game.Main;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MovingEnemy extends Point{
    public Float[] path;
    public float lifetime;
    public float moveTime;
    public double ct;
    public boolean shouldDie;
    public LineSegment pathLine;
    public float delay = 2;

    public MovingEnemy(Float[] position, float radius, Color renderColor, Float[] path, float lifetime){
        super(position, radius, renderColor);
        this.moveTime = lifetime;
        this.lifetime = lifetime+delay;
        this.path = path;
        ct = Main.scene.tickTime;
        pathLine = new LineSegment(new Float[]{position[0], position[1]}, new Float[]{position[0] + path[0], position[1] + path[1]}, new Color(255, 0, 0, 150));
    }

    public void tickUpdate(){
        if(moveTime > delay) {
            position[0] += path[0] * 1 / moveTime;
            position[1] += path[1] * 1 / moveTime;
        }

        if(Main.scene.tickTime - ct > lifetime){shouldDie = true;}
    }

    @Override
    public BufferedImage renderOnImage(BufferedImage image){
        image = super.renderOnImage(image);
        image = pathLine.renderOnImage(image);
        return image;
    }


}