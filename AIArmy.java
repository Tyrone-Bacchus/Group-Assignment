import java.util.*;

public class AIArmy
{
	// Integers for funds and buffers
	private int funds, buffer;
	private UnitStorage units;						// Used as a common object that many methods can access
	private Random gen = new Random();				// Randomizer for the class
	private ArrayList<ColorPanel> cities;			// References to the ColorPanels that are AI cities
	private ArrayList<ColorPanel> armies;			// References to the ColorPanels that hold AI armies
	private ArrayList<ColorPanel> playerCities;		// References to the ColorPanels that hold player cities

	public AIArmy()
	{
		funds = 1000;		// Instantiate funds

		// Instantiate lists
		units = new UnitStorage();
		cities = new ArrayList<ColorPanel>();
		armies = new ArrayList<ColorPanel>();
		playerCities = new ArrayList<ColorPanel>();

		// Fill ArrayLists to hold the ColorPanels with the AI cities and armies
		for (int r = 0; r < 5; r++)
		{
			for (int c = 0; c < 14; c++)
			{
				if (ColorPanel.enemyArmies[r][c])					// Uses ColorPanel's boolean array
				{
					cities.add(GameGUI.AIHalf[r][c - 7]);			// Adds the locations of all cities and starting positions of the armies
					armies.add(GameGUI.AIHalf[r][c - 7]);			// Subtract 7 to account for different array sizes
				}

				if (cities.size() == 4)			// There will only be four cities / armies when this is created
				{
					break;
				}
			}

			if (cities.size() == 4)				// Breaks out of outer loop
			{
				break;
			}
		}

		// Stores positions of player cities, used for attacking
		for (int r = 0; r < 5; r++)
		{
			for (int c = 0; c < 14; c++)
			{
				if (ColorPanel.playerArmies[r][c])
				{
					playerCities.add(GameGUI.myHalf[r][c]);		// Adds the locations of all player cities
				}

				if (playerCities.size() == 4)			// There will only be four cities when this is created
				{
					break;
				}
			}

			if (playerCities.size() == 4)
			{
				break;
			}
		}
	}

	// Controls the AI's actions
	public void run()
	{
		int moves = 4;

		for(int i = 1; i <= cities.size(); i++)
		{
			funds += gen.nextInt(31) + 20;				// Tax for each city
		}

		// Runs all processes for AI's moves
		for (int i = 1; i <= moves; i++)
		{
			// Selects the city to add units to
			int cityNum = gen.nextInt(cities.size());
			ColorPanel selectedCity = cities.get(cityNum);
			units = selectedCity.getUnitStorage();
			buffer = funds / 4;								// Buffer value for purchasing

			financeManager();		// Calls the finance manager

			if (battleManager())	// Calls the battle manager, which returns true if the game is lost; stops the AI from continuing
			{
				return;
			}
		}
	}

	private void financeManager()
	{
		int acceptableAmount = 10;										// Acceptable amount of Units for each type

		// Purchase Warriors, Archers, and Mages if there aren't enough!
		ArrayList<String> types = new ArrayList<String>();					// Used for selecting which type to purchase
		types.add("Warrior");
		types.add("Archer");
		types.add("Mage");

		// Selects a type of Unit to purchase randomly
		while (! types.isEmpty())
		{
			int index = gen.nextInt(types.size());
			String selectedType = types.remove(index);

			if (selectedType.equals("Warrior"))
			{
				if (units.warriorAmount() < acceptableAmount)
				{
					purchaseUnit("Warrior");
				}
			}
			else if (selectedType.equals("Archer"))
			{
				if (units.archerAmount() < acceptableAmount)
				{
					purchaseUnit("Archer");
				}
			}
			else
			{
				if (units.mageAmount() < acceptableAmount)
				{
					purchaseUnit("Mage");
				}
			}
		}
	}

