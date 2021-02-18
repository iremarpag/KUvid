package UI.ItemDisplays;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class PlaygroundDisplay {
    int h;
    int w;

    BufferedImage image;
    Image tmp;
    public PlaygroundDisplay(int L,int h,int w) {
        try {
            image = ImageIO.read(new File("assets/kuvid_bc.png"));
        } catch (IOException e) {
        }
        this.h = h;
        this.w = w;
        tmp = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);

    }

    public void paint(Graphics g) {

        g.drawImage(tmp, 0,0, null);

    }

}
