package entities;

public class Individual extends TaxPayer{

	private Double healthExpenditures;
	
	public Individual() {
		super();
	}
	
	public Individual(String name, Double anualIncome, Double healthExpenditures) {
		super(name, anualIncome);
		setHealthExpenditures(healthExpenditures);
	}
	
	public Double getHealthExpenditures() {
		return healthExpenditures;
	}

	public void setHealthExpenditures(Double healthExpenditures) {
		this.healthExpenditures = healthExpenditures;
	}

	@Override
	public double tax() {
		
		if(super.getAnualIncome() < 20000.00 ) {
			
			if(getHealthExpenditures() > 0) {
				return (super.getAnualIncome() * 0.15) -  (getHealthExpenditures() * 0.5);
			}
		
			else {
				return super.getAnualIncome() * 0.15;
			}
			
		}
		
		else {

			if(getHealthExpenditures() > 0) {
				return (super.getAnualIncome() * 0.25) -  (getHealthExpenditures() * 0.5);
			}
		
			else {
				return super.getAnualIncome() * 0.25;
			}
			
		}
	}

	@Override
	public String toString() {
		return super.getName() + " $ " + String.format("%.2f",tax());
	}

}
