 /*By: Coding Team
 *Integrated Software Development
 *CEO: Mr. Ryan
 *Due: Thursday, January 12, 2012 */

 import java.util.Random;

public class Store
{
	private Random gen = new Random();

	private int bank;

	public Store(){
		bank = 1000;
	}
	public void earn(int gold)
	{
		bank += gold;
	}
	public void buyWarrior()
	{
		bank -= 100;//+(WarriorLevel*100)
	}
	public void buyArcher()
	{
		bank -= 150;//+(ArcherLevel*100)
	}
	public void buyMage()
	{
		bank -= 200;//+(MageLevel*100)
	}
	public int getValue()
	{
		return bank;
	}

	public int incomeTax()
	{
		int pTownsCount = 0, eTownsCount = 0, totalTax = 0;
		double upgrade[] = new double[4];

		for(String temp : ColorPanel.playerTowns.keySet())
		{
			int x = ColorPanel.playerTowns.get(temp).getThisRow();
			int y = ColorPanel.playerTowns.get(temp).getThisColumn();

			upgrade[pTownsCount] = GameGUI.myHalf[x][y].getInterestRate();
			pTownsCount++;
		}

		for(int i = 0; i < pTownsCount; i++)
		{
			totalTax += (int)((gen.nextInt(61) + 40) * upgrade[i]);
		}

		bank += totalTax;

		return totalTax;
	}

	public int getCost(int checkedValue)
	{
		int x = -1;
		int y = -1;
		ColorPanel panelPointer = null;

		//point to the city of the upgrade
		try
		{
			if(checkedValue == 1 || checkedValue == 2)
			{
				x = (ColorPanel.playerTowns.get("Valkarth")).getThisRow();
				y = (ColorPanel.playerTowns.get("Valkarth")).getThisColumn();

				panelPointer = GameGUI.myHalf[x][y];
			}
			else if(checkedValue == 3 || checkedValue == 4)
			{
				x = (ColorPanel.playerTowns.get("The Rim")).getThisRow();
				y = (ColorPanel.playerTowns.get("The Rim")).getThisColumn();

				panelPointer = GameGUI.myHalf[x][y];
			}
			else if(checkedValue == 5 || checkedValue == 6)
			{
				x = (ColorPanel.playerTowns.get("Dither Elm")).getThisRow();
				y = (ColorPanel.playerTowns.get("Dither Elm")).getThisColumn();

				panelPointer = GameGUI.myHalf[x][y];
			}
			else if(checkedValue == 7 || checkedValue == 8)
			{
				x = (ColorPanel.playerTowns.get("Balroth")).getThisRow();
				y = (ColorPanel.playerTowns.get("Balroth")).getThisColumn();

				panelPointer = GameGUI.myHalf[x][y];
			}
		}
		catch(Exception ex)
		{
		}

		//get the variables
		int cost = 0;

		try
		{
			if(checkedValue % 2 == 1)
			{
				double current = ((double)Math.round(panelPointer.getLocationDefence() * 10)) / 10;

				if(current == 1.0)
				{
					cost = 100;
				}
				else if(current == 1.2)
				{
					cost = 200;
				}
				else if(current == 1.4)
				{
					cost = 400;
				}
				else if(current == 1.6)
				{
					cost = 800;
				}
				else if(current == 1.8)
				{
					cost = 1600;
				}
			}
			else if(checkedValue % 2 == 0)
			{
				double current = ((double)Math.round(panelPointer.getInterestRate() * 10)) / 10;

				if(current == 1.0)
				{
					cost = 100;
				}
				else if(current == 1.2)
				{
					cost = 157;
				}
				else if(current == 1.4)
				{
					cost = 225;
				}
				else if(current == 1.6)
				{
					cost = 400;
				}
				else if(current == 1.8)
				{
					cost = 506;
				}
			}
		}
		catch (Exception ex)
		{
		}

		return cost;
	}

}
/* for the updates think of the levels being numbered like an array. 0 = 1, 1 = 2......
this ensures that the price will rise by one hundred.
A suggestion to keep track of the levels of the upgrades is to have them as individual integers
with the corresponding get methods(Example .getWarriorLevel())*/