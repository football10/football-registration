package org.footballregistration.dao.entity;

public class EventParticipant {

	// 活动ID
	public long event_id;
	// 用户ID
	public String participant_user_id;
	// 参加与否
	public String participant_flg;
	// 留言
	public String comment;
	// 回答时间
	public String answer_dtime;

	public long getEvent_id() {
		return event_id;
	}
	public void setEvent_id(long event_id) {
		this.event_id = event_id;
	}
	public String getParticipant_user_id() {
		return participant_user_id;
	}
	public void setParticipant_user_id(String participant_user_id) {
		this.participant_user_id = participant_user_id;
	}
	public String getParticipant_flg() {
		return participant_flg;
	}
	public void setParticipant_flg(String participant_flg) {
		this.participant_flg = participant_flg;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAnswer_dtime() {
		return answer_dtime;
	}
	public void setAnswer_dtime(String answer_dtime) {
		this.answer_dtime = answer_dtime;
	}
}
