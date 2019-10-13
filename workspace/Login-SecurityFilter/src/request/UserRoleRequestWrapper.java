package request;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class UserRoleRequestWrapper extends HttpServletRequestWrapper{

	private String userName;
	private List<String> roles;
	private HttpServletRequest realRequest;
	
	public UserRoleRequestWrapper(String userName, List<String> roles, HttpServletRequest request) {
		super(request);
		this.userName = userName;
		this.roles = roles;
		this.realRequest = request;
	}

	
	
	@Override
	public boolean isUserInRole(String role) {
		if(role!=null) {
			return roles.contains(role);
		}
		return this.realRequest.isUserInRole(role);
	}

	@Override
	public Principal getUserPrincipal() {
		if(this.userName!=null) {
			return new Principal() {
				@Override
				public String getName() {
					// TODO Auto-generated method stub
					return userName;
				}
			};
		}
		return realRequest.getUserPrincipal();
	}
	
	
	
	
}
