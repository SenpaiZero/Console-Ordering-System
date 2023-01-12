package mainPackage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;


public class Coupons {
	static String path = ".\\src\\Data\\couponData.txt";
	private String[] coupon;
	private double discount;
	
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
	
	void setCoupons(String[] coupon)
	{
		this.coupon = coupon;
	}
	
	public String[] getCoupon()
	{
		return coupon;
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
				System.out.println("You've successfully removed the coupon: " + coupon);
			}
			else
			{
				System.out.println("The coupon you entered does not exist");
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
			System.out.println("You've successfully added a coupon! ");
			FileWriter writer = new FileWriter(path, true);
			writer.write("\n"+coupon + ":" + discount);
			writer.flush();
		}
		else
		{
			System.out.println("The coupon already exist");
		}
	}
	
	void setDiscount(double discount)
	{
		this.discount = discount;
	}
	
	public double getDiscount()
	{
		return discount;
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
}
