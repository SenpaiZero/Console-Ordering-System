package mainPackage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.NonWritableChannelException;
import java.util.Scanner;

import UserInterfaceClasses.BorderBox;
import UserInterfaceClasses.CostumerUI;

public class Costumer extends CostumerUI
{
	public static String path = ".\\src\\Data\\costumerData.txt";
	public static String path_temp = ".\\src\\Data\\tempCostumerData.txt";
	
	public boolean isLogin(String userName, String password) throws FileNotFoundException 
	{
		String[] accountInfo = new String[3]; 
		File file = new File(path);
		Scanner sc = new Scanner(file);
		costumerData cData = new costumerData();
		
		while (sc.hasNextLine()) 
		{
			accountInfo = sc.nextLine().split(":");
			if(userName.equals(accountInfo[0]) && password.equals(accountInfo[1])) 
			{
				cData.setUserName(accountInfo[0].trim());
				cData.setPassword(accountInfo[1].trim());
				cData.setBirthday(accountInfo[2].trim());
				cData.setContactNumber(accountInfo[3].trim());
				return true;
			} 
		}
		sc.close();
		BorderBox.printLine("Account does not exist");
		BorderBox.lineUp();
		return false;
	}
	
	public boolean isRegister(String userName, String password, String password_confirm, String birthday, String contactNum) throws IOException 
	{
		String[] accountInfo = new String[1]; 
		File file = new File(path);
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) 
		{
			accountInfo = sc.nextLine().split(":");
			if(userName.equals(accountInfo[0].toString()))
			{
				return false;
			} 
		}
		
		if(password.equals(password_confirm)) 
		{
			FileWriter writer = new FileWriter(path, true);
			writer.write(userName + ":" + password + ":" + birthday + ":" + contactNum + "\n");
			writer.flush();
			return true;
		}
		else {
			BorderBox.printLine("Incorrect confirm password");
		}
		sc.close();
		return false;
	}
	
}
