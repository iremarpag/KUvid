package UI.ItemDisplays;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class MoleculesDisplay implements ItemsDisplay {
	public int x;
	public int y;
	public BufferedImage image;
	public int L;
	public double angle = 10;
	private boolean isSpinning = false;
	Image tmp;

	
	public MoleculesDisplay(int L, int type, boolean isNormal, boolean isSpinning){

		switch (type){
			case 12:{
				try {
					if(isNormal){
						image = ImageIO.read(new File("assets/molecules/alpha-1.png"));
					}else{
						image = ImageIO.read(new File("assets/molecules/alpha-2.png"));
						this.isSpinning = isSpinning;
					}

				} catch (IOException e) {
				}
				break;
			}
			case 13:{
				try {
					if(isNormal){
						image = ImageIO.read(new File("assets/molecules/beta-1.png"));
					}else{
						image = ImageIO.read(new File("assets/molecules/beta-2.png"));
						this.isSpinning = isSpinning;
					}
				} catch (IOException e) {
				}
				break;
			}
			case 14:{
				try {
					image = ImageIO.read(new File("assets/molecules/gamma-.png"));
				} catch (IOException e) {
				}
				break;
			}
			case 15:{
				try {
					image = ImageIO.read(new File("assets/molecules/sigma-.png"));
				} catch (IOException e) {
				}
				break;
			}
		}

		tmp = image.getScaledInstance(L/2, L/2, Image.SCALE_SMOOTH);
		this.L =  L;
	}

	public void paint(Graphics2D g) {
		if(isSpinning){
			AffineTransform at = AffineTransform.getTranslateInstance(x+L/4 ,y);
			g.setTransform(at);
			at.rotate(Math.toRadians(angle), x+L/4 ,y+L/4);
			g.setTransform(at);
			g.drawImage(tmp, x, y, null);
			angle += 1;
		}else{
			g.drawImage(tmp, x, y, null);
		}



	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
}
