package UI.ItemDisplays;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class AtomsDisplay implements ItemsDisplay{
	public int x;
	public int y;
	public BufferedImage image;
	public double L;
	public double angle;
	Image tmp;

	public AtomsDisplay(double L, int type){
		switch (type){
			case 0:{
				try {
					image = ImageIO.read(new File("assets/atoms/alpha.png"));
				} catch (IOException e) {
				}
				break;
			}
			case 1:{
				try {
					image = ImageIO.read(new File("assets/atoms/beta.png"));
				} catch (IOException e) {
				}
				break;
			}
			case 2:{
				try {
					image = ImageIO.read(new File("assets/atoms/gamma.png"));
				} catch (IOException e) {
				}
				break;
			}
			case 3:{
				try {
					image = ImageIO.read(new File("assets/atoms/sigma.png"));
				} catch (IOException e) {
				}
				break;
			}
		}

		tmp = image.getScaledInstance((int)L/4, (int)L/4, Image.SCALE_SMOOTH);
		this.L =  L;
	}
	public void paint(Graphics2D g) {
		g.drawImage(tmp, (int)x, (int)y, null);
	}

	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}

	public void setAngle(double angle){
		this.angle = angle;
	}



}
