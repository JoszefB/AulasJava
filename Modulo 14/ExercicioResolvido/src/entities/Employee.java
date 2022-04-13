package entities;

public class Employee {
	
	private String name;
	private Integer hour;
	private Double valuePerHour;
	
	public Employee() {}

	public Employee(String name, Integer hour, Double valuePerHour) {
		setName(name);
		setHour(hour);
		setValuePerHour(valuePerHour);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Double getValuePerHour() {
		return valuePerHour;
	}

	public void setValuePerHour(Double valuePerHour) {
		this.valuePerHour = valuePerHour;
	}
	
	public double payment() {
		return getHour() * getValuePerHour();
	}

	@Override
	public String toString() {
		return  getName() + " - $" + String.format("%.2f", payment());
	}
	
}
