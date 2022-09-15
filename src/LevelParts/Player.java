package LevelParts;

import Game.Main;
import Game.Scene;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player{

    public Float[] position;
    public float radius;
    public Color renderColor;

    public Float[] velocity = new Float[]{3000f, 0f};
    public Float[] acceleration = new Float[]{0f, 0f};
    public Float[] gravity = new Float[]{0f, 200f};
    public Float[] friction = new Float[]{0.999f, 0.999f};
    public float bounceEfficiency = 0.7f;

    public float speedMod = 1;


    public float t = (1f/Main.tps)*speedMod;
    public Player(Float[] position, float radius, Color renderColor){
        this.position = position;
        this.radius = radius;
        this.renderColor = renderColor;
    }

    public void tickUpdate(){
        acceleration[0] = 0f;
        acceleration[1] = 0f;

        acceleration[0] = acceleration[0] + gravity[0];
        acceleration[1] = acceleration[1] + gravity[1];


        velocity[0] = velocity[0]*friction[0];
        velocity[1] = velocity[1]*friction[1];

        velocity[0] = velocity[0]+acceleration[0]*t;
        velocity[1] = velocity[1]+acceleration[1]*t;




        if(position[1]+velocity[1]*t < Main.height-radius){
            position[1] = position[1]+velocity[1]*t;
        }else{
            velocity[1] = velocity[1]*-1*bounceEfficiency;
            position[1] = position[1]+velocity[1]*t;
        }

        if(position[0]+velocity[0]*t > Main.width-radius || position[0]+velocity[0]*t < 0){
            velocity[0] = velocity[0]*-1*bounceEfficiency;
        }
        position[0] = position[0]+velocity[0]*t;


    }

    public BufferedImage renderOnImage(BufferedImage image){
        Graphics2D g2d = image.createGraphics();

        g2d.setColor(renderColor);
        g2d.fillOval(Math.round(Main.scene.toRenderCoords(position)[0]),  Math.round(Main.scene.toRenderCoords(position)[1]), Math.round(radius), Math.round(radius));
        g2d.dispose();

        return image;
    }




}
