package org.footballregistration.service;

import org.apache.commons.lang3.StringUtils;
import org.footballregistration.common.Constants;
import org.footballregistration.dao.EventParticipantDao;
import org.footballregistration.dao.UserInfoDao;
import org.footballregistration.dao.entity.EventParticipantEntity;
import org.footballregistration.dao.entity.UserInfoEntity;
import org.footballregistration.dao.parameter.EventParticipantParameter;
import org.footballregistration.request.ProposerEventRequest;
import org.footballregistration.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

@Service
@Transactional
public class ProposerEventService {

	@Autowired
	private EventParticipantDao eventParticipantDao;

	@Autowired
	private UserInfoDao userInfoDao;

	public String proposerEvent(String jsonRequest) {
		Gson gson = new Gson();
		CommonResponse response = new CommonResponse();

		System.out.println("ProposerEventRequest = " + jsonRequest);

		try {
			ProposerEventRequest request = gson.fromJson(jsonRequest, ProposerEventRequest.class);

			String userId = request.userInfo.userId;
			int eventId = request.requestInfo.eventId;
			if (StringUtils.isEmpty(userId)) {
				throw new IllegalArgumentException("Request Parameter is Empty : userId");
			}
			if (StringUtils.isEmpty(String.valueOf(eventId))) {
				throw new IllegalArgumentException("Request Parameter is Empty : eventId");
			}

			UserInfoEntity userInfo = new UserInfoEntity();
			userInfo.setUser_Id(request.userInfo.userId);
			userInfo.setUser_name(request.userInfo.userName);
			userInfo.setIcon(request.userInfo.icon);

			// DB保存当前用户信息
			UserInfoEntity userResult = userInfoDao.selectUserInfo(userId);
			if (userResult == null || StringUtils.isEmpty(userResult.getUser_Id())) {
				// DB里不存在当前用户信息，新规插入
				userInfoDao.insertUserInfo(userInfo);
			} else {
				userInfoDao.updateUserInfo(userInfo);
			}

			EventParticipantEntity eventParticipant = new EventParticipantEntity();
			eventParticipant.setEvent_id(eventId);
			eventParticipant.setParticipant_user_id(userId);
			eventParticipant.setEvent_date_1_flg(String.valueOf(request.requestInfo.selectEventDate1));
			eventParticipant.setEvent_date_2_flg(String.valueOf(request.requestInfo.selectEventDate2));
			eventParticipant.setEvent_date_3_flg(String.valueOf(request.requestInfo.selectEventDate3));
			eventParticipant.setEvent_date_4_flg(String.valueOf(request.requestInfo.selectEventDate4));
			eventParticipant.setComment(request.requestInfo.comment);

			// 查询参加情况
			EventParticipantParameter pk = new EventParticipantParameter();
			pk.setEvent_id(eventId);
			pk.setParticipant_user_id(userId);
			EventParticipantEntity participantResult = eventParticipantDao.selectEventParticipantByPk(pk);
			if (participantResult == null || StringUtils.isEmpty(participantResult.getParticipant_user_id())) {
				// 没有参加过报名，新规插入
				eventParticipantDao.insertEventParticipant(eventParticipant);
			} else {
				// 参加过报名，更新
				eventParticipantDao.updateEventParticipant(eventParticipant);
			}

			response.responseCode = Constants.RESPONSE_CODE_OK;
			response.errorInfo = null;

		} catch (Exception e) {
			response.responseCode = Constants.RESPONSE_CODE_NG;
			response.errorInfo.message = e.getMessage();
			e.printStackTrace();
		}

		String json = gson.toJson(response);
		System.out.println("ProposerEventResponse = " + json);
		return json;
	}
}
