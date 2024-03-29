package mainPackage;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import UserInterfaceClasses.BorderBox;

public class Payment 
{
	public Payment() throws IOException
	{
		Scanner scan = new Scanner(System.in);
		costumerData cData = new costumerData();
		Cart cart = new Cart();
		Coupons coupon = new Coupons();
		double balance = 0;
		String userInput;

		HtmlClass html = new HtmlClass();
		
		html.setReciept(cart.getCartDish(),cart.getCartQuantity(), coupon.getDiscount(), false);
		int total =  cData.getTotal();
		
		BorderBox.printLine("PAYMENT");
		BorderBox.printLine("YOUR TOTAL");
		BorderBox.printLine(total + "");
		BorderBox.lineDown();
		
		do
		{
			BorderBox.lineUp();
			BorderBox.printLine("Please Choose");
			BorderBox.printLine("[PAY NOW]  :  [CANCEL]");
			BorderBox.printLine("Enter:");
			BorderBox.printInput();
			userInput = scan.nextLine();
			BorderBox.lineDown();
			
			if(userInput.toLowerCase().matches("pay now|cancel"))
			{
				break;
			}
			
			BorderBox.printLine("Please enter to correct option");
		} while(true);

		
		switch (userInput.toLowerCase()) {
		case "pay now":
			Wallet wallet = new Wallet();
			balance = wallet.getWallet();
			if(balance >= total)
			{
				BorderBox.printLine("Your new balane will be " + Math.abs(balance-total) + " after the transaction");
				
				do
				{
					BorderBox.printLine("Are you sure you want to continue?");
					BorderBox.printLine("[YES]  :  [NO]");
					BorderBox.printInput();
					userInput = scan.nextLine();
					BorderBox.lineDown();
					
					if(userInput.toLowerCase().matches("yes|no"))
					{
						break;
					}
					
					BorderBox.printLine("Please choose the correct option");
				}while(true);
				
				if(userInput.equalsIgnoreCase("yes"))
				{
					html.setReciept(cart.getCartDish(),cart.getCartQuantity(), coupon.getDiscount(), true);

					coupon.removeCoupon(coupon.getCoupon_user(), true);
					BorderBox.printLine("You've successfully paid " + total);
					wallet.changeBalance(total, false);
					
					BorderBox.lineDown();
					
					System.out.println();
					System.out.println();
					BorderBox.lineUp();
					BorderBox.printLine("");
					BorderBox.printLine("");
					BorderBox.printLine("SYSTEM IS TERMINATING");
					BorderBox.printLine("");
					BorderBox.printLine("");
					BorderBox.lineDown();
					System.exit(0);
				}
				else
				{
					BorderBox.printLine("You cancelled the payment");
				}
			}
			else
			{
				BorderBox.printLine("Insufficient money");
				
				do 
				{
					BorderBox.lineUp();
					BorderBox.printLine("Please choose");
					BorderBox.printLine("[CASH IN]  :  [CANCEL]");
					BorderBox.printInput();
					userInput = scan.nextLine();
					BorderBox.lineDown();
					
					if(userInput.toLowerCase().matches("cash in|cancel"))
					{
						break;
					}
					
					BorderBox.printLine("Please enter the correct option");
				} while (true);
				
				String cashInput;
				
				if(userInput.equalsIgnoreCase("cash in"))
				{
					do 
					{
						BorderBox.lineUp();
						BorderBox.printLine("Enter the amount you want to add");
						BorderBox.printInput();
						cashInput = scan.nextLine();
						BorderBox.lineDown();
						
						if(Pattern.matches("\\d+", cashInput))
						{
							break;
						}
						
						BorderBox.printLine("Invalid input");
					} while (true);
					
					wallet.cashIn(Integer.valueOf(cashInput));
				}
				else if(userInput.equalsIgnoreCase("cancel"))
				{
					cart.clearCart();
					BorderBox.printLine("You canceled your order");
				}
			}
			break;
		case "cancel":
			break;
		}
	}
}
