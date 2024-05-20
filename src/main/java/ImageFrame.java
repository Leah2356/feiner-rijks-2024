import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class ImageFrame extends JFrame {
    public ImageFrame(String path) throws Exception {
        setTitle("ImageFrame");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        URL url = new URL(path);
        BufferedImage image = ImageIO.read(url);
        Image scaledImage = image.getScaledInstance(-1, 800, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaledImage));

        JScrollPane scrollPane = new JScrollPane(label);
        scrollPane.setPreferredSize(new Dimension(800, 600));
        add(scrollPane, BorderLayout.CENTER);
    }
}