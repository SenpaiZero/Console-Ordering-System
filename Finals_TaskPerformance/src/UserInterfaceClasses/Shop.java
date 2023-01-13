package UserInterfaceClasses;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop
{
	public static String mainPath = ".\\src\\Data\\mainMenu.txt";
	public static String drinkPath = ".\\src\\Data\\drinkMenu.txt";
	public static String desertPath = ".\\src\\Data\\dessertsMenu.txt";
	public static String specialPath = ".\\src\\Data\\specialMenu.txt";

	//Made a global variable para di na need mag re-create ng same variable sa ibang method
	//and to make sure na iisa lang value ng data kada function
	public static List<String> main = new ArrayList<String>();
	public static List<String> mainPrice = new ArrayList<String>();
	public static List<String> mainAvail = new ArrayList<String>();
	
	public static List<String> drink = new ArrayList<String>();
	public static List<String> drinkPrice = new ArrayList<String>();
	public static List<String> drinkAvail = new ArrayList<String>();
	
	public static List<String> dessert = new ArrayList<String>();
	public static List<String> dessertPrice = new ArrayList<String>();
	public static List<String> dessertAvail = new ArrayList<String>();
	
	public static List<String> special = new ArrayList<String>();
	public static List<String> specialPrice = new ArrayList<String>();
	public static List<String> specialAvail = new ArrayList<String>();
	
	public void printMenu(
			String[] main, String[] mainPrice, String[] mainAvail, 
			String[] drinks, String[] drinkPrice, String[] drinkAvail,
			String[] special, String[] specialPrice, String[] specialAvail,
			String[] desserts, String[] dessertsPrice, String[] dessertAvail) 
	{
		String printLine = "     ╔══════════════════════════════════════════════════════════════════════════════╗";
		String printLine2 = "     ╚══════════════════════════════════════════════════════════════════════════════╝";
		System.out.printf("%s %88s %n",printLine, printLine);
		printTitle("MAIN DISHES", true);
		printTitle("DRINKS", false);
		System.out.printf("%s %88s %n",printLine2, printLine2);
		int max1 = Math.max(main.length, drinks.length);
		
		for (int i = 0; i < max1; i++) 
		{
			System.out.printf("     %s %30s %10s %20s %15s", "║", main[i], mainPrice[i], mainAvail[i], "║");
			
			if(drinks.length > i)
			{
				System.out.printf("     %5s %30s %10s %20s %15s %n", "║", drinks[i], drinkPrice[i], drinkAvail[i], "║");
			}
			else if(drinks.length <= i)
			{
				System.out.printf("     %5s %30s %10s %20s %15s %n", "║", "", "", "", "║");
			}
		}
		System.out.printf("%s %88s %n",printLine, printLine);
		printTitle("SPECIAL MENU", true);
		printTitle("DESSERTS", false);
		System.out.printf("%s %88s %n",printLine2, printLine2);
		
		int max2 = Math.max(special.length, desserts.length);
		for (int i = 0; i < max2; i++) 
		{
			System.out.printf("     %s %30s %10s %20s %15s", "║", special[i], specialPrice[i], specialAvail[i], "║");
			
			if(desserts.length > i)
			{
				System.out.printf("     %5s %30s %10s %20s %15s %n", "║", desserts[i], dessertsPrice[i], dessertAvail[i], "║");
			}
			else if(desserts.length <= i)
			{
				System.out.printf("     %5s %30s %10s %20s %15s %n", "║", "", "", "", "║");
			}
		}

		System.out.printf("%5s %88s %n",printLine2, printLine2);
	}
	
	public void printMenu() throws FileNotFoundException 
	{
		File fileMain = new File(mainPath);
		File fileDrink = new File(drinkPath);
		File fileDessert = new File(desertPath);
		File fileSpecial = new File(specialPath);
		Scanner scMain = new Scanner(fileMain);
		Scanner scDrink = new Scanner(fileDrink);
		Scanner scDessert = new Scanner(fileDessert);
		Scanner scSpecial = new Scanner(fileSpecial);
		
		String[] line = new String[2];
		
		//Get line from txt file
		//stop if blank
		while (scMain.hasNextLine()) 
		{
			line = scMain.nextLine().split(":");
			main.add(line[0]);
			mainPrice.add(line[1]);
			mainAvail.add(line[2]);
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
			drinkAvail.add(line[2]);
		}
		
		while (scDessert.hasNextLine()) 
		{
			line = scDessert.nextLine().split(":");
			dessert.add(line[0]);
			dessertPrice.add(line[1]);
			dessertAvail.add(line[2]);
		}
		
		while (scSpecial.hasNextLine()) 
		{
			line = scSpecial.nextLine().split(":");
			special.add(line[0]);
			specialPrice.add(line[1]);
			specialAvail.add(line[2]);
		}
		if(main.size() <= 0 ||  drink.size() <= 0)
		{
			System.out.println("Something went wrong. Please Try again!");
			System.exit(0);
		}
		printMenu(
				//Main
				main.toArray(new String[main.size()]), 
				mainPrice.toArray(new String[mainPrice.size()]),
				mainAvail.toArray(new String[mainAvail.size()]),
				//Drink
				drink.toArray(new String[drink.size()]), 
				drinkPrice.toArray(new String[drinkPrice.size()]),
				drinkAvail.toArray(new String[drinkAvail.size()]),
				//Special
				special.toArray(new String[special.size()]), 
				specialPrice.toArray(new String[specialPrice.size()]),
				specialAvail.toArray(new String[specialAvail.size()]),
				//Dessers
				dessert.toArray(new String[dessert.size()]),
				dessertPrice.toArray(new String[dessertPrice.size()]),
				dessertAvail.toArray(new String[dessertAvail.size()]));
	}
	
	static void printTitle(String title, boolean isFront)
	{
		if(isFront)
		{
			System.out.printf("     %s %30s %10s %20s %15s", "║", title, "PRICE", "AVAILABILITY", "║");
		}
		else
		{
			System.out.printf("     %5s %30s %10s %20s %15s%n", "║", title, "PRICE", "AVAILABILITY", "║");
		}
	}
}
