package org.footballregistration.dao.parameter;

public class EventParticipantParameter {

	// 活动ID
	public int event_id;
	// 用户ID
	public String participant_user_id;
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public String getParticipant_user_id() {
		return participant_user_id;
	}
	public void setParticipant_user_id(String participant_user_id) {
		this.participant_user_id = participant_user_id;
	}
}
