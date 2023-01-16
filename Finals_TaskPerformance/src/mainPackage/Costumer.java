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
		
		//Get the lines of txt file
		//Pag blank na = stop
		while (sc.hasNextLine()) 
		{
			//Splitting each line sa txt file with : as separator
			//from username:pass:bday:contact num
			//to this sa baba
			
			//Index 0: Username
			//Index 1: Password
			//Index 2: Birthday (formot: YYYY-MM-DD)
			//Index 3: Contact Number
			accountInfo = sc.nextLine().split(":");
			//Check if username and password is correct
			if(userName.equals(accountInfo[0]) && password.equals(accountInfo[1])) 
			{
				//Pag nakalogin yung user ay i-set yung mga data
				//in the encapsulation
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
			//username : password : Birthday : Contact Number
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
	
	
	@SuppressWarnings("resource")
	public boolean changePassword(String currentPass, String newPass, String newPassConfirm) throws IOException 
	{
		File file = new File(path);
		Scanner sc = new Scanner(file);
		StringBuilder sBuild = new StringBuilder();
		String line;
		String[] tempData = new String[3];
		boolean isChange = false, isDone = false;
		
		costumerData cData = new costumerData();
		
		//Check each line of txt file
		while (sc.hasNextLine()) 
		{
			line = sc.nextLine();
			tempData = line.split(":");
			
			//Check username
			if(tempData[0].equals(cData.getUserName()))
			{
				//Check current password
				if(currentPass.equals(tempData[1]))
				{
					//confirms the password
					if(newPass.equals(newPassConfirm))
					{
						//save the user input to StringBuilder for temporary data
						sBuild.append(tempData[0] + ":" + newPass + ":" + tempData[2] + ":" + tempData[3] + "\n");
						isChange = true;
						isDone = true;
					}
					else
					{
						BorderBox.printLine("Please confirm your new password");
					}
				}
				else 
				{
					BorderBox.printLine("Incorrect current password");
				}
			}
			
			//Prevent saving twice 
			if(isChange == false)
			{
				sBuild.append(line + "\n");
				isChange = false;
			}
			
		}
		
		//save the data if the user input is correct
		if(isDone == true)
		{
			FileWriter writer = new FileWriter(path);
			writer.write(sBuild.toString());
			writer.flush();
			return true;
		}
		return false;
	}
}
