package org.footballregistration.service;

import org.footballregistration.dao.HelloWorldDao;
import org.footballregistration.dao.entity.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HelloWorldService {

	@Autowired
	private HelloWorldDao hwDao;

	public String getMyvalue(String mykey) {

		HelloWorld hw = hwDao.selectMyValue(mykey);

		System.out.println("mykey = " + hw.getMykey());
		System.out.println("myvalue = " + hw.getMyvalue());

		return hw.getMyvalue();
	}

}
