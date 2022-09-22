package LevelParts;

import Game.Main;

import java.awt.*;

public class Enemy extends Point{
    float lifetime;
    double ct;
    public boolean shouldDie = false;
    public Enemy(Float[] position, float radius, Color renderColor, float lifetime){
        super(position, radius, renderColor);
        this.lifetime = lifetime;
        ct = Main.scene.tickTime;
    }
    public void tickUpdate(){
        if(Main.scene.tickTime - ct > lifetime){shouldDie = true;
        }}
}
