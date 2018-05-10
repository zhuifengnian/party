package com.hust.party.common;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.hust.party.pojo.User;
import com.hust.party.service.LoginService;

public class MyRealm extends AuthorizingRealm{
	
	@Autowired
	private LoginService lgservice;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("进入...doGetAuthorizationInfo");


		String userName = (String) getAvailablePrincipal(principals);


		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		

		Set<String> s = new HashSet<String>();
		List<Map<String,Object>> list = lgservice.queryPerm(userName);
		for (Map map : list) {
			System.out.println(map.get("id"));
			s.add((String)map.get("id"));
		}
		info.setStringPermissions(s);


		Set<String> r = new HashSet<String>();
		list = lgservice.queryRole(userName);
		for (Map map : list) {
			System.out.println(map.get("name"));
			r.add((String)map.get("name"));
		}
		info.setRoles(r);
		
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {


		System.out.println("++++++++++++++++++++++++++++++++++!!!!!!!!!!!!!!!!!!!!");
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		String password = String.valueOf(upToken.getPassword());
		
		User user = new User();
		user.setId(username);
		user.setPassword(password);
		
		Map map = lgservice.queryuser(user);
		String flag = (String) map.get("flag");
		
		if (flag.equals("null"))
			throw new UnknownAccountException();
		else if(flag.equals("invalid")){
			throw new IncorrectCredentialsException();
		}
		else {			

			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,
					password.toCharArray(), getName());
			return info;
		}
	}
	public void removeUserCache(String userName) {
		SimplePrincipalCollection pc = new SimplePrincipalCollection();
		pc.add(userName, super.getName());
		super.clearCachedAuthorizationInfo(pc);
	}
}
