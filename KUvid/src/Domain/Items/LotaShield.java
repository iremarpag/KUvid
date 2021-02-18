package Domain.Items;

public class LotaShield extends ShieldDecorator{

	public double LotaEffBoost = 0.1;
	
	public LotaShield(Items atom) {
		super(atom);
		setEfficiency(atom);
		// TODO Auto-generated constructor stub
	}


	public double calculateEfficiency(Items atom) {
		return atom.getEfficiency()*(1-atom.getEfficiency())*LotaEffBoost;
	}
	
	public void setEfficiency(Items atom) {
		atom.setEfficiency(calculateEfficiency(atom));
	}

	public void setSpeed(Items atom) {
		atom.setXVelocity(atom.getXVelocity()*0.93);
		atom.setYVelocity(atom.getYVelocity()*0.93);
	}
}
