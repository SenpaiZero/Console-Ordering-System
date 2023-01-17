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
			
			if(shopClass.main.stream().anyMatch(choiceDish::equalsIgnoreCase) 
					|| shopClass.drink.stream().anyMatch(choiceDish::equalsIgnoreCase)
					|| shopClass.special.stream().anyMatch(choiceDish::equalsIgnoreCase)
					|| shopClass.dessert.stream().anyMatch(choiceDish::equalsIgnoreCase))
			{
				
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
				
				cart.addCart(choiceDish, choiceQuantity);
				
				if(orderAgain(scan) == true)
				{
					continue;
				}
				else
				{
					boolean isAllowed = false;
					do
					{
						BorderBox.lineUp();
						BorderBox.printLine("Enter a coupon   :   Enter exit to skip");
						BorderBox.printLine("Enter: ");
						BorderBox.printInput();
						user = scan.nextLine();
						BorderBox.lineDown();
						
						String[] data = new String[1];
						for (int i = 0; i < coupon.getCoupon().length; i++) 
						{
							data = coupon.getCoupon()[i].split(":");
							if(user.equalsIgnoreCase("exit"))
							{
								isAllowed = true;
							}
							
							if(user.equals(data[0]))
							{
								coupon.useCoupon(user);
								BorderBox.printLine("You've successfully used a coupon!");
								isAllowed = true;
								break;
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
							Payment pay = new Payment();
							break;
						case "cancel":
							cart.clearCart();
							costumerChoices();
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
				if(choices.toLowerCase().matches("shop|wallet|logout|change password"))
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
			case "logout":
				logout();
				break;
			}
		} while(!choices.equalsIgnoreCase("logout"));
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
	
	public void showMenu() throws IOException
	{
		Costumer costumer = new Costumer();
		costumerData cData = new costumerData();
		Scanner scan = new Scanner(System.in);
		
		String choice;
		String username, password, confirmPassword, birthday, contactNum;
		
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
					BorderBox.printLine("you've successfully authenticated");
					costumer.costumerChoices();
				}
				break;
			//Register
			case "register":
				//Looping for registration
				do {				
					//Looping for username validation
					do
					{
						BorderBox.lineUp();
						BorderBox.printLine("Enter your account information");
						BorderBox.printLine("Username: ");
						BorderBox.printInput();
						username = scan.nextLine();
						BorderBox.lineDown();
						
						/*
						 * This regular expression will match a string that
						 *  starts and ends with alphanumeric characters
						 *   (including letters, numbers, periods, underscores, 
						 *   and dashes), and is between 3 and 15 characters long.
						 */
						if(Pattern.matches("^[a-zA-Z0-9._-]{3,15}$", username))
						{
							break;
						}
						
						BorderBox.printLine("Invalid username, Please try again");
					} while(true);
					
					//Looping for password vaildation
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
						//Check if password does not have space
						if(!password.isEmpty())
						{
							break;
						}
						
						BorderBox.printLine("Invalid password, Pleae try again");
					} while (true);
					
					//Looping for birthday validation
					do
					{
						BorderBox.lineUp();
						BorderBox.printLine("Birthday: ");
						BorderBox.printInput();
						birthday = scan.nextLine();
						BorderBox.lineDown();
							//Check if its YYYY-MM-DD format
							if(Pattern.matches("^([1-2][0-9][0-9][0-9])-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$",
									birthday))
							{
								break;
							}
						BorderBox.printLine("Invalid Birthday, Please try again");
						BorderBox.printLine("Format: YYYY-MM-DD");
					} while(true);
					
					//Looping for phone number validation
					do 
					{
						BorderBox.lineUp();
						BorderBox.printLine("contact Number: ");
						BorderBox.printInput();
						contactNum = scan.nextLine();
						BorderBox.lineDown();
						
						//Check if the number is integer and 11 characters
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
				break;
			//Go back
			case "go back":
				isContinue = false;
				break;
			}
		} while(isContinue == true);
	}
}


