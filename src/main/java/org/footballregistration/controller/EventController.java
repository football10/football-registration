package org.footballregistration.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.footballregistration.service.CreateEventService;
import org.footballregistration.service.DeleteEventService;
import org.footballregistration.service.GetEventService;
import org.footballregistration.service.ProposerEventService;
import org.footballregistration.service.UpdateEventService;
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
	private UpdateEventService updateEventService;

	@Autowired
	private ProposerEventService proposerEventService;

	// 参加的Event一览取得API
	@RequestMapping(value = "/event/getProposerEventList", method = POST, produces = "application/json;charset=UTF-8")
	public String getProposerEventList(@RequestBody String jsonRequest){
		return getEventSevice.getProposerEventList(jsonRequest);
	}

	// 参加的Event一览取得API
	@RequestMapping(value = "/event/getCreateEventList", method = POST, produces = "application/json;charset=UTF-8")
	public String getCreateEventList(@RequestBody String jsonRequest){
		return getEventSevice.getCreateEventList(jsonRequest);
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

	// Event更新API
	@RequestMapping(value = "/event/updateEvent", method = POST, produces = "application/json;charset=UTF-8")
	public String updateEvent(@RequestBody String jsonRequest){
		return updateEventService.updateEvent(jsonRequest);
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

	// Event参加删除API
	@RequestMapping(value = "/event/proposerDeleteEvent", method = POST, produces = "application/json;charset=UTF-8")
	public String proposerDeleteEvent(@RequestBody String jsonRequest){
		return proposerEventService.proposerDeleteEvent(jsonRequest);
	}

}