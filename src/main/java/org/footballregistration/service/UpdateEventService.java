package org.footballregistration.service;

import org.apache.commons.lang3.StringUtils;
import org.footballregistration.common.Constants;
import org.footballregistration.dao.EventInfoDao;
import org.footballregistration.dao.entity.EventInfoEntity;
import org.footballregistration.request.UpdateEventRequest;
import org.footballregistration.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

@Service
@Transactional
public class UpdateEventService {

	static final Logger log = LoggerFactory.getLogger(UpdateEventService.class);

	@Autowired
	private EventInfoDao eventInfoDao;

	// Event更新
	public String updateEvent(String jsonRequest) {

		Gson gson = new Gson();
		CommonResponse response = new CommonResponse();

		log.info("GetEventListRequest = " + jsonRequest);

		try {
			UpdateEventRequest request = gson.fromJson(jsonRequest, UpdateEventRequest.class);

			int eventId = request.requestInfo.eventid;
			if (StringUtils.isEmpty(String.valueOf(eventId))) {
				throw new IllegalArgumentException("Request Parameter is Empty : eventId");
			}

			EventInfoEntity updateInfo = new EventInfoEntity();
			updateInfo.setEvent_id(eventId);
			updateInfo.setEvent_name(request.requestInfo.eventName);
			updateInfo.setEvent_kbn(request.requestInfo.eventKbn);
			updateInfo.setDeadline_date(request.requestInfo.deadlineDate);
			updateInfo.setEvent_date_1(request.requestInfo.eventDate1);
			updateInfo.setEvent_date_2(request.requestInfo.eventDate2);
			updateInfo.setEvent_date_3(request.requestInfo.eventDate3);
			updateInfo.setEvent_date_4(request.requestInfo.eventDate4);
			updateInfo.setEvent_place_name(request.requestInfo.eventPlaceName);
			updateInfo.setEvent_place_x(request.requestInfo.eventPlaceX);
			updateInfo.setEvent_place_y(request.requestInfo.eventPlaceY);
			updateInfo.setEvent_cost(request.requestInfo.eventCost);
			updateInfo.setCost_unit(request.requestInfo.costUnit);
			updateInfo.setPhone_no(request.requestInfo.phoneNo);
			updateInfo.setComment(request.requestInfo.comment);

			eventInfoDao.updateEventInfo(updateInfo);
			response.responseCode = Constants.RESPONSE_CODE_OK;

		} catch (Exception e) {
			response.responseCode = Constants.RESPONSE_CODE_NG;
			response.errorInfo.message = e.getMessage();
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}

		String json = gson.toJson(response);
		log.info("UpdateEvent = " + json);

		return json;
	}

}