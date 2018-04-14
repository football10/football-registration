package org.footballregistration.dao;

import org.footballregistration.dao.entity.HelloWorld;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloWorldDao {

	HelloWorld selectMyValue(String mykey);

}
