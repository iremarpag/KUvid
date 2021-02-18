package Domain.Shooter;


import Domain.Game;
import Domain.Items.*;
import UI.GameScreen;


import java.io.Serializable;
import java.util.Random;


public class Shooter implements Serializable {


    private double width;
    private double height;
    private double xPos = 0;  // leftmost of the shooter.
    private double yPos = 0; // downmost of the shooter.
    private double anglePos = 0;
    private double horizontalSpeed = 10; //= L / sec;
    private double rotationSpeed; // = 90 degrees / sec;
    private double healthPoints = 100;
    private Items item ; //{0: 'alpha', 1: 'sigma', 2: 'beta', 3: 'gamma'}
    private boolean loaded = false;


    private static Type[] type = {Type.Alpha, Type.Sigma, Type.Beta, Type.Gamma};

    Random rand = new Random();
//private Atom atomSelected;
    private static Shooter single_instance = null;

    private Shooter(double xPos, double yPos, double L) {
        this.xPos = xPos;
        this.yPos = yPos;
      //this.anglePos = anglePos;
        this.width = L/2;
        this.height = L;


        item = new Atom(type[0]);

        loaded = true;


    }
    public static Shooter getInstance(double xPos, double yPos, double L) {
    	if (single_instance == null) 
    		single_instance = new Shooter(xPos, yPos, L);
    		
    	return single_instance;
    }


    public double gethealth() {
        return healthPoints;
    }


    public boolean decreaseHealth(double points) {
        /*
        EFFECTS: If 'points' is equal to or greater than healthPoints, returns false
                 If 'healthPoints' is not less than 0, returns true
                 If 'healthPoints'-'points' is greater than 0, returns true
        */
        if (healthPoints - points <= 0)
            return false;
        healthPoints =  healthPoints - points;
        return true;
    }

    public double getXPos() {
        return xPos;
    }
    public double getYPos() {
        return yPos;
    }
    public double getAnglePos() {
        return anglePos;
    }
    public double moveLinear(String direction) {
        //

        if (direction == "right"){
            if(xPos+horizontalSpeed >= (1000-this.width)){
                return xPos;
            }else {
                xPos += horizontalSpeed;
                item.setXPos(item.getXPos() + horizontalSpeed);
            }
        }

        if (direction == "left"){
            if((xPos-horizontalSpeed) < 0){
                //xPos = 0;
                return xPos;
            }else {
                xPos -= horizontalSpeed;
                item.setXPos(item.getXPos() - horizontalSpeed);
            }
        }

        return xPos;
    }
    public double moveAngular(String direction) {
        double angle =Math.toRadians(anglePos);


        if(direction == "cw"){
            if(anglePos >= 90 ){
                anglePos = 90;
            }else {
                this.anglePos += 10;
                //yPos += height * Math.sin(Math.toRadians(anglePos+270));
                //xPos += height * Math.cos(Math.toRadians(anglePos+270));
            }
        }
        if (direction == "ccw"){
                if(anglePos <= -90 ){
                    anglePos= -90;
                }else {
                    this.anglePos -= 10;
                    //yPos += height * Math.sin(Math.toRadians(anglePos+270));
                    //xPos -= height * Math.cos(Math.toRadians(anglePos+270));

                }
            }
       return anglePos;
        }




    public void changeItem(Items powup) {  //changing powerup

        item = powup ;  // new Item() or new Powerup() ??
        item.setXPos(this.xPos);
        item.setYPos(this.yPos);

    }
    public void changeAtom(Items atom) { //  for changing atoms randomly
        item = atom;
        item.setXPos(this.xPos);
        item.setYPos(this.yPos);
    }
    public Type getItemType() {
        return item.getType();
    }
    public Items getItem() {
        return item;
    }
    public boolean isLoaded() {
        return loaded;
    }


    public void notLoaded(){loaded = false;}
    public void shoot() {

    }
    public double getXPosition() {
        return xPos;
    }

    public void setXPosition(double position) {
        this.xPos = position;
    }

    public double getYPosition() {
        return yPos;
    }

    public void setYPosition(double position) {
        this.yPos = position;
    }

    public double getHealthPoints() {
        return healthPoints;
    }
}

