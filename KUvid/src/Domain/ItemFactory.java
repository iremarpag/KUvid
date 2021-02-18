package Domain;

import Domain.Items.*;

import java.io.Serializable;
import java.util.Random;

public class ItemFactory implements Serializable {



    static Items createItem(Type type, int xPos, int yPos, int xVel, int yVel){
        Random rand = new Random();
        Items item;
        if(type.equals(Type.AlphaM)||type.equals(Type.BetaM)||type.equals(Type.GammaM)||type.equals(Type.SigmaM)){
            item = new Molecule(type,xPos,yPos,xVel,yVel);
        }else if(type.equals(Type.AlphaP)||type.equals(Type.BetaP)||type.equals(Type.GammaP)||type.equals(Type.SigmaP)){
            item = new Powerup(type,xPos,yPos,xVel,yVel);
        }else if(type.equals(Type.Alpha)||type.equals(Type.Beta)||type.equals(Type.Gamma)||type.equals(Type.Sigma)){
            item = new Atom(type,xPos,yPos,xVel,yVel);
        }else{
            item = new ReactionBlocker(type,xPos,yPos,xVel,yVel);
        }
        return item;
    }
}
