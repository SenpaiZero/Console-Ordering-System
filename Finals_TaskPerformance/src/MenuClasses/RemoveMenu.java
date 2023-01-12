package MenuClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import UserInterfaceClasses.BorderBox;

public class RemoveMenu {
	static String mainPath = ".\\src\\Data\\mainMenu.txt";
	static String drinkPath = ".\\src\\Data\\drinkMenu.txt";
	static String dessertPath = ".\\src\\Data\\dessertsMenu.txt";
	static String specialPath = ".\\src\\Data\\specialMenu.txt";
	
	public void removeExistingMenu(Scanner scan) throws FileNotFoundException
	{
		String choice;
		String dish;
		BorderBox.printLine("[1] MAIN DISH  [2] DRINKS  [3] DESSERT  [4] SPECIAL MENU  [5] GO BACK");
		
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
			BorderBox.printLine("What dish you want to remove?");
			BorderBox.printLine("Enter: ");
			dish = scan.nextLine();
			BorderBox.lineDown();
			
			switch (Integer.valueOf(choice)) 
			{
			case 1:
				mainDish(dish);
				break;
			case 2:
				drinkDish(dish);
				break;
			case 3:
				dessertDish(dish);
				break;
			case 4:
				specialDish(dish);
				break;
			}
		}
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
			System.out.println("\nYou've successfully removed " + dish);
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
