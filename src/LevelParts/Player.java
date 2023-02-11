package LevelParts;

import Game.Main;
import Game.Scene;

import javax.swing.*;
import java.awt.* ;

public class Player extends Point {

    public Float[] velocity = new Float[]{0f, 0f};
    public Float[] acceleration = new Float[]{0f, 0f};
    public Float[] gravity = new Float[]{0f, 0f};
    public Float[] friction = new Float[]{0.99f, 0.99f};
    public float bounceEfficiency = 0.7f;
    public int lastMouseClickCount = 0;
    public float defaultSpeedMod = 0.3f;
    public float speedMod = defaultSpeedMod;
    public float forceRadius = 100000/radius;

    public float t = (1f/Main.tps)*speedMod;

    Float[] calculatedPosition = new Float[]{0f, 0f};

    public float mass = radius;

    public Player(Float[] position, float radius, Color renderColor){
        super(position, radius, renderColor);
    }

    public void calculatePosition(){

        Main.scene.lineSegments.get(1).p2[0] = position[0]+velocity[0]*0.1f;
        Main.scene.lineSegments.get(1).p2[1] = position[1]+velocity[1]*0.1f;

        if(Main.w.isSpaceDown){speedMod = defaultSpeedMod/5;}else{speedMod = defaultSpeedMod;}
        t = (1f/Main.tps)*speedMod;

        acceleration[0] = 0f;
        acceleration[1] = 0f;

        Main.scene.lineSegments.get(0).renderColor = new Color(0, 0, 0, 0);

        if(Main.w.isMouseDown){
            lastMouseClickCount = Main.w.mouseClickCount;
            java.awt.Point mousePosition = Main.w.frame.getMousePosition();



            if(mousePosition != null) {

                mousePosition.x -= Main.w.panel.getComponent(0).getX();
                mousePosition.y -= Main.w.panel.getComponent(0).getY() + 34;

                Main.scene.lineSegments.get(0).renderColor = Color.GREEN;


                Float[] forceVector = getForceVector(new Float[]{(float)mousePosition.x, (float)mousePosition.y}, position, forceRadius);

                Main.scene.lineSegments.get(0).p2[0] = (float) position[0] + forceVector[0]*0.001f*radius;
                Main.scene.lineSegments.get(0).p2[1] = (float) position[1] + forceVector[1]*0.001f*radius;


                acceleration[0] = acceleration[0] + (forceVector[0]);
                acceleration[1] = acceleration[1] + (forceVector[1]);
            }

        }

        acceleration[0] = acceleration[0] + gravity[0];
        acceleration[1] = acceleration[1] + gravity[1];


        velocity[0] = velocity[0]*friction[0];
        velocity[1] = velocity[1]*friction[1];

        velocity[0] = velocity[0]+acceleration[0]*t;
        velocity[1] = velocity[1]+acceleration[1]*t;

        if(position[1]+velocity[1]*t > Main.height-radius || position[1]+velocity[1]*t < 0+radius){
            velocity[1] = velocity[1]*-1*bounceEfficiency;
            calculatedPosition[1] = position[1]+velocity[1]*t;
        }else{
            calculatedPosition[1] = position[1]+velocity[1]*t;
        }

        if(position[0]+velocity[0]*t > Main.width-radius || position[0]+velocity[0]*t < 0+radius){
            velocity[0] = velocity[0]*-1*bounceEfficiency;
        }
        calculatedPosition[0] = position[0]+velocity[0]*t;


    }

    public void updatePosition(){
        position[0] = calculatedPosition[0];
        position[1] = calculatedPosition[1];
    }

    public Float[]getForceVector (Float[] pos, Float[] centre, float radius){
        Float[] dirVec = new Float[]{pos[0]-centre[0], pos[1] - centre[1]};
        float absOfDirVec = pyth(dirVec[0], dirVec[1]);
        float ratio = radius/absOfDirVec;
        return new Float[]{dirVec[0]*ratio, dirVec[1]*ratio};
    }





}
