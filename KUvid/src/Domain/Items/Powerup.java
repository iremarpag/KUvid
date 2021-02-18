package Domain.Items;
import Domain.Observer;

public class Powerup implements Items, Observer{

    private Type type;
    private double xPos;
    private double yPos;
    private double xVelocity;
    private double yVelocity;


    public Powerup(Type type) {
        this.type = type;
    }

    public Powerup(Type type, double xPos, double yPos, double xVelocity, double yVelocity) {
        this.type = type;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

   /* @Override
    public void update(int xPos, int yPos) {
    	this.xPos = xPos;
        this.yPos = yPos;
    }*/

    @Override
    public double getXPos() {
        return this.xPos;
    }

    @Override
    public double getYPos() {
        return this.yPos;
    }

    @Override
    public double getXVelocity() {
        return this.xVelocity;
    }

    @Override
    public double getYVelocity() {
        return this.yVelocity;
    }

    @Override
    public void setXPos(double x) {
        this.xPos = x;
    }

    @Override
    public void setYPos(double y) {
        this.yPos = y;
    }
    @Override
    public void setXVelocity(double x) {
        this.xVelocity = x;
    }
    @Override
    public void setYVelocity(double y) {
        this.yVelocity = y;
    }
    @Override
    public Type getType(){
        return this.type;
    }

    public Items getMainType(){
        return this;
    }

    @Override
    public void setType(Type type){
        this.type = type;
    }

    @Override
    public void startMoving() {
        setXVelocity(0);
        setYVelocity(1);
    }

    @Override
    public void move() {
        xPos += xVelocity;
        yPos += yVelocity;
    }





    @Override
    public int getNeutron() {
        return 0;
    }

    @Override
    public int getProton() {
        return 0;
    }

    @Override
    public double getStability() {
        return 0;
    }

    @Override
    public double getEfficiency() {
        return 0;
    }

    @Override
    public void setStability(double stability) {

    }

    @Override
    public void setEfficiency(double efficiency) {

    }


    @Override
    public void update(double xPos, double yPos) {

    }
}

