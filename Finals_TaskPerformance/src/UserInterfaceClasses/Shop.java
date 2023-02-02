package UserInterfaceClasses;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.border.Border;

import mainPackage.ShopData;

public class Shop extends ShopData
{
	
	public Shop() throws FileNotFoundException
	{
		setDishes();
	}
	
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
		if(main.size() <= 0 ||  drink.size() <= 0 
				|| dessert.size() <= 0 || special.size() <= 0)
		{
			BorderBox.lineUp();
			BorderBox.printLine("Something went wrong. System is terminating!");
			BorderBox.lineDown();
			System.exit(0);
		}
		printMenu(
				main.toArray(new String[main.size()]), 
				mainPrice.toArray(new String[mainPrice.size()]),
				mainAvail.toArray(new String[mainAvail.size()]),
				
				drink.toArray(new String[drink.size()]), 
				drinkPrice.toArray(new String[drinkPrice.size()]),
				drinkAvail.toArray(new String[drinkAvail.size()]),
				
				special.toArray(new String[special.size()]), 
				specialPrice.toArray(new String[specialPrice.size()]),
				specialAvail.toArray(new String[specialAvail.size()]),
				
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
