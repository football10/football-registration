package org.footballregistration.response;

import java.util.List;

import org.footballregistration.response.model.EventDetailInfo;
import org.footballregistration.response.model.ProposerUserInfo;

public class EventDetailResponse extends CommonResponse {

	public EventDetailResult result = new EventDetailResult();

	public class EventDetailResult {
		public EventDetailInfo eventDetailInfo;
		public int proposerUserCount;
		public List<ProposerUserInfo> proposerUserList;
	}
}
