package com.wucl.shiro;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.activemq.console.Main;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.wucl.service.IUserService;
import com.wucl.util.VaildataCodeServlet;
import com.wucl.vo.User;

public class MyFormAuthrnticationFilter extends FormAuthenticationFilter {

	@Autowired
	private IUserService userServiceImpl;
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		//调用servlet的API
		HttpServletRequest req = (HttpServletRequest) request;
		//获取页面提交的验证码
		String j_random = req.getParameter("j_random");
		if(j_random!=null){
			if(!VaildataCodeServlet.validate(req, j_random)){
				req.setAttribute("shiroLoginFailure", "randomCodeError");
				return true;
			}
		}
		return super.onAccessDenied(request, response);
	}
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		// 1.用户存入session
				Session session = subject.getSession();
				String staffId = (String) token.getPrincipal();
				String username=(String) token.getPrincipal();
				User user= userServiceImpl.getUserInfoByUsername(username);
				session.setAttribute("user", user);
				// 2.跳转页面
				String fallbackUrl = this.getSuccessUrl();
				String successUrl = null;
				SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
				if (savedRequest != null && savedRequest.getMethod().equalsIgnoreCase(AccessControlFilter.GET_METHOD)) {
					successUrl = savedRequest.getRequestUrl();
				}
				if (!StringUtils.isEmpty(fallbackUrl) && !AuthenticationFilter.DEFAULT_SUCCESS_URL.equals(fallbackUrl)) {
					successUrl = ((HttpServletRequest) request).getContextPath() + fallbackUrl;
				}
				if (successUrl == null) {
					successUrl = this.getSuccessUrl();
				}
				if (successUrl == null) {
					throw new IllegalStateException(
							"SuccessURLnotavailableviasavedrequestorviathe"
									+ "successUrlFallbackmethodparameter.Oneofthesemustbenon-nullfor"
									+ "issueSuccessRedirect()towork.");
				}
				WebUtils.issueRedirect(request, response, successUrl,null,false);
				return false;
	}
	@Override
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		String md5password = encode(password);
		return createToken(username, md5password, request, response);
	}

	// MD5加密
	public  String encode(String inStr) {
		String outStr = "";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] output = Base64.encode(md5.digest(inStr.getBytes("utf-8")));
			outStr = new String(output, "utf-8");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return outStr;
	}
	
}
