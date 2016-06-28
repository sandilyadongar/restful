package restEasy.client;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import oracle.jdbc.driver.OracleDriver;

import org.apache.http.client.ClientProtocolException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import restEasy.UserDetails;

public class JsonStoreAndRetriveClient {
	
	public static void main(String[] args) {
		  
		 //setJsonEmp();
		 getJsonEmp();
		
	  }
	
	public static void setJsonEmp() {
		
		System.out.println("Enter firstname: ");
		Scanner input = new Scanner(System.in);
		String firstname = input.next();
		System.out.println("Enter lastname: ");
		String lastname = input.next();
		System.out.println("Enter Phone number: ");
		String phnumber = input.next();
		
		insert(firstname, lastname, phnumber);
		
		UserDetails user = new UserDetails();
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setPhnumber(phnumber);
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target("http://localhost:8081/restfulprogram/rest/usingJson/setJsonInfo");
		Builder request = target.request();
		request.accept(MediaType.APPLICATION_JSON);
		Response response = request.post(Entity.entity("{\"firstname\":\""+firstname+"\",\"lastname\":\""+lastname+"\",\"phnumber\":\""+phnumber+"\"}", MediaType.APPLICATION_JSON));
		String value = response.readEntity(String.class);

		System.out.println(value);
		response.close();
	}
	
	public static void insert(String firstname, String lastname, String phnumber)
	{
		Connection conn = null;
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		long maxid = 0;
		
		try
		{
			conn = getConnection();
			
			String id = "SELECT MAX(EMPID) FROM EMPLOYEE_REST";
			pst = conn.prepareStatement(id);
			rs = pst.executeQuery();			
			while(rs.next())
			{
				maxid = rs.getLong("MAX(EMPID)");
			}
			
			String query = "INSERT INTO EMPLOYEE_REST(EMPID, FIRSTNAME, LASTNAME, PHONENUMBER) VALUES(?,?,?,?)";
			pst1 = conn.prepareStatement(query);
			pst1.setLong(1,maxid+1);
			pst1.setString(2,firstname);
			pst1.setString(3,lastname);
			pst1.setString(4,phnumber);
			
			int count = pst1.executeUpdate();
			conn.commit();
		}
		catch(Exception e)
		{
			System.out.println("Error.." + e);
		}
	}
	
	public static void getJsonEmp() {
		
		System.out.println("Enter phone number to find emp: ");
		Scanner input = new Scanner(System.in);
		String phnumber = input.next();
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try
		{
			conn = getConnection();
			
			String query = "SELECT FIRSTNAME, LASTNAME, PHONENUMBER FROM EMPLOYEE_REST WHERE PHONENUMBER=?";
			pst = conn.prepareStatement(query);
			pst.setString(1,phnumber);
			rs = pst.executeQuery();
		    while(rs.next())
		    {
		    	String firstname = rs.getString("FIRSTNAME");
		    	String lastname = rs.getString("LASTNAME");
		    	String phnum = rs.getString("PHONENUMBER");
		    	
		    	UserDetails user = new UserDetails();
				user.setFirstname(firstname);
				user.setLastname(lastname);
				user.setPhnumber(phnum);

				ResteasyClient client = new ResteasyClientBuilder().build();
				ResteasyWebTarget target = client.target("http://localhost:8081/restfulprogram/rest/usingJson/emp");
				Builder request = target.request();
				request.accept(MediaType.APPLICATION_JSON);
				Response response = request.post(Entity.entity("{\"firstname\":\""+firstname+"\",\"lastname\":\""+lastname+"\",\"phnumber\":\""+phnum+"\"}", MediaType.APPLICATION_JSON));
				String result = response.readEntity(String.class);

				System.out.println(result);
				response.close();
		    }
		}
		catch(Exception e)
		{
			System.out.println("error..." + e);
		}
		
	}
	
	private static Connection getConnection() throws Exception
	{
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String username = "system";
		String password = "sysadmin";
		DriverManager.registerDriver(new OracleDriver());
		Connection conn = DriverManager.getConnection(url,username,password);
		conn.setAutoCommit(false);
		
		return conn;
	}

	
} 