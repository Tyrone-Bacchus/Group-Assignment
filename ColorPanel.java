/*By: Coding Team
 *Integrated Software Development
 *CEO: Mr. Ryan
 *Due: Thursday, January 12, 2012 */

 import javax.swing.*;
 import java.awt.*;
 import java.util.*;

public class ColorPanel extends JPanel
{
	private ImageIcon image;	//the actual image to be displayed
	private int tester = -1;
	private int row = -1, column = - 1;
	private boolean border, blue, uncrossable, playerSide, playerOccupies, AIOccupies;
	private UnitStorage currentArmy = new UnitStorage();

	//for the upgrades
	private double locationDefence = 1.0;
	private double interestRate = 1.0;

	public static boolean playerArmies[][] = new boolean[5][14];
	public static boolean enemyArmies[][] = new boolean[5][14];

	public static boolean confirmClicked;

	public static Map<String, Coordinates> playerTowns = new HashMap <String, Coordinates> ();
	public static Map<String, Coordinates> enemyTowns = new HashMap <String, Coordinates> ();

	public ColorPanel(int i, int r, int c, boolean p)
	{
		setPreferredSize(new Dimension(48, 48));	//each tile is size-determined!

		//set up class variable
		confirmClicked = false;
		blue = false;

		Random gen = new Random();
		tester = i;
		row = r;
		column = c;
		playerSide = p;

		border = false;

		if(i == 1)	//for the player's cities
		{
			Coordinates location = new Coordinates(getRow(), getColumn());

			if(playerTowns.get("Valkarth") == null)
			{
				playerTowns.put("Valkarth", location);
			}
			else if(playerTowns.get("The Rim") == null)
			{
				playerTowns.put("The Rim", location);
			}
			else if(playerTowns.get("Dither Elm") == null)
			{
				playerTowns.put("Dither Elm", location);
			}
			else if(playerTowns.get("Balroth") == null)
			{
				playerTowns.put("Balroth", location);
			}

			image = Resources.rCity;

			for(int a = 1; a <= 2; a++)
			{
				currentArmy.addUnit(new Archer(0, 0, true));
				for(int w = 1; w <= 2; w++)
				{
					currentArmy.addUnit(new Warrior(0, 0, true));
				}
			}
			currentArmy.addUnit(new Mage(0, 0, true));

			playerArmies[r][c] = true;

			playerOccupies = true;
		}
		else if(i == 2)	//for the AI's cities
		{
			Coordinates location = new Coordinates(getRow(), getColumn());

			if(enemyTowns.get("Uriel") == null)
			{
				enemyTowns.put("Uriel", location);
			}
			else if(enemyTowns.get("Dragon’s Nest") == null)
			{
				enemyTowns.put("Dragon’s Nest", location);
			}
			else if(enemyTowns.get("Agaroth") == null)
			{
				enemyTowns.put("Agaroth", location);
			}
			else if(enemyTowns.get("Trodlom") == null)
			{
				enemyTowns.put("Trodlom", location);
			}

			image = Resources.bCity;

			for(int a = 1; a <= 2; a++)
			{
				currentArmy.addUnit(new Archer(0, 0, false));
				for(int w = 1; w <= 2; w++)
				{
					currentArmy.addUnit(new Warrior(0, 0, false));
				}
			}
			currentArmy.addUnit(new Mage(0, 0, false));

			enemyArmies[r][c] = true;
		}
		else if(i == 3)	//the forests
		{
			int chooseF = gen.nextInt(4);

			if(chooseF == 0)
			{
				image = Resources.forest1;
			}
			else if(chooseF == 1)
			{
				image = Resources.forest2;
			}
			else if(chooseF == 2)
			{
				image = Resources.forest3;
			}
			else
			{
				image = Resources.forest4;
			}
		}
		else if(i == 4)	//mountains
		{
			image = Resources.mountain;
			uncrossable = true;
		}
		else if(i == 5)	//the plains / DEFAULT area
		{
			image = Resources.plains;
		}
		//next two are the borders
		else if(i == 6)
		{
			image = Resources.lBorder;
		}
		else if(i == 7)
		{
			image = Resources.rBorder;
		}
		else if(i == 8)	//the river; you can't stand on, but can pass over!!
		{
			image = Resources.river;
		}
		else if(i == 9)	//the lakes
		{
			image = Resources.lake;
			uncrossable = true;
		}
	}

	public static void nameCities()
	{
		playerTowns.put("Valkarth", null);
		playerTowns.put("The Rim", null);
		playerTowns.put("Dither Elm", null);
		playerTowns.put("Balroth", null);

		enemyTowns.put("Uriel", null);
		enemyTowns.put("Dragon’s Nest", null);
		enemyTowns.put("Agaroth", null);
		enemyTowns.put("Trodlom", null);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		image.paintIcon(this, g, 0, 0);

		if(border == true)
		{
			if(blue == true)
			{
				g.setColor(Color.blue);
			}
			else
			{
				g.setColor(Color.yellow);
			}

			g.drawRect(0, 0, getWidth()- 2, getHeight()- 2);
		}

		if(playerOccupies == true)
		{
			Resources.redArmy.paintIcon(this, g, 0, 0);
		}
		else if(AIOccupies == true)
		{
			Resources.blueArmy.paintIcon(this, g, 0, 0);
		}
	}

	public void setImage(ImageIcon icon)
	{
		image = icon;
	}

	public ImageIcon getImage()
	{
		return image;
	}

	public boolean isPlayerTile()
	{
		return playerSide;
	}

	public boolean isUncrossable()
	{
		return uncrossable;
	}

