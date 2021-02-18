package UI.ItemDisplays;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class PowerupsDisplay implements ItemsDisplay {
	public int x;
	public int y;
	public BufferedImage image;
	public int L;
	public int angle;
	Image tmp;


	public PowerupsDisplay(int L, int type){
		switch (type){
			case 4:{
				try {
					image = ImageIO.read(new File("assets/powerups/+alpha-b.png"));
				} catch (IOException e) {
				}
				break;
			}
			case 5:{
				try {
					image = ImageIO.read(new File("assets/powerups/+beta-b.png"));
				} catch (IOException e) {
				}
				break;
			}
			case 6:{
				try {
					image = ImageIO.read(new File("assets/powerups/+gamma-b.png"));
				} catch (IOException e) {
				}
				break;
			}
			case 7:{
				try {
					image = ImageIO.read(new File("assets/powerups/+sigma-b.png"));
				} catch (IOException e) {
				}
				break;
			}
		}

		tmp = image.getScaledInstance(L/2, L/2, Image.SCALE_SMOOTH);
		this.L =  L;
	}
	public void paint(Graphics2D g) {

		g.drawImage(tmp, x,y, null);

	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
}