	private void purchaseUnit(String u)
	{
		if (funds > buffer)
		{
			int unitPrice = 1;
			int usableFunds = funds - buffer;

			if (u.equals("Warrior"))
			{
				unitPrice = 100;			// Warrior Unit Price
			}
			else if (u.equals("Archer"))
			{
				unitPrice = 150;			// Archer Unit Price
			}
			else if (u.equals("Mage"))
			{
				unitPrice = 200;			// Mage Unit Price
			}

			int maxUnits = usableFunds / unitPrice;

			if (maxUnits < 1)		 // May not be enough to purchase the desired unit
			{
				return;
			}

			int unitsPurchased = gen.nextInt(maxUnits) + 1;		// Randomize Units purchased
			funds = funds - (unitPrice * unitsPurchased);		// Takes away from the funds the price of all Units

			// Purchases and adds as many units as necessary
			for (int i = 1; i <= unitsPurchased; i++)
			{
				if (u.equals("Warrior"))
				{
					units.addUnit(new Warrior(0, 0, false));
				}
				else if (u.equals("Archer"))
				{
					units.addUnit(new Archer(0, 0, false));
				}
				else if (u.equals("Mage"))
				{
					units.addUnit(new Mage(0, 0, false));
				}
			}
		}
	}

	private boolean battleManager()
	{
		ColorPanel currentArmy = null;					// ColorPanel of the selected army; null to begin with
		Collections.shuffle(armies);					// Makes sure the selection is randomized

		for (int i = 0; i < armies.size(); i++)
		{
			if (cities.contains(armies.get(i)))						// If the army is currently in a city
			{
				if (armies.get(i).getUnitStorage().size() >= 10)	// Use the city if it has enough units
				{
					currentArmy = armies.get(i);
					break;
				}
			}
			else	// If the army is free-roaming
			{
				currentArmy = armies.get(i);
				break;
			}
		}

		if (currentArmy == null)		// If all cities do not have enough, skip this phase
		{
			return false;
		}

		if (gen.nextInt(10) != 0)
		{
			for (int i = 0; i < armies.size(); i++)
			{
				if (armies.get(i).isPlayerTile())
				{
					currentArmy = armies.get(i);				// 90% chance that AI will select an army on the player's side, if one exists
					break;
				}
			}
		}

		boolean withCity = cities.contains(currentArmy);		// Checks to see if that army is currently in an AI city
		UnitStorage temp = currentArmy.getUnitStorage();		// UnitStorage of the army
		int armyRow = currentArmy.getRow();						// Location of the army
		int armyCol = currentArmy.getColumn();

		if (withCity)		// If the army is moving from an AI city
		{
			int range = temp.size() / 2;	// Takes half the units for movement

			ColorPanel target = selectTarget(armyRow, armyCol - 7, currentArmy);	// Chooses a ColorPanel to move to
			split(currentArmy, target, range);										// Splits army into that selected ColorPanel

			try
			{
				if (! target.getUnitStorage().peek().isPlayerSide())						// If split occurs successfully
				{
					armies.add(target);														// Track that ColorPanel, since an army is there
					ColorPanel.enemyArmies[target.getRow()][target.getColumn()] = true;		// Mark it on the boolean grid
					ColorPanel.playerArmies[target.getRow()][target.getColumn()] = false;	// Take out any player information from the panel
					target.setPlayerOccupies(false);
				}
			}
			catch (Exception ex){}
		}
		else
		{
			// Subtracts 7 if it is using the AI's side, since the array sizes are different
			if (! currentArmy.isPlayerTile())
			{
				armyCol -= 7;
			}

			ColorPanel target = selectTarget(armyRow, armyCol, currentArmy);		// Chooses a ColorPanel to move to
			move(currentArmy, target);												// Moves entire squad to that panel
			armies.remove(currentArmy);												// Removes old position
			ColorPanel.enemyArmies[currentArmy.getRow()][currentArmy.getColumn()] = false;	// Removes old position from boolean grid

			try
			{
				if (! target.getUnitStorage().peek().isPlayerSide())						// If move occurs successfully
				{
					armies.add(target);														// Track that ColorPanel, since an army is there
					ColorPanel.enemyArmies[target.getRow()][target.getColumn()] = true;		// Mark it on the boolean grid
					target.setPlayerOccupies(false);										// Take out any player information from the panel
					ColorPanel.playerArmies[target.getRow()][target.getColumn()] = false;

					if (playerCities.contains(target))				// If the army moved on to the city
					{
						target.setImage(Resources.rCityDestroyed);	// Destroy the city, take out player icon, and remove it from list
						playerCities.remove(target);

						String destroyedTown = new String("");

						for(String name : ColorPanel.playerTowns.keySet())
						{
							if(target.getRow() == ColorPanel.playerTowns.get(name).getThisRow() && target.getColumn() == ColorPanel.playerTowns.get(name).getThisColumn())
							{
								destroyedTown = name;
								break;
							}
						}

						ColorPanel.playerTowns.remove(destroyedTown);

						if (playerCities.size() == 0)				// If all cities are destroyed, the player lost
						{
							GameGUI.gameOver("You Lose!");
							return true;
						}
					}
				}
			}
			catch (Exception ex){}
		}

		return false;
	}

