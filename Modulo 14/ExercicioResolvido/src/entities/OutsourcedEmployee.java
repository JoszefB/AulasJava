package entities;

public class OutsourcedEmployee extends Employee{
	
	private Double additionalCharge;

	public OutsourcedEmployee() {
		super();
	}
	
	public OutsourcedEmployee(String name, Integer hour, Double valuePerHour, Double additionalCharge) {
		super(name, hour, valuePerHour);
		setAdditionalCharge(additionalCharge);
	}
	
	public Double getAdditionalCharge() {
		return additionalCharge;
	}

	public void setAdditionalCharge(Double additionalCharge) {
		this.additionalCharge = additionalCharge;
	}

	@Override
	public double payment() {
		return super.payment() + getAdditionalCharge() * 1.1;
	}

	@Override
	public String toString() {
		return super.getName() + " - $" + String.format("%.2f", payment());
	}

}