	public void setClicked()
	{
		if(border == true)
		{
			border = false;
		}
		else if(border == false)
		{
			border = true;
		}

		repaint();
	}

	public void setOptions()
	{
		if(border == true)
		{
			blue = false;
			border = false;
		}
		else if(border == false)
		{
			blue = true;
			border = true;
		}

		repaint();
	}

	public void setConfirmed()
	{
		int x = this.getRow();
		int y = this.getColumn();

		if(confirmClicked == true)
		{
			confirmClicked = false;
		}
		else
		{
			confirmClicked = true;
		}

		try
		{
			if(y < 6)
			{
				if(GameGUI.myHalf[x][y + 1].isUncrossable())
				{
					int temp = 1/0;
				}

				GameGUI.myHalf[x][y + 1].setOptions();
			}
			else if(y == 6)
			{
				GameGUI.AIHalf[x][0].setOptions();
			}
			else
			{
				if(GameGUI.AIHalf[x][y + 1 - 7].isUncrossable())
				{
					int temp = 1/0;
				}

				GameGUI.AIHalf[x][y + 1 - 7].setOptions();
			}
		}
		catch(Exception ex)
		{
		}

		try
		{
			if(y < 7)
			{
				if(GameGUI.myHalf[x + 1][y].isUncrossable())
				{
					int temp = 1/0;
				}

				GameGUI.myHalf[x + 1][y].setOptions();
			}
			else
			{
				if(GameGUI.AIHalf[x + 1][y - 7].isUncrossable())
				{
					int temp = 1/0;
				}

				GameGUI.AIHalf[x + 1][y - 7].setOptions();
			}
		}
		catch(Exception ex)
		{
		}

		try
		{
			if(y < 7)
			{
				if(GameGUI.myHalf[x][y - 1].isUncrossable())
				{
					int temp = 1/0;
				}

				GameGUI.myHalf[x][y - 1].setOptions();
			}
			else if(y > 7)
			{
				if(GameGUI.AIHalf[x][y - 1 - 7].isUncrossable())
				{
					int temp = 1/0;
				}

				GameGUI.AIHalf[x][y - 1 - 7].setOptions();
			}
			else
			{
				GameGUI.myHalf[x][6].setOptions();
			}
		}
		catch(Exception ex)
		{
		}

		try
		{
			if(y < 7)
			{
				if(GameGUI.myHalf[x - 1][y].isUncrossable())
				{
					int temp = 1/0;
				}

				GameGUI.myHalf[x - 1][y].setOptions();
			}
			else
			{
				if(GameGUI.AIHalf[x - 1][y - 7].isUncrossable())
				{
					int temp = 1/0;
				}

				GameGUI.AIHalf[x - 1][y - 7].setOptions();
			}
		}
		catch(Exception ex)
		{
		}
	}

	public boolean getConfirmed()
	{
		return blue;
	}

	public void setPlayerOccupies(boolean pO)
	{
		playerOccupies = pO;
		repaint();
	}

	public void setAIOccupies(boolean AIO)
	{
		 AIOccupies= AIO;
		 repaint();
	}

	public String getTile()
	{
		if(tester == 1 || tester == 2)
		{
			String rString = new String("------------------------\nCoordinates-(" + getRow() + "," + getColumn() + ") is a town named ");

			if(playerSide && image == Resources.rCity)
			{
				for(String temp : playerTowns.keySet())
				{
					if(getRow() == playerTowns.get(temp).getThisRow() &&
						getColumn() == playerTowns.get(temp).getThisColumn())
					{
						rString += "\"" + temp + "\"" +".\n";
						break;
					}
				}
			}
			else if(image == Resources.bCity)
			{
				for(String temp : enemyTowns.keySet())
				{
					if(getRow() == enemyTowns.get(temp).getThisRow() &&
						getColumn() == enemyTowns.get(temp).getThisColumn())
					{
						rString += "\"" + temp + "\"" +".\n";
						break;
					}
				}
			}

			if(getImage() == Resources.bCityDestroyed || getImage() == Resources.rCityDestroyed)
			{
				rString = new String("------------------------\nCoordinates-(" + getRow() + "," + getColumn() + ") is a town that was destroyed.\n");
			}

			return rString;
		}
		else if(tester == 3)
		{
			return "------------------------\nCoordinates-(" + getRow() + "," + getColumn() + ") is a forest.\n";
		}
		else if(tester == 4 || tester == 9)
		{
			return "------------------------\nCoordinates-(" + getRow() + "," + getColumn() + ") is an uncrossable terrain.\n";
		}
		else
		{
			return "------------------------\nCoordinates-(" + getRow() + "," + getColumn() + ") are some plains.\n";
		}
	}

	public int getRow()
	{
		return row;
	}

	public int getColumn()
	{
		return column;
	}

	public UnitStorage getUnitStorage()
	{
		return currentArmy;
	}

	public void setUnitStorage(UnitStorage newArmy)
	{
		currentArmy = newArmy;
	}

	public double getLocationDefence()
	{
		return locationDefence;
	}

	public void setLocationDefence(double upgrade)
	{
		locationDefence = upgrade;
	}

	public double getInterestRate()
	{
		return interestRate;
	}

	public void setInterestRate(double upgrade)
	{
		interestRate = upgrade;
	}

	public class Coordinates
	{
		private int thisRow = -1;
		private int thisColumn = -1;

		public Coordinates(int row, int column)
		{
			thisRow = row;
			thisColumn = column;
		}

		public int getThisRow()
		{
			return thisRow;
		}

		public int getThisColumn()
		{
			return thisColumn;
		}
	}
}