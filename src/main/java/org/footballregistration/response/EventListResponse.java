package org.footballregistration.response;

import java.util.List;

import org.footballregistration.response.model.EventInfo;

public class EventListResponse extends CommonResponse {

	public EventListResult result = new EventListResult();

	public class EventListResult {
		public int eventCount;
		public List<EventInfo> eventList;
	}

}
