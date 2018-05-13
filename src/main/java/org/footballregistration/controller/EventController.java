package org.footballregistration.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.footballregistration.service.CreateEventService;
import org.footballregistration.service.DeleteEventService;
import org.footballregistration.service.GetEventService;
import org.footballregistration.service.ProposerEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

	@Autowired
	private CreateEventService createEventSevice;

	@Autowired
	private GetEventService getEventSevice;

	@Autowired
	private DeleteEventService delteEventService;

	@Autowired
	private ProposerEventService proposerEventService;

	// Event一览取得API
	@RequestMapping(value = "/event/getEventList", method = POST, produces = "application/json;charset=UTF-8")
	public String getEventList(@RequestBody String jsonRequest){
		return getEventSevice.getEventList(jsonRequest);
	}

	// Event详细取得API
	@RequestMapping(value = "/event/getEventDetail", method = POST, produces = "application/json;charset=UTF-8")
	public String getEventDetail(@RequestBody String jsonRequest){
		return getEventSevice.getEventDetail(jsonRequest);
	}

	// Event创建API
	@RequestMapping(value = "/event/createEvent", method = POST, produces = "application/json;charset=UTF-8")
	public String createEvent(@RequestBody String jsonRequest){
		return createEventSevice.createEvent(jsonRequest);
	}

	// Event删除API
	@RequestMapping(value = "/event/deleteEvent", method = POST, produces = "application/json;charset=UTF-8")
	public String deleteEvent(@RequestBody String jsonRequest){
		return delteEventService.deleteEvent(jsonRequest);
	}

	// Event参加API
	@RequestMapping(value = "/event/proposerEvent", method = POST, produces = "application/json;charset=UTF-8")
	public String proposerEvent(@RequestBody String jsonRequest){
		return proposerEventService.proposerEvent(jsonRequest);
	}

}