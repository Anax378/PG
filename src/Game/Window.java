package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Window {
    public JFrame frame;
    public JPanel panel;
    public JLabel label;

    public BufferedImage image;
    public Icon icon;

    public int width;
    public int height;

    public volatile boolean isMouseDown;
    public volatile boolean isSpaceDown;

    public volatile int mouseClickCount = 0;

    public Window(int width, int height){
        this.width = width;
        this.height = height;

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        icon = new ImageIcon(image);

        panel = new JPanel();
        frame = new JFrame();
        label = new JLabel(icon);

        panel.add(label);
        frame.add(panel);

        frame.setSize(width, height+40);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){isMouseDown = true;mouseClickCount++;}
            public void mouseReleased(MouseEvent e){isMouseDown = false;}

        });

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){if(e.getKeyCode() == KeyEvent.VK_SPACE){isSpaceDown = true;}}
            public void keyReleased(KeyEvent e){if(e.getKeyCode() == KeyEvent.VK_SPACE){isSpaceDown = false;}}
        });

    }




    public void setImage(BufferedImage image){
        icon = new ImageIcon(image);
        label.setIcon(icon);
        SwingUtilities.updateComponentTreeUI(frame);
    }


}

