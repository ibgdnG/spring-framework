import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.message.MessageService;

/**
 * Spring 容器加载方式
 *
 * @author ice
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
public class ContextLoadTest {

	private static final Logger log = LoggerFactory.getLogger(ContextLoadTest.class);

	/**
	 * 1、类路径获取配置文件
	 */
	@Test
	public void classPathXml() {
		log.info("class path xml start.");
		log.info("在 ClassPath 中寻找 xml 配置文件，根据 xml 文件内容来构建 ApplicationContext。");
//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
		// 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
		MessageService messageService = applicationContext.getBean(MessageService.class);
		// 这句将输出: hello world
		log.info("messageService: {}",messageService.getMessage());
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

	@Test
	public void psTest() {
		System.out.println("中国..信保".toCharArray());
	}
}