package Domain;

import Domain.Items.*;
import Domain.Shooter.Shooter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game implements Observer, Serializable {
    private static final long serialVersionUID = 1L;
    double L;
    int atom;
    int molecule;
    int powerup;
    int reactionBlocker;
    private Shooter s;
    private Items current;
    private double score = 0;
    public double height;
    public double width;
    public ArrayList<Molecule> mols = new ArrayList<Molecule>();
    public Blender blender = new Blender();
    private ArrayList<Items> shootedObjects = new ArrayList<Items>();
    private int shootInd = -1;
    private ArrayList<Items> everything = new ArrayList<Items>();
    private int sentInd = -1;
    private int powInd = -1;
    private ArrayList<Items> atoms = new ArrayList<Items>();
    private ArrayList<Items> powerups = new ArrayList<Items>();
    Items dropping;
    ArrayList<Items> sent = new ArrayList<Items>();

    public void addObserver(Items item){
        this.shootedObjects.add(item);
    }

    public void removeObserver(Items item){
        this.shootedObjects.remove(item);
    }

    public HashMap<Type,Integer> fallin = new HashMap<Type,Integer>();
    public HashMap<Type,Integer> inventory = new HashMap<Type,Integer>();

    Type[] t = {Type.Alpha, Type.Sigma, Type.Beta, Type.Gamma};
    
    public double xPos = width/2-L/2;
    public double yPos = height-L;

    Random rand = new Random();

    public Shooter initShooter(){
        //EFFECTS:  initializes the


        Shooter s = Shooter.getInstance(xPos, yPos, L);
        return s;
    }


    public Game(int atom, int molecule, int powerup, int reactionBlocker, double L, boolean isNormal) {
        this.L = L;
        this.atom = atom;
        this.molecule = molecule;
        this.powerup = powerup;
        this.reactionBlocker = reactionBlocker;
        createAtoms(atom);
        createAll(molecule,powerup,reactionBlocker);
        initMap();
        s = initShooter();
        current = s.getItem();
    }

    public void createAll(int m, int p, int b){
        //EFFECTS: creates m number of molecules of each type
        //         creates p number of powerups of each type
        //         creates b number of reaction blockers of each type
        //         adds the created items to the ArrayList everything

        for (int i=0; i<m; i++){
            everything.add(ItemFactory.createItem(Type.GammaM,0,0,0,0));
            everything.add(ItemFactory.createItem(Type.SigmaM,0,0,0,0));
            everything.add(ItemFactory.createItem(Type.AlphaM,0,0,0,0));
            everything.add(ItemFactory.createItem(Type.BetaM,0,0,0,0));
        }
        for (int i=0; i<p; i++){
            everything.add(ItemFactory.createItem(Type.GammaP,0,0,0,0));
            everything.add(ItemFactory.createItem(Type.SigmaP,0,0,0,0));
            everything.add(ItemFactory.createItem(Type.AlphaP,0,0,0,0));
            everything.add(ItemFactory.createItem(Type.BetaP,0,0,0,0));
        }
        for (int i=0; i<b; i++){
            everything.add(ItemFactory.createItem(Type.GammaB,0,0,0,0));
            everything.add(ItemFactory.createItem(Type.SigmaB,0,0,0,0));
            everything.add(ItemFactory.createItem(Type.AlphaB,0,0,0,0));
            everything.add(ItemFactory.createItem(Type.BetaB,0,0,0,0));
        }
    }

    public void createAtoms(int atom){
        //EFFECTS: creates atom number of atoms of each type
        //         adds the atoms created to the ArrayList atoms
        for (int i=0; i<atom; i++){
            atoms.add(ItemFactory.createItem(Type.Gamma,0,0,0,0));
            atoms.add(ItemFactory.createItem(Type.Sigma,0,0,0,0));
            atoms.add(ItemFactory.createItem(Type.Alpha,0,0,0,0));
            atoms.add(ItemFactory.createItem(Type.Beta,0,0,0,0));
        }
    }

    public void removeAtom(Type type){
        for(int i=0; i < atoms.size(); i++){
            if(atoms.get(i).getType().equals(type)){
                atoms.remove(i);
                return;
            }
        }
    }

    public void initMap(){


        fallin.put(Type.AlphaM,molecule);
        fallin.put(Type.BetaM,molecule);
        fallin.put(Type.SigmaM,molecule);
        fallin.put(Type.GammaM,molecule);
        fallin.put(Type.AlphaB,reactionBlocker);
        fallin.put(Type.BetaB,reactionBlocker);
        fallin.put(Type.GammaB,reactionBlocker);
        fallin.put(Type.SigmaB,reactionBlocker);
        fallin.put(Type.AlphaP,powerup);
        fallin.put(Type.BetaP,powerup);
        fallin.put(Type.GammaP,powerup);
        fallin.put(Type.SigmaP,powerup);


        inventory.put(Type.AlphaP,0);
        inventory.put(Type.BetaP,0);
        inventory.put(Type.GammaP,0);
        inventory.put(Type.SigmaP,0);
        inventory.put(Type.Alpha,atom);
        inventory.put(Type.Beta,atom);
        inventory.put(Type.Gamma,atom);
        inventory.put(Type.Sigma,atom);

    }

    public int getCount(Type type) {
        return inventory.get(type);
    }

    public void setCount(Type type, String s) { 
    	if (s.equals("+"))
    		inventory.replace(type, inventory.get(type) + 1 );
    	else if (s.equals("-"))
    		inventory.replace(type, inventory.get(type) - 1 );
    }

    public int getFallin(Type type){
        return fallin.get(type);
    }


    public void openBlender(){
        blender.openBlender();
    }
    public void blendAtoms(int a, int b){
        //EFFECTS: If a and b are not between 1 and 4 closes blender and terminates.
        //         If a and b are equal closes blender and terminates.
        //         If we don't have enough atoms of type a closes and terminates.
        //         If a>b breaks one atom of type a and creates |a-b|+1 atoms of type b
        //         If a<b blends |a-b|+1 atoms of type a into one atom of type b


        if(a==b){
            blender.closeBlender();
            return;
        }
        Type typeA;
        Type typeB;

        switch (a){
            case 1:
                typeA = Type.Alpha;
                break;
            case 2:
                typeA = Type.Beta;
                break;
            case 3:
                typeA = Type.Gamma;
                break;
            case 4:
                typeA = Type.Sigma;
                break;
            default:
                blender.closeBlender();
                return;
        }
        switch (b){
            case 1:
                typeB = Type.Alpha;
                break;
            case 2:
                typeB = Type.Beta;
                break;
            case 3:
                typeB = Type.Gamma;
                break;
            case 4:
                typeB = Type.Sigma;
                break;
            default:
                blender.closeBlender();
                return;
        }



        int change = blender.blend(a,b);
        int amountA = inventory.get(typeA);
        int amountB = inventory.get(typeB);
        if(a>b){                //if breaking
            if(amountA > 0) {
                inventory.put(typeA, amountA - 1);
                removeAtom(typeA);
                inventory.put(typeB, amountB + change);
                for(int i=0; i<change; i++){
                    atoms.add(ItemFactory.createItem(typeB,0,0,0,0));
                }
            }
        }else if(b>a){          //if blending
            if(amountA > change){
                inventory.put(typeA, amountA - change);
                for(int i=0; i<change;i++){
                    removeAtom(typeA);
                }
                inventory.put(typeB, amountB + 1);
                atoms.add(ItemFactory.createItem(typeB,0,0,0,0));
            }
        }



        blender.closeBlender();
    }

    public double getL(){
        return this.L;
    }


    public void loseHealth(double dist) {
        double p = width / dist;
        s.decreaseHealth(p);
    }

    public void updateScore(Items atom){
        score += atom.getEfficiency();
        System.out.println(score);
    }

    
    public int shoot() {
    	if (!s.isLoaded())
    		return -1;
    	s.shoot();
    	current.setXVelocity(Math.sin(Math.toRadians(s.getAnglePos())));
    	current.setYVelocity(-Math.cos(Math.toRadians(s.getAnglePos())));
        shootedObjects.add(current);
        atoms.remove(current);
        if(atoms.isEmpty()){
        	s.notLoaded();
        }

    	return changeAtom();
    }
    public int changeAtom(){
        int index = rand.nextInt(atoms.size());
        s.changeAtom(atoms.get(index));
        current = s.getItem();
        Type typ = current.getType();
        return typ.ordinal();
    }

    public int drop(){
        if(everything.isEmpty()){
            return -1;
        }
        int index = rand.nextInt(everything.size());
        dropping = everything.remove(index);
        dropping.startMoving();
        dropping.setYPos(0);
        dropping.setXPos(rand.nextInt((int)(width-L)));
        Type typ = dropping.getType();
        sent.add(dropping);
        return typ.ordinal();
    }



    public void moveMol(){
        for (Items item : sent) {

            if(item.getXPos() < 0){
                item.setXPos(0);
                item.setXVelocity(-item.getXVelocity());
            }else if(item.getXPos() > width-L/5){
                item.setXPos(width-L/5);
                item.setXVelocity(-item.getXVelocity());
            }
            else if(item.getYPos()<0){

            }
            if(item.getType() == Type.BetaM || item.getType() == Type.BetaB){
                if(item.getYPos() == height/4){
                    item.setXVelocity(1);
                }
            }else if(item.getType() == Type.GammaM ||item.getType() == Type.GammaB){
                if(item.getYPos() == height/2){
                    item.setXVelocity(1);
                }
            }

            item.move();
        }
    }

    public void moveItem() {
    	for(Items item:shootedObjects){
        if(item.getXPos() < 0){
    	    item.setXPos(0);
    	    item.setXVelocity(-item.getXVelocity());
        }else if(item.getXPos() > width-L/5){
            item.setXPos(width-L/5);
            item.setXVelocity(-item.getXVelocity());
        }
    	else if(item.getYPos()<0){

        }
    	item.getXVelocity();
    	item.move();
    }
    }


    public double rotateShooter(String input) {
            double angle;
    	if (input.equals("cw")) {
            angle = s.moveAngular("cw");
            return angle;
    	}
    	else {

    	    angle = s.moveAngular("ccw");
    	    return angle;
    	}

    }
    public double moveShooter(String input) { // sınırı geçip geçmediğini kontrol etmiyor
        /*REQUIREMENTS: input must be "left" or "right"
		 EFFECTS: if input is different than "left" and "right"
		 		  throws IllegalArgumentException
		          else returns moveLinear method
		*/
		 if (input != "left" && input != "right") {
			 throw new IllegalArgumentException("input is invalid, input must be 'left' or 'right'");
		 }

	        return s.moveLinear((input));

    }

    public boolean checkGroundTouch(Items item){
        if(item.getYPos() == s.getYPos()+L){
            return true;
        }
        return false;
    }
    /*public void eraseItem(Items item){
        if (checkGroundTouch(item)) {
            item.setYPos(2000);
            item.setXPos(2000);
            item.setXVelocity(0);
            item.setYVelocity(0);
        }
        react(item);
    }*/

    public void addShield(String shield){

        switch (shield){
            case "j":
                EtaShield eta = new EtaShield(current);

                atoms.remove(current);
                atoms.add(eta);
                break;
            case "u":
                LotaShield lota = new LotaShield(current);
                atoms.remove(current);
                atoms.add(lota);
                break;
            case "y":
                ThetaShield theta = new ThetaShield(current);
                atoms.remove(current);
                atoms.add(theta);
                break;
            case "h":
                ZetaShield zeta = new ZetaShield(current);
                atoms.remove(current);
                atoms.add(zeta);
                break;

        }
    }



    public void react(Items item){
        if(checkGroundTouch(item)){
            for(Items i:sent){
                if(hitBoxCheck(i, item,L/2, (2*L-(L/2))) || hitBoxCheck(i, item,L/2, (-2*L+(L/2)))){
                    i.setYVelocity(0);
                    i.setXVelocity(0);
                    i.setYPos(-500);
                }
            }
            for(Items a:shootedObjects){
                if(hitBoxCheck(a, item,L/5, (2*L-(L/2)))|| hitBoxCheck(a, item,L/2, (-2*L+(L/2)))){
                    a.setYVelocity(0);
                    a.setXVelocity(0);
                    a.setYPos(-500);

                }
            }
            if((item.getXPos()+2*L) >= s.getXPos() || (item.getXPos()-2*L) >= s.getXPos()+L/5){
                loseHealth(Math.abs(item.getXPos() - s.getXPos()));

            }

        }
    }



    public boolean interaction(Items a, Items b) {
        if ((a.getType().ordinal() + 12) == b.getType().ordinal()) {
            if (hitBoxCheck(a, b, L/5,L/2)) {
                return true;
            }
        }
            return false;
    }

    public boolean hitBoxCheck(Items a, Items b, double sizeA, double sizeB){
        if(a.getXPos()<=(b.getXPos()+sizeB) && a.getXPos()>=(b.getXPos()) && a.getYPos()<=(b.getYPos()+sizeB) && a.getYPos()>=(b.getYPos())){
            return true;
        }if((a.getXPos()+sizeA)<=(b.getXPos()+sizeB) && (a.getXPos()+sizeA)>=(b.getXPos()) && a.getYPos()<=(b.getYPos()+sizeB) && a.getYPos()>=(b.getYPos())){
            return true;
        }if(a.getXPos()<=(b.getXPos()+sizeB) && a.getXPos()>=(b.getXPos()) && (a.getYPos()+sizeA)<=(b.getYPos()+sizeB) && (a.getYPos()+sizeA)>=(b.getYPos())){
            return true;
        }if((a.getXPos()+sizeA)<=(b.getXPos()+sizeB) && (a.getXPos()+sizeA)>=(b.getXPos()) && (a.getYPos()+sizeA)<=(b.getYPos()+sizeB) && (a.getYPos()+sizeA)>=(b.getYPos())){
            return true;
        }
        return false;
    }

    public void catchPowerup(Items pow){
        Type typ = pow.getType();
        if((s.getXPos() >= pow.getXPos() && s.getXPos() <= pow.getXPos()) ||(pow.getXPos() >= s.getXPos() && pow.getXPos() <= s.getXPos() + L/2)){
            powerups.add(ItemFactory.createItem(typ,0,0,0,0));
            inventory.put(typ, 1);
            powInd = sent.indexOf(pow);
            pow.setYPos(-500);
            System.out.println("caught");
        }

    }

   /*TO BE USED BELOW */
    public void checkInteraction() {

    	for (Items d:sent){
    	    //If the interaction is between an atom and molecule
            if (d.getType() == Type.AlphaB || d.getType() == Type.BetaB || d.getType() == Type.SigmaB || d.getType() == Type.GammaB) {
                react(d);
            }
            if (d.getType().ordinal()>3 && d.getType().ordinal()<8 && d.getYPos()+L/2 > s.getYPos()){
                catchPowerup(d);

            }
            if (d.getType() == Type.AlphaM || d.getType() == Type.BetaM || d.getType() == Type.SigmaM || d.getType() == Type.GammaM){

    	        for (Items a:shootedObjects){

    	            if(interaction(a,d)){
    	                Type aType = a.getType();
    	                if(aType.ordinal() > 3){
    	                    return;
                        }
                        updateScore(a);
    	                for (int i = 0; i <aType.ordinal()+3 ; i++) {
    	                    atoms.add(ItemFactory.createItem(aType,0,0,0,0));
    	                }
    	                inventory.put(aType, aType.ordinal()+3);

    	                d.setYPos(-500);
    	                a.setYPos(-500);
    	                d.setYVelocity(0);
    	                d.setXVelocity(0);
                        shootInd = shootedObjects.indexOf(a);
                        sentInd = sent.indexOf(d);
                    }
                }
            }
        }
    }

    public int removePow(){
        int index = powInd;
        if(powInd != -1){
            sent.remove(powInd);
            powInd = -1;
        }
        return index;
    }

    public int removeShooted(){
        int index = shootInd;
        if(shootInd != -1){
            shootedObjects.remove(shootInd);
            shootInd = -1;
        }
        return index;
    }
    public int removeSent(){
        int index = sentInd;
        if(sentInd != -1){
            sent.remove(sentInd);
            sentInd = -1;
        }
        return index;
    }

    public Shooter getShooter() {
    	return s;
    }
    public Items getAtom() {
    	return current;
    }



    public void initializeGameSet(int height, int width){
        /*REQUIREMENTS: heigth and width must be greater than 0
	 	 *EFFECT: if height <= 0 or width <= 0 throws IllegalArgumentException
	 	 *else initializes height and width with the given input.
	 	 *randomly setting the atom of shooter.
	 	 *setting shooters position according to the height and width of the window.
	 	 *and finally setting current items position 
	 	 */
	 	if (height <= 0) 
	 		throw new IllegalArgumentException("height must be greater than 0");
	 	if (width <= 0) 
	 		throw new IllegalArgumentException("width must be greater than 0");
      	
	 	this.height = height;
        this.width = width;
        
        changeAtom();
        s.setYPosition(height-L);
        s.setXPosition(width/2 - L/4);
        current.setYPos(height-6*L / 5);
        current.setXPos(width/2 - L/12);

    }



    public double getShooterX(){
        return s.getXPos();

    }
    public double getShooterY(){
        return s.getYPos();
    }

    public double getItemX(){
        return current.getXPos();
    }

    public double getItemY(){
        return current.getYPos();
    }

    public ArrayList<Items> getShootedObjects() {
        return shootedObjects;
    }
    public ArrayList<Items> getSent(){
        return sent;
    }
    
    public void update(double x, double y) {
    	current.setXPos(x);
    	current.setYPos(y);
    }


    public double getDroppedX(){
        return dropping.getXPos();
    }
    public double getDroppedY(){
        return dropping.getYPos();
    }

    public double getScore() {
        return score;
    }

    public double getHealth() {
        return s.getHealthPoints();
    }
}
