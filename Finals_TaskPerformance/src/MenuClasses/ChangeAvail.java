package MenuClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import UserInterfaceClasses.AdminUI;
import UserInterfaceClasses.BorderBox;
import UserInterfaceClasses.Shop;
import mainPackage.Admin;
import mainPackage.ShopData;

public class ChangeAvail 
{

	String mainPath = ShopData.mainPath;
	String drinkPath = ShopData.drinkPath;
	String dessertPath = ShopData.desertPath;
	String specialPath = ShopData.specialPath;
	
	public void changeAvailability(Scanner scan) throws FileNotFoundException
	{
		String choice;
		String dish, avail;
		
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
				
				if(choice.toLowerCase().matches("main dish|drinks|dessert|special menu|go back"))
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
			BorderBox.printLine("What dish you want to change the availability?");
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
				BorderBox.lineUp();
				BorderBox.printLine("Please choose");
				BorderBox.printLine("[AVAILABLE]  :  [UNAVAILABLE]");
				BorderBox.printLine("Enter: ");
				BorderBox.printInput();
				avail = scan.nextLine();
				BorderBox.lineDown();
				
				
				if(avail.toLowerCase().matches("available|unavailable")) 
				{
					break;
				}
				BorderBox.printLine("Please enter a valid input");
			} while(true);
			
			switch (choice.toLowerCase()) 
			{
			case "main dish":
				mainDish(dish, avail);
				break;
			case "drinks":
				drinkDish(dish, avail);
				break;
			case "dessert":
				dessertDish(dish, avail);
				break;
			case "special menu":
				specialDish(dish, avail);
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
	
	void mainDish(String dish, String availability) throws FileNotFoundException
	{
		File fileMain = new File(mainPath);
		Scanner scMain = new Scanner(fileMain);
		
		printExist(scMain, dish, availability, mainPath);
	}
	
	void drinkDish(String dish, String availability) throws FileNotFoundException
	{
		File fileDrink = new File(drinkPath);
		Scanner scDrink = new Scanner(fileDrink);

		printExist(scDrink, dish, availability, drinkPath);
	}
	
	void dessertDish(String dish, String availability) throws FileNotFoundException
	{
		File fileDessert = new File(dessertPath);
		Scanner scMain = new Scanner(fileDessert);
		
		printExist(scMain, dish, availability, dessertPath);
	}
	
	void specialDish(String dish, String availability) throws FileNotFoundException
	{
		File fileSpecial = new File(specialPath);
		Scanner scSpecial = new Scanner(fileSpecial);

		printExist(scSpecial, dish, availability, specialPath);
	}
	
	boolean isExisting(Scanner sc, String dish, String availability, String path)
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
				if(availability.equalsIgnoreCase("available"))
				{
					txtTemp.append(temp[0] + ":" + temp[1] + ":Available");
				}
				else
				{
					txtTemp.append(temp[0] + ":" + temp[1] + ":Unavailable");
				}
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
			e.printStackTrace();
		}
		return isChanged;
	}
	
	void printExist(Scanner sc, String dish, String availability, String path)
	{
		if(isExisting(sc, dish, availability, path))
		{
			BorderBox.printLine("You've successfully change the availability of " + dish + " to " + availability);
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
