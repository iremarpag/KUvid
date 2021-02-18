package UI.ItemDisplays;

import java.awt.*;

public interface ItemsDisplay {
    int x = 0;
    int y = 0;
    int L = 0;
    public void paint(Graphics2D g);

    public void setX(int x);
    public void setY(int y);
}
