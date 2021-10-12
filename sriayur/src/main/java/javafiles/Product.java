package javafiles;

public class Product {
	
	protected String productname;
	protected int productprice;
	
	public Product() {}
	
	public Product(String productname, int productprice) {
		super();
		this.productname = productname;
		this.productprice = productprice;
	}
	
	public String getproductname() {
		return productname;
	}
	public void setproductname(String productname) {
		this.productname = productname;
	}
	public int getproductprice() {
		return productprice;
	}
	public void setproductprice(int productprice) {
		this.productprice = productprice;
	}
}
