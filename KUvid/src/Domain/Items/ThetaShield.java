package Domain.Items;

import java.util.Random;

public class ThetaShield extends ShieldDecorator{
	
	private double random = new Random().nextDouble();
	public double ThetaEffBoost = 0.05 + (random * 0.1);
	
	public ThetaShield(Items atom) {
		super(atom);
		setEfficiency(atom);
		// TODO Auto-generated constructor stub
	}

	public double calculateEfficiency(Items atom) {
		return atom.getEfficiency()*(1-atom.getEfficiency())*ThetaEffBoost;
	}
	
	public void setEfficiency(Items atom) {
		atom.setEfficiency(calculateEfficiency(atom));
	}

	public void setSpeed(Items atom) {
		atom.setXVelocity(atom.getXVelocity()*0.91);
		atom.setYVelocity(atom.getYVelocity()*0.91);
	}
}
