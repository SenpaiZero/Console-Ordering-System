
package mainPackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import UserInterfaceClasses.BorderBox;
import UserInterfaceClasses.WalletUI;

public class Wallet extends WalletUI
{
	static String walletPath = ".\\src\\Data\\costumerWallet.txt";
	
	int balance;
	public Wallet() throws IOException
	{
		File file = new File(walletPath);
		Scanner sc = new Scanner(file);
		costumerData cData = new costumerData();
		
		String line;
		String temp[] = new String[1];
		boolean isExist = false;
		while(sc.hasNextLine())
		{
			line = sc.nextLine();
			temp = line.split(":");
			if(line.contains(cData.getUserName()))
			{
				setWallet(Integer.valueOf(temp[1]));
				isExist = true;
				break;
			}
		}
		
		//Add the user to the wallet txt file
		//if the user wallet does not exist
		if(isExist == false)
		{
			FileWriter writer = new FileWriter(walletPath, true);
			writer.write(cData.getUserName() + ":" + "0" + "\n");
			writer.flush();
		}
	}
	
	
	
	public void changeBalance(int money, boolean isCashIn) throws IOException
	{
		File file = new File(walletPath);
		Scanner sc = new Scanner(file);
		costumerData cData = new costumerData();
		
		String line;
		while(sc.hasNextLine())
		{
			line = sc.nextLine();
			if(line.contains(cData.getUserName()))
			{
				if(isCashIn == true)
				{
					setWallet((getWallet() + money));
				}
				else 
				{
					setWallet(Math.abs(getWallet() - money));
				}
				saveWallet(cData.getUserName(), getWallet());
				break;
			}
		}
	}
	
	void saveWallet(String user, int wallet) throws IOException
	{
		File file = new File(walletPath);
		Scanner sc = new Scanner(file);
		costumerData cData = new costumerData();
		String line;
		String temp[] = new String[1];
		StringBuilder str = new StringBuilder();
		
		while(sc.hasNextLine())
		{
			line = sc.nextLine();
			temp = line.split(":");
			                   
			if(line.contains(cData.getUserName()))
			{
				str.append(temp[0] + ":" + wallet);
				break;
			}
			else
			{
				str.append(line + "\n");
			}
		}
		
		FileWriter writer = new FileWriter(walletPath);
		writer.write(str + "");
		writer.flush();
		
	}
	
	public void setWallet(int balance)
	{
		this.balance = balance;
	}
	
	public int getWallet()
	{
		return balance;
	}
	
	public void cashIn(int cash) throws IOException
	{
		if(cash > 0)
		{
			changeBalance(cash, true);
			BorderBox.lineUp();
			BorderBox.printLine("You've successfully cash in");
			BorderBox.lineDown();
		}
		else
		{
			BorderBox.printLine("Invalid input");
		}
	}
	
	public void cashOut(int cash) throws IOException
	{
		if(cash < getWallet())
		{
			changeBalance(cash, false);
			BorderBox.lineUp();
			BorderBox.printLine("You've successfully cash out");
			BorderBox.lineDown();
		}
		else 
		{
			BorderBox.printLine("Insufficient Funds");
		}
	}
	
}
