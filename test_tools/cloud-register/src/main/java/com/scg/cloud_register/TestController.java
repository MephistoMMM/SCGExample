package com.scg.cloud_register;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@RequestMapping("/")
	public String content(@RequestParam(value="key") String key) {
		return "Return test value "+key;
	}

}
