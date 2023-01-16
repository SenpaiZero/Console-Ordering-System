package UserInterfaceClasses;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import mainPackage.Coupons;

public class CouponUI 
{
	public void showMenu() throws IOException
	{
		String choice, couponTitle, couponRate;
		Scanner sc = new Scanner(System.in);
		Coupons coup = new Coupons();
		
		//Looping for option validation
		do
		{
			BorderBox.lineUp();
			BorderBox.printLine("Please choose");
			BorderBox.printLine("[ADD COUPON]   [REMOVE COUPON]");
			BorderBox.printLine("Enter: ");
			BorderBox.printInput();
			choice = sc.nextLine();
			BorderBox.lineDown();
			
			//Stop the loop if the user input is correct
			if(choice.toLowerCase().matches("add coupon|remove coupon"))
			{
				break;
			}
			
			BorderBox.printLine("Please enter the correct option");
		} while(true);
		
		switch (choice.toLowerCase()) {
		case "add coupon":
			//Looping for add coupon
			do
			{
				BorderBox.printLine(Arrays.toString(coup.getCoupon()));
				BorderBox.printLine("Enter coupon and it's discount rate");
				BorderBox.printLine("Coupon: ");
				BorderBox.printInput();
				couponTitle = sc.nextLine();
				BorderBox.lineDown();
				
				//Checking if user input is empty
				if(!couponTitle.isBlank())
				{
					//looping for discount validation
					do 
					{
						BorderBox.printLine("Discount: ");
						BorderBox.printInput();
						couponRate = sc.nextLine();
						BorderBox.lineDown();
						
						//Stop the loop if the user input is correct
						if(Pattern.matches("\\d+", couponRate))
						{
							break;
						}
						BorderBox.printLine("Invalid input");
					} while (true);
					break;
				}
				BorderBox.printLine("Invalid input");
			} while(true);
			
			//calling add coupon method from coupon class
			coup.addCoupon(couponTitle, couponRate);
			break;
		case "remove coupon":
			BorderBox.printLine(Arrays.toString(coup.getCoupon()));
			//Looping for coupon validation
			do
			{
				BorderBox.printLine("Enter the coupon you want to remove");
				BorderBox.printLine("Coupon: ");
				BorderBox.printInput();
				couponTitle = sc.nextLine();
				BorderBox.lineDown();
				
				//Stop the loop if the user input is not empty
				if(!couponTitle.isBlank())
				{
					break;
				}
				
				BorderBox.printLine("Invalid Input");
			} while(true);
			coup.removeCoupon(couponTitle, false);
			break;
		}

		
		//Re-enter all coupon on the ArrayList in coupon class
		coup.refreshCoupon();
	}
}
