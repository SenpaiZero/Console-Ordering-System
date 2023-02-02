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

import javax.imageio.spi.RegisterableService;

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
			
			if(shopClass.main.stream().anyMatch(choiceDish::equalsIgnoreCase) 
					|| shopClass.drink.stream().anyMatch(choiceDish::equalsIgnoreCase)
					|| shopClass.special.stream().anyMatch(choiceDish::equalsIgnoreCase)
					|| shopClass.dessert.stream().anyMatch(choiceDish::equalsIgnoreCase))
			{
				//etoooooooooooooooooooooo
				do
				{
					BorderBox.lineUp();
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
				//etoooooooooooooooooooooooooooooooooooooooooooo
				
				cart.addCart(choiceDish, choiceQuantity);
				
				if(orderAgain(scan) == true)
				{
					continue;
				}
				else
				{
					coupon.enterCoupon();
					
					BorderBox.lineUp();
					BorderBox.printLine("Please enter your address");
					BorderBox.printInput();
					costumerData cData = new costumerData();
					cData.setAddress(scan.nextLine());
					BorderBox.lineDown();
					
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
							new Payment();
							break;
						case "cancel":
							cart.clearCart();
							costumerMenu();
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
	
	boolean orderAgain(Scanner scan) throws FileNotFoundException
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
				Shop shop = new Shop();
				shop.printMenu();
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
	
	public void costumerMenu() throws IOException
	{
		Scanner scan = new Scanner(System.in);
		String choices;
		boolean isLogout = false;
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
				
				//Stop if the user input is valid
				if(choices.toLowerCase().matches("shop|wallet|logout"))
				{
					break;
				}
				
				BorderBox.printLine("Please enter the correct option");
			} while(true);
			
			switch (choices.toLowerCase()) {
			case "shop":
				shop();
				break;
			case "wallet":
				Wallet wallet = new Wallet();
				wallet.showMenu();
				break;
			}
			
			if(choices.equalsIgnoreCase("logout"))
			{
				logout();
				isLogout = true;
				break;
			}
			
			if(isLogout == true) break;
		} while(true);
	}
	
	void logout()
	{
		try 
		{
			BorderBox.lineUp();
			BorderBox.printInput();
			System.out.print("\n\t\t\t\t\t\t\t\t\t\t   Logging off.");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			System.out.println("\n\t\t\t\t\t\t\t\t\t  You've successfully logged out");
			BorderBox.lineDown();
			
			BorderBox.lineUp();
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void startMenu() throws IOException
	{
		Costumer costumer = new Costumer();
		Scanner scan = new Scanner(System.in);
		
		String choice;
		boolean isContinue = true;

		BorderBox.lineUp();
		BorderBox.printLine("You entered costumer");
		
		do
		{
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
			case "login":
				login(costumer);
				break;
			case "register":
				register(costumer);
				break;
			case "go back":
				isContinue = false;
				break;
			}
		} while(isContinue == true);
	}

	void register(Costumer costumer) throws IOException 
	{
		String username, password, confirmPassword, birthday, contactNum;
		
		do {				
			do
			{
				BorderBox.lineUp();
				BorderBox.printLine("Enter your account information");
				BorderBox.printLine("Username: ");
				BorderBox.printInput();
				username = scan.nextLine();
				BorderBox.lineDown();
				if(Pattern.matches("^[a-zA-Z0-9._-]{3,15}$", username))
				{
					break;
				}
				
				BorderBox.printLine("Invalid username, Please try again");
			} while(true);
			
			do 
			{
				BorderBox.lineUp();
				BorderBox.printLine("Password: ");
				BorderBox.printInput();
				password = scan.nextLine();
				
				BorderBox.printLine("Re-Enter Password: ");
				BorderBox.printInput();
				confirmPassword = scan.nextLine();
				BorderBox.lineDown();
				
				if(password.equals(confirmPassword))
				{
					break;
				}
				else if(!password.equals(confirmPassword)){
					BorderBox.printLine("Incorrect");
					continue;
				}
				if(!password.isEmpty())
				{
					break;
				}
				
				BorderBox.printLine("Invalid password, Pleae try again");
			} while (true);
		
			do
			{
				BorderBox.lineUp();
				BorderBox.printLine("Birthday: ");
				BorderBox.printInput();
				birthday = scan.nextLine();
				BorderBox.lineDown();
					if(Pattern.matches("^([1-2][0-9][0-9][0-9])-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$",
							birthday))
					{
						break;
					}
				BorderBox.printLine("Invalid Birthday, Please try again");
				BorderBox.printLine("Format: YYYY-MM-DD");
			} while(true);
			
			do 
			{
				BorderBox.lineUp();
				BorderBox.printLine("contact Number: ");
				BorderBox.printInput();
				contactNum = scan.nextLine();
				BorderBox.lineDown();
				
				if(Pattern.matches("[0-9]{11}", contactNum))
				{
					break;
				}
				BorderBox.printLine("Invalid Phone Number, Please try again");
			} while (true);
			
			if(costumer.isRegister(username, password, confirmPassword, birthday, contactNum))
			{
				BorderBox.lineUp();
				BorderBox.printLine("You've successfully registered ");
				BorderBox.lineDown();
				break;
			}
		} while (true);
	}

	void login(Costumer costumer) throws FileNotFoundException, IOException 
	{
		String username, password;
		
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
			BorderBox.printLine("you've successfully authenticated");
			costumer.costumerMenu();
		}
		
	}
}


