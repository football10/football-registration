package org.footballregistration.service;

import org.apache.commons.lang3.StringUtils;
import org.footballregistration.common.Constants;
import org.footballregistration.dao.EventInfoDao;
import org.footballregistration.request.DeleteEventRequest;
import org.footballregistration.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

@Service
@Transactional
public class DeleteEventService {

	static final Logger log = LoggerFactory.getLogger(DeleteEventService.class);

	@Autowired
	private EventInfoDao eventInfoDao;

	public String deleteEvent(String jsonRequest) {
		Gson gson = new Gson();
		CommonResponse response = new CommonResponse();

		log.info("DeleteEventRequest = " + jsonRequest);

		try {
			DeleteEventRequest request = gson.fromJson(jsonRequest, DeleteEventRequest.class);
			int eventId = request.requestInfo.eventId;
			if (StringUtils.isEmpty(String.valueOf(eventId))) {
				throw new IllegalArgumentException("Request Parameter is Empty : eventId");
			}

			eventInfoDao.deleteEventInfo(eventId);

			response.responseCode = Constants.RESPONSE_CODE_OK;
			response.errorInfo = null;
		} catch (Exception e) {
			response.responseCode = Constants.RESPONSE_CODE_NG;
			response.errorInfo.message = e.getMessage();
			e.printStackTrace();

			log.error(e.getMessage(), e);
		}

		String json = gson.toJson(response);
		log.info("DeleteEventResponse = " + json);

		return json;
	}
}
