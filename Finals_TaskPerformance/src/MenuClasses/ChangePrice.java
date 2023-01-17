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

public class ChangePrice 
{

	String mainPath = ShopData.mainPath;
	String drinkPath = ShopData.drinkPath;
	String dessertPath = ShopData.desertPath;
	String specialPath = ShopData.specialPath;
	
	public void changePriceMenu(Scanner scan) throws FileNotFoundException
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
				BorderBox.printLine("What dish you want to change price?");
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
			
			do {
				BorderBox.printLine("What price do you want it to change?");
				BorderBox.printLine("Enter: ");
				BorderBox.printInput();
				price = scan.nextLine();
				BorderBox.lineDown();
				
				if(Pattern.matches("\\d+", price)) 
				{
					break;
				}
				BorderBox.printLine("Please enter integers only");
			} while(true);
			
			switch (choice.toLowerCase()) 
			{
			case "main dish":
				mainDish(dish, price);
				break;
			case "drinks":
				drinkDish(dish, price);
				break;
			case "dessert":
				dessertDish(dish, price);
				break;
			case "special menu":
				specialDish(dish, price);
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
	
	void mainDish(String dish, String price) throws FileNotFoundException
	{
		File fileMain = new File(mainPath);
		Scanner scMain = new Scanner(fileMain);
		
		printExist(scMain, dish, price, mainPath);
	}
	
	void drinkDish(String dish, String price) throws FileNotFoundException
	{
		File fileDrink = new File(drinkPath);
		Scanner scDrink = new Scanner(fileDrink);

		printExist(scDrink, dish, price, drinkPath);
	}
	
	void dessertDish(String dish, String price) throws FileNotFoundException
	{
		File fileDessert = new File(dessertPath);
		Scanner scMain = new Scanner(fileDessert);
		
		printExist(scMain, dish, price, dessertPath);
	}
	
	void specialDish(String dish, String price) throws FileNotFoundException
	{
		File fileSpecial = new File(specialPath);
		Scanner scSpecial = new Scanner(fileSpecial);

		printExist(scSpecial, dish, price, specialPath);
	}
	
	boolean isExisting(Scanner sc, String dish, String price, String path)
	{
		String line;
		String[] temp = new String[2];
		StringBuilder txtTemp = new StringBuilder();
		boolean isChanged = false;
		
		while (sc.hasNextLine()) 
		{
			line = sc.nextLine();
			temp = line.split(":");
			
			if(dish.equalsIgnoreCase(temp[0]))
			{
				txtTemp.append(temp[0] + ":" + price + ":" + temp[2]);
				isChanged = true;
			}
			else
			{
				txtTemp.append(line);
			}
			
			txtTemp.append("\n");
		}
		try 
		{
			write(txtTemp, path);
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isChanged;
	}
	
	void printExist(Scanner sc, String dish, String price, String path)
	{
		if(isExisting(sc, dish, price, path))
		{
			BorderBox.printLine("You've successfully change the price of " + dish +" to " + price + " ");
		}
		else
		{
			BorderBox.printLine("The dish " + dish + " you entered does not exist");
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
