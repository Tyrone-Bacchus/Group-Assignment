/*By: Coding Team
 *Integrated Software Development
 *CEO: Mr. Ryan
 *Due: Thursday, January 12, 2012 */

public class Archer extends Unit
{
	// Inherited instance variables are hp, atk, acc, offBonus (ints), def, defBonus (doubles), dead, playerUnit (boolean)
	public Archer(int offLevel, int defLevel, boolean playerSide)
	{
		// (atk, def, acc, offLevel, defLevel) is constructor; high attack, low defense, 70% accuracy
		// offLevel and defLevel are the level of upgrades for offense and defense; calculates and sets bonuses
		super(15, 0.15, 70, offLevel, defLevel, playerSide);			//(Attack, Defence, Accuarcy, upgrade level: Attack, upgrade level: Defence, team)
	}

	public Archer(int atk, double def, int acc, int offLevel, int defLevel, boolean playerSide)
	{
		// User-determined stats, if needed
		super(atk, def, acc, offLevel, defLevel, playerSide);
	}

	public void attack(Unit u)
	{
		if (! dead)											//What you are
		{
			double damageBonus = 1.0;						//Damage bonus vs Archerers

			if (u instanceof Mage)							//If attacking a Mage
			{
				damageBonus = 1.4;							//Bonus to attack
			}
			else if (u instanceof Warrior)					//If against a Warrior
			{
				damageBonus = 0.8;							//Hinderance to Attack
			}

			super.attack(u, damageBonus, area);				//Damage to be delt
		}
	}

	public void setOffenseBonus(int offLevel)
	{
		if (offLevel >= 0 && offLevel < 5)					//Levels 1 - 4
		{
			offBonus = 10 * offLevel;						//Ten times the offence level
		}													//Level 1= +10, level 2 = +20, level 3 = +30, level 4 = + 40
		else if (offLevel == 5)								//Level 5
		{
			offBonus = 60;									//Bonus = +60
		}
	}

	public void setDefenseBonus(int defLevel)
	{
		if (defLevel >= 0 && defLevel < 2)					//Level 0, 1
		{
			defBonus = 0.05 * defLevel;						//One twentyith of the level
		}													//Level 1 = + 0.05
		else if (defLevel > 1 && defLevel < 5)              //Levels 2, 3, 4
		{
			defBonus = 0.1 * (defLevel - 1);				//One tenth of the defensive level minus 1
		}													//Level 2 = + 0.1, level 3 = + 0.02, level 4 = + 0.03
		else if (defLevel == 5)								//Level 5
		{
			defBonus = 0.5;									//Bonus = + 0.5
		}
	}

	// get methods and set methods are in abstract Unit class
}
