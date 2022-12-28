import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Costumer extends costumerData
{
	public static String path = ".\\src\\Data\\costumerData.txt";
	public static String path_temp = ".\\src\\Data\\tempCostumerData.txt";
	
	Costumer() 
	{
		
	}
	
	public boolean isLogin(String userName, String password) throws FileNotFoundException 
	{
		String[] accountInfo = new String[4]; 
		File file = new File(path);
		Scanner sc = new Scanner(file);
		
		//Get the lines of txt file
		//Pag blank na = stop
		while (sc.hasNextLine()) 
		{
			//Splitting each line sa txt file
			//from username:pass:1:1:2
			//to this sa baba
			
			//Index 1: Username
			//Index 2: Password
			//Index 3: 10% Voucher
			//Index 4: 25% Voucher
			//Index 5: 50% Voucher
			accountInfo = sc.nextLine().split(":");
			//Check if username and password is correct
			if(userName.equals(accountInfo[0]) && password.equals(accountInfo[1])) 
			{
				//Pag nakalogin yung user ay i-set yung count ng vouchers
				//in the encapsulation
				setVoucher10(Integer.valueOf(accountInfo[2].trim()));
				setVoucher25(Integer.valueOf(accountInfo[3].trim()));
				setVoucher50(Integer.valueOf(accountInfo[4].trim()));
				return true;
			} 
		}
		sc.close();
		return false;
	}
	
	public boolean isRegister(String userName, String password, String password_confirm) throws IOException 
	{
		//Taking username and password in one line
		String[] accountInfo = new String[1]; 
		File file = new File(path);
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) 
		{
			accountInfo = sc.nextLine().split(":");
			//Check if username already exist
			if(userName.equals(accountInfo[0].toString()))
			{
				//Stop the code (return) pag may katulad
				return false;
			} 
		}
		
		//Continue dito pag walang katulad yung username
		//Confirming password
		if(password.equals(password_confirm)) 
		{
			FileWriter writer = new FileWriter(path, true);
			//Save into txt file with the format 
			//username : password : voucher 10% : voucher 25% : voucher 50%
			writer.write("\n" + userName + ":" + password + ":0:0:0");
			writer.flush();
			return true;
		}
		sc.close();
		return false;
	}
	
	
	@SuppressWarnings("resource")
	public void changePassword(String username, String  password, String passwordChange) throws IOException 
	{
		File file = new File(path);
		Scanner sc = new Scanner(file);

		File file_temp = new File(path_temp);
		Scanner sc_temp = new Scanner(file_temp);
		
		String[] userInfo = new String[4];
		String line;
		
		FileWriter writer_temp = new FileWriter(path_temp);
		FileWriter writer = new FileWriter(path, true);
		
		//Moving original txt file to temp txt file then change the password
		while (sc.hasNextLine()) 
		{
			line = sc.nextLine();
			userInfo = line.split(":");
			//Check if the username is correct
			if(userInfo[0].equals(username)) 
			{
				//check if the password is correct
				if(userInfo[1].equals(password))
				{
					//Nilagay sa temp txt file yung user information (password changed)
					writer_temp.write(username + ":" + passwordChange + ":" 
							+ userInfo[2] + ":" + userInfo[3] + ":" + userInfo[4] + "\n");
				}
			}
			else
			{
				//Nilagay sa temp txt file yung user information data
				writer_temp.write(line + "\n");
			}
			writer_temp.flush();
		}
		
		//Instantiating new filewriter para ma-overwrite yung 
		//original txt file. Bali para maging blanko ulit
		new FileWriter(file, false);
		
		//Moving the temp txt file into original txt file 
		//each lines ng temp txt ay nireread
		while (sc_temp.hasNextLine()) 
		{
			//Nilalagay yung temporary txt lines sa string 
			//tapos ilalagay sa original txt files
			line = sc_temp.nextLine();
			writer.write(line + "\n");
			
		}
		writer.flush();
	}
}


//2nd class
class costumerData {
	//User personal information
	private String userName, password, contactNumber; 
	//Stores voucher count from user
	private int voucher10, voucher25, voucher50;
	
	
	public String getUserName() 
	{
		return userName;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	
	public void setContactNumber(String contactNumber) 
	{
		this.contactNumber = contactNumber;
	}
	
	public int getVoucher10() 
	{
		return voucher10;
	}
	
	public void setVoucher10(int voucher10) 
	{
		this.voucher10 = voucher10;
	}
	
	public int getVoucher25() 
	{
		return voucher25;
	}
	
	public void setVoucher25(int voucher25) 
	{
		this.voucher25 = voucher25;
	}
	
	public int getVoucher50() 
	{
		return voucher50;
	}
	
	public void setVoucher50(int voucher50) 
	{
		this.voucher50 = voucher50;
	} 
	
}
