package org.footballregistration.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;

import org.footballregistration.common.Constants;
import org.footballregistration.response.EventDetailResponse;
import org.footballregistration.response.EventListResponse;
import org.footballregistration.response.model.EventDetailInfo;
import org.footballregistration.response.model.EventInfo;
import org.footballregistration.response.model.ProposerUserInfo;
import org.footballregistration.service.CreateEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class EventController {

	@Autowired
	private CreateEventService createEventSevice;

	@RequestMapping(value = "/event/getEventList", method = POST, produces = "application/json;charset=UTF-8")
	public String getEventList(@RequestBody String jsonRequest){

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

	@RequestMapping(value = "/event/getEventDetail", method = POST, produces = "application/json;charset=UTF-8")
	public String getEventDetail(@RequestBody String jsonRequest){

		Gson gson = new Gson();

		EventDetailResponse response = new EventDetailResponse();

		response.responseCode = Constants.RESPONSE_CODE_OK;

		EventDetailInfo eventDetailInfo = new EventDetailInfo();
		eventDetailInfo.eventId = 1;
		eventDetailInfo.eventName = "足球活动1";
		eventDetailInfo.status = "报名中";
		eventDetailInfo.eventKbn = "训练";
		eventDetailInfo.eventDate1 = "2018/05/01 上午10点00分";
		eventDetailInfo.eventDate2 = "2018/05/01 上午11点00分";
		eventDetailInfo.eventDate3 = "2018/05/01 下午13点00分";
		eventDetailInfo.eventDate4 = "2018/05/01 下午14点00分";
		eventDetailInfo.eventPlaceName = "000-0000-0000";
		eventDetailInfo.eventPlaceX = "35.6895";
		eventDetailInfo.eventPlaceY = "139.69169";
		eventDetailInfo.eventCost = 1000;
		eventDetailInfo.phoneNo = "000-0000-0000";
		eventDetailInfo.comment = "踊跃报名!";
		response.result.eventDetailInfo = eventDetailInfo;


		ProposerUserInfo proposerUserInfo1 = new ProposerUserInfo();
		proposerUserInfo1.userId = 1;
		proposerUserInfo1.icon = "XXX";
		proposerUserInfo1.comment = "这是1的备注";
		proposerUserInfo1.selectEventDate1 = true;
		proposerUserInfo1.selectEventDate2 = false;
		proposerUserInfo1.selectEventDate3 = true;
		proposerUserInfo1.selectEventDate4 = false;

		ProposerUserInfo proposerUserInfo2 = new ProposerUserInfo();
		proposerUserInfo2.userId = 1;
		proposerUserInfo2.icon = "XXX";
		proposerUserInfo2.comment = "这是2的备注";
		proposerUserInfo2.selectEventDate1 = true;
		proposerUserInfo2.selectEventDate2 = true;
		proposerUserInfo2.selectEventDate3 = true;
		proposerUserInfo2.selectEventDate4 = true;

		List<ProposerUserInfo> proposerUserInfoList = new ArrayList<ProposerUserInfo>();
		proposerUserInfoList.add(proposerUserInfo1);
		proposerUserInfoList.add(proposerUserInfo2);

		response.result.proposerUserCount = 2;
		response.result.proposerUserList = proposerUserInfoList;

		String json = gson.toJson(response);
		System.out.println("EventDetailResponse = " + json);
		return json;
	}

	// Event创建API
	@RequestMapping(value = "/event/createEvent", method = POST, produces = "application/json;charset=UTF-8")
	public String createEvent(@RequestBody String jsonRequest){
		return createEventSevice.createEvent(jsonRequest);
	}

}
