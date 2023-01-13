package MenuClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import UserInterfaceClasses.BorderBox;

public class ChangePrice 
{
	static String mainPath = ".\\src\\Data\\mainMenu.txt";
	static String drinkPath = ".\\src\\Data\\drinkMenu.txt";
	static String dessertPath = ".\\src\\Data\\dessertsMenu.txt";
	static String specialPath = ".\\src\\Data\\specialMenu.txt";
	
	public void changePriceMenu(Scanner scan) throws FileNotFoundException
	{
		String choice;
		String dish, price;
		
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

		BorderBox.lineUp();
		BorderBox.printLine("What dish you want to change price?");
		BorderBox.printLine("Enter: ");
		BorderBox.printInput();
		dish = scan.nextLine();
		BorderBox.lineDown();
		
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
		case "desser":
			dessertDish(dish, price);
			break;
		case "special menu":
			specialDish(dish, price);
			break;
		}
	
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
			System.out.printf("\nYou've successfully change the price of %s to %s ", dish, price);
		}
		else
		{
			System.out.printf("\nThe dish (%s) you entered does not exist", dish);
		}
	}
	
	void write(StringBuilder txtWrite, String path) throws IOException
	{
		FileWriter writer = new FileWriter(path);
		writer.write(String.valueOf(txtWrite));
		writer.flush();
	}
}
