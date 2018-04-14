package org.footballregistration.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;

import org.footballregistration.common.Constants;
import org.footballregistration.response.EventListResponse;
import org.footballregistration.response.model.EventInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

@RestController
public class EventController {

	@RequestMapping(value = "/event/getEventList", method = POST, produces = "application/json;charset=UTF-8")
	public String responseJson(){

		Gson gson = new Gson();

		EventListResponse response = new EventListResponse();

		response.responseCode = Constants.RESPONSE_CODE_OK;
		response.result.eventCount = 3;

		EventInfo ei1 = new EventInfo();
		ei1.eventId = 1;
		ei1.eventName = "足球活动1";
		ei1.status = "报名中";
		ei1.eventKbn = "训练";
		ei1.eventDate = "2018/05/01 上午10点00分";
		ei1.eventPlaceName = "东京都江东区XX体育场";
		ei1.proposerUserCount = 1;

		EventInfo ei2 = new EventInfo();
		ei2.eventId = 2;
		ei2.eventName = "足球活动2";
		ei2.status = "报名中";
		ei2.eventKbn = "比赛";
		ei2.eventDate = "2018/05/11 下午16点30分";
		ei2.eventPlaceName = "东京都江东区YY体育场";
		ei2.proposerUserCount = 10;

		EventInfo ei3 = new EventInfo();
		ei3.eventId = 3;
		ei3.eventName = "吃喝玩乐";
		ei3.status = "已结束";
		ei3.eventKbn = "聚餐";
		ei3.eventDate = "2018/04/01 下午19点00分";
		ei3.eventPlaceName = "东京都江东区ZZ餐厅";
		ei3.proposerUserCount = 20;

		List<EventInfo> eventList = new ArrayList<EventInfo>();
		eventList.add(ei1);
		eventList.add(ei2);
		eventList.add(ei3);

		response.result.eventList = eventList;

		String json = gson.toJson(response);
		System.out.println("EventListResponse = " + json);
		return json;
	}

}
