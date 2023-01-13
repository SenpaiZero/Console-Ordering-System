package UserInterfaceClasses;

import java.io.IOException;
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
		
		do
		{
			BorderBox.lineUp();
			BorderBox.printLine("Please choose");
			BorderBox.printLine("[ADD COUPON]   [REMOVE COUPON]");
			BorderBox.printLine("Enter: ");
			BorderBox.printInput();
			choice = sc.nextLine();
			BorderBox.lineDown();
			
			if(choice.toLowerCase().matches("add coupon|remove coupon"))
			{
				break;
			}
			
			BorderBox.printLine("Please enter the correct option");
		} while(true);
		
		switch (choice.toLowerCase()) {
		case "add coupon":
			do
			{
				BorderBox.printLine("Enter coupon and it's discount rate");
				BorderBox.printLine("Coupon: ");
				BorderBox.printInput();
				couponTitle = sc.nextLine();
				BorderBox.lineDown();
				if(!couponTitle.isBlank())
				{
					do 
					{
						BorderBox.printLine("Discount: ");
						BorderBox.printInput();
						couponRate = sc.nextLine();
						BorderBox.lineDown();
						
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
			
			coup.addCoupon(couponTitle, couponRate);
			break;
		case "remove coupon":
			do
			{
				BorderBox.printLine("Enter the coupon you want to remove");
				BorderBox.printLine("Coupon: ");
				BorderBox.printInput();
				couponTitle = sc.nextLine();
				BorderBox.lineDown();
				
				if(!couponTitle.isBlank())
				{
					break;
				}
				
				BorderBox.printLine("Invalid Input");
			} while(true);
			coup.removeCoupon(couponTitle, false);
			break;
		}
	}
}
