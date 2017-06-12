/*By: Coding Team
 *Integrated Software Development
 *CEO: Mr. Ryan
 *Due: Thursday, January 12, 2012 */

 import java.util.*;


public class UnitStorage					//Puts the units in their arrays/list/army
{
	protected LinkedList<Unit> list;		//We use LinkedList because Tyrone said so

	public UnitStorage()
	{
		list = new LinkedList<Unit>();
	}

	public boolean addUnit(Unit element)	//Makes the first unit stay in front
	{
		list.addFirst(element);				//Add to front
		return true;
	}

	public boolean isEmpty()				//If list is empty, no army
	{
		return list.isEmpty();
	}

	public Unit peek()						//Gets the first Unit
	{
		if(list.isEmpty())					//If no Unit in list
		{
			return null;					//Noone is there
		}

		return list.getFirst();				//Peek at the first  unit
	}

	public Unit remove()
	{
		if(list.isEmpty())						//No unit left
		{
			throw new NoSuchElementException();	//Can't remove no one, send error
		}

		return list.removeFirst();			//Death of Unit, moved to Valhalla List
	}

	public int size()
	{
		return list.size();					//How many troops live
	}

	public int archerAmount()				//How many Archerers in your army
	{
		int amount = 0;						//Default: No Archerers

		for(Object check : list)			//Loops until every object in list is hit
		{
			if(check instanceof Archer)		//If the object is an Archerer
			{
				amount++;					//Add 1 Archerer to the count
			}
		}

		return amount;						//How many Archerers there are in your army
	}

	public int mageAmount()					//Number of Mages in your army
	{
		int amount = 0;						//Default: no Mageses

		for(Object check : list)			//Loops until every object in list is hit
		{
			if(check instanceof Mage)		//If the Object is a Mage
			{
				amount++;					//Add 1 Mage to the count
			}
		}

		return amount;						//How many Mages
	}

	public int warriorAmount()				//How many Warriors
	{
		int amount = 0;						//Default: no Warriors

		for(Object check : list)			//Loops until every object in list is hit
		{
			if(check instanceof Warrior)	//If Object is a Warrior
			{
				amount++;					//Add 1 to the count
			}
		}

		return amount;						//How many Warriors
	}
}
