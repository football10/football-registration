package org.footballregistration.dao.entity;

public class EventInfoEntity {

	// 活动ID
	public int event_id;
	// 活动名
	public String event_name;
	// 状态
	public String status;
	// 有效期限
	public String deadline_date;
	// 活动类型
	public String event_kbn;
	// 发起人ID
	public String proposer_user_id;
	// 活动时间 候选1
	public String event_date_1;
	// 活动时间 候选2
	public String event_date_2;
	// 活动时间 候选3
	public String event_date_3;
	// 活动时间 候选4
	public String event_date_4;
	// 活动地点
	public String event_place_name;
	// 活动地点 坐标X
	public String event_place_x;
	// 活动地点 坐标Y
	public String event_place_y;
	// 费用
	public long event_cost;
	// 费用单位
	public String cost_unit;
	// 联系方式
	public String phone_no;
	// 留言
	public String comment;
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeadline_date() {
		return deadline_date;
	}
	public void setDeadline_date(String deadline_date) {
		this.deadline_date = deadline_date;
	}
	public String getEvent_kbn() {
		return event_kbn;
	}
	public void setEvent_kbn(String event_kbn) {
		this.event_kbn = event_kbn;
	}
	public String getProposer_user_id() {
		return proposer_user_id;
	}
	public void setProposer_user_id(String proposer_user_id) {
		this.proposer_user_id = proposer_user_id;
	}
	public String getEvent_date_1() {
		return event_date_1;
	}
	public void setEvent_date_1(String event_date_1) {
		this.event_date_1 = event_date_1;
	}
	public String getEvent_date_2() {
		return event_date_2;
	}
	public void setEvent_date_2(String event_date_2) {
		this.event_date_2 = event_date_2;
	}
	public String getEvent_date_3() {
		return event_date_3;
	}
	public void setEvent_date_3(String event_date_3) {
		this.event_date_3 = event_date_3;
	}
	public String getEvent_date_4() {
		return event_date_4;
	}
	public void setEvent_date_4(String event_date_4) {
		this.event_date_4 = event_date_4;
	}
	public String getEvent_place_name() {
		return event_place_name;
	}
	public void setEvent_place_name(String event_place_name) {
		this.event_place_name = event_place_name;
	}
	public String getEvent_place_x() {
		return event_place_x;
	}
	public void setEvent_place_x(String event_place_x) {
		this.event_place_x = event_place_x;
	}
	public String getEvent_place_y() {
		return event_place_y;
	}
	public void setEvent_place_y(String event_place_y) {
		this.event_place_y = event_place_y;
	}
	public long getEvent_cost() {
		return event_cost;
	}
	public void setEvent_cost(long event_cost) {
		this.event_cost = event_cost;
	}
	public String getCost_unit() {
		return cost_unit;
	}
	public void setCost_unit(String cost_unit) {
		this.cost_unit = cost_unit;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
