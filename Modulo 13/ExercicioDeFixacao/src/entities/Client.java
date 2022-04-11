package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

	private static SimpleDateFormat sdf;
	private String name;
	private String email;
	private Date birthDate;
	
	public Client(){
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}

	public Client(String name, String email, Date birthDate) {
		setName(name);
		setEmail(email);
		setBirthDate(birthDate);
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	@Override
	public String toString() {
		return getName()+" (" +sdf.format(getBirthDate())+") - "+getEmail();
	}
	
}