package org.footballregistration.request;

public class ProposerEventRequest extends CommonRequest {

	public RequestInfo requestInfo;

	public class RequestInfo {
		public int eventId;
		public boolean selectEventDate1;
		public boolean selectEventDate2;
		public boolean selectEventDate3;
		public boolean selectEventDate4;
		public String comment;
	}
}
