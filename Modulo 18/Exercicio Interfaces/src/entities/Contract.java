package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Contract {
	
	private Integer number;
	private Date date;
	private Double totalValue;
	
	private List<Installment> installments;

	public Contract(Integer number, Date date, Double totalValue) {
		setNumber(number);
		setDate(date);
		setTotalValue(totalValue);
		installments = new ArrayList<Installment>();
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public List<Installment> getInstallments() {
		return installments;
	}

	public void addInstallment(Installment installments) {
		this.installments.add(installments);
	}
	
	public void removeInstallment(Installment installments) {
		this.installments.remove(installments);
	}
	
}
