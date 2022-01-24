package spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring 容器加载方式
 *
 * @author ice
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
public class ContextLoadTest {

	/**
	 * 1、类路径获取配置文件
	 */
	@Test
	public void classPathXml() {
		System.out.println("class path xml start.");
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
		System.out.println("class path xml end.");
	}

	/**
	 * 2、文件系统路径获取配置文件[绝对路径]
	 */
	@Test
	public void fileSystemXml() {
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext("E:\\idea\\springdemo\\spring.xml");
	}

	/**
	 * 3 、 无配置文件加载容器
	 */
	@Test
	public void annotationConfig() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.xx.jack");
	}

	/**
	 * 4、springboot 加载容器
	 */
	@Test
	public void embeddedWeb() {
//		ApplicationContext applicationContext = new EmbeddedWebApplicationContext();
	}
}