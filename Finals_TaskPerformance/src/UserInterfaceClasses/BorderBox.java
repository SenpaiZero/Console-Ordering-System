package UserInterfaceClasses;

import org.apache.commons.lang3.StringUtils;

public class BorderBox {

	//Function for printing centered string
	public static void printLine(String print)
	{
		//If the string length is more than 145 = split the string into 2 parts
		if(print.length() > 145)
		{
			String[] temp = new String[2];
			//Checking if the string is odd
			//even
			if(print.length() % 2 == 0)
			{
				//Splitting the string for even
				temp[0] = print.substring(0, (print.length()/2));
				temp[1] = print.substring((print.length()/2));
			}
			//odd
			else
			{
				//splitting the string for odd
				temp[0] = print.substring(0, ((print.length()/2)-1));
				temp[1] = print.substring((print.length()/2));
			}
			
			lineStart();
			System.out.print(StringUtils.center(temp[0], 145));
			lineLast();
			lineStart();
			System.out.print(StringUtils.center(temp[1], 145));
			lineLast();
		}
		else {
			lineStart();
			System.out.print(StringUtils.center(print, 145));
			lineLast();
		}
	}
	
	//Function for printing centered scanner (most of the time)
	public static void printInput()
	{
		System.out.print(StringUtils.center("", 85));
	}
	//Printing lines para sa gilid
	static void lineStart()
	{
		System.out.print("                ║");
	}
	
	//Printing lines para sa gilid sa dulo 
	//new method since may new line
	static void lineLast()
	{
		System.out.print("║\n");
	}
	
	//Printing lines for up
	public static void lineUp()
	{
		System.out.print("                ╔");
		for (int i = 0; i < 145; i++) 
		{
			System.out.print("═");
		}
		System.out.print("╗\n");
	}
	
	//Printing lines for downn
	//May corner din kaya hiwalay
	public static void lineDown()
	{
		System.out.print("                ╚");
		for (int i = 0; i < 145; i++) 
		{
			System.out.print("═");
		}
		System.out.print("╝\n");
	}
}
