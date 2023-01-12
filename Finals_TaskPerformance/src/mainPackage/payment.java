package mainPackage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import UserInterfaceClasses.BorderBox;

public class payment 
{
	public payment() throws IOException
	{
		Scanner scan = new Scanner(System.in);
		HtmlClass html = new HtmlClass();
		Cart cart = new Cart();
		Coupons coupon = new Coupons();
		double balance = 0;
		String userInput;
		
		BorderBox.printLine("PAYMENT");
		BorderBox.printLine("YOUR TOTAL");
		BorderBox.printLine(String.valueOf(html.getTotal()));
		BorderBox.lineDown();
		
		do
		{
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

			if(balance >= html.getTotal())
			{
				BorderBox.printLine("Your new balane will be" + balance + "after the transaction");
				
				//Loopings para icheck kung tama input ng user
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
					BorderBox.printLine("You've successfully paid " + html.getTotal());
					html.setReciept(cart.getCartDish(),cart.getCartQuantity(), coupon.getDiscount());
					//set wallet new balance
				}
				else
				{
					BorderBox.printLine("You cancelled the peyment");
				}
			}
			else
			{
				BorderBox.printLine("Insufficient money");
				BorderBox.printLine("Please choose");
				BorderBox.printLine("[CASH IN]  :  [CANCEL]");
				
				//cash in method
			}
			break;
		case "cancel":
			break;
		}
	}
}
