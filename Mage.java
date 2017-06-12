
public class Mage extends Unit
{
	// Inherited instance variables are hp, atk, acc, offBonus (ints), def, defBonus (doubles), dead, playerUnit (boolean)
	public Mage(int offLevel, int defLevel, boolean playerSide)
	{

		// (atk, def, acc, offLevel, defLevel) is constructor; highest attack, lowest defense, 60% accuracy
		// offLevel and defLevel are the level of upgrades for offense and defense; calculates and sets bonuses
		super(25, 0.05, 60, offLevel, defLevel, playerSide);
	}

	public Mage(int atk, double def, int acc, int offLevel, int defLevel, boolean playerSide)
	{

		super(atk, def, acc, offLevel, defLevel, playerSide); // User-determined stats, if needed
	}

	public void attack(Unit u)
	{
		if (! dead)
		{
			double damageBonus = 1.0;					//Damage vs Mages

			if (u instanceof Warrior)					//Opposing Unit is Warrior
			{
				damageBonus = 1.4;						//Bonus vs Warriors
			}
			else if (u instanceof Archer)				//Opposing Unit is Archer
			{
				damageBonus = 0.8;						//Hinderance vs Archers
			}

			super.attack(u, damageBonus, area);			//Unit class deals with attacks
		}
	}

	public void setOffenseBonus(int offLevel)
	{
		if (offLevel >= 0 && offLevel < 5)				//First 4 upgrades (and level 0)
		{
			offBonus = 15 * offLevel;					// 15 times the level
		}												// Lvl1 = 15, lvl2 =	30, lvl3 = 45, lvl4= 60
		else if (offLevel == 5)							// Final Upgrade
		{
			offBonus = 90;								// Plus 90 to attack
		}												// 115 final attack
	}

	public void setDefenseBonus(int defLevel)
	{
		if (defLevel >= 0 && defLevel < 5)				//First 4 upgrades (and level 0)
		{
			defBonus = 0.05 * defLevel;					// 0.05 times the level
		}												// lvl1 = 0.05, lvl2 = 0.1, lvl3 = 0.15, lvl4 = 0.2
		else if (defLevel == 5)							// Maxed out
		{
			defBonus = 0.3;								// + 0.3 to defense
		}												// 0.35 defense final
	}

	// get methods and set methods are in abstract Unit class
}
