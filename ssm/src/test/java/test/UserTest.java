package test;

import java.io.FileNotFoundException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Log4jConfigurer;

import com.wucl.ssm.service.IUserService;
import com.wucl.ssm.vo.User;

public class UserTest {

	private static IUserService  userServiceImpl;

	static{
		try {
			Log4jConfigurer.initLogging("classpath:config/env/log4j.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("log4j �ļ�������");
		}
	}
	
	@BeforeClass
	public static void init() {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath:/config/env/applicationContext.xml",
				"classpath:/config/env/dataSource.xml",
				"classpath*:/config/persistence/hibernate.xml",
				"classpath*:/config/persistence/mybatis.xml",
				"classpath*:/config/persistence/transaction.xml");
		userServiceImpl = (IUserService) ctx.getBean("userServiceImpl");
	}
	
	@Test
	public void testGetUserById(){
		User user = userServiceImpl.getUserById("1");
		System.out.println(user);
	}
}
