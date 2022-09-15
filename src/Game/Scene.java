package Game;

import LevelParts.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Scene {
    public Player player;

    public Scene(Player player){
        this.player = player;
    }

    public void tickUpdate(){
        player.tickUpdate();
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
        return image;
    }

}
