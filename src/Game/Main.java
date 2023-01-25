package Game;
import LevelParts.Boulder;
import LevelParts.LineSegment;
import LevelParts.Player;

import java.awt.*;

public class Main {

    //TODO: add support for clicking on w.frame to move
    //TODO: add score per second counter
    //TODO: add score per tick counter
    //TODO: add more boulders
    //TODO: add tick substeps
    private static boolean AllowLogs = true;
    public static Window w;
    public static Player player;

    public static Boulder boulder;
    public static Scene scene;

    public static int width = 600;
    public static int height = 600;
    public static int tps = 100;

    public static boolean isRunning = true;

    public static void Log(String message){
        if(AllowLogs){
            System.out.println("[" + System.currentTimeMillis() + "] " + message);
        }
    }

    public static void main(String[] args){
        w = new Window(width, height);

        player = new Player(new Float[]{300f, 300f}, 10f, Color.BLACK);
        boulder = new Boulder(new Float[]{500f, 500f}, 10f, Color.BLUE);
        scene = new Scene(player, boulder, 0);


        scene.add(new LineSegment(scene.player.position, new Float[]{0f, 0f}, Color.GREEN));
        scene.add(new LineSegment(scene.player.position, new Float[]{0f, 0f}, Color.RED));

        scene.add(new LineSegment(scene.boulder.position, new Float[]{0f, 0f}, Color.GREEN));



        long start = System.currentTimeMillis();
        while (isRunning) // game loop
        {
            w.setImage(scene.getFrame());

            if((System.currentTimeMillis() - start) > (1000f/tps)){

                scene.tickUpdate();
                start = System.currentTimeMillis();
            }

        }


    }


}
