 package mainPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShopData 
{
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
	
	//Path global variables
	public static String mainPath = ".\\src\\Data\\mainMenu.txt";
	public static String drinkPath = ".\\src\\Data\\drinkMenu.txt";
	public static String desertPath = ".\\src\\Data\\dessertsMenu.txt";
	public static String specialPath = ".\\src\\Data\\specialMenu.txt";
	
	public ShopData() throws FileNotFoundException {
		setDishes();
	}
	
	public void setDishes() throws FileNotFoundException
	{
		clearDishData();
		
		File fileMain = new File(mainPath);
		File fileDrink = new File(drinkPath);
		File fileDessert = new File(desertPath);
		File fileSpecial = new File(specialPath);
		Scanner scMain = new Scanner(fileMain);
		Scanner scDrink = new Scanner(fileDrink);
		Scanner scDessert = new Scanner(fileDessert);
		Scanner scSpecial = new Scanner(fileSpecial);
		
		while (scMain.hasNextLine()) 
		{
			String[] line = new String[2];
			line = scMain.nextLine().split(":");
			main.add(line[0]);
			mainPrice.add(line[1]);
			mainAvail.add(line[2]);
		}
		while (scDrink.hasNextLine()) 
		{
			String[] line = new String[2];
			line = scDrink.nextLine().split(":");
			drink.add(line[0]);
			drinkPrice.add(line[1]);
			drinkAvail.add(line[2]);
		}
		
		while (scDessert.hasNextLine()) 
		{
			String[] line = new String[2];
			line = scDessert.nextLine().split(":");
			dessert.add(line[0]);
			dessertPrice.add(line[1]);
			dessertAvail.add(line[2]);
		}
		
		while (scSpecial.hasNextLine()) 
		{
			String[] line = new String[2];
			line = scSpecial.nextLine().split(":");
			special.add(line[0]);
			specialPrice.add(line[1]);
			specialAvail.add(line[2]);
		}
	}
	
	void clearDishData()
	{

		main.clear();
		mainPrice.clear();
		mainAvail.clear();

		drink.clear();
		drinkPrice.clear();
		drinkAvail.clear();
		
		dessert.clear();
		dessertPrice.clear();
		dessertAvail.clear();
		
		special.clear();
		specialPrice.clear();
		specialAvail.clear();
	}
}