	private void split(ColorPanel start, ColorPanel end, int units)
	{
		UnitStorage temp = start.getUnitStorage();
		UnitStorage moved = new UnitStorage();

		for (int i = 1;	i <= units; i++)
		{
			moved.addUnit(temp.remove());
		}

		if (temp == null)
		{
			start.setUnitStorage(new UnitStorage());	// If the old UnitStorage was empty, do NOT send in a null value
		}
		else
		{
			start.setUnitStorage(temp);					// Updates the UnitStorage in the ColorPanel that the units came from
		}

		merge(moved, end);			// Merge (add 'moved' into what's in 'end'); end is destination
	}

	private void merge(UnitStorage added, ColorPanel destination)
	{
		if (added.size() == 0)
		{
			return;				// Exit if there is nothing to add.
		}

		// added = queue being added on
		UnitStorage temp = destination.getUnitStorage();

		if (temp == added)
		{
			return;				// UnitStorages will be the same if the AI is on a player city.
		}

		if ((temp.size() == 0) || (temp.peek().isPlayerSide() == added.peek().isPlayerSide()))	// If there is no units, or if they are on the same side
		{
			// merges the queue being added with the queue in 'destination'
			while (! added.isEmpty())
			{
				temp.addUnit(added.remove());
			}
		}
		else if (temp.peek().isPlayerSide() != added.peek().isPlayerSide())		// If the units are from opposing sides
		{
			// The units now fight!
			temp = battle(added, temp, destination);
		}

		// Updates the UnitStorage in the panel where the units have moved to
		destination.setUnitStorage(temp);
	}

	private void move(ColorPanel start, ColorPanel end)
	{
		// Move temp into end(destination), and leaves the starting point with an empty UnitStorage
		UnitStorage temp = start.getUnitStorage();
		merge(temp, end);
		start.setUnitStorage(new UnitStorage());
	}

	private UnitStorage battle(UnitStorage attacker, UnitStorage defender, ColorPanel dest)
	{
		// Both UnitStorages fight until one is exhausted
		while (! defender.isEmpty())
	 	{
	 		while (! attacker.isEmpty())
			{
				Unit attUnit = attacker.peek();
				Unit defUnit = defender.peek();

		   		defUnit.setArea(dest.getLocationDefence());
		   		attUnit.attack(defUnit);
		   		defUnit.setArea(1.0);

		 		if (defUnit.getHP() <= 0)
	   			{
	    			defender.remove();
	   				break;
		   		}

		   		defUnit.attack(attUnit);

	   			if (attUnit.getHP() <= 0)
		   		{
	   				attacker.remove();
	    			break;
		   		}
			}

			if (attacker.isEmpty())
			{
				break;
			}
	 	}

	 	if (! defender.isEmpty())		// If the defenders won, return them
	 	{
	  		return defender;
	 	}

	  	return attacker;	// Otherwise, return the attackers

	}

