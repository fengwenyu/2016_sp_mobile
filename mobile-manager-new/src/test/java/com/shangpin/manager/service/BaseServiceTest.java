package com.shangpin.manager.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-mvc.xml",
        "classpath:/applicationContext-spring.xml",
        "classpath:/applicationContext-shiro.xml",
        "classpath:/applicationContext-captcha.xml"})
public class BaseServiceTest{

}
