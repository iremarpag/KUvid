package UI.ItemDisplays;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ShooterDisplay {
	BufferedImage image = null;
	Image tmp = null;
	public int x;
	public int y;
	public double angle;
	public int L;

	public ShooterDisplay(int L){
		try {
			image = ImageIO.read(new File("assets/shooter.png"));
		} catch (IOException e) {
		}
		tmp = image.getScaledInstance(L/2, L, Image.SCALE_SMOOTH);
		this.L=L;
	}


	public void paint(Graphics2D g) {
		AffineTransform at = AffineTransform.getTranslateInstance(x + L/4,y);
		g.setTransform(at);
		at.rotate(Math.toRadians(angle), x+L/4 ,y+L);
		g.setTransform(at);
		g.drawImage(tmp, x, y, null);
	}

	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}
	public void setAngle(double angle){
		this.angle = angle;
	}

	public double getAngle(){
		return this.angle;
	}

}
