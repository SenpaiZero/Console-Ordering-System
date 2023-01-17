package UserInterfaceClasses;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import mainPackage.Admin;
import mainPackage.Coupons;

public class AdminUI 
{
	public void adminLogin() throws IOException
	{
		Scanner scan = new Scanner(System.in);
		Admin admin = new Admin();
		String username, password;
		do 
		{
			BorderBox.lineUp();
			BorderBox.printLine("Please enter admin account");
			
			//Askin admin username
			BorderBox.printLine("Username: ");
			BorderBox.printInput();
			username = scan.nextLine();
			
			//Asking admin password
			BorderBox.printLine("Password: ");
			BorderBox.printInput();
			password = scan.nextLine();
			BorderBox.lineDown();
			
			//Gagana if tama yung input ng admin username at password
			while (admin.isAdmin(username, password)) 
			{
				String choice;
				
				//Looping for choices between menu and coupons
				do 
				{
					BorderBox.lineUp();
					BorderBox.printLine("Please choose");
					BorderBox.printLine("[MENU]  :  [COUPON]  :  [LOGOUT]");
					BorderBox.printLine("Enter: ");
					BorderBox.printInput();
					choice = scan.nextLine();
					BorderBox.lineDown();
					
					//if input is menu or coupon, hihinto na yung loop
					//mag tutuloy na sa baba yung program
					if(choice.toLowerCase().matches("menu|coupon|logout"))
					{
						break;
					}
					
					BorderBox.printLine("Please enter the correct option");
				} while (true);
				//End of looping ng menu and coupon
				
				//Mag tutuloy dito if tama yung input ng user sa menu or coupon
				switch (choice.toLowerCase()) 
				{
				//If user input is menu dito magtutuloy
				case "menu":
					//calls the menu method (static)
					admin.showMenu(admin);
					break;
				//if user input is coupon, dito mag tutuloy
				case "coupon":
					//calls the coupon method (static)
					Coupons coupon = new Coupons();
					coupon.showMenu();
					break;
				case "logout":
					logout();
					break;
				}
				
			}
		} while (true);
		//End of looping ng user input for admin username and password
	}
	
	public void showMenu(Admin admin) throws IOException
	{
		new Shop();
		Scanner scan = new Scanner(System.in);
		String choice;
		
		
		//Looping for choices 1 - 4 (add menu, remove, replace and go back)
		do 
		{
			BorderBox.lineUp();
			BorderBox.printLine("Please choose");
			BorderBox.printLine("[ADD MENU]  :  [REMOVE MENU]  :  [CHANGE PRICE]  :  [CHANGE AVAIL]  :  [GO BACK]");
			BorderBox.printLine("Enter: ");
			BorderBox.printInput();
			choice = scan.nextLine();
			BorderBox.lineDown();
			
			//Checks if the user input is 1 to 4 numbers and only 1 character
			if(choice.toLowerCase().matches("add menu|remove menu|change price|change avail|go back"))
			{
				break;
			}
			
			BorderBox.printLine("Please enter the correct option");
		} while (true);
			//Tinawag yung method na changeMenu sa admin class
			admin.changeMenu(choice);
	}
	
	static void logout()
	{
		try 
		{
			BorderBox.lineUp();
			BorderBox.printInput();
			System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t      Logging off.");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			System.out.println("\n\t\t\t\t\t\t\t\t\t\t     You've successfully logged out");
			BorderBox.lineDown();
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		System.exit(0);
	}
}
