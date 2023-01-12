package mainPackage;

import java.io.IOException; 
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import UserInterfaceClasses.BorderBox;
import UserInterfaceClasses.CostumerUI;

public class OrderRun {
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException 
	{
		String userChoice;
		BorderBox.lineUp();
		BorderBox.printLine("WELCOME TO WORTHY RESTAURANT");
		do
			{
			BorderBox.printLine("Please choose");
			BorderBox.printLine("[ADMIN]  :  [COSTUMER]  :  [EXIT]");
			BorderBox.lineDown();
			//looping for admin and costumer choices
			do 
			{
				BorderBox.lineUp();
				BorderBox.printLine("Enter: ");
				BorderBox.printInput();
				userChoice = scan.nextLine();
				BorderBox.lineDown();
				//if input is costumer or admin, hihinto na yung loop
				//mag tutuloy na sa baba yung program
				if(userChoice.toLowerCase().matches("costumer|admin|exit"))
				{
					break;
				}
				
				BorderBox.printLine("Please enter the correct option");
			} while (true);
			
			//Continue the program pag tama na yung input ng user
			//Switch case for admin and costumer
			switch (userChoice.toLowerCase())
			{
				//if the user input is admin
				case "admin":
					admin();
					break;
				//if the user input is costumer
				case "costumer":
					costumers();
					break;
				//if the user input is exit
				case "exit":
					BorderBox.lineUp();
					BorderBox.printLine("The system has stopped");
					BorderBox.lineDown();
					System.exit(0);
					break;
			}
		} while(true);
	}
	
	static void admin()
	{
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
				BorderBox.lineUp();
				BorderBox.printLine("Please choose");
				BorderBox.printLine("[MENU]  :  [COUPON]  :  [LOGOUT]");
				BorderBox.lineDown();
				
				//Looping for choices between menu and coupons
				do 
				{
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
					menu(admin);
					break;
				//if user input is coupon, dito mag tutuloy
				case "coupon":
					//calls the coupon method (static)
					try {
						coupon();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "logout":
					logout();
					break;
				}
				
			}
		} while (true);
		//End of looping ng user input for admin username and password
	}
	
	static void costumers() throws IOException
	{
		Costumer costumer = new Costumer();
		costumerData cData = new costumerData();
		CostumerUI cosUI = new CostumerUI();
		Scanner scan = new Scanner(System.in);

		BorderBox.lineUp();
		BorderBox.printLine("You entered costumer");
		boolean isBack = true;
		String choice;
		String username, password, confirmPassword, birthday, contactNum;
		
		BorderBox.printLine("Please choose");
		BorderBox.printLine("[LOGIN]  [REGISTER]  [GO BACK]");
		BorderBox.lineDown();
			do
			{
				BorderBox.lineUp();
				BorderBox.printLine("Enter: ");
				BorderBox.printInput();
				choice = scan.nextLine();
				BorderBox.lineDown();
				
				if(Pattern.matches("login|register|go back", choice.toLowerCase()))
				{
					break;
				}
				
				BorderBox.printLine("Please enter the correct option");
			} while(true);
			
			switch (choice.toLowerCase()) 
			{
			//login
			case "login":
				BorderBox.lineUp();
				BorderBox.printLine("Username: ");
				BorderBox.printInput();
				username = scan.nextLine();
				
				BorderBox.printLine("Password: ");
				BorderBox.printInput();
				password = scan.nextLine();
				BorderBox.lineDown();
				
				if(costumer.isLogin(username, password) == true)
				{
					BorderBox.printLine("Please choose");
					BorderBox.printLine("[SHOP]  :  [WALLET]  :  [LOGOUT]");
					cosUI.costumerChoices();
				}
				break;
			//Register
			case "register":
				BorderBox.lineUp();
				BorderBox.printLine("Username: ");
				username = scan.nextLine();
				BorderBox.printInput();
				
				BorderBox.printLine("Password: ");
				password = scan.nextLine();
				BorderBox.printInput();
				
				BorderBox.printLine("Re-Enter Password: ");
				confirmPassword = scan.nextLine();
				BorderBox.printInput();
	
				BorderBox.printLine("Birthday: ");
				birthday = scan.nextLine();
				BorderBox.printInput();
				
				BorderBox.printLine("contact Number: ");
				contactNum = scan.nextLine();
				BorderBox.printInput();
				BorderBox.lineDown();
				if(costumer.isRegister(username, password, confirmPassword, birthday, contactNum))
				{
					BorderBox.printLine("\nYou've successfully registered ");
					BorderBox.lineDown();
				}
			//Go back
			case "go back":
				isBack = false;
				break;
			}
	}
	
	static void menu(Admin admin)
	{
		String choice;
		BorderBox.lineUp();
		BorderBox.printLine("Please choose");
		BorderBox.printLine("[ADD MENU]  :  [REMOVE MENU]  :  [CHANGE PRICE]  :  [GO BACK]");
		BorderBox.lineDown();
		
		
		//Looping for choices 1 - 4 (add menu, remove, replace and go back)
		do 
		{
			BorderBox.lineUp();
			BorderBox.printLine("Enter: ");
			BorderBox.printInput();
			choice = scan.nextLine();
			BorderBox.lineDown();
			
			//Checks if the user input is 1 to 4 numbers and only 1 character
			if(choice.toLowerCase().matches("add menu|remove menu|change price|go back"))
			{
				break;
			}
			
			BorderBox.printLine("Please enter the correct option");
		} while (true);

		try 
		{
			//Tinawag yung method na changeMenu sa admin class
			admin.changeMenu(choice);
		} 
		catch (IOException e)
		{
			//throw errors pag walang nahanap na txt file
			System.out.println("\nSomething went wrong");
		}
	}
	
	static void coupon() throws IOException
	{
		String choice, couponTitle, couponRate;
		Scanner sc = new Scanner(System.in);
		Coupons coup = new Coupons();
		
		do
		{
			BorderBox.lineUp();
			BorderBox.printLine("Please choose");
			BorderBox.printLine("[ADD COUPON]   [REMOVE COUPON]");
			BorderBox.printLine("Enter: ");
			BorderBox.printInput();
			choice = scan.nextLine();
			BorderBox.lineDown();
			
			if(choice.toLowerCase().matches("add coupon|remove coupon"))
			{
				break;
			}
			
			BorderBox.printLine("Please enter the correct option");
		} while(true);
		
		switch (choice) {
		case "1":
			BorderBox.printLine("Enter coupon and it's discount rate");
			BorderBox.printLine("Coupon: ");
			couponTitle = scan.nextLine();
			
			BorderBox.printLine("Discount: ");
			BorderBox.printInput();
			couponRate = scan.nextLine();
			BorderBox.lineDown();
			
			coup.addCoupon(couponTitle, couponRate);
			break;
		case "2":
			BorderBox.printLine("Enter the coupon you want to remove");
			BorderBox.printLine("Coupon: ");
			BorderBox.printInput();
			couponTitle = scan.nextLine();
			BorderBox.lineDown();

			coup.removeCoupon(couponTitle, false);
			break;
		default:
			break;
		}
	}
	
	static void logout()
	{
		try 
		{
			BorderBox.lineUp();
			BorderBox.printInput();
			System.out.print("\n\t\t\t\t\t\t\t\t   Logging off.");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			System.out.println("\n\t\t\t\t\t\t\t You've successfully logged out");
			BorderBox.lineDown();
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		System.exit(0);
	}
}
