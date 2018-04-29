package org.footballregistration.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.footballregistration.service.OpenIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

	@Autowired
	private OpenIdService openidService;

	// getOpenId?code=XXXX的形式
	@RequestMapping(value = "/common/getOpenId", method = GET, produces = "application/json;charset=UTF-8")
	public String getOpenId(@RequestParam("code") String code) {

		return openidService.getOpenId(code);
	}
}
