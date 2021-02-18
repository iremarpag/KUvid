package Domain.Items;
import Domain.Observer;
import java.util.Random;

public class Atom implements Items, Observer {

    Random rand = new Random();
    private Type type;
    private double xPos;
    private double yPos;
    private double xVelocity;
    private double yVelocity;
    private double number;
    private int neutron;
    private int proton;
    private double stability;
    private double efficiency;

    public Atom(Type type) {
        this.type = type;
    }

    public Atom(Type type, double xPos, double yPos, double xVelocity, double yVelocity) {
        this.type = type;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        switch(this.type.ordinal()){
            case 0:
                stability = 0.85;
                proton = 8;
                setNeutron();
                efficiency = (1 - (Math.abs(neutron - proton)/proton)) * stability;
                break;
            case 1:
                stability = 0.9;
                proton = 16;
                setNeutron();
                efficiency = stability - (0.5*Math.abs(neutron-proton)/proton);
                break;
            case 2:
                stability = 0.8;
                proton = 32;
                setNeutron();
                efficiency = stability + (Math.abs(neutron-proton)/(2*proton));
                break;
            case 3:
                stability = 0.7;
                proton = 64;
                setNeutron();
                efficiency = (1 + stability)/2 + (Math.abs(neutron-proton)/proton);
                break;
        }


    }




    @Override
    public double getXPos() {
        return xPos;
    }

    @Override
    public double getYPos() {
        return yPos;
    }

    @Override
    public double getXVelocity() {
        return xVelocity;
    }

    @Override
    public double getYVelocity() {
        return yVelocity;
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

    }


    @Override
    public void move(){
        xPos += xVelocity;
        yPos += yVelocity;
    }



    @Override
    public void update(double xPos, double yPos) {

    }

    public void setNeutron(){
        int n;
        switch (proton){
            case 8:
                n = rand.nextInt(3);
                neutron = n + 7;
                break;
            case 16:
                n = rand.nextInt(5);
                switch (n){
                    case 0:
                        neutron = 15;
                        break;
                    case 1:
                        neutron = 16;
                        break;
                    case 2:
                        neutron = 17;
                        break;
                    case 3:
                        neutron = 18;
                        break;
                    case 4:
                        neutron = 21;
                }
                break;
            case 32:
                n = rand.nextInt(3);
                switch (n){
                    case 0:
                        neutron = 29;
                        break;
                    case 1:
                        neutron = 32;
                        break;
                    case 2:
                        neutron = 33;
                        break;
                }
                break;
            case 64:
                n = rand.nextInt(3);
                switch (n){
                    case 0:
                        neutron = 63;
                        break;
                    case 1:
                        neutron = 64;
                        break;
                    case 2:
                        neutron = 67;
                }
                break;
        }
    }

    public double getEfficiency(){
        return efficiency;
    }

    @Override
    public void setStability(double stability) {
        this.stability = stability;
    }

    @Override
    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    public int getNeutron(){
        return this.neutron;
    }

    public int getProton(){
        return this.proton;
    }

    @Override
    public double getStability() {
        return this.stability;
    }
}
