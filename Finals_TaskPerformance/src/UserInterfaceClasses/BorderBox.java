package UserInterfaceClasses;

import org.apache.commons.lang3.StringUtils;

public class BorderBox {

	public static void printLine(String print)
	{
		if(print.length() > 145)
		{
			String[] temp = new String[2];
			if(print.length() % 2 == 0)
			{
				temp[0] = print.substring(0, (print.length()/2));
				temp[1] = print.substring((print.length()/2));
			}
			else
			{
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
	
	public static void printInput()
	{
		System.out.print(StringUtils.center("", 85));
	}
	static void lineStart()
	{
		System.out.print("                ║");
	}
	
	static void lineLast()
	{
		System.out.print("║\n");
	}
	
	public static void lineUp()
	{
		System.out.print("                ╔");
		for (int i = 0; i < 145; i++) 
		{
			System.out.print("═");
		}
		System.out.print("╗\n");
	}
	
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
