package UserInterfaceClasses;

import mainPackage.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class CostumerUI 
{
	public static String walletPath = ".\\src\\Data\\costumerWallet.txt";
	static Scanner scan = new Scanner(System.in);
	
	void shop() throws IOException
	{
		Shop shopClass = new Shop();
		Cart cart = new Cart();
		shopClass.printMenu();
		Coupons coupon = new Coupons();
		HtmlClass html = new HtmlClass();
		String choiceDish, choiceQuantity, user;
		
		do
		{
			BorderBox.lineUp();
			BorderBox.printLine("Enter the name of the food or exit to go back");
			BorderBox.printLine("Dish: ");
			BorderBox.printInput();
			choiceDish = scan.nextLine();
			BorderBox.lineDown();
			
			if(choiceDish.equalsIgnoreCase("exit"))
			{
				break;
			}
			
			if(shopClass.main.contains(choiceDish) || shopClass.drink.contains(choiceDish)
					|| shopClass.special.contains(choiceDish) || shopClass.dessert.contains(choiceDish))
			{
				
				do
				{
					BorderBox.printLine("Quantity: ");
					BorderBox.printInput();
					choiceQuantity = scan.nextLine();
					BorderBox.lineDown();
					
					if(Pattern.matches("\\d+", choiceQuantity))
					{
						break;
					}
					
					BorderBox.printLine("Please enter number only");
				} while(true);
				
				cart.addCart(choiceDish, choiceQuantity);
				
				if(orderAgain(scan) == true)
				{
					continue;
				}
				else
				{
					boolean isAllowed = false;
					//to fix
					//to fix
					//to fix
					//after coupon add pay, cancel, edit cart
					do
					{
						BorderBox.lineUp();
						BorderBox.printLine("Enter a coupon   :   Enter exit to skip");
						BorderBox.printLine("Enter: ");
						BorderBox.printInput();
						user = scan.nextLine();
						BorderBox.lineDown();
						
						for (int i = 0; i < coupon.getCoupon().length; i++) 
						{
							if(user.equalsIgnoreCase("exit"))
							{
								isAllowed = true;
							}
							
							if(user.equals(coupon.getCoupon()[i]))
							{
								BorderBox.printLine("You've successfully used a coupon!");
								isAllowed = true;
							}
						}
						
						if(isAllowed == false)
						{
							BorderBox.printLine("The coupon does not exist");
						}
						else
						{
							break;
						}
					} while(true);
					
					if(coupon.useCoupon("exit"))
					{
						break;
					}
					
					cart.viewCart();
					do
					{
						BorderBox.lineUp();
						BorderBox.printLine("Please choose");
						BorderBox.printLine("[PAY]  :  [CANCEL]  :  [EDIT]");
						BorderBox.printLine("Enter: ");
						BorderBox.printInput();
						user = scan.nextLine();
						BorderBox.lineDown();
						switch (user.toLowerCase())
						{
						case "pay":
							Payment pay = new Payment();
							break;
						case "cancel":
							cart.clearCart();
							break;
						case "edit":
							cart.editCart();
							break;
						}
					} while(true);
				}
			}
			else
			{
				BorderBox.printLine("The item you entered does not exist");
			}
				
		} while(true);
	}
	
	boolean orderAgain(Scanner scan)
	{
		String user;
		
		do
		{
			BorderBox.lineUp();
			BorderBox.printLine("Do you want to order again?");
			BorderBox.printLine("[YES]  :  [NO]");
			BorderBox.printLine("Enter: ");
			BorderBox.printInput();
			user = scan.nextLine();
			BorderBox.lineDown();
			if(user.equalsIgnoreCase("yes"))
			{
				return true;
			}
			else if(user.equalsIgnoreCase("no"))
			{
				return false;
			}
			else
			{
				BorderBox.printLine("Invalid input please try again!");
			}
		} while(true);
	}
	
	void wallets() throws IOException
	{
		Wallet wallet = new Wallet();
		String user;
		
		System.out.println("Please enter:  [1] CASH IN   [2] CASH OUT  [3] GO BACK");
		System.out.print("Enter: ");
		user = scan.nextLine();
		switch (user) {
		case "1":
			break;
		case "2":
			break;
		default:
			break;
		}
	}
	
	void logout()
	{
		try 
		{
			BorderBox.lineUp();
			BorderBox.printInput();
			System.out.print("\n\t\t\t\t\t\t\t\t\t      Logging off.");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			System.out.println("\n\t\t\t\t\t\t\t\t     You've successfully logged out");
			BorderBox.lineDown();
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public void costumerChoices() throws IOException
	{
		Scanner scan = new Scanner(System.in);
		String choices;
		do
		{
			do
			{
				BorderBox.lineUp();
				BorderBox.printLine("Please choose");
				BorderBox.printLine("[SHOP]  :  [WALLET]  :  [LOGOUT]");
				BorderBox.printLine("Enter: ");
				BorderBox.printInput();
				choices = scan.nextLine();
				BorderBox.lineDown();
				if(choices.toLowerCase().matches("shop|wallet|logout"))
				{
					break;
				}
			} while(true);
			
			switch (choices.toLowerCase()) {
			case "shop":
				shop();
				break;
			case "wallet":
				Wallet wallet = new Wallet();
				wallet.showMenu();
				break;
			case "logout":
				logout();
				break;
			}
		} while(!choices.equalsIgnoreCase("logout"));
	}
	
	public void showMenu() throws IOException
	{
		Costumer costumer = new Costumer();
		costumerData cData = new costumerData();
		Scanner scan = new Scanner(System.in);

		BorderBox.lineUp();
		BorderBox.printLine("You entered costumer");
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
					costumer.costumerChoices();
				}
				break;
			//Register
			case "register":
				BorderBox.lineUp();
				BorderBox.printLine("Username: ");
				BorderBox.printInput();
				username = scan.nextLine();
				
				BorderBox.printLine("Password: ");
				BorderBox.printInput();
				password = scan.nextLine();
				
				BorderBox.printLine("Re-Enter Password: ");
				BorderBox.printInput();
				confirmPassword = scan.nextLine();
	
				BorderBox.printLine("Birthday: ");
				BorderBox.printInput();
				birthday = scan.nextLine();
				
				BorderBox.printLine("contact Number: ");
				BorderBox.printInput();
				contactNum = scan.nextLine();
				BorderBox.lineDown();
				if(costumer.isRegister(username, password, confirmPassword, birthday, contactNum))
				{
					BorderBox.printLine("You've successfully registered ");
					BorderBox.lineDown();
				}
			//Go back
			case "go back":
				break;
			}
	}
}


