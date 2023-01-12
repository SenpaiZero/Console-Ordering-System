package UserInterfaceClasses;

import mainPackage.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
				System.out.print("Quantity: ");
				choiceQuantity = scan.nextLine();
				
				cart.addCart(choiceDish, choiceQuantity);
				
				if(orderAgain(scan) == true)
				{
					continue;
				}
				else
				{
					//to fix
					//to fix
					//to fix
					//after coupon add pay, cancel, edit cart
					BorderBox.lineUp();
					BorderBox.printLine("Enter a coupon   :   Enter exit to skip");
					BorderBox.printLine("Enter: ");
					BorderBox.printInput();
					user = scan.nextLine();
					BorderBox.lineDown();
					
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
						System.out.println("Enter: ");
						BorderBox.printInput();
						user = scan.nextLine();
						BorderBox.lineDown();
						switch (user)
						{
						case "1":
							payment pay = new payment();
							break;
						case "2":
							cart.clearCart();
							break;
						case "3":
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
	
	void wallet() throws IOException
	{
		String user;
		checkWallet();
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
		{	BorderBox.printInput();
			System.out.print("\n\t\t\t\t\t\t\t\t   Logging off.");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			System.out.println("\n\t\t\t\t\t\t\t You've successfully logged out");
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
			wallet();
			break;
		case "logout":
			logout();
			break;
		}
	}

	public void checkWallet() throws IOException
	{
		Costumer costumer = new Costumer();
		costumerData cData = new costumerData();
		
		File file = new File(walletPath);
		Scanner sc = new Scanner(file);
		String line;
		boolean isExist = false;
		while(sc.hasNextLine())
		{
			line = sc.nextLine();
			if(line.contains(cData.getUserName()))
			{
				isExist = true;
				break;
			}
		}
		
		if(isExist == false)
		{
			FileWriter writer = new FileWriter(walletPath, true);
			writer.write(cData.getUserName() + ":" + "0" + "\n");
			writer.flush();
		}
	}
}


