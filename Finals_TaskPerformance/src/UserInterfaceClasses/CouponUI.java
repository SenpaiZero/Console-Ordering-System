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
		String choice;
		Scanner sc = new Scanner(System.in);
		Coupons coup = new Coupons();
		
		do
		{
			//Looping for option validation
			do
			{
				BorderBox.lineUp();
				BorderBox.printLine("Please choose");
				BorderBox.printLine("[ADD COUPON]  :  [REMOVE COUPON]  :  [GO BACK]");
				BorderBox.printLine("Enter: ");
				BorderBox.printInput();
				choice = sc.nextLine();
				BorderBox.lineDown();
				
				//Stop the loop if the user input is correct
				if(choice.toLowerCase().matches("add coupon|remove coupon|go back"))
				{
					break;
				}
				
				BorderBox.printLine("Please enter the correct option");
			} while(true);
	
			if(choice.toLowerCase().matches("go back|exit"))
			{
				break;
			}
			
			switch (choice.toLowerCase()) {
			case "add coupon":
				addCouponUI(coup);
				break;
			case "remove coupon":
				removeCouponUI(coup);
				break;
			}
			
			//Re-enter all coupon on the ArrayList in coupon class
			coup.refreshCoupon();
		} while(true);
	}
	
	void addCouponUI(Coupons coup) throws IOException
	{
		String couponTitle, couponRate;
		Scanner sc = new Scanner(System.in);
		//Looping for add coupon
		do
		{
			BorderBox.printLine(Arrays.toString(coup.getCoupon()).replaceAll(":\\d+", ""));
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
	}
	
	void removeCouponUI(Coupons coup) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		String couponTitle;
		BorderBox.printLine(Arrays.toString(coup.getCoupon()).replaceAll(":\\d+", ""));
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
	}
	
	public void enterCoupon() throws IOException
	{
		Scanner scan = new Scanner(System.in);
		Coupons coupon = new Coupons();
		String user;
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
	}
}
