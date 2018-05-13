package org.footballregistration.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.footballregistration.common.Constants;
import org.footballregistration.dao.EventInfoDao;
import org.footballregistration.dao.EventParticipantDao;
import org.footballregistration.dao.UserInfoDao;
import org.footballregistration.dao.entity.EventInfoEntity;
import org.footballregistration.dao.entity.EventParticipantEntity;
import org.footballregistration.dao.entity.UserInfoEntity;
import org.footballregistration.request.GetEventDetailRequest;
import org.footballregistration.request.GetEventListRequest;
import org.footballregistration.response.EventDetailResponse;
import org.footballregistration.response.EventListResponse;
import org.footballregistration.response.model.EventDetailInfo;
import org.footballregistration.response.model.EventInfo;
import org.footballregistration.response.model.ProposerUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

@Service
@Transactional
public class GetEventService {

	@Autowired
	private EventInfoDao eventInfoDao;

	@Autowired
	private EventParticipantDao eventParticipantDao;

	@Autowired
	private UserInfoDao userInfoDao;

	public String getEventList(String jsonRequest) {

		Gson gson = new Gson();
		EventListResponse response = new EventListResponse();

		System.out.println("GetEventListRequest = " + jsonRequest);

		try {
			GetEventListRequest request = gson.fromJson(jsonRequest, GetEventListRequest.class);

			String userId = request.userInfo.userId;
			if (StringUtils.isEmpty(userId)) {
				throw new IllegalArgumentException("Request Parameter is Empty : userId");
			}

			// 取得自己参加的EventList
			List<EventParticipantEntity> envenList = eventParticipantDao.selectEventParticipantByUserId(userId);
			response.result.eventCount = envenList.size();

			List<EventInfo> eventList = new ArrayList<EventInfo>();
			for(EventParticipantEntity participantInfo : envenList) {
				int eventId = participantInfo.getEvent_id();
				// 取得Event情报
				EventInfoEntity eventInfo = eventInfoDao.selectEventInfo(eventId);
				EventInfo ei = new EventInfo();
				ei.eventId = eventId;
				ei.eventName = eventInfo.getEvent_name();
				ei.status = eventInfo.getStatus();
				ei.eventKbn = eventInfo.getEvent_kbn();
				ei.eventDate1 = eventInfo.getEvent_date_1();
				ei.eventDate2 = eventInfo.getEvent_date_2();
				ei.eventDate3 = eventInfo.getEvent_date_3();
				ei.eventDate4 = eventInfo.getEvent_date_4();
				ei.eventPlaceName = eventInfo.getEvent_place_name();

				// 取得参加者的EventList
				List<EventParticipantEntity> participantList = eventParticipantDao.selectEventParticipantByEventId(eventId);
				ei.proposerUserCount = participantList.size();

				eventList.add(ei);
			}

			response.result.eventList = eventList;
			response.responseCode = Constants.RESPONSE_CODE_OK;

		} catch (Exception e) {
			response.result = null;
			response.responseCode = Constants.RESPONSE_CODE_NG;
			response.errorInfo.message = e.getMessage();
			e.printStackTrace();
		}

		String json = gson.toJson(response);
		System.out.println("GetEventListResponse = " + json);
		return json;
	}

	public String getEventDetail(String jsonRequest) {

		Gson gson = new Gson();
		EventDetailResponse response = new EventDetailResponse();
		System.out.println("GetEventDetailRequest = " + jsonRequest);

		try {
			GetEventDetailRequest request = gson.fromJson(jsonRequest, GetEventDetailRequest.class);
			// ParameterCheck
			if (StringUtils.isEmpty(request.userInfo.userId)) {
				throw new IllegalArgumentException("Request Parameter is Empty : userId");
			}
			if (StringUtils.isEmpty(String.valueOf(request.requestInfo.eventId))) {
				throw new IllegalArgumentException("Request Parameter is Empty : eventId");
			}

			// 取得Event详细情报
			EventInfoEntity envenInfo = eventInfoDao.selectEventInfo(request.requestInfo.eventId);
			EventDetailInfo eventDetailInfo = new EventDetailInfo();
			eventDetailInfo.eventId = envenInfo.getEvent_id();
			eventDetailInfo.eventName = envenInfo.getEvent_name();
			eventDetailInfo.status = envenInfo.getStatus();
			eventDetailInfo.eventKbn = envenInfo.getEvent_kbn();
			eventDetailInfo.eventDate1 = envenInfo.getEvent_date_1();
			eventDetailInfo.eventDate2 = envenInfo.getEvent_date_2();
			eventDetailInfo.eventDate3 = envenInfo.getEvent_date_3();
			eventDetailInfo.eventDate4 = envenInfo.getEvent_date_4();
			eventDetailInfo.eventPlaceName = envenInfo.getEvent_place_name();
			eventDetailInfo.eventPlaceX = envenInfo.getEvent_place_x();
			eventDetailInfo.eventPlaceY = envenInfo.getEvent_place_y();
			eventDetailInfo.eventCost = envenInfo.getEvent_cost();
			eventDetailInfo.phoneNo = envenInfo.getPhone_no();
			eventDetailInfo.comment = envenInfo.getComment();
			response.result.eventDetailInfo = eventDetailInfo;

			// 取得参加者的EventList
			List<EventParticipantEntity> participantList = eventParticipantDao.selectEventParticipantByEventId(request.requestInfo.eventId);
			response.result.proposerUserCount = participantList.size();
			List<ProposerUserInfo> proposerUserInfoList = new ArrayList<ProposerUserInfo>();
			for (EventParticipantEntity eventParticipant : participantList) {
				ProposerUserInfo proposerUserInfo = new ProposerUserInfo();
				proposerUserInfo.userId = eventParticipant.getParticipant_user_id();

				proposerUserInfo.comment = eventParticipant.getComment();
				proposerUserInfo.selectEventDate1 = Boolean.getBoolean(eventParticipant.event_date_1_flg);
				proposerUserInfo.selectEventDate2 = Boolean.getBoolean(eventParticipant.event_date_2_flg);
				proposerUserInfo.selectEventDate3 = Boolean.getBoolean(eventParticipant.event_date_3_flg);
				proposerUserInfo.selectEventDate4 = Boolean.getBoolean(eventParticipant.event_date_4_flg);

				// 取得用户详细信息
				UserInfoEntity userInfo = userInfoDao.selectUserInfo(eventParticipant.getParticipant_user_id());
				proposerUserInfo.userName = userInfo.getUser_name();
				proposerUserInfo.icon = userInfo.getIcon();

				proposerUserInfoList.add(proposerUserInfo);
			}

			response.result.proposerUserList = proposerUserInfoList;
		} catch (Exception e) {
			response.result = null;
			response.responseCode = Constants.RESPONSE_CODE_NG;
			response.errorInfo.message = e.getMessage();
			e.printStackTrace();
		}

		String json = gson.toJson(response);
		System.out.println("GetEventDetailResponse = " + json);
		return json;
	}

}
