package com.scg.cloud_discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
	@Autowired
    TestRemote TestRemote;
	
	@RequestMapping("/{key}")
	public String index(@PathVariable("key") String key) {
		return TestRemote.content(key);
	}
}
