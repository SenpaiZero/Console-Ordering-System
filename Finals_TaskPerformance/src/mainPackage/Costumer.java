package mainPackage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import UserInterfaceClasses.BorderBox;

public class Costumer
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
			//Index 2: Birthday (formot: YY-MM-DD)
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
			writer.write("\n" + userName + ":" + password + ":" + birthday + ":" + contactNum);
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
		
		String[] userInfo = new String[3];
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
