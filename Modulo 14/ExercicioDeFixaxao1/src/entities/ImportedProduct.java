package entities;

public class ImportedProduct extends Product{
	
	private Double customsFee;
	
	public ImportedProduct(){
		super();
	}

	public ImportedProduct(String name, Double price, Double customsFee) {
		super(name, price);
		setCustomsFee(customsFee);
	}

	public Double getCustomsFee() {
		return customsFee;
	}

	public void setCustomsFee(Double customsFee) {
		this.customsFee = customsFee;
	}
	
	public double totalPrice() {
		return getPrice() + getCustomsFee();
	}
	
	@Override
	public String priceTag() {
		return getName() + " $ " + String.format("%.2f", totalPrice()) + " (Customs fee: $ " + String.format("%.2f",getCustomsFee()) + ")";
	}
}
