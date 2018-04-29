package org.footballregistration.request;

public class CreateEventRequest extends CommonRequest {

	public RequestInfo requestInfo;

	public class RequestInfo {
		// 名称
		public String eventName;
		// 分类
		public String eventKbn;
		// 有效期限
		public String deadlineDate;
		// 备选日期1
		public String eventDate1;
		// 备选日期2
		public String eventDate2;
		// 备选日期3
		public String eventDate3;
		// 备选日期4
		public String eventDate4;
		// 地点名称
		public String eventPlaceName;
		// 地点坐标X
		public String eventPlaceX;
		// 地点坐标Y
		public String eventPlaceY;
		// 费用
		public long eventCost;
		// 费用单位
		public String costUnit;
		// 电话
		public String phoneNo;
		// 备注
		public String comment;
	}
}
