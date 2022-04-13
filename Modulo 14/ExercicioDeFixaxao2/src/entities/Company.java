package entities;

public class Company  extends TaxPayer{

	private Integer numberOfEmployee;
	
	public Company(){
		super();
	}

	public Company(String name, Double anualIncome, Integer numberOfEmployee) {
		super(name, anualIncome);
		setNumberOfEmployee(numberOfEmployee);
	}

	public Integer getNumberOfEmployee() {
		return numberOfEmployee;
	}

	public void setNumberOfEmployee(Integer numberOfEmployee) {
		this.numberOfEmployee = numberOfEmployee;
	}

	@Override
	public double tax() {
		
		if(getNumberOfEmployee() > 10) 
			return super.getAnualIncome() * 0.14;
		
		else
			return super.getAnualIncome() * 0.16;

	}

	@Override
	public String toString() {
		return super.getName() + " $ " + String.format("%.2f",tax());
	}
}
