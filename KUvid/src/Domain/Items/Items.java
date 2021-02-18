package Domain.Items;

import java.io.Serializable;

public interface Items extends Serializable {
    //int number = 0;
    Type type = null;
    double xPos = 0;
    double yPos = 0;
    double xVelocity = 0;
    double yVelocity = 0;
    int neutron = 0;
    int proton = 0;

    double getXPos();
    double getYPos();
    double getXVelocity();
    double getYVelocity();
    void setXPos(double x);
    void setYPos(double y);
    void setXVelocity(double xVel);
    void setYVelocity(double yVel);
    Type getType();
    Items getMainType();
    void setType(Type type);
    void startMoving();
    void move();

    int getNeutron();
    int getProton();
	double getStability();
	double getEfficiency();
	void setStability(double stability);
	void setEfficiency(double efficiency);


}
