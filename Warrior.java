public class Warrior extends Unit
{
	// Inherited instance variables are hp, atk, acc, offBonus (ints), def, defBonus (doubles), dead, playerUnit (boolean)
	public Warrior(int offLevel, int defLevel, boolean playerSide)
	{
		// (atk, def, acc, offLevel, defLevel) is constructor; midrange attack, highest defense, 80% accuracy
		// offLevel and defLevel are the level of upgrades for offense and defense; calculates and sets bonuses
		super(10, 0.2, 80, offLevel, defLevel, playerSide);		//(Attack, Defence, Accuracy, upgrade level for Atk, level for def upgrades, Team)
	}

	public Warrior(int atk, double def, int acc, int offLevel, int defLevel, boolean playerSide)
	{
		// User-determined stats, if needed
		super(atk, def, acc, offLevel, defLevel, playerSide);
	}

	public void attack(Unit u)
	{
		if (! dead)									//Duh
		{
			double damageBonus = 1.0;				//Damage vs Warriors

			if (u instanceof Archer)				//If attacking an Archerer
			{
				damageBonus = 1.4;					//Attack Bonus vs Archerers
			}
			else if (u instanceof Mage)				//If attacking a Mage
			{
				damageBonus = 0.8;					//Hinderance vs Mages
			}

			super.attack(u, damageBonus, area);		//How much damage they do
		}
	}

	public void setOffenseBonus(int offLevel)
	{
		if (offLevel >= 0 && offLevel < 2)			//Levels 0, 1
		{
			offBonus = 5 * offLevel;				//Bonus: 5 Times the level
		}											//Level 1 = +5 Attack
		else if (offLevel < 5 && offLevel > 1)		//Levels 2, 3, 4
		{
			offBonus = 10 * (offLevel - 1);			//Bonus: 10 times the upgrade level minus 1
		}											//Level 2 = +10, level 3 = + 20, Level 4 = +30
		else if (offLevel == 5)						//Level 5
		{
			offBonus = 50;							//Bonus: + 50 Attack
		}
	}

	public void setDefenseBonus(int defLevel)
	{
		if (defLevel >= 0 && defLevel < 5)			//Levels 1, 2, 3, 4
		{
			defBonus = 0.1 * defLevel;				//One tenth of the level
		}											//Level 1 = +.1, level 2 = +.2. level 3 = +.3, level 4 = +.4
		else if (defLevel == 5)						//Level 5
		{
			defBonus = 0.6;							//Bonus: +.6 Defense
		}
	}

	// get methods and set methods are in abstract Unit class
}
