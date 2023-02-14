package Game;
import LevelParts.Boulder;
import LevelParts.LineSegment;
import LevelParts.Player;

import java.awt.*;

public class Main {
    private static final boolean AllowLogs = true;
    public static Window w;
    public static Player player;

    public static Boulder boulder;
    public static Scene scene;

    public static int width = 600;
    public static int height = 600;

    public static int ssps = 1;
    public static int sps = 100;
    public static int tps = sps*ssps;
    public static boolean isRunning = true;

    public static void Log(String message){
        if(AllowLogs){
            System.out.println("[" + System.currentTimeMillis() + "] " + message);
        }
    }

    public static void main(String[] args){
        w = new Window(width, height);

        player = new Player(new Float[]{300f, 300f}, 10f, Color.BLACK);
        scene = new Scene(player, 0);


        scene.add(new LineSegment(scene.player.position, new Float[]{0f, 0f}, Color.GREEN));
        scene.add(new LineSegment(scene.player.position, new Float[]{0f, 0f}, Color.RED));



        long start = System.currentTimeMillis();
        long beg = System.currentTimeMillis();
        int measurredTPS = 0;
        while (isRunning) // game loop
        {
            w.setImage(scene.getFrame());

            if((System.currentTimeMillis() - start) > (1000f/sps)){
                for(int i = 0; i < ssps; i++){
                    scene.tickUpdate();
                    measurredTPS++;
                }
                start = System.currentTimeMillis();
            }

            if(beg - System.currentTimeMillis() < -1000){
                beg = System.currentTimeMillis();
                Log("TPS: " + measurredTPS);
                measurredTPS = 0;
            }


        }


    }


}
