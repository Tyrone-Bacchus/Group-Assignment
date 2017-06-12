// Abstract class for general format of all units.

abstract public class Unit
{
	// Attributes of all units, including bonuses from upgrades and state of life
	protected int hp, atk, acc, offBonus;
	protected double def, defBonus, area = 1.0;
	protected boolean dead;
	protected boolean playerSide;

	// Default Constructor
	public Unit()
	{
		this(0, 0.0, 0, 0, 0, false);		//(attack, defense, accuracy, offensive bonus, defensive bonus)
	}

	// User-defined Constructor
	public Unit(int a, double d, int ac, int offLevel, int defLevel, boolean p)
	{
		hp = 100;					// Default heath for all units
		setOffenseBonus(offLevel);	// Uses method to set the offense bonus, based on upgrade level
		setDefenseBonus(defLevel);	// Uses method to set the defense bonus, based on upgrade level
		atk = a;
		def = d;
		acc = ac;
		dead = false;				// Unit is alive
		playerSide = p;
	}

	// Returns values of parameters and sets parameter values
	//The rest of the code is pretty obvious, contact me for help if you are lost
	final public int getAttack()
	{
		return atk;
	}

	final public void setAttack(int i)
	{
		atk = i;
	}

	final public double getArea()
	{
		return area;
	}

	final public void setArea(double i)
	{
		area = i;
	}

	final public int getHP()
	{
		return hp;
	}

	final public void setHP(int i)
	{
		hp = i;

		if (hp <= 0)
		{
			dead = true;	// Unit has died
		}
	}

	final public double getDefense()
	{
		return def;
	}

	final public void setDefense(double d)
	{
		def = d;
	}

	final public int getAccuracy()
	{
		return acc;
	}

	final public void setAccuracy(int i)
	{
		acc = i;
	}

	final public boolean getDead()
	{
		return dead;
	}

	final public void setDead(boolean b)
	{
		dead = b;
	}

	final public boolean isPlayerSide()
	{
		return playerSide;
	}
	// Called only by the subclasses and their attack() methods; calculates and deals damage to Unit

	// Base of attack() method; calculates and deals damage to Unit
	protected void attack(Unit u, double damageBonus, double areaEffect)
	{
		int baseOffense = atk + offBonus; 					// Attack after upgrades
		int damage = (int)((baseOffense + (baseOffense * damageBonus)) - (baseOffense * (u.getDefense() * areaEffect)));	// How much damage the units do
		u.setHP(u.getHP() - damage);						// Heath - damage = current health
	}

	abstract public void setOffenseBonus(int offLevel); // Calculates offense upgrade bonus based on the level of upgrade
	abstract public void setDefenseBonus(int defLevel); // Calculates defense upgrade bonus based on the level of upgrade
	abstract public void attack(Unit u);	//The method that is called by other classes that use this one
}
