package org.footballregistration.dao.entity;

public class EventParticipantEntity {

	// 活动ID
	public int event_id;
	// 用户ID
	public String participant_user_id;
	// 参加与否
	public String event_date_1_flg;
	// 参加与否
	public String event_date_2_flg;
	// 参加与否
	public String event_date_3_flg;
	// 参加与否
	public String event_date_4_flg;
	// 留言
	public String comment;
	// 回答时间
	public String answer_dtime;
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
	public String getEvent_date_1_flg() {
		return event_date_1_flg;
	}
	public void setEvent_date_1_flg(String event_date_1_flg) {
		this.event_date_1_flg = event_date_1_flg;
	}
	public String getEvent_date_2_flg() {
		return event_date_2_flg;
	}
	public void setEvent_date_2_flg(String event_date_2_flg) {
		this.event_date_2_flg = event_date_2_flg;
	}
	public String getEvent_date_3_flg() {
		return event_date_3_flg;
	}
	public void setEvent_date_3_flg(String event_date_3_flg) {
		this.event_date_3_flg = event_date_3_flg;
	}
	public String getEvent_date_4_flg() {
		return event_date_4_flg;
	}
	public void setEvent_date_4_flg(String event_date_4_flg) {
		this.event_date_4_flg = event_date_4_flg;
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
