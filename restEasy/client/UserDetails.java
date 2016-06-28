package restEasy.client;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDetails {
	
	private String firstname;
	private String lastname;
	private String phnumber;
	
	public UserDetails()
	{
		
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPhnumber() {
		return phnumber;
	}
	public void setPhnumber(String phnumber) {
		this.phnumber = phnumber;
	}
	
} 