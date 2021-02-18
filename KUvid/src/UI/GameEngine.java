package UI;

import Domain.GameController;
import UI.ItemDisplays.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.*;


public class GameEngine extends JPanel implements KeyListener, ActionListener {

	public Timer timer;
	private int delay=10;

	public static int atom;
	public static int molecule;
	public static int blocker;
	public static int powerup;
	private static double L;
	private static boolean isNormal;
	private static boolean isSpinning;
	private double height;
	private double width;
	private int rank1 = 0;
	private int rank2 = 0;
	public GameController game;
	private JLabel health = new JLabel();
	private JLabel score = new JLabel();
	private PlaygroundDisplay playground;
	private AtomsDisplay item, a;
	private MoleculesDisplay falling;
	private PowerupsDisplay flying;
	private BlockersDisplay dropping;
	private ShooterDisplay shooter;
	private ArrayList<AtomsDisplay> shooted = new ArrayList<>();
	private ArrayList<ItemsDisplay> sent = new ArrayList<>();
	private boolean load;
	//private AtomsDisplay bullet;
	private JPanel info = new JPanel();


	public GameEngine(int atom, int molecule, int powerup, int blocker, double L, int height, int width, boolean isNormal, boolean isSpinning) {
		game=new GameController(atom,molecule,powerup,blocker,L,isNormal);
		this.height = height;
		this.width = width;
		this.L = L;
		this.isNormal = isNormal;
		this.isSpinning = isSpinning;
		game.initializeGameSet(height,width);

		health.setText("100.0");
		score.setText("0.0");



		shooter = new ShooterDisplay((int)L);
		shooter.setX((int)game.getShooterX());

		shooter.setY((int)game.getShooterY());
		item = new AtomsDisplay(L,0);
		item.setX((int)game.getItemX());
		item.setY((int)game.getItemY());
		falling = new MoleculesDisplay((int)L,12, isNormal, isSpinning);
		falling.setY(-100);
		flying = new PowerupsDisplay((int)L,5);
		flying.setY(-100);
		dropping = new BlockersDisplay((int)L,8);
		dropping.setY(-100);
		playground = new PlaygroundDisplay((int)L,height,width);

		//setSize(300,300);  
		setLayout(null);  
		setVisible(true);

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);


