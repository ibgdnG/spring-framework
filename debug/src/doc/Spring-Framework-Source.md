# Spring-Framework-Source

## 调试源码
### 下载源码

配置 Gradle home 目录。

> File -> New -> Project from Version Control...

填写 git 地址`https://github.com/spring-projects/spring-framework.git`完成克隆。

#### 构建
到下载的 spring 源码路径执行 gradle 命令。

```
gradle :spring-oxm:compileTestJava
```

### 创建测试项目

> Project（右键） -> New -> Module... -> Gradle -> Java

### 配置
1. 修改文件`/home/ice/Git-Repositories/spring-framework/settings.gradle`
- 注释掉
```
plugins {
...
	id "io.spring.ge.conventions" version "0.0.9"
...
}
```

- 添加项目引用（具体名称视项目名而定）
```
include 'debug'
```

2. 在`/home/ice/Git-Repositories/spring-framework/debug/build.gradle`文件添加如下内容
```
dependencies {
    ...
    implementation 'junit:junit:4.13'
    implementation 'org.hamcrest:hamcrest-all:1.3'
    implementation project(':spring-context')
    implementation project(':spring-test')
    implementation project(':spring-core')
    implementation project(':spring-context')
}
...
//bootJar {
//    enabled = true
//}

jar {
    enabled = true
}
```
3. 创建测试类`/home/ice/Git-Repositories/spring-framework/debug/src/main/java/spring/ContextLoadTest.java`
```java
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
```

4. 创建配置文件`/home/ice/Git-Repositories/spring-framework/debug/src/main/resources/application.xml`
```
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
</beans>
```




## 源码
[Spring源码分析](https://www.cnblogs.com/chenyanbin/p/11756034.html)

### Spring核心概念介绍

　　IoC(核心中的核心)：Inverse of Control，控制反转。对象的创建权力由程序反转给Spring框架。

　　AOP：Aspect Oriented Programming，面向切面编程。在不修改目标对象的源代码情况下，增强IoC容器中Bean的功能。

　　DI：Dependency Injection，依赖注入。在Spring框架负责创建Bean对象时，动态的将依赖对象注入到Bean组件中！！

　　Spring容器：指的就是IoC容器。

### Spring IoC原理分析
#### 什么是IoC容器？

　　所谓的IoC容器就是指的是Spring中Bean工厂里面的Map存储结构(存储了Bean的实例)。
　　
#### Spring框架中的工厂有哪些？

　　ApplicationContext接口()

　　　　实现了BeanFactory接口

　　　　实现ApplicationContext接口的工厂，可以获取到容器中具体的Bean对象

　　BeanFactory工厂(是Spring架构早期的创建Bean对象的工厂接口)

　　　　实现BeanFactory接口的工厂也可以获取到Bean对象

## 源码解读

### AbstractApplicationContext 的 refresh() 方法
该方法是一个典型的模板方法模式的实现。