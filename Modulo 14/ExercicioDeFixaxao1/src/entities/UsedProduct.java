package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UsedProduct extends Product{

	private SimpleDateFormat sdf;
	private Date manufactureDate;
	
	public UsedProduct() {
		super();
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}

	public UsedProduct(String name, Double price, Date manufactureDate) {
		super(name, price);
		setManufactureDate(manufactureDate);
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
	
	public String priceTag() {
		return getName() +"(used) $ " + String.format("%.2f", getPrice()) + "(Manufacture date: " + sdf.format(getManufactureDate()) + ")";
	}
}
