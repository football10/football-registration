package org.footballregistration.service;

import org.apache.commons.lang3.StringUtils;
import org.footballregistration.common.Constants;
import org.footballregistration.dao.EventInfoDao;
import org.footballregistration.dao.UserInfoDao;
import org.footballregistration.dao.entity.EventInfoEntity;
import org.footballregistration.dao.entity.UserInfoEntity;
import org.footballregistration.request.CreateEventRequest;
import org.footballregistration.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

@Service
@Transactional
public class CreateEventService {

	@Autowired
	private EventInfoDao eventInfoDao;

	@Autowired
	private UserInfoDao userInfoDao;

	public String createEvent(String jsonRequest) {
		Gson gson = new Gson();
		CommonResponse response = new CommonResponse();

		System.out.println("CreateEventRequest = " + jsonRequest);
		try {
			CreateEventRequest request = gson.fromJson(jsonRequest, CreateEventRequest.class);

			// ParameterCheck TODO
			String userId = request.userInfo.userId;
			if (StringUtils.isEmpty(userId)) {
				throw new IllegalArgumentException("Request Parameter is Empty : userId");
			}

			UserInfoEntity userInfo = new UserInfoEntity();
			userInfo.setUser_Id(request.userInfo.userId);
			userInfo.setUser_name(request.userInfo.userName);
			userInfo.setIcon(request.userInfo.icon);

			// DB保存当前用户信息
			UserInfoEntity result = userInfoDao.selectUserInfo(userId);
			if (result == null || StringUtils.isEmpty(result.getUser_Id())) {
				// DB里不存在当前用户信息，新规插入
				userInfoDao.insertUserInfo(userInfo);
			} else {
				userInfoDao.updateUserInfo(userInfo);
			}

			EventInfoEntity eventInfoEntity = new EventInfoEntity();
			eventInfoEntity.setProposer_user_id(request.userInfo.userId);
			eventInfoEntity.setEvent_name(request.requestInfo.eventName);
			// 报名中
			eventInfoEntity.setStatus(Constants.EVENT_STATUS_S01);
			eventInfoEntity.setEvent_kbn(request.requestInfo.eventKbn);
			eventInfoEntity.setDeadline_date(request.requestInfo.deadlineDate);
			eventInfoEntity.setEvent_date_1(request.requestInfo.eventDate1);
			eventInfoEntity.setEvent_date_2(request.requestInfo.eventDate2);
			eventInfoEntity.setEvent_date_3(request.requestInfo.eventDate3);
			eventInfoEntity.setEvent_date_4(request.requestInfo.eventDate4);
			eventInfoEntity.setEvent_place_name(request.requestInfo.eventPlaceName);
			eventInfoEntity.setEvent_place_x(request.requestInfo.eventPlaceX);
			eventInfoEntity.setEvent_place_y(request.requestInfo.eventPlaceY);
			eventInfoEntity.setEvent_cost(request.requestInfo.eventCost);
			eventInfoEntity.setCost_unit(request.requestInfo.costUnit);
			eventInfoEntity.setPhone_no(request.requestInfo.phoneNo);
			eventInfoEntity.setComment(request.requestInfo.comment);

			eventInfoDao.insertEventInfo(eventInfoEntity);
			response.responseCode = Constants.RESPONSE_CODE_OK;
			response.errorInfo = null;
		} catch (Exception e) {
			response.responseCode = Constants.RESPONSE_CODE_NG;
			response.errorInfo.message = e.getMessage();
			e.printStackTrace();
		}

		String json = gson.toJson(response);
		System.out.println("CreateEventResponse = " + json);
		return json;
	}

}
