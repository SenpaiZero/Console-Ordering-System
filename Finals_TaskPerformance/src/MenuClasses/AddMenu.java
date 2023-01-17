package MenuClasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import UserInterfaceClasses.BorderBox;
import UserInterfaceClasses.Shop;
import mainPackage.ShopData;

public class AddMenu 
{

	String mainPath = ShopData.mainPath;
	String drinkPath = ShopData.drinkPath;
	String dessertPath = ShopData.desertPath;
	String specialPath = ShopData.specialPath;
	
	public void AddNewMenu(Scanner scan) throws IOException
	{
		String choice;
		String dish, price;
		do
		{
			do 
			{
				BorderBox.lineUp();
				BorderBox.printLine("[MAIN DISH]  :  [DRINKS]  :  [DESSERT]  :  [SPECIAL MENU]  :  [GO BACK]");
				BorderBox.printLine("Enter: ");
				BorderBox.printInput();
				choice = scan.nextLine();
				BorderBox.lineDown();
				
				//Checks the user input
				if(choice.matches("main dish|drinks|dessert|special menu|go back"))
				{
					break;
				}
				
				BorderBox.printLine("Please enter the correct option");
			} while (true);
			
			switch (choice.toLowerCase()) 
			{
			case "main dish":
				BorderBox.printLine(Arrays.toString(ShopData.main.toArray()));
				break;
			case "drinks":
				BorderBox.printLine(Arrays.toString(ShopData.drink.toArray()));
				break;
			case "dessert":
				BorderBox.printLine(Arrays.toString(ShopData.dessert.toArray()));
				break;
			case "special menu":
				BorderBox.printLine(Arrays.toString(ShopData.special.toArray()));
				break;
			}
			
			if(choice.equalsIgnoreCase("go back"))
			{
				break;
			}

			boolean isExit;
			do
			{
				isExit = false;
				BorderBox.lineUp();
				BorderBox.printLine("Enter what dish and price you want to add");
				BorderBox.printLine("Dish: ");
				BorderBox.printInput();
				dish = scan.nextLine();
				BorderBox.lineDown();
				
				if(dish.toLowerCase().matches("exit|go back"))
				{
					isExit = true;
					break;
				}
				
				if(checkMenu(dish) == true)
				{
					break;
				}
				BorderBox.printLine("The dish " + dish + " you entered already exist");
			} while(true);
			
			if(isExit == true)
			{
				break;
			}
			
			do {
				isExit = false;
				BorderBox.printLine("Price: ");
				BorderBox.printInput();
				price = scan.nextLine();
				
				if(dish.toLowerCase().matches("exit|go back"))
				{
					isExit = true;
					break;
				}
				
				if(Pattern.matches("\\d+", price)) 
				{
					break;
				}
				BorderBox.printLine("Please enter integers only");
			} while(true);
			
			if(isExit == true)
			{
				break;
			}
			
			switch (choice.toLowerCase()) 
			{
			case "main dish":
				mainDish(dish, price);
				break;
			case "drinks":
				drinks(dish, price);
				break;
			case "dessert":
				desserts(dish, price);
				break;
			case "special menu":
				specialMenu(dish, price);
				break;
			}
		} while(true);
	}
	
	boolean checkMenu(String input)
	{

		if(ShopData.main.stream().anyMatch(input::equalsIgnoreCase))
		{
			return false;
		}
		
		if(ShopData.drink.stream().anyMatch(input::equalsIgnoreCase))
		{
			return false;
		}
		
		if(ShopData.dessert.stream().anyMatch(input::equalsIgnoreCase))
		{
			return false;
		}
		
		if(ShopData.special.stream().anyMatch(input::equalsIgnoreCase))
		{
			return false;
		}
		
		return true;
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
		File fileDessert = new File(dessertPath);
		Scanner scDessert = new Scanner(fileDessert);
		
		if(isAvail(scDessert, dish))
		{
			write(dish, price, dessertPath);
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
				BorderBox.printLine("The dish " + dish + " you entered already exist");
				return false;
			} 
		}
		return true;
	}
	
	void write(String dish, String price, String path) throws IOException
	{
		BorderBox.printLine("You've successfully added a new dish! ");
		FileWriter writer = new FileWriter(path, true);
		writer.write(dish + ":" + price + ":Available" + "\n");
		writer.flush();

		ShopData aData = new ShopData();
		aData.setDishes();
	}
}
