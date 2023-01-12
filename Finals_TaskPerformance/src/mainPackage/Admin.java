package mainPackage;
import MenuClasses.*;
import UserInterfaceClasses.BorderBox;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Admin 
{	
	private String username;
	private String password;
	private static boolean isPrint = false;
	
	public Admin() {
		username = "admin";
		password = "admin";
	}
	
	public boolean isAdmin(String username, String password)
	{
		if(this.username.equals(username) && this.password.equals(password))
		{
			printOnce("you've successfully authenticated");
			return true;
		}
		else if(username.equalsIgnoreCase("exit")
				|| password.equalsIgnoreCase("exit"))
		{
			BorderBox.lineUp();
			BorderBox.printLine("The system has stopped");
			BorderBox.lineDown();
			System.exit(0);
		}
		else
		{
			BorderBox.printLine("Incorrect admin information");
		}
		return false;
	}
	
	static void printOnce(String text)
	{
		if(isPrint == false)
		{
			BorderBox.printLine(text);
			isPrint = true;
		}
	}
	
	public void changeMenu(String choice) throws IOException
	{		
		Scanner scanner = new Scanner(System.in);
		switch (choice.toLowerCase()) 
		{
			case "add menu":
				AddMenu add = new AddMenu();
				add.AddNewMenu(scanner);  
				break;
			case "remove menu": 
				RemoveMenu remove = new RemoveMenu();
				remove.removeExistingMenu(scanner);
				break;
			case "change price": 
				changePrice replace = new changePrice();
				replace.changePriceMenu(scanner);
				break;
		}
		
	}
	

}
