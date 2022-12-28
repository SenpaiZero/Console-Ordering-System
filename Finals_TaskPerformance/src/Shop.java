import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop extends shopData
{
	public static String foodPath = ".\\src\\Data\\foodMenu.txt";
	public static String drinkPath = ".\\src\\Data\\drinkMenu.txt";

	//Made a global variable para di na need mag re-create ng same variable sa ibang method
	//and to make sure na iisa lang value ng data kada function
	static List<String> food = new ArrayList<String>();
	static List<String> foodPrice = new ArrayList<String>();
	static List<String> drink = new ArrayList<String>();
	static List<String> drinkPrice = new ArrayList<String>();

	//Call the method each time a user buys something
	//each call adds a total profit
	public void customerBill(String cart, int quantity) 
	{
		//Converting list to array
		String[] foodArr = food.toArray(new String[food.size()]);
		String[] foodPriceArr = foodPrice.toArray(new String[foodPrice.size()]);
		String[] drinkArr = drink.toArray(new String[drink.size()]);
		String[] drinkPriceArr = drinkPrice.toArray(new String[drinkPrice.size()]);

		//Checking the array if it has the same string
		//as the user input
		//add money to totalProfit pag may katulad
		for (int i = 0; i < foodArr.length; i++) 
		{
			if(cart.equalsIgnoreCase(foodArr[i])) 
			{
				setTotalProfit(
						getTotalProfit() + (Double.valueOf(foodPriceArr[i]) * quantity));
			}
		}
		
		//same sa food
		for (int i = 0; i < drinkArr.length; i++) 
		{
			if(cart.equalsIgnoreCase(drinkArr[i])) 
			{
				setTotalProfit(
						getTotalProfit() + (Double.valueOf(drinkPriceArr[i]) * quantity));
			}
		}
	}	
	
	public void printMenu(String[] foods, String[] foodPrice, String[] drinks, String[] drinkPrice) 
	{
		String printLine = "=================================================";
		System.out.println(printLine);
		System.out.printf("%s %20s %10s %15s", "|", "FOOD", "PRICE", "|");
		System.out.println("\n" + printLine);
		
		for (int i = 0; i < foods.length; i++) 
		{
			System.out.printf("%s %20s %10s %15s %n", "|", foods[i], foodPrice[i], "|");
		}
		
		System.out.println(printLine + "\n\n");

		System.out.println(printLine);
		System.out.printf("%s %20s %10s %15s", "|", "DRINKS", "PRICE", "|");
		System.out.println("\n" + printLine);
		
		for (int i = 0; i < drinks.length; i++)
		{
			System.out.printf("%s %20s %10s %15s %n", "|", drinks[i], drinkPrice[i], "|");
		}
		
		System.out.println(printLine);
	}
	
	public void printMenu() throws FileNotFoundException 
	{
		File fileFood = new File(foodPath);
		File fileDrink = new File(drinkPath);
		Scanner scFood = new Scanner(fileFood);
		Scanner scDrink = new Scanner(fileDrink);
		
		String[] line = new String[1];
		
		//Get line from txt file
		//stop if blank
		while (scFood.hasNextLine()) 
		{
			line = scFood.nextLine().split(":");
			food.add(line[0]);
			foodPrice.add(line[1]);
		}
		

		//Get line from txt file
		//stop if blank
		while (scDrink.hasNextLine()) 
		{
			//each line sa txt file may menu:price
			//seseparate into 2 index array
			//tapos add sa list ung array
			//index 1: menu
			//index 2: price
			line = scDrink.nextLine().split(":");
			drink.add(line[0]);
			drinkPrice.add(line[1]);
		}
		
		if(food.size() <= 0 ||  drink.size() <= 0)
		{
			System.out.println("Something went wrong. Please Try again!");
			System.exit(0);
		}
		printMenu(food.toArray(new String[food.size()]), foodPrice.toArray(new String[foodPrice.size()]),
				drink.toArray(new String[drink.size()]), drinkPrice.toArray(new String[drinkPrice.size()]));
	}
}

class shopData
{
	private double totalProfit;
	
	public shopData() 
	{
		totalProfit = 0;
	}
	
	public void setTotalProfit(double totalProfit)
	{
		this.totalProfit = totalProfit;
	}
	
	public double getTotalProfit() 
	{
		return totalProfit;
	}
}
