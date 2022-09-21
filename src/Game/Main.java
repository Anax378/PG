package Game;
import Game.Window;
import LevelParts.LineSegment;
import LevelParts.Player;
import LevelParts.Triangle;

import java.awt.*;

public class Main {
    public static Window w;
    public static Player player;
    public static Scene scene;

    public static int width = 600;
    public static int height = 600;
    public static int tps = 100;

    public static boolean isRunning = true;

    public static void main(String[] args){
        w = new Window(width, height);

        player = new Player(new Float[]{300f, 300f}, 10f, Color.BLACK);
        scene = new Scene(player);

        scene.add(new LineSegment(scene.player.position, new Float[]{0f, 0f}, Color.GREEN));
        scene.add(new LineSegment(scene.player.position, new Float[]{0f, 0f}, Color.RED));
        scene.add(new Triangle(new Float[]{300f, 300f}, 40, Color.RED));



        long start = System.currentTimeMillis();
        while (isRunning) // game loop
        {
            w.setImage(scene.getFrame());

            if((System.currentTimeMillis() - start) > 1000f/tps){
                scene.tickUpdate();
                start = System.currentTimeMillis();
            }

        }


    }


}
