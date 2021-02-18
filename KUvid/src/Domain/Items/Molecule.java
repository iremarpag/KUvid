package Domain.Items;
import Domain.Observer;

public class Molecule implements Items, Observer {


    private Type type;
    private double xPos;
    private double yPos;
    private double xVelocity;
    private double yVelocity;
    private boolean zigzag = false;

    public Molecule(Type type, double xPos, double yPos, double xVelocity, double yVelocity) {
        this.type = type;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }



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

        this.xPos=x;

    }

    @Override
    public void setYPos(double y) {

        this.yPos = y;
    }

    @Override
    public void setXVelocity(double xVel) {

        this.xVelocity = xVel;
    }

    public void startMoving(){

        if(this.type == Type.AlphaM){
            this.zigzag = true;
            setXVelocity(1);
            setYVelocity(1);
        }else if(this.type == Type.BetaM){
            setXVelocity(0);
            setYVelocity(1);
        }else if(this.type == Type.GammaM){
            setXVelocity(0);
            setYVelocity(1);
        }else if(this.type == Type.SigmaM){
            setXVelocity(0);
            setYVelocity(1);
        }
    }

    @Override
    public void setYVelocity(double yVel) {

        this.yVelocity=yVel;
    }

    @Override
    public Type getType() {

        return this.type;
    }

    public Items getMainType(){

        return this;
    }

    @Override
    public void setType(Type type) {

        this.type=type;
    }
    private void zigZag(){
        if(yPos%80 == 0){
            xVelocity *= -1;
        }
    }

    @Override
    public void move() {
        xPos += xVelocity;
        yPos += yVelocity;
        if(zigzag || xVelocity != 0){
            zigZag();
        }
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
