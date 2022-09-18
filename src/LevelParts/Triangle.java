package LevelParts;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Triangle {
    public Float[] position;
    public float radius;
    public Color renderColor;
    public float rotation;

    public Float[] defaultVert1 = new Float[]{0f, 0f};
    public Float[] defaultVert2 = new Float[]{0f, 0f};
    public Float[] defaultVert3 = new Float[]{0f, 0f};

    public Float[] vert1 = new Float[]{0f, 0f};
    public Float[] vert2 = new Float[]{0f, 0f};
    public Float[] vert3 = new Float[]{0f, 0f};

    public float rotationAngle = 0f;

    public Float[] moveVec = new Float[]{0.866666f, -0.5f};

    public Triangle(Float[] position, float radius, Color renderColor){
        this.position = position;
        this.radius = radius;
        this.renderColor = renderColor;

        defaultVert1[0] = position[0]+radius;
        defaultVert1[1] = position[1]+radius;

        defaultVert2[0] = position[0]+moveVec[0];
        defaultVert2[1] = position[1]+moveVec[1];

        defaultVert3[0] = position[0]-moveVec[0];
        defaultVert3[1] = position[1]+moveVec[1];
    }

    public void tickUpdate(){
        float sin0 = (float) Math.sin(rotationAngle);
        float cos0 = (float) Math.cos(rotationAngle);

        vert1[0] = defaultVert1[0]*cos0-defaultVert1[0]*sin0;
        vert1[1] = defaultVert1[1]*cos0+defaultVert1[0]*sin0;

        vert2[0] = defaultVert2[0]*cos0-defaultVert2[0]*sin0;
        vert2[1] = defaultVert2[1]*cos0+defaultVert2[0]*sin0;

        vert3[0] = defaultVert3[0]*cos0-defaultVert3[0]*sin0;
        vert3[1] = defaultVert3[1]*cos0+defaultVert3[0]*sin0;

    }


    public BufferedImage renderOnImage(BufferedImage image){
        Graphics2D g2d = image.createGraphics();
        g2d.setPaint(renderColor);
        g2d.fillPolygon(new int[]{Math.round(vert1[0]), Math.round(vert2[0]), Math.round(vert3[0])}, new int[]{Math.round(vert1[1]), Math.round(vert2[1]), Math.round(vert3[1])}, 3);
        return image;
    };


}
