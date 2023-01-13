package UserInterfaceClasses;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import mainPackage.Wallet;

public class WalletUI 
{
	public void showMenu() throws NumberFormatException, IOException
	{
		String input;
		Scanner scanner = new Scanner(System.in);
		Wallet wallet = new Wallet();
		
		BorderBox.lineUp();
		BorderBox.printLine("Your current balance: " + wallet.getWallet());
		BorderBox.lineDown();
		
		do
		{
			BorderBox.lineUp();
			BorderBox.printLine("Please choose");
			BorderBox.printLine("[CASH IN]  :  [CASH OUT]  :  [EXIT]");
			BorderBox.printInput();
			input = scanner.nextLine();
			BorderBox.lineDown();
			
			if(input.toLowerCase().matches("cash in|cash out|exit"))
			{
				break;
			}
			
			BorderBox.printLine("Please enter the correct option");
		} while(true);
		
		switch (input.toLowerCase()) {
		case "cash in":
			do
			{
				BorderBox.lineUp();
				BorderBox.printLine("Enter the amount you want to cash in");
				BorderBox.printInput();
				input = scanner.nextLine();
				BorderBox.lineDown();
				
				if(Pattern.matches("\\d+", input))
				{
					break;
				}
				
				BorderBox.printLine("Invalid input");
			} while(true);
			
			wallet.cashIn(Integer.valueOf(input));
			break;
		case "cash out":
			do
			{
				BorderBox.lineUp();
				BorderBox.printLine("Enter the amount you want to cash out");
				BorderBox.printInput();
				input = scanner.nextLine();
				BorderBox.lineDown();
				
				if(Pattern.matches("\\d+", input))
				{
					break;
				}
				
				BorderBox.printLine("Invalid input");
			} while(true);
			wallet.cashOut(Integer.valueOf(input));
			break;
		case "exit":
			break;
		}
	}
}
