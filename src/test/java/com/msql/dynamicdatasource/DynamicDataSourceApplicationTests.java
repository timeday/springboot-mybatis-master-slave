package com.msql.dynamicdatasource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicDataSourceApplication.class)
public class DynamicDataSourceApplicationTests {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void contextLoads() {
	    String[] files={"wxebd5c339e5f04d2c","f7de64839d484ae83fc7b3144ad00e1a"};

	    String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxebd5c339e5f04d2c&secret=f7de64839d484ae83fc7b3144ad00e1a";

        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        System.out.println(forEntity.getBody());
    }

}
