package LevelParts;

import Game.Main;
import java.awt.*;

public class Player extends Ball{

    public Float[] velocity = new Float[]{3000f, 2000f};
    public Float[] acceleration = new Float[]{0f, 0f};
    public Float[] gravity = new Float[]{0f, 0f};
    public Float[] friction = new Float[]{0.999f, 0.999f};
    public float bounceEfficiency = 0.7f;
    public int lastMouseClickCount = 0;
    public float speedMod = 0.5f;
    public float forceRadius = 2000;


    public float t = (1f/Main.tps)*speedMod;

    public Player(Float[] position, float radius, Color renderColor){
        super(position, radius, renderColor);
    }

    public void tickUpdate(){
        acceleration[0] = 0f;
        acceleration[1] = 0f;

        if(Main.w.isMouseDown){
            lastMouseClickCount = Main.w.mouseClickCount;
            if(Main.w.label.getMousePosition() != null) {
                Float[] forceVector = getForceVector(new Float[]{(float)Main.w.label.getMousePosition().x, (float)Main.w.label.getMousePosition().y}, position, forceRadius);
                acceleration[0] = acceleration[0] + (forceVector[0]*-1);
                acceleration[1] = acceleration[1] + (forceVector[1]*-1);
            }

        }

        acceleration[0] = acceleration[0] + gravity[0];
        acceleration[1] = acceleration[1] + gravity[1];


        velocity[0] = velocity[0]*friction[0];
        velocity[1] = velocity[1]*friction[1];

        velocity[0] = velocity[0]+acceleration[0]*t;
        velocity[1] = velocity[1]+acceleration[1]*t;




        if(position[1]+velocity[1]*t > Main.height-radius || position[1]+velocity[1]*t < 0){
            velocity[1] = velocity[1]*-1*bounceEfficiency;
            position[1] = position[1]+velocity[1]*t;
        }else{
            position[1] = position[1]+velocity[1]*t;
        }

        if(position[0]+velocity[0]*t > Main.width-radius || position[0]+velocity[0]*t < 0){
            velocity[0] = velocity[0]*-1*bounceEfficiency;
        }
        position[0] = position[0]+velocity[0]*t;


    }

    public Float[]getForceVector (Float[] pos, Float[] centre, float radius){
        Float[] dirVec = new Float[]{pos[0]-centre[0], pos[1] - centre[1]};
        float absOfDirVec = pyth(dirVec[0], dirVec[1]);
        float ratio = radius/absOfDirVec;
        return new Float[]{dirVec[0]*ratio, dirVec[1]*ratio};
    }





}
