package org.footballregistration.request;

public class GetEventDetailRequest extends CommonRequest {

	public RequestInfo requestInfo;

	public class RequestInfo {
		public int eventId;
	}
}
