package org.footballregistration.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.footballregistration.common.Constants;
import org.footballregistration.common.Util;
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
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

@Service
@Transactional
public class GetEventService {

	static final Logger log = LoggerFactory.getLogger(GetEventService.class);

	@Autowired
	private EventInfoDao eventInfoDao;

	@Autowired
	private EventParticipantDao eventParticipantDao;

	@Autowired
	private UserInfoDao userInfoDao;

	// 参加的Event一览取得
	public String getProposerEventList(String jsonRequest) {

		Gson gson = new Gson();
		EventListResponse response = new EventListResponse();

		log.info("GetProposerEventListRequest = " + jsonRequest);

		try {
			GetEventListRequest request = gson.fromJson(jsonRequest, GetEventListRequest.class);

			String userId = request.userInfo.userId;
			if (StringUtils.isEmpty(userId)) {
				throw new IllegalArgumentException("Request Parameter is Empty : userId");
			}

			// 取得自己参加的EventList
			List<EventParticipantEntity> participantEnvenList = eventParticipantDao.selectEventParticipantByUserId(userId);

			List<EventInfo> eventList = new ArrayList<EventInfo>();
			for(EventParticipantEntity participantInfo : participantEnvenList) {
				int eventId = participantInfo.getEvent_id();
				// 取得Event情报
				EventInfoEntity eventInfo = eventInfoDao.selectEventInfo(eventId);

				if(eventInfo != null) {
					EventInfo ei = new EventInfo();
					ei.eventId = eventId;
					ei.eventName = eventInfo.getEvent_name();
					ei.status = Util.getStatus(eventInfo.getDeadline_date());
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
			}

			response.result.eventCount = eventList.size();

			response.result.eventList = eventList;
			response.responseCode = Constants.RESPONSE_CODE_OK;

		} catch (Exception e) {
			response.result = null;
			response.responseCode = Constants.RESPONSE_CODE_NG;
			response.errorInfo.message = e.getMessage();
			e.printStackTrace();

			log.error(e.getMessage(), e);
		}

		String json = gson.toJson(response);
		log.info("GetProposerEventListResponse = " + json);

		return json;
	}

	// 创建的Event一览取得
	public String getCreateEventList(String jsonRequest) {

		Gson gson = new Gson();
		EventListResponse response = new EventListResponse();

		log.info("GetCreateEventListRequest = " + jsonRequest);

		try {
			GetEventListRequest request = gson.fromJson(jsonRequest, GetEventListRequest.class);

			String userId = request.userInfo.userId;
			if (StringUtils.isEmpty(userId)) {
				throw new IllegalArgumentException("Request Parameter is Empty : userId");
			}

			// 取得自己创建的EventList
			List<EventInfoEntity> creatEventList = eventInfoDao.selectEventInfoByUserId(userId);

			List<EventInfo> eventList = new ArrayList<EventInfo>();
			for(EventInfoEntity eventInfo : creatEventList) {
				int eventId = eventInfo.getEvent_id();
				EventInfo ei = new EventInfo();
				ei.eventId = eventId;
				ei.eventName = eventInfo.getEvent_name();
				ei.status = Util.getStatus(eventInfo.getDeadline_date());
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

			response.result.eventCount = eventList.size();

			response.result.eventList = eventList;
			response.responseCode = Constants.RESPONSE_CODE_OK;

		} catch (Exception e) {
			response.result = null;
			response.responseCode = Constants.RESPONSE_CODE_NG;
			response.errorInfo.message = e.getMessage();
			e.printStackTrace();

			log.error(e.getMessage(), e);
		}

		String json = gson.toJson(response);
		log.info("GetCreateEventListResponse = " + json);

		return json;
	}

	// Event详细取得
	public String getEventDetail(String jsonRequest) {

		Gson gson = new Gson();
		EventDetailResponse response = new EventDetailResponse();

		log.info("GetEventDetailRequest = " + jsonRequest);

		try {
			GetEventDetailRequest request = gson.fromJson(jsonRequest, GetEventDetailRequest.class);
			// ParameterCheck
			if (StringUtils.isEmpty(String.valueOf(request.requestInfo.eventId))) {
				throw new IllegalArgumentException("Request Parameter is Empty : eventId");
			}

			// 取得Event详细情报
			EventInfoEntity eventInfo = eventInfoDao.selectEventInfo(request.requestInfo.eventId);
			if (eventInfo == null) {
				throw new IllegalArgumentException("Not Found Event Detail Info for EventId : " + request.requestInfo.eventId);
			}

			EventDetailInfo eventDetailInfo = new EventDetailInfo();
			eventDetailInfo.eventId = eventInfo.getEvent_id();
			eventDetailInfo.eventName = eventInfo.getEvent_name();
			eventDetailInfo.status = Util.getStatus(eventInfo.getDeadline_date());
			eventDetailInfo.eventKbn = eventInfo.getEvent_kbn();
			eventDetailInfo.eventDate1 = eventInfo.getEvent_date_1();
			eventDetailInfo.eventDate2 = eventInfo.getEvent_date_2();
			eventDetailInfo.eventDate3 = eventInfo.getEvent_date_3();
			eventDetailInfo.eventDate4 = eventInfo.getEvent_date_4();
			eventDetailInfo.eventPlaceName = eventInfo.getEvent_place_name();
			eventDetailInfo.eventPlaceX = eventInfo.getEvent_place_x();
			eventDetailInfo.eventPlaceY = eventInfo.getEvent_place_y();
			eventDetailInfo.eventCost = eventInfo.getEvent_cost();
			eventDetailInfo.costUnit = eventInfo.getCost_unit();
			eventDetailInfo.phoneNo = eventInfo.getPhone_no();
			eventDetailInfo.comment = eventInfo.getComment();
			response.result.eventDetailInfo = eventDetailInfo;

			// 取得参加者的EventList
			List<EventParticipantEntity> participantList = eventParticipantDao.selectEventParticipantByEventId(request.requestInfo.eventId);
			response.result.proposerUserCount = participantList.size();
			List<ProposerUserInfo> proposerUserInfoList = new ArrayList<ProposerUserInfo>();
			for (EventParticipantEntity eventParticipant : participantList) {
				ProposerUserInfo proposerUserInfo = new ProposerUserInfo();
				proposerUserInfo.userId = eventParticipant.getParticipant_user_id();

				proposerUserInfo.comment = eventParticipant.getComment();
				proposerUserInfo.selectEventDate1 = Boolean.parseBoolean(eventParticipant.getEvent_date_1_flg());
				proposerUserInfo.selectEventDate2 = Boolean.parseBoolean(eventParticipant.getEvent_date_2_flg());
				proposerUserInfo.selectEventDate3 = Boolean.parseBoolean(eventParticipant.getEvent_date_3_flg());
				proposerUserInfo.selectEventDate4 = Boolean.parseBoolean(eventParticipant.getEvent_date_4_flg());

				// 取得用户详细信息
				UserInfoEntity userInfo = userInfoDao.selectUserInfo(eventParticipant.getParticipant_user_id());
				proposerUserInfo.userName = userInfo.getUser_name();
				proposerUserInfo.icon = userInfo.getIcon();

				proposerUserInfoList.add(proposerUserInfo);
			}

			response.result.proposerUserList = proposerUserInfoList;
			response.responseCode = Constants.RESPONSE_CODE_OK;

		} catch (Exception e) {
			response.result = null;
			response.responseCode = Constants.RESPONSE_CODE_NG;
			response.errorInfo.message = e.getMessage();
			e.printStackTrace();

			log.error(e.getMessage(), e);
		}

		String json = gson.toJson(response);
		log.info("GetEventDetailResponse = " + json);

		return json;
	}

}
