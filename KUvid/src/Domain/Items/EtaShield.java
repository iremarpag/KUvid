package Domain.Items;
/*Eta shields improve the efficiency of the shielded atom by a factor of:
if # shielded atom neutrones != # shielded atom protones: 
(1 - shielded atom efficiency) * |# shielded atom neutrones - # shielded atom protones| / # shielded atom protones.
Otherwise:
(1 - shielded atom efficiency) * Eta_efficiency_boost 
Eta_efficiency_boost = 0.05 */


public class EtaShield extends ShieldDecorator{
	
	public double etaEffBoost = 0.05;
	
	public EtaShield(Items atom) {
		super(atom);
		setEfficiency(atom);
	}
	
	public double calculateEfficiency(Items atom) {
		if (atom.getNeutron() != atom.getProton()) {
			return atom.getEfficiency()*((1-atom.getEfficiency())* Math.abs(atom.getNeutron()-atom.getProton()))/atom.getProton();
		} else {
			return atom.getEfficiency()*(1-atom.getEfficiency())*etaEffBoost;
		}
	}
	
	public void setEfficiency(Items atom) {
		atom.setEfficiency(calculateEfficiency(atom));
	}
	
	public void setSpeed(Items atom) {
		atom.setXVelocity(atom.getXVelocity()*0.95);
		atom.setYVelocity(atom.getYVelocity()*0.95);
	}
}
