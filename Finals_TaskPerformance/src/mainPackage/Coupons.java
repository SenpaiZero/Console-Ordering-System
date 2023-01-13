package mainPackage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import UserInterfaceClasses.BorderBox;
import UserInterfaceClasses.CouponUI;


public class Coupons extends CouponUI
{
	static String path = ".\\src\\Data\\couponData.txt";
	private String[] coupon;
	private double discount;
	
	//Constructor for adding all coupons in string array
	public Coupons() throws FileNotFoundException {
		File file = new File(path);
		Scanner sc = new Scanner(file);
		LinkedList<String> tempCoupon = new LinkedList<String>();

		while (sc.hasNextLine()) 
		{
			tempCoupon.add(sc.nextLine());
		}
		
		setCoupons(tempCoupon.toArray(new String[tempCoupon.size()]));
	}
	
	public void removeCoupon(String coupon, boolean isUse) throws IOException
	{
		File file = new File(path);
		Scanner sc = new Scanner(file);
		StringBuilder couponTxt = new StringBuilder();
		String line;
		String[] lineArr = new String[1];
		boolean isExist = false;

		while (sc.hasNextLine()) 
		{
			line = sc.nextLine();
			lineArr = line.split(":");
			if(coupon.equalsIgnoreCase(lineArr[0]))
			{
				isExist = true;
				couponTxt.append("");
			}
			else
			{
				couponTxt.append(line + "\n");
			}
		}
		
		if(isUse == false)
		{
			if(isExist == true)
			{
				BorderBox.lineUp();
				BorderBox.printLine("You've successfully removed the coupon: " + coupon);
				BorderBox.lineDown();
			}
			else
			{
				BorderBox.printLine("The coupon you entered does not exist");
			}
		}
		FileWriter writer = new FileWriter(path);
		writer.write(String.valueOf(couponTxt));
		writer.flush();	
	}
	
	public void addCoupon(String coupon, String discount) throws IOException
	{
		File file = new File(path);
		Scanner scan = new Scanner(file);
		String line;
		String[] lineArr = new String[1];
		boolean isWrite = true;
		while (scan.hasNextLine())
		{
			line = scan.nextLine();
			lineArr = line.split(":");
			//if it exist dont add
			if(lineArr[0].equalsIgnoreCase(coupon))
			{
				isWrite = false;
			}
		}
		
		if(isWrite == true)
		{
			BorderBox.lineUp();
			BorderBox.printLine("You've successfully added a coupon! ");
			BorderBox.lineDown();
			FileWriter writer = new FileWriter(path, true);
			writer.write("\n"+coupon + ":" + discount);
			writer.flush();
		}
		else
		{
			BorderBox.printLine("The coupon already exist");
		}
	}
	
	public boolean useCoupon(String coupon) throws IOException
	{
		String line;
		String[] data = new String[1];
		for (int i = 0; i < getCoupon().length; i++) 
		{
			line = getCoupon()[i];
			data = line.split(":");
			if(data[0].equals(coupon))
			{
				removeCoupon(coupon, true);
				setDiscount(Double.valueOf(data[1]));
				return true;
			}
		}
		return false;
	}


	void setDiscount(double discount)
	{
		this.discount = discount;
	}
	
	public double getDiscount()
	{
		return discount;
	}
	
	void setCoupons(String[] coupon)
	{
		this.coupon = coupon;
	}
	
	public String[] getCoupon()
	{
		return coupon;
	}
	
}

