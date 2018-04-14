package org.footballregistration.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.footballregistration.dao.entity.HelloWorld;
import org.footballregistration.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class HelloWorldController {

	@Autowired
	private HelloWorldService helloWorldSevice;

	@RequestMapping(value = "/hello", method = GET)
	public String helloWrold(){
		return "HelloWorld !";
	}

	@RequestMapping(value = "/json", method = GET, produces = "application/json")
	public String responseJson(){

		Gson gson = new Gson();

		HelloWorld hw = new HelloWorld();
		hw.setMykey("hello");
		hw.setMyvalue("world");
		String json = gson.toJson(hw);

		System.out.println("json = " + json);
		return json;
	}

	@RequestMapping(value = "/selectdb", method = GET)
	public String selectdb(){

		return helloWorldSevice.getMyvalue("key1");
	}

}
