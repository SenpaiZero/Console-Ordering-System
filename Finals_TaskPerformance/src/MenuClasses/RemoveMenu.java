package MenuClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import UserInterfaceClasses.BorderBox;
import UserInterfaceClasses.Shop;
import mainPackage.ShopData;

public class RemoveMenu
{

	String mainPath = ShopData.mainPath;
	String drinkPath = ShopData.drinkPath;
	String dessertPath = ShopData.desertPath;
	String specialPath = ShopData.specialPath;
	
	public void removeExistingMenu(Scanner scan) throws FileNotFoundException
	{
		String choice;
		String dish;
			
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
				BorderBox.printLine("What dish you want to remove?");
				BorderBox.printLine("Enter: ");
				BorderBox.printInput();
				dish = scan.nextLine();
				BorderBox.lineDown();
				
				if(dish.toLowerCase().matches("exit|go back"))
				{
					isExit = true;
					break;
				}
				else if(checkMenu(dish))
				{
					break;
				}
				
			BorderBox.printLine("The dish \"" + dish + "\" you entered does not exist");
			} while(true);
			
			if(isExit == true)
			{
				break;
			}
			
			switch (choice.toLowerCase()) 
			{
			case "main dish":
				mainDish(dish);
				break;
			case "drinks":
				drinkDish(dish);
				break;
			case "dessert":
				dessertDish(dish);
				break;
			case "special menu":
				specialDish(dish);
				break;
			}
		} while(true);
	}
	
	boolean checkMenu(String input)
	{

		if(ShopData.main.stream().anyMatch(input::equalsIgnoreCase))
		{
			return true;
		}
		
		if(ShopData.drink.stream().anyMatch(input::equalsIgnoreCase))
		{
			return true;
		}
		
		if(ShopData.dessert.stream().anyMatch(input::equalsIgnoreCase))
		{
			return true;
		}
		
		if(ShopData.special.stream().anyMatch(input::equalsIgnoreCase))
		{
			return true;
		}
		
		return false;
	}
	
	void mainDish(String dish) throws FileNotFoundException
	{
		File fileMain = new File(mainPath);
		Scanner scMain = new Scanner(fileMain);
		
		printExist(scMain, dish, mainPath);
	}
	
	void drinkDish(String dish) throws FileNotFoundException
	{
		File fileDrink = new File(drinkPath);
		Scanner scDrink = new Scanner(fileDrink);

		printExist(scDrink, dish, drinkPath);
	}
	
	void dessertDish(String dish) throws FileNotFoundException
	{
		File fileDessert = new File(dessertPath);
		Scanner scMain = new Scanner(fileDessert);
		
		printExist(scMain, dish, dessertPath);
	}
	
	void specialDish(String dish) throws FileNotFoundException
	{
		File fileSpecial = new File(specialPath);
		Scanner scSpecial = new Scanner(fileSpecial);

		printExist(scSpecial, dish, specialPath);
	}
	
	boolean isExisting(Scanner sc, String dish, String path)
	{
		String line;
		String[] temp = new String[2];
		StringBuilder txtTemp = new StringBuilder();
		boolean isRemove = false;
		
		while (sc.hasNextLine()) 
		{
			line = sc.nextLine();
			temp = line.split(":");
			
			if(dish.equalsIgnoreCase(temp[0]))
			{
				txtTemp.append("");
				isRemove = true;
			}
			else
			{
				txtTemp.append(line + "\n");
			}
		}
		try 
		{
			write(txtTemp, path);
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isRemove;
	}
	
	void printExist(Scanner sc, String dish, String path)
	{
		if(isExisting(sc, dish, path))
		{
			BorderBox.printLine("You've successfully removed " + dish);
		}
	}
	
	void write(StringBuilder txtWrite, String path) throws IOException
	{
		FileWriter writer = new FileWriter(path);
		writer.write(String.valueOf(txtWrite));
		writer.flush();

		ShopData aData = new ShopData();
		aData.setDishes();
	}
}
