package mainPackage;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.apache.commons.lang3.StringUtils;

import UserInterfaceClasses.Shop;


public class HtmlClass {
	public static String htmlPath = ".\\src\\html\\Reciept.html";
	
	static StringBuilder html = new StringBuilder();
	static double total;
	
	public void setReciept(String[] cart, String[] quantity, double discount, boolean isOpen) throws IOException
	{
		costumerData cData = new costumerData();
		html.delete(0, html.length());
		total = 0;
		//Adding value to string builder with string
		//bali string builder accepts all tags like /n and more
		html.append("<html>\r\n"
				+ "    <head>\r\n"
				+ "        <style>\r\n"
				+ "            #invoice-POS{\r\n"
				+ "  box-shadow: 0 0 1in -0.25in rgba(0, 0, 0, 0.5);\r\n"
				+ "  padding:2mm;\r\n"
				+ "  margin: 0 auto;\r\n"
				+ "  width: 44mm;\r\n"
				+ "  background: #FFF;\r\n"
				+ "            }\r\n"
				+ "  \r\n"
				+ "::selection {background: #f31544; color: #FFF;}\r\n"
				+ "::moz-selection {background: #f31544; color: #FFF;}\r\n"
				+ "h1{\r\n"
				+ "  font-size: 1.5em;\r\n"
				+ "  color: #222;\r\n"
				+ "}\r\n"
				+ "h2{font-size: .9em;}\r\n"
				+ "h3{\r\n"
				+ "  font-size: 1.2em;\r\n"
				+ "  font-weight: 300;\r\n"
				+ "  line-height: 2em;\r\n"
				+ "}\r\n"
				+ "p{\r\n"
				+ "  font-size: .7em;\r\n"
				+ "  color: #666;\r\n"
				+ "  line-height: 1.2em;\r\n"
				+ "}\r\n"
				+ " \r\n"
				+ "#top, #mid,#bot{ /* Targets all id with 'col-' */\r\n"
				+ "  border-bottom: 1px solid #EEE;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "#top{min-height: 100px;}\r\n"
				+ "#mid{min-height: 80px;} \r\n"
				+ "#bot{ min-height: 50px;}\r\n"
				+ "\r\n"
				+ "#top .logo{\r\n"
				+ "  //float: left;\r\n"
				+ "	height: 80px;\r\n"
				+ "	width: 80px;\r\n"
				+ "	background: url(https://i.imgur.com/jBCrmO1.png) no-repeat;\r\n"
				+ "	background-size: 80px 80px;\r\n"
				+ "}\r\n"
				+ ".clientlogo{\r\n"
				+ "  float: left;\r\n"
				+ "	height: 80px;\r\n"
				+ "	width: 80px;\r\n"
				+ "	background: url(http://michaeltruong.ca/images/client.jpg) no-repeat;\r\n"
				+ "	background-size: 60px 60px;\r\n"
				+ "  border-radius: 50px;\r\n"
				+ "}\r\n"
				+ ".info{\r\n"
				+ "  display: block;\r\n"
				+ "  //float:left;\r\n"
				+ "  margin-left: 0;\r\n"
				+ "}\r\n"
				+ ".title{\r\n"
				+ "  float: right;\r\n"
				+ "}\r\n"
				+ ".title p{text-align: right;} \r\n"
				+ "table{\r\n"
				+ "  width: 100%;\r\n"
				+ "  border-collapse: collapse;\r\n"
				+ "}\r\n"
				+ "td{\r\n"
				+ "  //padding: 5px 0 5px 15px;\r\n"
				+ "  //border: 1px solid #EEE\r\n"
				+ "}\r\n"
				+ ".tabletitle{\r\n"
				+ "  //padding: 5px;\r\n"
				+ "  font-size: .5em;\r\n"
				+ "  background: #EEE;\r\n"
				+ "}\r\n"
				+ ".service{border-bottom: 1px solid #EEE;}\r\n"
				+ ".item{width: 24mm;}\r\n"
				+ ".itemtext{font-size: .5em;}\r\n"
				+ "\r\n"
				+ "#legalcopy{\r\n"
				+ "  margin-top: 5mm;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "  \r\n"
				+ "  \r\n"
				+ "}\r\n"
				+ "        </style>\r\n"
				+ "    </head>\r\n"
				+ "    <body>\r\n"
				+ "        \r\n"
				+ "  <div id=\"invoice-POS\">\r\n"
				+ "    \r\n"
				+ "    <center id=\"top\">\r\n"
				+ "      <div class=\"logo\"></div>\r\n"
				+ "      <div class=\"info\"> \r\n"
				+ "        <h2>WORTHY RESTAURANT</h2>\r\n"
				+ "      </div><!--End Info-->\r\n"
				+ "    </center><!--End InvoiceTop-->\r\n"
				+ "    \r\n"
				+ "    <div id=\"mid\">\r\n"
				+ "      <div class=\"info\">\r\n"
				+ "        <h2>Contact Info</h2>\r\n"
				+ "        <p> \r\n"
				+ "            <b>ADDRESS</b>   : " + cData.getAddress() + "</br>\n"
				+ "            <b>USERNAME</b>   : " + cData.getUserName() + "</br>\n"
				+ "            <b>Phone</b>   : "+ cData.getContactNumber() +"</br>\n"
				+ "        </p>\r\n"
				+ "      </div>\r\n"
				+ "    </div><!--End Invoice Mid-->\n"
				+ "    \n"
				+ "    <div id=\"bot\">\n"
				+ "\n"
				+ "					<div id=\"table\">\r\n"
				+ "						<table>\r\n"
				+ "							<tr class=\"tabletitle\">\r\n"
				+ "								<td class=\"item\"><h2>Item</h2></td>\r\n"
				+ "								<td class=\"Hours\"><h2>Qty</h2></td>\r\n"
				+ "								<td class=\"Rate\"><h2>Sub Total</h2></td>\r\n");
				
				
				//add new item on html for every new item that costumers added
				for (int i = 0; i < cart.length; i++)
				{
										
					addTextReciept(Shop.dessert, cart, quantity, i);
					addTextReciept(Shop.special, cart, quantity, i);
					addTextReciept(Shop.drink, cart, quantity, i);
					addTextReciept(Shop.main, cart, quantity, i);
				}
				html.append("\r\n"
				+ "							<tr class=\"tabletitle\">\r\n"
				+ "								<td></td>\r\n"
				+ "								<td class=\"Rate\"><h2>Discount</h2></td>\r\n"
				+ "								<td class=\"payment\"><h2>"+ discount +"%</h2></td>\r\n"
				+ "							</tr>\r\n"
				+ "\r\n"
				+ "							<tr class=\"tabletitle\">\r\n"
				+ "								<td></td>\r\n"
				+ "								<td class=\"Rate\"><h2>Total</h2></td>\r\n"
				+ "								<td class=\"payment\"><h2>"+ (total -((discount / 100) * total)) +"</h2></td>\r\n"
				+ "							</tr>\r\n"
				+ "\r\n"
				+ "						</table>\r\n"
				+ "					</div><!--End Table-->\r\n"
				+ "\r\n"
				+ "					<div id=\"legalcopy\">\r\n"
				+ "						<p class=\"legal\"><strong>Thank you for your order!</strong> <br> Your food will be delivered within 30 minutes. \r\n"
				+ "						</p>\r\n"
				+ "					</div>\r\n"
				+ "\r\n"
				+ "				</div><!--End InvoiceBot-->\r\n"
				+ "  </div><!--End Invoice-->\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "    <script>\r\n"
				+ "        document.body.style.zoom=\"170%\"\r\n"
				+ "    </script>\r\n"
				+ "    </body>\r\n"
				+ "</html>");
				
				//total in console
				cData.setTotal(Integer.valueOf((int) (total -((discount / 100) * total))));
				//Writing and saving the string builder into html
				FileWriter writer = new FileWriter(htmlPath);
				writer.write(html.toString());
				writer.flush();
				
				//Opening the html file in browser
				if(isOpen == true)
				{
				File htmlFile = new File(htmlPath);
				Desktop.getDesktop().open(htmlFile);
				}
		
	}
	
