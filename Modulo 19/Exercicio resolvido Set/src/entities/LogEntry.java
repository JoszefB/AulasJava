package entities;

import java.util.Date;

public class LogEntry {
	
	private String username;
	private Date moment;
	
	public LogEntry(String username, Date moment) {
		setUsername(username);
		setMoment(moment);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getUsername() == null) ? 0 : username.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		else if (obj == null)
			return false;
		else if (getClass() != obj.getClass())
			return false;
		LogEntry other = (LogEntry) obj;
		if(username == null) 
			if(other.username != null)
				return false;
		else if (!username.equals(other.username))
			return false;
		return true;
	}
}
