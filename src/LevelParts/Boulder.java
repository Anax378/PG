package LevelParts;

import Game.Main;
import Game.Scene;

import java.awt.*;

public class Boulder extends Point {
    public Float[] velocity;
    public Float[] friction;
    public Float[] gravity;
    public float bounceEfficiency;
    Float[] acceleration = new Float[]{0f, 0f};
    public float mass = radius;
    float t = (1f/ Main.tps)*Main.player.speedMod;
    public Boulder (Float[] position, float size, Color color) {
        super(position, size, color);
        this.position = position;
        this.radius = size;
        this.renderColor = color;
        this.velocity = new Float[] {0f, 0f};
    }
    public void tickUpdate() {
        if (!contains_null(position)) {
            t = (1f / Main.tps) * Main.player.speedMod;
            acceleration[0] = 0f;
            acceleration[1] = 0f;

            acceleration[0] += velocity[0];
            acceleration[1] += velocity[1];
            acceleration[0] += gravity[0];
            acceleration[1] += gravity[1];

            velocity[0] *= friction[0];
            velocity[1] *= friction[1];

            velocity[0] += acceleration[0] * t;
            velocity[1] += acceleration[1] * t;

            if (isColliding(position, Main.player.position, radius, Main.player.radius)) {
                velocity = Scene.getElasticDynamicCircleCollisionVelocity(
                        this.position,
                        Main.player.position,
                        this.velocity,
                        Main.player.velocity,
                        this.mass,
                        Main.player.mass
                );
            }

            position[0] = velocity[0] * t;
            position[1] = velocity[1] * t;

        }
    }

    public boolean isColliding(Float[] position1, Float[] position2, float radius1, float radius2){
        if(contains_null(position1) || contains_null(position2)){return false;}
        float a = position1[0] - position2[0];
        float b = position1[1] - position2[1];
        float dist = (float) Math.sqrt(a*a + b*b);
        return dist <= (radius1+radius2);

    }

    float avg(float a, float b){return (a+b)/2f;}

    float pyth(Float[] dist){
        if (contains_null(dist)){return 0;}
        return (float) Math.sqrt(dist[0]*dist[0] + dist[1]*dist[1]);
    }


    float[] toFloat(Float[] x){
        if (contains_null(x)) {return new float[]{0, 0};}
        return new float[]{x[0], x[1]};
    }

    float dotProduct(float[] V1, float[] V2) {return V1[0] * V2[0] + V1[1] + V2[1];}

    boolean contains_null(Float[] x) {
        return x[0] == null || x[1] == null;
    }



}
