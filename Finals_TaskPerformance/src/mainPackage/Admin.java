package mainPackage;
import MenuClasses.*;
import UserInterfaceClasses.AdminUI;
import UserInterfaceClasses.BorderBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Admin extends AdminUI
{	
	ArrayList<String> username = new ArrayList<>();
	ArrayList<String> password = new ArrayList<>();
	private static boolean isPrint = false;
	
	public Admin() {
		username.add("admin");
		password.add("admin");
		
		username.add("ygi");
		password.add("santos");
		
		username.add("dean");
		password.add("boringot");
		
		username.add("kyla");
		password.add("arquio");
		
		username.add("mark");
		password.add("agustin");
	}
	
	public boolean isAdmin(String username, String password)
	{
		String user, pass;
		for (int i = 0; i < this.username.size(); i++) 
		{
			user = this.username.get(i);
			pass = this.password.get(i);
			if(username.equals(user) && password.equals(pass))
			{
				printOnce("you've successfully authenticated");
				return true;
			}
		}

		if(username.equalsIgnoreCase("exit")
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
				ChangePrice replace = new ChangePrice();
				replace.changePriceMenu(scanner);
				break;
			case "change avail":
				ChangeAvail avail = new ChangeAvail();
				avail.changeAvailability(scanner);
				break;
		}
		
	}
	

}
