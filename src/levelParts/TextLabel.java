package levelParts;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TextLabel {
    public Float[] position;
    public String text = "";
    public Color renderColor;
    public TextLabel(Float[] position, Color renderColor){
        this.position = position;
        this.renderColor = renderColor;
    }

    public BufferedImage renderOnImage(BufferedImage image) {
        Graphics2D g2d = image.createGraphics();
        g2d.setPaint(renderColor);
        g2d.setFont(new Font("default", Font.PLAIN, 30));
        g2d.drawString(text, position[0], position[1]+30);
        g2d.dispose();
        return image;
    }
}