		timer=new Timer(delay,this);


	}

	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g.create();



		playground.paint(g2);
		health.setVisible(true);

		score.setVisible(true);
		info.add(score);
		info.add(health);
		info.setVisible(true);
		info.setLocation((int)width/2,(int)height/2);
		info.transferFocusUpCycle();
		item.paint(g2); //drawItem(string type)

		dropping.paint(g2);
		flying.paint(g2);

		for (AtomsDisplay bullet:shooted){
			bullet.paint(g2);
		}



		for (ItemsDisplay items:sent){
			items.paint(g2);
		}

		falling.paint(g2);
		shooter.paint(g2);
		g2.dispose();
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub		
		game.moveMol();
		game.moveItem();
		game.checkInteraction();

		if(load){
			try {
				game.setLoad();
				load = false;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}


		for (int i = 0; i<game.getShootedObjects().size(); i++){
			shooted.get(i).setX((int)game.getShootedObjects().get(i).getXPos());
			shooted.get(i).setY((int)game.getShootedObjects().get(i).getYPos());
		}
		for (int i = 0; i < game.getSent().size(); i++){
			sent.get(i).setX((int)game.getSent().get(i).getXPos());
			sent.get(i).setY((int)game.getSent().get(i).getYPos());
		}

		int index = game.removeShooted();

		if(index != -1) {
			shooted.remove(index);
			index = -1;
		}

		index = game.removeSent();

		if(index != -1){
			sent.remove(index);
			index = -1;
		}

		index = game.removePow();

		if(index != -1){
			sent.remove(index);
			index = -1;
		}
		repaint();
	}



	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT){
			shooter.setX((int)game.moveShooter("right"));
			item.setX((int)game.getItemX());

		}
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT){          
			shooter.setX((int)game.moveShooter("left"));
			item.setX((int)game.getItemX());
		}
		if(arg0.getKeyCode()==KeyEvent.VK_UP) {
			a = item;
			shooted.add(a);
			int t = game.shoot();
			item = new AtomsDisplay(L,t);
			item.setX((int)game.getItemX());
			item.setY((int)game.getItemY());
		}
		if(arg0.getKeyCode()==KeyEvent.VK_A) {
			double angle = game.rotateShooter("ccw");
			shooter.setAngle(angle);
			item.setAngle(angle);


			item.setX((int)game.getItemX());

		}

		if(arg0.getKeyCode()==KeyEvent.VK_D) {
			shooter.setAngle(game.rotateShooter("cw"));
			item.setX((int)game.getItemX());

			 }
		if(arg0.getKeyCode()==KeyEvent.VK_J){
			game.addShield("j");
		}
		if(arg0.getKeyCode()==KeyEvent.VK_U){
			game.addShield("u");
		}
		if(arg0.getKeyCode()==KeyEvent.VK_Y){
			game.addShield("y");
		}
		if(arg0.getKeyCode()==KeyEvent.VK_H){
			game.addShield("h");
		}

		if(arg0.getKeyCode()==KeyEvent.VK_B) {	
			game.openBlender();

		}

		if (game.checkBlender() && (rank1 != 0) && (arg0.getKeyCode()==KeyEvent.VK_1 || arg0.getKeyCode()==KeyEvent.VK_2 || arg0.getKeyCode()==KeyEvent.VK_3 || arg0.getKeyCode()==KeyEvent.VK_4)) {
			if((int)arg0.getKeyChar() == 49){
				rank2 = 1;
			}else if((int)arg0.getKeyChar() == 50){
				rank2 = 2;
			}else if((int)arg0.getKeyChar() == 51){
				rank2 = 3;
			}else if((int)arg0.getKeyChar() == 52){
				rank2 = 4;
			}
			game.blendAtoms(rank1,rank2);
			rank1 = 0;
			rank2 = 0;
		}

		if (game.checkBlender() && (rank1 == 0) && (arg0.getKeyCode()==KeyEvent.VK_1 || arg0.getKeyCode()==KeyEvent.VK_2 || arg0.getKeyCode()==KeyEvent.VK_3 || arg0.getKeyCode()==KeyEvent.VK_4)) {

			if((int)arg0.getKeyChar() == 49){
				rank1 = 1;
			}else if((int)arg0.getKeyChar() == 50){
				rank1 = 2;
			}else if((int)arg0.getKeyChar() == 51){
				rank1 = 3;
			}else if((int)arg0.getKeyChar() == 52){
				rank1 = 4;
			}
		}



		if(arg0.getKeyCode()==KeyEvent.VK_C) {
			int t = game.changeAtom();
			item = new AtomsDisplay(L,t);
			item.setX((int)game.getItemX());
			item.setY((int)game.getItemY());
		}

		if(arg0.getKeyCode()==KeyEvent.VK_M) {
			int t = game.drop();
			if(t>11){
				falling = new MoleculesDisplay((int)L,t, isNormal, isSpinning);
				falling.setX((int)game.getDroppedX());
				falling.setY((int)game.getDroppedY());
				sent.add(falling);
			}else if(t>7){
				dropping = new BlockersDisplay((int)L,t);
				dropping.setX((int)game.getDroppedX());
				dropping.setY((int)game.getDroppedY());
				sent.add(dropping);
			}else if(t>3){
				flying = new PowerupsDisplay((int)L,t);
				flying.setX((int)game.getDroppedX());
				flying.setY((int)game.getDroppedY());
				sent.add(flying);
			}
		}
		if(arg0.getKeyCode()==KeyEvent.VK_S){
			try {
				game.save("save.txt");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if(arg0.getKeyCode()==KeyEvent.VK_P) {
			timer.stop();
		}
		if(arg0.getKeyCode()==KeyEvent.VK_R) {
			timer.start();
		}



	}


	public void mouseClicked(MouseEvent e) {  
		//powerup değişiyor
	}  

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}



	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}



	public static int getAtom() {
		return atom;
	}

	public static int getMolecule() {
		return molecule;
	}

	public static int getBlocker() {
		return blocker;
	}

	public static int getPowerup() {
		return powerup;
	}

	public static void setAtom(int atom) {
		GameEngine.atom = atom;
	}

	public static void setMolecule(int molecule) {
		GameEngine.molecule = molecule;
	}

	public static void setBlocker(int blocker) {
		GameEngine.blocker = blocker;
	}

	public static void setPowerup(int powerup) {
		GameEngine.powerup = powerup;
	}

	public static int getL() {
		return atom;
	}

	public static void setL(int L) {
		GameEngine.L = L;
	}


	public void setHeight(int y) {
		this.height = y;
	}
	public void setWidth(int x) {
		this.width = x;
	}

	public void setLoad(boolean load){
		this.load = load;
	}
	public boolean getLoad(){
		return load;
	}
}
