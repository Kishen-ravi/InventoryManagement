import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ExecutingClass implements Referance {
	Map<Integer,Products> existing= new HashMap<Integer,Products>();
	Map<Integer,Products> buying= new HashMap<Integer,Products>();

	@Override
	public void mainMenu() {
		System.out.println("Product available:");
		System.out.println("_________________________________________________");
		System.out.println("1. Buy Products \n2. View Cart \n3. Exit");
		System.out.println("Choose a number:");
		try{
		@SuppressWarnings("resource")
		Scanner in=new Scanner(System.in);
		int key= in.nextInt();
		switch (key) {
			case 1:
				showList();
				break;
			case 2:
				cart();
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("Choose the correct number...");
				mainMenu();
				
			}
		}
		catch(Exception e){
			System.out.println("Enter a number...");
			System.out.println(e);
			mainMenu();
		}
	}
	

	public void putItem() {
		existing.put(1, new Products("books",25,120));
		existing.put(2, new Products("vegetable",15,220));  
		existing.put(3, new Products("fruit",10,20));  
	}
	
	@Override
	public void showList() {
		Set set = existing.entrySet();
		Iterator itr= set.iterator();
		System.out.println("The Available items are:");
		System.out.println("ID  Product  Price/unit  Available units");
		System.out.println("______________________________________________________");
		while(itr.hasNext()){  
			Map.Entry<Integer, Products> entry=(Map.Entry)itr.next();
			System.out.println(entry.getKey()+ "  " +  entry.getValue().getName() + "\t" + entry.getValue().getPrice() + "\t" +entry.getValue().getQuantity());
		}
		System.out.println("Enter the ID of the product you want to choose:");
		try{
			@SuppressWarnings("resource")
			Scanner in=new Scanner(System.in);
			int ID= in.nextInt();
			if(ID<4 && ID>0){
				System.out.println("The product you have choosed is:");
				System.out.println("Product  Price/unit  Available units");
				System.out.println("______________________________________________________");
				Products ps = existing.get(ID);
				System.out.println(ps.getName() + "\t" + ps.getPrice() + "\t"+ ps.getQuantity());
				puting(ID);
			}
			else{
				System.out.println("Enter the correct ID...");
				showList();
			}
		}
		catch(Exception e){
			System.out.println("Enter a number...");
			System.out.println(e);
			showList();
		}
	}

	public void puting(int i){
		System.out.println("Enter the number of units:");
		Scanner in=new Scanner(System.in);
		try{
			int quan= in.nextInt();
			Set set = existing.entrySet();
			Iterator itr= set.iterator();
			while(itr.hasNext()){  
				Map.Entry<Integer, Products> entry=(Map.Entry)itr.next();
				if(entry.getKey() == i){
					if(entry.getValue().getQuantity() >= quan){
						int quantity = entry.getValue().getQuantity();
						quantity-=quan;
						existing.put(i, new Products(entry.getValue().getName(),entry.getValue().getPrice(),quantity));
						try{
							Products ps = buying.get(i);
							int remain = ps.getQuantity();
							remain +=quan;
							buying.put(i, new Products(entry.getValue().getName(),entry.getValue().getPrice(),remain));
						}
						catch(Exception e){
							buying.put(i, new Products(entry.getValue().getName(),entry.getValue().getPrice(),quan));
						}
					}
					else{
						System.out.println("The value you have entered is higher than the available quantity.");
						System.out.println("Please enter a value lesser than the avaiable quantity...");
						puting(i);
					}
				}
			}
		}
		catch(Exception e){
			System.out.println("Enter a number...");
			System.out.println(e);
			puting(i);
		}
		exiting();
	}
	
	private void exiting() {
		Scanner in=new Scanner(System.in);
		System.out.println("Do you want to continue shopping? y/n");
		try{
			String choose= in.next();
			switch(choose){
				case "y":
					showList();
					break;
				case "n":
					mainMenu();
					break;
				default:
					System.out.println("Enter y/n...");
			}
		}
		catch(Exception e){
			System.out.println("Enter y/n...");
			exiting();
		}
	}


	@Override
	public void cart() {
		if(buying.size()==0){
			System.out.println("Your Cart is Empty");
			mainMenu();
		}
		else{
			int items=0,total=0,pricePerItem=0;
			System.out.println("Products in carts:");
			System.out.println("ID  Product  Price/unit  units");
			System.out.println("_________________________________________________");
			Set set = buying.entrySet();
			Iterator itr= set.iterator();
			while(itr.hasNext()){  
				Map.Entry<Integer, Products> entry=(Map.Entry)itr.next();
				System.out.println(entry.getKey()+ "  "+ entry.getValue().getName() + "\t" + entry.getValue().getPrice() + "\t" +entry.getValue().getQuantity());				
				items+=entry.getValue().getQuantity();
				pricePerItem=entry.getValue().getQuantity()*entry.getValue().getPrice();
				total+=pricePerItem;
			}	
			System.out.println("Total Items: "+items);
			System.out.println("Total price is: "+total);
			System.out.println("Choose an option:");
			System.out.println(" 1.Main Menu \n 2.Continue shopping \n 3.Remove items \n 4.Checkout");
			Scanner in=new Scanner(System.in);
			try{
				int choose= in.nextInt();
				switch(choose){
					case 1:
						mainMenu();
						break;
					case 2:
						showList();
						break;
					case 3:
						remove();
						break;
					case 4:
						checkout();
						break;
					default:
						System.out.println("Enter a correct choice...");
						cart();
				}
			}
			catch(Exception e){
				System.out.println("Enter a number...");
				cart();
			}
			}
		}
	public void remove(){
		
		System.out.println("Products in carts:");
		System.out.println("ID  Product  Price/unit  Available units");
		System.out.println("______________________________________________________");
		Set set = buying.entrySet();
		Iterator itr= set.iterator();
		while(itr.hasNext()){  
			Map.Entry<Integer, Products> entry=(Map.Entry)itr.next();
			System.out.println(entry.getKey()+ "  " + entry.getValue().getName() + "\t" + entry.getValue().getPrice() + "\t" +entry.getValue().getQuantity());				
		}
		System.out.println("Choose the item you want to remove:");
		Scanner in=new Scanner(System.in);
		try{
			int choose= in.nextInt();
			if(choose <=3 && choose >0){
				System.out.println("The product you have choosed is:");
				System.out.println("Product  Price/unit  Available units");
				System.out.println("______________________________________________________");
				Products ps = buying.get(choose);
				System.out.println(ps.getName() + "\t" + ps.getPrice() + "\t"+ ps.getQuantity());
				update(choose);
			}
			else{
				System.out.println("Enter the correct ID");
				remove();
			}
		}
		catch(Exception e){
			System.out.println("Enter a number...");
		}
	}
	
	public void update(int i){
		System.out.println("Enter the number of units:");
		Scanner in=new Scanner(System.in);
		try{
			int quan= in.nextInt();
			Set set = buying.entrySet();
			Iterator itr= set.iterator();
			while(itr.hasNext()){  
				Map.Entry<Integer, Products> entry=(Map.Entry)itr.next();
				if(entry.getKey() == i){
					if(entry.getValue().getQuantity() >= quan){
						int quantity = entry.getValue().getQuantity();
						quantity-=quan;
						buying.put(i, new Products(entry.getValue().getName(),entry.getValue().getPrice(),quantity));
						Products ps = existing.get(i);
						int remain = ps.getQuantity();
						remain +=quan;
						existing.put(i, new Products(entry.getValue().getName(),entry.getValue().getPrice(),remain));
						cart();
					}
					else{
						System.out.println("The value you have entered is higher than the available quantity.");
						System.out.println("Please enter a value lesser than the avaiable quantity...");
						update(i);
					}
				}
			}
		}
		catch(Exception e){
			System.out.println("Enter a number...");
			puting(i);
		}
	}
	public void checkout(){
		System.out.println("The products you have ordered:");
		int items=0,total=0,pricePerItem=0;
		System.out.println("Products in carts:");
		System.out.println("ID  Product  Price/unit  units");
		System.out.println("_________________________________________________");
		Set set = buying.entrySet();
		Iterator itr= set.iterator();
		while(itr.hasNext()){  
			Map.Entry<Integer, Products> entry=(Map.Entry)itr.next();
			System.out.println(entry.getKey()+ "  "+ entry.getValue().getName() + "\t" + entry.getValue().getPrice() + "\t" +entry.getValue().getQuantity());				
			items+=entry.getValue().getQuantity();
			pricePerItem=entry.getValue().getQuantity()*entry.getValue().getPrice();
			total+=pricePerItem;
		}	
		System.out.println("Total Items: "+items);
		System.out.println("Total price is: "+total);
		System.out.println("Thank you!!");
	}
}
