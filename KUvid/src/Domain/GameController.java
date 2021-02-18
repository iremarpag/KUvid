package Domain;
import Domain.Items.Items;
import Domain.Items.Type;
import Domain.Storage.ResourceManager;

import java.util.ArrayList;

public class GameController {
    Game game;
    boolean load;

    public GameController(int atom, int molecule, int powerup, int blocker, double L, boolean isNormal){
        game = new Game(atom,molecule,powerup,blocker,L,isNormal);
    }


    public double moveShooter(String input) {
        return game.moveShooter(input);
    }

    public int shoot() {
       return game.shoot();
    }
    public void moveItem(){
        game.moveItem();
    }

    public double rotateShooter(String d) {
       return game.rotateShooter(d);
    }

    public void initializeGameSet(int height, int width){ game.initializeGameSet(height, width);  }

    public double getShooterX(){ return game.getShooterX(); }
    public double getShooterY(){ return game.getShooterY(); }
    public double getItemX(){return game.getItemX();}
    public double getItemY(){return game.getItemY();}
    public void openBlender(){game.openBlender();}
    public boolean checkBlender(){return game.blender.open();}
    public void blendAtoms(int a, int b){game.blendAtoms(a,b);}
    public ArrayList<Items> getShootedObjects(){
        return game.getShootedObjects();
    }
    public int removeShooted(){return game.removeShooted();}
    public ArrayList<Items> getSent(){
        return game.getSent();
    }
    public int removeSent(){
        return game.removeSent();
    }
    public int removePow(){
        return game.removePow();
    }
    public double getL(){
        return game.getL();
    }

    public int getCount(Type type){
        return game.getCount(type);
    }

    public int getFallin(Type type){
        return game.getFallin(type);
    }

    public int drop(){ return game.drop();}
    public void moveMol(){game.moveMol();}

    public int changeAtom(){return game.changeAtom();}
    
    public void update(int xPos, int yPos) {
    	game.update(xPos,yPos);
    }

    public double getDroppedX(){return game.getDroppedX();}
    public double getDroppedY(){return game.getDroppedY();}
    public void checkInteraction(){game.checkInteraction();}
    public void save(String s) throws Exception {
        ResourceManager r = new ResourceManager();
        r.save(game, s);
    }
    public void setLoad() throws Exception {
        if (load){
            load("save.txt");
        }
    }
    public void load(String s) throws Exception {
        ResourceManager r = new ResourceManager();
        game = (Game) r.load(game, s);
    }

    public void addShield(String shield){game.addShield(shield);}

}
