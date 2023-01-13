package UserInterfaceClasses;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class BorderBox {

	//Function for printing centered string
	public static void printLine(String print)
	{
		lineStart();
		System.out.print(StringUtils.center(print, 135));
		lineLast();
	}
	
	//Function for printing centered scanner (most of the time)
	public static void printInput()
	{
		System.out.print(StringUtils.center("", 80));
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
		for (int i = 0; i < 135; i++) 
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
		for (int i = 0; i < 135; i++) 
		{
			System.out.print("═");
		}
		System.out.print("╝\n");
	}
}
