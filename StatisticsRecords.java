/**
 * @(#)StatisticsRecords.java
 *
 *
 * @author
 * @version 1.00 2012/1/17
 */



public class StatisticsRecords {
	private int SNumofDays;
	private int SNumofTurns;
	private int SNumofPopulation;
	//private int SNumofNewGamePressed;
	private String SAmountofMoney;

	public StatisticsRecords(){
	    SNumofDays = 1;
		SNumofTurns = 0;
		SNumofPopulation = 28;
		//SNumofNewGamePressed = 0;
	    SAmountofMoney = "";
	}

	public void setNumofDays(int NumDays){
		int temp1 = SNumofDays;
		SNumofDays = NumDays;
	}


	public void setPopulation(int pop){
		SNumofPopulation = pop;
	}

	public void setAmountofMoney(String money){
		SAmountofMoney = money;


	}

	public String toString(){
		String str = "Number of Days: " + SNumofDays + "\n" +
					 "Population: " + SNumofPopulation + "\n" +
					 "Gold: " + SAmountofMoney + "\n";
		return str;
	}
}
