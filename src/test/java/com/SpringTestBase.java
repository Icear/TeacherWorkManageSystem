package com;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileNotFoundException;

/**
 * 用于在Test中提供Spring容器支持
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:/web/WEB-INF/applicationContext.xml")
public class SpringTestBase {

}

