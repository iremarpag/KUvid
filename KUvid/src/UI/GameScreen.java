package UI;


import javax.swing.*;
import java.awt.*;
import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;

public class GameScreen implements Runnable{
    public boolean load  = false;
    int x = 1000;
    int y = 800;
    int atom;
    int molecule;
    int powerup;
    int blocker;
    int L;

    boolean isNormal;
    boolean isSpinning;

    JFrame f = new JFrame();
    GameEngine gamePlay;

    public GameScreen() {

    }


    @Override
    public void run() {

        f.setBounds(0, 0, x , y );
        f.setResizable(false);


        gamePlay = new GameEngine(atom,molecule,powerup,blocker,L,y,x,isNormal,isSpinning);
        gamePlay.setHeight(y);
        gamePlay.setWidth(x);
        //gamePlay.setL(L);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(gamePlay,BorderLayout.CENTER);
        f.setVisible(true);
        if(load){
            gamePlay.setLoad(true);
        }
        gamePlay.timer.start();


    }
    public int getAtom() {
        return atom;
    }

    public int getMolecule() {
        return molecule;
    }

    public int getBlocker() {
        return blocker;
    }

    public int getPowerup() {
        return powerup;
    }

    public void setAtom(int atom) { this.atom = atom;
    }
    public void setNormal(boolean bool){this.isNormal = bool;}
    public void setSpinning(boolean bool){this.isSpinning = bool;}
    public void setMolecule(int molecule) {
        this.molecule = molecule;
    }

    public void setBlocker(int blocker) {
        this.blocker = blocker;
    }

    public void setPowerup(int powerup) {
        this.powerup = powerup;
    }

    public int getL() {
        return L;
    }

    public void setL(int L) {
        this.L = y * L/100;
    }
    public GameEngine getGamePlay(){
        return gamePlay;
    }

}

