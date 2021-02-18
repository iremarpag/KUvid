package Domain.Items;

public class ZetaShield extends ShieldDecorator{

	public double ZetaEffBoost = 0.2;

	public ZetaShield(Items atom) {
		super(atom);
		setEfficiency(atom);
		// TODO Auto-generated constructor stub
	}

	public double calculateEfficiency(Items atom) {
		return (atom.getNeutron() == atom.getProton()) ? atom.getEfficiency()*(1-atom.getEfficiency())*ZetaEffBoost : atom.getEfficiency()*atom.getEfficiency();
	}

	public void setEfficiency(Items atom) {
		atom.setEfficiency(calculateEfficiency(atom));
	}
	
	public void setSpeed(Items atom) {
		atom.setXVelocity(atom.getXVelocity()*0.89);
		atom.setYVelocity(atom.getYVelocity()*0.89);
	}
}
