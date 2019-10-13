package bean;

import java.util.ArrayList;
import java.util.List;

public class UserAccount {
	public static final String GENDER_MALE = "M";
	public static final String GENDER_FEMALE = "F";
	
	private String userName;
	private String password;
	private String gender;
	private List<String> roles;

	public UserAccount() {
		
	}
	public UserAccount(String userName, String password, String gender, String... roles) {
		super();
		this.userName = userName;
		this.password = password;
		this.gender = gender;
		this.roles = new ArrayList<String>();
		if(roles!=null) {
			for(String x : roles) {
				this.roles.add(x);
			}
		}
		
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}

	
	

	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public List<String> getRoles() {
		return roles;
	}



	public void setRoles(List<String> roles) {
		this.roles = roles;
	}


}