	private ColorPanel selectTarget(int row, int col, ColorPanel curPos)
	{
		int distance = 100;										// Starts with a high value, so that it works later
		ColorPanel target = new ColorPanel(0 , -1, -1, false);	// Used as a pointer

		// Finds the closest city of the player, based off vertical and horizontal distances
		for (int i = 0; i < playerCities.size(); i++)
		{
			ColorPanel temp = playerCities.get(i);
			int sum, targetRow, targetCol;
			targetRow = temp.getRow();

			if (curPos.isPlayerTile())
			{
				targetCol = temp.getColumn();
				sum = Math.abs(curPos.getColumn() - targetCol) + Math.abs(targetRow - curPos.getRow());
			}
			else
			{
				sum = Math.abs(targetRow - curPos.getRow()) + curPos.getColumn() + (7 - temp.getColumn());	// (7 - col) spaces from the river
			}

			if (sum < distance)
			{
				distance  = sum;
				target = temp;
			}
		}

		if ((target.getRow() == row) && (curPos.isPlayerTile()))	// If the AI army and the targeted city are on the same row, on the player's half
		{
			if (target.getColumn() < col)		// Moves left towards the city
			{
				ColorPanel panel = GameGUI.myHalf[row][col - 1];

				if (! panel.isUncrossable())		// If the panel can be walked on, return it
				{
					return panel;
				}
				else							// If the panel cannot be walked on
				{
					// Move up or down, and avoid uncrossable terrains
					if (row == 0)
					{
						return GameGUI.myHalf[row + 1][col];
					}
					else
					{
						panel = GameGUI.myHalf[row - 1][col - 1];

						if (panel.isUncrossable())
						{
							return GameGUI.myHalf[Math.min(row + 1, 4)][col];
						}
						else
						{
							return GameGUI.myHalf[row - 1][col];
						}
					}
				}
			}
			else if (target.getColumn() > col)		// Moves right towards the city
			{
				ColorPanel panel = GameGUI.myHalf[row][col + 1];

				if (! panel.isUncrossable())		// If the panel can be walked on, return it
				{
					return panel;
				}
				else							// If the panel cannot be walked on
				{
					// Move up or down, and avoid uncrossable terrains
					if (row == 0)
					{
						return GameGUI.myHalf[row + 1][col];
					}
					else
					{
						panel = GameGUI.myHalf[row - 1][col + 1];

						if (panel.isUncrossable())
						{
							return GameGUI.myHalf[Math.min(row + 1, 4)][col];
						}
						else
						{
							return GameGUI.myHalf[row - 1][col];
						}
					}
				}
			}
		}
		else if (target.getRow() == row)		// If the AI army is in the same row, but not on the player's grid
		{
			if (col == 0)		// If the AI army is by the river
			{
				ColorPanel panel = GameGUI.myHalf[row][GameGUI.myHalf[row].length - 1];		// Select the panel on the player's side of the river
				return panel; 																// Near the river, the panel is always grassland
			}
			else		// If the AI army is in the same row, on its own side, and not by the river, go left
			{
				ColorPanel panel = GameGUI.AIHalf[row][col - 1];

				if (! panel.isUncrossable())		// If the panel can be walked on, return it
				{
					return panel;
				}
				else							// If the panel cannot be walked on
				{
					// Move up or down, and avoid uncrossable terrains
					if (row == 0)
					{
						return GameGUI.AIHalf[row + 1][col];
					}
					else
					{
						panel = GameGUI.AIHalf[row - 1][col - 1];

						if (panel.isUncrossable())
						{
							return GameGUI.AIHalf[Math.min(row + 1, 4)][col];
						}
						else
						{
							return GameGUI.AIHalf[row - 1][col];
						}
					}
				}
			}
		}
		else if ((target.getColumn() == col) && (curPos.isPlayerTile()))		// If the AI army is in the same column on the player's side
		{
			if (target.getRow() < row)		// Moves up towards the city
			{
				ColorPanel panel = GameGUI.myHalf[row - 1][col];

				if (! panel.isUncrossable())		// If the panel can be walked on, return it
				{
					return panel;
				}
				else							// If the panel cannot be walked on
				{
					// Move left or right instead, and avoid uncrossable terrain
					if (col == 0)
					{
						return GameGUI.myHalf[row][col + 1];
					}
					else
					{
						panel = GameGUI.myHalf[row - 1][col - 1];

						if (panel.isUncrossable())
						{
							return GameGUI.myHalf[row][Math.min(col + 1, 6)];
						}
						else
						{
							return GameGUI.myHalf[row][col - 1];
						}
					}
				}
			}
			else if (target.getRow() > row)		// Moves down towards the city
			{
				ColorPanel panel = GameGUI.myHalf[row + 1][col];

				if (! panel.isUncrossable())		// If the panel can be walked on, return it
				{
					return panel;
				}
				else							// If the panel cannot be walked on
				{
					// Move left or right instead, and avoid uncrossable terrain
					if (col == 0)
					{
						return GameGUI.myHalf[row][col + 1];
					}
					else
					{
						panel = GameGUI.myHalf[row + 1][col - 1];

						if (panel.isUncrossable())
						{
							return GameGUI.myHalf[row][Math.min(col + 1, 6)];
						}
						else
						{
							return GameGUI.myHalf[row][col - 1];
						}
					}
				}
			}
		}
		else		// If the row or column aren't the same
		{
			int num = gen.nextInt(2);

			if (num == 0)		// Changes its column
			{
				if (curPos.isPlayerTile())		// If the AI army is on the player's grid
				{
					if (target.getColumn() < col)		// Moves left towards the city
					{
						ColorPanel panel = GameGUI.myHalf[row][col - 1];

						if (! panel.isUncrossable())		// If the panel can be walked on, return it
						{
							return panel;
						}
						else							// If the panel cannot be walked on
						{
							// Move up or down, and avoid uncrossable terrains
							if (row == 0)
							{
								return GameGUI.myHalf[row + 1][col];
							}
							else
							{
								panel = GameGUI.myHalf[row - 1][col - 1];

								if (panel.isUncrossable())
								{
									return GameGUI.myHalf[Math.min(row + 1, 4)][col];
								}
								else
								{
									return GameGUI.myHalf[row - 1][col];
								}
							}
						}
					}
					else if (target.getColumn() > col)		// Moves right towards the city
					{
						ColorPanel panel = GameGUI.myHalf[row][col + 1];

						if (! panel.isUncrossable())		// If the panel can be walked on, return it
						{
							return panel;
						}
						else							// If the panel cannot be walked on
						{
							// Move up or down, and avoid uncrossable terrains
							if (row == 0)
							{
								return GameGUI.myHalf[row + 1][col];
							}
							else
							{
								panel = GameGUI.myHalf[row - 1][col + 1];

								if (panel.isUncrossable())
								{
									return GameGUI.myHalf[Math.min(row + 1, 4)][col];
								}
								else
								{
									return GameGUI.myHalf[row - 1][col];
								}
							}
						}
					}
				}
				else		// If the AI army is on its own grid
				{
					if (col == 0)		// If the AI army is by the river, cross over the river
					{
						ColorPanel panel = GameGUI.myHalf[row][GameGUI.myHalf[row].length - 1];
						return panel; 															// Near the river, the panel is always grassland
					}
					else		// Moves left if the AI army is in any other position on its own side
					{
						ColorPanel panel = GameGUI.AIHalf[row][col - 1];

						if (! panel.isUncrossable())		// If the panel can be walked on, return it
						{
							return panel;
						}
						else							// If the panel cannot be walked on
						{
							// Move up or down, and avoid uncrossable terrains
							if (row == 0)
							{
								return GameGUI.AIHalf[row + 1][col];
							}
							else
							{
								panel = GameGUI.AIHalf[row - 1][col - 1];

								if (panel.isUncrossable())
								{
									return GameGUI.AIHalf[Math.min(row + 1, 4)][col];
								}
								else
								{
									return GameGUI.AIHalf[row - 1][col];
								}
							}
						}
					}
				}
			}
			else		// Changes its row
			{
				if (curPos.isPlayerTile())		// If the AI army is on the player's grid
				{
					if (target.getRow() < row)		// Moves up towards the city
					{
						ColorPanel panel = GameGUI.myHalf[row - 1][col];

						if (! panel.isUncrossable())		// If the panel can be walked on, return it
						{
							return panel;
						}
						else							// If the panel cannot be walked on
						{
							// Move left or right instead, and avoid uncrossable terrain
							if (col == 0)
							{
								return GameGUI.myHalf[row][col + 1];
							}
							else
							{
								panel = GameGUI.myHalf[row - 1][col - 1];

								if (panel.isUncrossable())
								{
									return GameGUI.myHalf[row][Math.min(col + 1, 6)];
								}
								else
								{
									return GameGUI.myHalf[row][col - 1];
								}
							}
						}
					}
					else if (target.getRow() > row)		// Moves down towards the city
					{
						ColorPanel panel = GameGUI.myHalf[row + 1][col];

						if (! panel.isUncrossable())		// If the panel can be walked on, return it
						{
							return panel;
						}
						else							// If the panel cannot be walked on
						{
							// Move left or right instead, and avoid uncrossable terrain
							if (col == 0)
							{
								return GameGUI.myHalf[row][col + 1];
							}
							else
							{
								panel = GameGUI.myHalf[row + 1][col - 1];

								if (panel.isUncrossable())
								{
									return GameGUI.myHalf[row][Math.min(col + 1, 6)];
								}
								else
								{
									return GameGUI.myHalf[row][col - 1];
								}
							}
						}
					}
				}
				else		// If the AI army is on its own grid
				{
					if (target.getRow() < row)		// Moves up towards the city
					{
						ColorPanel panel = GameGUI.AIHalf[row - 1][col];

						if (! panel.isUncrossable())		// If the panel can be walked on, return it
						{
							return panel;
						}
						else							// If the panel cannot be walked on
						{
							// Move left or right instead, and avoid uncrossable terrain
							if (col == 0)
							{
								return GameGUI.myHalf[row][GameGUI.myHalf[row].length - 1];
							}
							else
							{
								panel = GameGUI.AIHalf[row - 1][col - 1];

								if (panel.isUncrossable())
								{
									return GameGUI.AIHalf[row][Math.min(col + 1, 6)];
								}
								else
								{
									return GameGUI.AIHalf[row][col - 1];
								}
							}
						}
					}
					else if (target.getRow() > row)		// Moves down towards the city
					{
						ColorPanel panel = GameGUI.AIHalf[row + 1][col];

						if (! panel.isUncrossable())		// If the panel can be walked on, return it
						{
							return panel;
						}
						else							// If the panel cannot be walked on
						{
							// Move left or right instead, and avoid uncrossable terrain
							if (col == 0)
							{
								return GameGUI.myHalf[row][GameGUI.myHalf[row].length - 1];
							}
							else
							{
								panel = GameGUI.AIHalf[row + 1][col - 1];

								if (panel.isUncrossable())
								{
									return GameGUI.AIHalf[row][Math.min(col + 1, 6)];
								}
								else
								{
									return GameGUI.AIHalf[row][col - 1];
								}
							}
						}
					}
				}
			}
		}

		return curPos;		// This line will only be reached if the player is already on the city.
	}

	public void removeAITown(ColorPanel c)
	{
		cities.remove(c);
	}

	public void removeAIArmy(ColorPanel c)
	{
		armies.remove(c);
	}
}
