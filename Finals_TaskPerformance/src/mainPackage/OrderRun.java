package mainPackage;

import java.io.IOException; 
import java.util.Scanner;
import UserInterfaceClasses.BorderBox;

public class OrderRun {
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException 
	{
		String userChoice;
		boolean isStart = false;
		header();
		do
		{
			if(isStart == true) BorderBox.lineUp();
			isStart = true;
			
			BorderBox.lineUp();
			BorderBox.printLine("Please choose");
			BorderBox.printLine("[ADMIN]  :  [COSTUMER]  :  [EXIT]");
			BorderBox.lineDown();
			do 
			{
				BorderBox.lineUp();
				BorderBox.printLine("Enter: ");
				BorderBox.printInput();
				userChoice = scan.nextLine();
				BorderBox.lineDown();
				
				if(userChoice.toLowerCase().matches("costumer|admin|exit"))
				{
					break;
				}
				
				BorderBox.printLine("Please enter the correct option");
			} while (true);
			
			switch (userChoice.toLowerCase())
			{
				case "admin":
					Admin admin = new Admin();
					admin.adminLogin();
					break;
				case "costumer":
					Costumer costumer = new Costumer();
					costumer.startMenu();
					break;
				case "exit":
					BorderBox.lineUp();
					BorderBox.printLine("The system has stopped");
					BorderBox.lineDown();
					System.exit(0);
					break;
			}
		} while(true);
	}
	
	static void header()
	{       
		BorderBox.lineUp();
		BorderBox.printLine("                                                                      ");
		BorderBox.printLine("             _ _ _  _____  _____  _____  _____  __ __                 ");
		BorderBox.printLine("            | | | ||     || __  ||_   _||  |  ||  |  |                ");
		BorderBox.printLine("            | | | ||  |  ||    -|  | |  |     ||_   _|                ");
		BorderBox.printLine("            |_____||_____||__|__|  |_|  |__|__|  |_|                  ");
		BorderBox.printLine("                                                                      ");
		BorderBox.printLine("                                                                      ");
		BorderBox.printLine(" _____  _____  _____  _____  _____  _____  _____  _____  _____  _____ ");
		BorderBox.printLine("| __  ||   __||   __||_   _||  _  ||  |  || __  ||  _  ||   | ||_   _|");
		BorderBox.printLine("|    -||   __||__   |  | |  |     ||  |  ||    -||     || | | |  | |  ");
		BorderBox.printLine("|__|__||_____||_____|  |_|  |__|__||_____||__|__||__|__||_|___|  |_|  ");
		BorderBox.printLine("                                                                      ");
		BorderBox.printLine("                                                                      ");
		BorderBox.lineDown();
	}
}
