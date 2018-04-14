package org.footballregistration.dao.entity;

import com.google.gson.annotations.SerializedName;

public class HelloWorld {

	@SerializedName("key")
	public String mykey;

	@SerializedName("value")
	public String myvalue;

	public String getMykey() {
		return mykey;
	}
	public void setMykey(String mykey) {
		this.mykey = mykey;
	}
	public String getMyvalue() {
		return myvalue;
	}
	public void setMyvalue(String myvalue) {
		this.myvalue = myvalue;
	}
}
