package Domain.Items;

public class ShieldDecorator implements Items {
	
	private Items atom;
	
	public ShieldDecorator(Items atom) {
		this.atom = atom;
	}
	
	@Override
	public double getXPos() {
		// TODO Auto-generated method stub
		return atom.getXPos();
	}

	@Override
	public double getYPos() {
		// TODO Auto-generated method stub
		return atom.getYPos();
	}

	@Override
	public double getXVelocity() {
		// TODO Auto-generated method stub
		return atom.getXVelocity();
	}

	@Override
	public double getYVelocity() {
		// TODO Auto-generated method stub
		return atom.getYVelocity();
	}

	@Override
	public void setXPos(double x) {
		// TODO Auto-generated method stub
		atom.setXPos(x);
	}

	@Override
	public void setYPos(double y) {
		// TODO Auto-generated method stub
		atom.setYPos(y);
	}

	@Override
	public void setXVelocity(double xVel) {
		// TODO Auto-generated method stub
		atom.setXVelocity(xVel);
	}

	@Override
	public void setYVelocity(double yVel) {
		// TODO Auto-generated method stub
		atom.setYVelocity(yVel);
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return atom.getType();
	}

	@Override
	public Items getMainType() {
		// TODO Auto-generated method stub
		return atom.getMainType();
	}

	@Override
	public void setType(Type type) {
		// TODO Auto-generated method stub
		atom.setType(type);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		atom.move();
	}

	@Override
	public int getNeutron() {
		// TODO Auto-generated method stub
		return atom.getNeutron();
	}



	@Override
	public int getProton() {
		// TODO Auto-generated method stub
		return atom.getProton();
	}



	@Override
	public void setEfficiency(double efficiency) {
		// TODO Auto-generated method stub
		atom.setEfficiency(efficiency);
	}

	@Override
	public void setStability(double stability) {
		// TODO Auto-generated method stub
		atom.setStability(stability);
	}

	@Override
	public double getEfficiency() {
		// TODO Auto-generated method stub
		return atom.getEfficiency();
	}

	@Override
	public double getStability() {
		// TODO Auto-generated method stub
		return atom.getStability();
	}

	@Override
	public void startMoving() {
		// TODO Auto-generated method stub
		atom.startMoving();
	}

}
