package com.wucl.ssm.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.wucl.ssm.service.IUserService;
import com.wucl.ssm.util.MD5;
import com.wucl.ssm.vo.User;

public class MyShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private IUserService UserServiceImpl;
	String pass;
	
	/* 授权
	 * (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Object principal = principals.getPrimaryPrincipal();
		if("测试".equals(principal)){
			authorizationInfo.addRole("admin");
		}
		if("user".equals(principal)){
			authorizationInfo.addRole("list");
		}
		
		authorizationInfo.addRole("user");
		return authorizationInfo;
	}
	
	/*	用户验证
	 * (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1.从token中获取登录的username, 不需要password。
		Object principal = token.getPrincipal();
		//2. 利用username查询数据库得到用户的信息
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		User user = UserServiceImpl.loginCheck(username);
		if(user!=null){
			pass = user.getPassWord();
		}else if(user==null){
			throw new UnknownAccountException("用户名不存在");
		}
		String credentials = pass;
		//设置盐值
		String source = "abcdefg";
		ByteSource credentialsSalt = new Md5Hash(source);
		//当前realm的name
		String realmName = getName();
		//返回值实例化
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,realmName);
		return info;
	}
	
	public void setCredentialMatcher(){
        HashedCredentialsMatcher  credentialsMatcher = new HashedCredentialsMatcher();    
        credentialsMatcher.setHashAlgorithmName("MD5");//MD5算法加密
        credentialsMatcher.setHashIterations(1024);//1024次循环加密      
        setCredentialsMatcher(credentialsMatcher);
    }
	
	//用来测试的算出密码password盐值加密后的结果，下面方法用于新增用户添加到数据库操作的，我这里就直接用main获得，直接数据库添加了，省时间
    public static void main(String[] args) {
        String saltSource = "abcdef";    
        String hashAlgorithmName = "MD5";
        String credentials = "passwor";
        Object salt = new Md5Hash(saltSource);
        int hashIterations = 1024;            
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }

	
}
