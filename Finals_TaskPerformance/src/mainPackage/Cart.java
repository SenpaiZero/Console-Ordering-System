package mainPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import UserInterfaceClasses.BorderBox;
import UserInterfaceClasses.Shop;

public class Cart 
{
	static ArrayList<String> cartDish = new ArrayList<>();
	static ArrayList<String> cartQuantity = new ArrayList<>();
	
	public void addCart(String dish, String quantity)
	{
		boolean isAdd = false;
		if(searchCart(Shop.main, dish))
		{
			isAdd = true;
		}
		
		if(searchCart(Shop.drink, dish))
		{
			isAdd = true;
		}

		if(searchCart(Shop.dessert, dish))
		{
			isAdd = true;
		}
		
		if(searchCart(Shop.special, dish))
		{
			isAdd = true;
		}
		
		if(isAdd == true)
		{
			cartDish.add(dish);
			cartQuantity.add(quantity);
		}
	}
	
	boolean searchCart(List<String> list, String dish)
	{
		for (int i = 0; i < list.size(); i++)
		{
			if(list.get(i).toString().equalsIgnoreCase(dish))
			{
				if(list.get(i).toString().equalsIgnoreCase("available"))
				{
					BorderBox.lineUp();
					BorderBox.printLine("The " + dish + " has been added to the cart");
					BorderBox.lineDown();
					return true;
				}
				else {
					BorderBox.printLine(dish + " is unavailable at the moment");
				}
			}
			else {
				BorderBox.printLine("The " + dish + " is not on the menu");
			}
		}
		return false;
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
		
		BorderBox.lineUp();
		System.out.printf("                %s %25s %44s %21s %42s%n%n","║", "", "YOUR CART", "", "║");
		System.out.printf("                %s %25s %40s %25s %42s%n","║", "ID", "CART", "QUANTITY", "║");
		for (int i = 0; i < cartDish.size(); i++) 
		{
			System.out.printf("                %s %25s %40s %25s %42s%n", "║", (i+1), cartDish.get(i), cartQuantity.get(i), "║");
		}
		BorderBox.lineDown();
	}
	
	public void editCart()
	{
		String user, quantity, id;
		Scanner scanner = new Scanner(System.in);
		BorderBox.lineUp();
		BorderBox.printLine("Please choose");
		BorderBox.printLine("[REMOVE]  :  [CHANGE QUANTITY]  :  [EXIT]");
		BorderBox.printLine("Enter: ");
		BorderBox.printInput();
		user = scanner.nextLine();
		BorderBox.lineDown();
		
		switch (user.toLowerCase()) 
		{
		//Remove
		case "remove":
			BorderBox.lineUp();
			BorderBox.printLine("Enter the ID of the item you want to remove");
			BorderBox.printLine("Enter ID: ");
			BorderBox.printInput();
			id = scanner.nextLine();
			BorderBox.lineDown();			
			removeCart_edit(Integer.valueOf(id));
			break;
		//Change
		case "change quantity":
			do
			{
				BorderBox.lineUp();
				BorderBox.printLine("Enter the ID of the item you want to change");
				BorderBox.printLine("Enter ID:");
				BorderBox.printInput();
				id = scanner.nextLine();
				if(Pattern.matches("^[0-9]", id))
				{
					if(cartDish.size() < Integer.valueOf(id))
					{
						BorderBox.printLine("Enter Quantity: ");
						BorderBox.printInput();
						quantity = scanner.nextLine();
						changeQuantityCart(Integer.valueOf(id), quantity);
						BorderBox.lineDown();
						break;
					}
					BorderBox.printLine("Please enter the correct ID");
				}
				else
				{
					BorderBox.printLine("Please enter a valid input");
				}
			} while(true);
			break;
		//exit
		case "exit":
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
			BorderBox.printLine("Invalid input");
		}
	}
	
	void changeQuantityCart(int id, String quantity)
	{
		if(id >= 1 && Integer.valueOf(quantity) >= 1)
		{
			id--;
			cartQuantity.set(id, quantity);
			BorderBox.printLine("The " + cartDish.get(id) + " quantity have been changed to " + quantity);
		}
		
	}
}
