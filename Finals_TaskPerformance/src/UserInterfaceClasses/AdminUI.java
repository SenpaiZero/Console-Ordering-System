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
		boolean isLogout = false;
		do 
		{
			BorderBox.lineUp();
			BorderBox.printLine("Please enter admin account");
			
			BorderBox.printLine("Username: ");
			BorderBox.printInput();
			username = scan.nextLine();
			
			BorderBox.printLine("Password: ");
			BorderBox.printInput();
			password = scan.nextLine();
			BorderBox.lineDown();
			
			
			if(username.toLowerCase().matches("go back|exit")
					|| password.matches("go back|exit"))
			{
				break;
			}
			
			while (admin.isAdmin(username, password)) 
			{
				String choice;
				
				do 
				{
					BorderBox.lineUp();
					BorderBox.printLine("Please choose");
					BorderBox.printLine("[MENU]  :  [COUPON]  :  [LOGOUT]");
					BorderBox.printLine("Enter: ");
					BorderBox.printInput();
					choice = scan.nextLine();
					BorderBox.lineDown();
					
					if(choice.toLowerCase().matches("menu|coupon|logout"))
					{
						break;
					}
					
					BorderBox.printLine("Please enter the correct option");
				} while (true);

				switch (choice.toLowerCase()) 
				{
				case "menu":
					admin.showMenu(admin);
					break;
				case "coupon":
					Coupons coupon = new Coupons();
					coupon.showMenu();
					break;
				}
				
				if(choice.equalsIgnoreCase("logout"))
				{
					logout();
					isLogout = true;
					break;
				}
			}

			if(isLogout == true) break;
		} while (true);
	}
	
	public void showMenu(Admin admin) throws IOException
	{
		new Shop();
		Scanner scan = new Scanner(System.in);
		String choice;
		
		
		do 
		{
			BorderBox.lineUp();
			BorderBox.printLine("Please choose");
			BorderBox.printLine("[ADD MENU]  :  [REMOVE MENU]  :  [CHANGE PRICE]  :  [CHANGE AVAIL]  :  [GO BACK]");
			BorderBox.printLine("Enter: ");
			BorderBox.printInput();
			choice = scan.nextLine();
			BorderBox.lineDown();
			
			if(choice.toLowerCase().matches("add menu|remove menu|change price|change avail|go back"))
			{
				break;
			}
			
			BorderBox.printLine("Please enter the correct option");
		} while (true);
			admin.changeMenu(choice);
	}
	
	static void logout()
	{
		try 
		{
			BorderBox.lineUp();
			BorderBox.printInput();
			System.out.print("\n\t\t\t\t\t\t\t\t\t\t  Logging off.");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			System.out.println("\n\t\t\t\t\t\t\t\t\t You've successfully logged out");
			BorderBox.lineDown();
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
