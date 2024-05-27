package feiner.rijks;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class ImageFrame extends JFrame {
    public ImageFrame(String title, String artist, String path) throws Exception {
        setTitle(title + " by " + artist);
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        URL url = new URL(path);
        BufferedImage image = ImageIO.read(url);
        Image scaledImage = image.getScaledInstance(800, -1, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaledImage));

        JScrollPane scrollPane = new JScrollPane(label);
        add(scrollPane, BorderLayout.CENTER);
    }
}