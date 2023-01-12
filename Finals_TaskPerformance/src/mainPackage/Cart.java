package mainPackage;

import java.util.ArrayList;
import java.util.Scanner;

import UserInterfaceClasses.BorderBox;

public class Cart 
{
	ArrayList<String> cartDish = new ArrayList<>();
	ArrayList<String> cartQuantity = new ArrayList<>();
	
	public void addCart(String dish, String quantity)
	{
		cartDish.add(dish);
		cartQuantity.add(quantity);
	}
	
	public String[] getCartDish()
	{
		return cartDish.toArray(new String[cartDish.size()]);
	}
	
	public String[] getCartQuantity()
	{
		return cartQuantity.toArray(new String[cartQuantity.size()]);
	}
	
	public void clearCart()
	{
		cartDish.clear();
		cartQuantity.clear();
	}
	
	public void viewCart()
	{
		final String line = "================================================================";
		BorderBox.printLine(line);
		System.out.printf("%15s %15s %15s%n", "ID", "CART", "QUANTITY");
		for (int i = 0; i < cartDish.size(); i++) 
		{
			System.out.printf("%15s %15s %15s%n", (i+1), cartDish.get(i), cartQuantity.get(i));
		}
		BorderBox.printLine(line);
	}
	
	public void editCart()
	{
		String user, quantity, id;
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nPlease choose: [1] Remove  [2] Change Quantity  [3] Exit");
		System.out.print("Enter: ");
		user = scanner.nextLine();
		
		switch (user) 
		{
		//Remove
		case "1":
			System.out.println("\nEnter the ID of the item you want to remove");
			System.out.print("Enter ID: ");
			id = scanner.nextLine();
			removeCart_edit(Integer.valueOf(id));
			break;
		//Change
		case "2":
			System.out.println("\nEnter the ID of the item you want to change");
			System.out.print("Enter ID: ");
			id = scanner.nextLine();
			System.out.print("Enter Quantity: ");
			quantity = scanner.nextLine();
			changeQuantityCart(Integer.valueOf(id), quantity);
			break;
		//exit
		case "3":
			break;
		}
	}
	
	void removeCart_edit(int id)
	{
		if(id >= 1)
		{
			id--;
			cartDish.remove(id);
			cartQuantity.remove(id);
		}
		else 
		{
			System.out.println("Invalid input");
		}
	}
	
	void changeQuantityCart(int id, String quantity)
	{
		if(id >= 1 && Integer.valueOf(quantity) >= 1)
		{
			id--;
			cartQuantity.set(id, quantity);
		}
		
	}
}
