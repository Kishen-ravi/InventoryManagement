
public class Products {
	int index,price,quantity;  
	String name;    
	public Products(String name,int price,int quantity){  
		this.name=name;  
		this.price=price;  
		this.quantity=quantity;
	}


	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}  
	
	
}
