package entities;

public class Employee {
	
	private int id;
	private String name;
	private double salary;
	
	public Employee() {
		setId(0);
		setName(null);
		setSalary(0);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public void increaseSalary(double percent) {
		salary+=(getSalary()*(percent/100));
	}
	
	public String toString() {
		return getId()+", "+getName()+", "+getSalary();

	}

}
