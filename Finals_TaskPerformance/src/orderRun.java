
import java.io.IOException;
import java.net.URISyntaxException;

public class orderRun {
	public static void main(String[] args) throws IOException, URISyntaxException {
		Costumer costumer = new Costumer();
		Shop shop = new Shop();
		
		if(costumer.isLogin("user", "pass")) {
			System.out.println("asd");
		}
		
		if(costumer.isRegister("usedrlkksnuuuame", "aksd", "aksd")) {
			System.out.println("fhg");
		}
		
		costumer.changePassword("usedrlkksname", "aksd", "ygu");
		
		shop.printMenu();
		
		shop.customerBill("Food4", 4);
		shop.customerBill("drink3", 2);
		shop.customerBill("Food7", 1);
		
		System.out.println(shop.getTotalProfit());
		
		String[] food = {"Food2", "Food3", "Food7", "Food4", "drink5"};
		int[] quantity = {2, 3, 8, 5, 10};
		
		//bali example is food1 food3 food7
		htmlClass.setReciept(food, quantity, 5);
	}
}
