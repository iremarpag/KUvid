package UI.ItemDisplays;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class BlockersDisplay implements ItemsDisplay{
	public int x;
	public int y;
	public BufferedImage image;
	public int L;
	public double angle;
	Image tmp;

	public BlockersDisplay(int L, int type){
		switch (type){
			case 8:{
				try {
					image = ImageIO.read(new File("assets/blockers/alpha-b.png"));
				} catch (IOException e) {
				}
				break;
			}
			case 9:{
				try {
					image = ImageIO.read(new File("assets/blockers/beta-b.png"));
				} catch (IOException e) {
				}
				break;
			}
			case 10:{
				try {
					image = ImageIO.read(new File("assets/blockers/gamma-b.png"));
				} catch (IOException e) {
				}
				break;
			}
			case 11:{
				try {
					image = ImageIO.read(new File("assets/blockers/sigma-b.png"));
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
