package Game;

import LevelParts.*;
import LevelParts.Point;

import javax.swing.text.Position;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Scene {
    int maxEnemyCount;

    public Player player;
    public List<LineSegment> lineSegments = new ArrayList<>();
    public List<Enemy> enemies = new ArrayList<>();
    public List<MovingEnemy> movingEnemies = new ArrayList<>();
    Float[] initialPlayerPosition = {0f, 0f};
    ScorePoint scorePoint;

    public int maxEnemySize = 20;
    public int minEnemySize = 5;

    public float maxMovingEnemySpeed = 5; //pixels per tickTimeUnit
    public float minMovingEnemySpeed = 1; //pixels per tickTimeUnit

    public float scorePointSize = 7f;
    public TextLabel scoreCounter = new TextLabel(new Float[]{10f, 0f}, Color.BLACK);
    public long score = 0;

    public float maxLifetime = 1100/2;
    public float minLifetime = 500/2;

    public double tickTime = 0;

    public Scene(Player player, int maxEnemyCount){
        this.player = player;
        this.maxEnemyCount = maxEnemyCount;
        initialPlayerPosition[0] = player.position[0];
        initialPlayerPosition[1] = player.position[1];
        scorePoint = new ScorePoint(new Float[] {0f, 0f}, scorePointSize, Color.GREEN);
        regenerateScorePoint();
    }

    public void tickUpdate(){
        scoreCounter.text = "Score: " + score;

        if(player.isColiding(scorePoint.position, player.radius)){score++; regenerateScorePoint();}
        tickTime = tickTime + 1*player.speedMod;

        int supposedEnemyListSize = enemies.size();
        int supposedMovingEnemyListSize = movingEnemies.size();
        for(int i =0;i < supposedEnemyListSize; i++){enemies.get(i).tickUpdate(); if(enemies.get(i).shouldDie){enemies.remove(i); supposedEnemyListSize--; i--;}}
        for(int i =0;i < supposedMovingEnemyListSize; i++){movingEnemies.get(i).tickUpdate(); if(movingEnemies.get(i).shouldDie){movingEnemies.remove(i); supposedMovingEnemyListSize--; i--;}}

        if(enemies.size()+movingEnemies.size() < maxEnemyCount){
            Random rand = new Random();
            if(rand.nextInt(0, 2) == 1){
                System.out.println("Here");
                boolean validPosFound = false;
                Float[] pos = new Float[]{0f, 0f};
                while(!validPosFound) {
                    validPosFound = true;

                    pos = new Float[]{rand.nextFloat(0, Main.width - maxEnemySize), rand.nextFloat(0, Main.height - maxEnemySize)};
                    if(player.isColiding(pos, maxEnemySize+10)){
                        validPosFound = false;
                    }
                    if(scorePoint.isColiding(pos, maxEnemySize+10)){
                        validPosFound = false;
                    }
                }
                Float[] path = new Float[]{pos[0]-rand.nextFloat(0, Main.width-maxEnemySize), pos[1]-rand.nextFloat(0, Main.height-maxEnemySize)};
                movingEnemies.add(new MovingEnemy(new Float[]{pos[0], pos[1]}, rand.nextFloat(minEnemySize, maxEnemySize), Color.RED, path, pyth(path, pos)/rand.nextFloat(minMovingEnemySpeed, maxMovingEnemySpeed)));


            }else{

            Float[] position = new Float[]{0f, 0f};
            boolean isValidPosition = false;
            while(!isValidPosition){
            position = new Float[]{rand.nextFloat(0, Main.width), rand.nextFloat(0, Main.height)};
            if(!player.isColiding(position, maxEnemySize + 10f) && !scorePoint.isColiding(position, maxEnemySize+10f)){isValidPosition = true;}}
            enemies.add(new Enemy(position, rand.nextFloat(minEnemySize, maxEnemySize), new Color(255, rand.nextInt(0, 50), rand.nextInt(0, 50)), rand.nextFloat(minLifetime, maxLifetime)));
            }
        }

        for(int i = 0; i < enemies.size(); i++){if(enemies.get(i).isColiding(player.position, player.radius)){
            Main.w.setImage(getFrame());
            while(!Main.w.isSpaceDown){}
            restart();
        }}

        for(int i = 0; i < movingEnemies.size(); i++){if(movingEnemies.get(i).isColiding(player.position, player.radius)){
            Main.w.setImage(getFrame());
            while(!Main.w.isSpaceDown){}
            restart();
        }}

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

        for(int i = 0; i < lineSegments.size(); i++){image = lineSegments.get(i).renderOnImage(image);}
        for(int i = 0; i < enemies.size(); i++){image = enemies.get(i).renderOnImage(image);}
        for(int i = 0; i < movingEnemies.size(); i++){image = movingEnemies.get(i).renderOnImage(image);}
        image = scorePoint.renderOnImage(image);
        image = scoreCounter.renderOnImage(image);

        return image;
    }

    public void add(Object o)
    {
        if(o instanceof LineSegment){lineSegments.add((LineSegment) o);}
        if(o instanceof Enemy){
            enemies.add((Enemy) o);}
    }

    public void restart(){
        score = 0;
        player.position[0] = initialPlayerPosition[1];
        player.position[1] = initialPlayerPosition[1];

        player.velocity[0] = 0f;
        player.velocity[1] = 0f;

        enemies.clear();
        movingEnemies.clear();
    }

    public void regenerateScorePoint(){
        Random rand = new Random();
        Float[] position = new Float[]{0f, 0f};
        boolean isValidPosition = false;
        while (!isValidPosition) {
            position[0] = rand.nextFloat(scorePointSize, Main.width - scorePointSize);
            position[1] = rand.nextFloat(scorePointSize, Main.height - scorePointSize);

            boolean passed = true;
            if(!player.isColiding(position, scorePointSize+10f)){for(int i =0; i < enemies.size(); i++){if(enemies.get(i).isColiding(position, scorePointSize+5f)){passed = false;}}}
            if(passed){isValidPosition = true;}
        }
        scorePoint.position[0] = position[0];
        scorePoint.position[1] = position[1];
    }

    public static float pyth(Float[] vec1, Float[] vec2){
        float a = vec1[0] - vec2[0];
        float b = vec1[1] - vec2[1];

        return (float) Math.sqrt(a*a + b*b);
    }
}
