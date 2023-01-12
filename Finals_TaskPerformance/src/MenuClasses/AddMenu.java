package MenuClasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import UserInterfaceClasses.BorderBox;

public class AddMenu {
	
	static String mainPath = ".\\src\\Data\\mainMenu.txt";
	static String drinkPath = ".\\src\\Data\\drinkMenu.txt";
	static String desertPath = ".\\src\\Data\\dessertsMenu.txt";
	static String specialPath = ".\\src\\Data\\specialMenu.txt";
	
	public void AddNewMenu(Scanner scan) throws IOException
	{
		String choice;
		String dish, price;
		BorderBox.lineUp();
		BorderBox.printLine("[1] MAIN DISH  [2] DRINKS  [3] DESSERT  [4] SPECIAL MENU  [5] GO BACK");
		BorderBox.lineDown();
		do 
		{
			BorderBox.lineUp();
			BorderBox.printLine("Enter: ");
			BorderBox.printInput();
			choice = scan.nextLine();
			BorderBox.lineDown();
			
			//Checks if the user input is 1 to 4 numbers and only 1 character
			if(Pattern.matches("[1-5]{1}", choice))
			{
				break;
			}
			
			BorderBox.printLine("Please enter the correct option");
		} while (true);
		if(!choice.equals("5"))
		{
			BorderBox.lineUp();
			BorderBox.printLine("Enter what dish and price you want to add");
			BorderBox.printLine("Dish: ");
			BorderBox.printInput();
			dish = scan.nextLine();
			BorderBox.lineDown();
			do {
				BorderBox.printLine("Price: ");
				BorderBox.printInput();
				price = scan.nextLine();
				
				if(Pattern.matches("\\d+", price)) 
				{
					break;
				}
				BorderBox.printLine("Please enter integers only");
			} while(true);
			
			switch (Integer.valueOf(choice)) 
			{
			case 1:
				mainDish(dish, price);
				break;
			case 2:
				drinks(dish, price);
				break;
			case 3:
				desserts(dish, price);
				break;
			case 4:
				specialMenu(dish, price);
				break;
			}
		}
	}
	
	void mainDish(String dish, String price) throws IOException
	{
		File fileMain = new File(mainPath);
		Scanner scMain = new Scanner(fileMain);
		
		if(isAvail(scMain, dish))
		{
			write(dish, price, mainPath);
		}
	}
	
	void drinks(String dish, String price) throws IOException
	{
		File fileDrink = new File(drinkPath);
		Scanner scDrink = new Scanner(fileDrink);
		
		if(isAvail(scDrink, dish))
		{
			write(dish, price, drinkPath);
		}
	}
	
	public void desserts(String dish, String price) throws IOException
	{
		File fileDessert = new File(desertPath);
		Scanner scDessert = new Scanner(fileDessert);
		
		if(isAvail(scDessert, dish))
		{
			write(dish, price, desertPath);
		}
	}
	
	void specialMenu(String dish, String price) throws IOException
	{
		File fileSpecial = new File(specialPath);
		Scanner scSpecial = new Scanner(fileSpecial);
		
		if(isAvail(scSpecial, dish))
		{
			write(dish, price, specialPath);
		}
	}
	
	boolean isAvail(Scanner sc, String dish)
	{
		String[] data = new String[2];
		while (sc.hasNextLine()) 
		{
			data = sc.nextLine().split(":");
			//Check if dish already exist
			if(data[0].equalsIgnoreCase(dish))
			{
				//Stop the code (return) pag may katulad
				return false;
			} 
		}
		return true;
	}
	
	void write(String dish, String price, String path) throws IOException
	{
		BorderBox.printLine("You've successfully added a new dish! ");
		FileWriter writer = new FileWriter(path, true);
		writer.write("\n" + dish + ":" + price + ":Available");
		writer.flush();
	}
}
