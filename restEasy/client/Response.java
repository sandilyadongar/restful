package restEasy.client;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {
  
	private String firstname;
	private String lastname;
	private String phnumber;
	
	public String getResponseFname() {
		return firstname;
	}
	public void setResponseFname(String firstname) {
		this.firstname = firstname;
	}
	public String getResponseLname() {
		return lastname;
	}
	public void setResponseLname(String lastname) {
		this.lastname = lastname;
	}
	public String getResponsePnum() {
		return phnumber;
	}
	public void setResponsePnum(String phnumber) {
		this.phnumber = phnumber;
	}
	
} 