	public static void addTextReciept(List<String> listItem, String[] cart, String[] quantity, int i)
	{
		String noVowels = cart[i].replaceAll("[aeiou]", "");
		if(listItem.stream().anyMatch(cart[i]::equalsIgnoreCase))
		{
				html.append("							<tr class=\"service\">\n"
						+"								<td class=\"tableitem\"><p class=\"itemtext\">"+ noVowels.replaceAll(" ", "_") +"</p></td>\n"
						+"								<td class=\"tableitem\"><p class=\"itemtext\">"+ quantity[i] +"</p></td>\n"
						+"								<td class=\"tableitem\"><p class=\"itemtext\">"+ (getPrice(cart[i]) * Integer.valueOf(quantity[i])) +"</p></td>\r\n\n"
						+"							</tr>\n");
				total += (getPrice(cart[i]) * Integer.valueOf(quantity[i]));
		}
	}
	
	//Getting the price of the item by looking at the cart
	static int getPrice(String cart) 
	{
		//Getting the value of food price
		for (int i = 0; i < Shop.main.size(); i++) 
		{
			if(cart.equalsIgnoreCase(Shop.main.get(i))) 
			{
				return Integer.valueOf(Shop.mainPrice.get(i));
			}
		}
		
		//Getting the value of drinks price
		for (int i = 0; i < Shop.drink.size(); i++) 
		{
			if(cart.equalsIgnoreCase(Shop.drink.get(i))) 
			{
				return Integer.valueOf(Shop.drinkPrice.get(i));
			}
		}

		//Getting the value of special menu price
		for (int i = 0; i < Shop.special.size(); i++) 
		{
			if(cart.equalsIgnoreCase(Shop.special.get(i))) 
			{
				return Integer.valueOf(Shop.specialPrice.get(i));
			}
		}
		
		//Getting the value of dessert price
		for (int i = 0; i < Shop.dessert.size(); i++) 
		{
			if(cart.equalsIgnoreCase(Shop.dessert.get(i))) 
			{
				return Integer.valueOf(Shop.dessertPrice.get(i));
			}
		}
		return 0;
	}
}
