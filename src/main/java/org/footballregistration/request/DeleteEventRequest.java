package org.footballregistration.request;

public class DeleteEventRequest extends CommonRequest {
	public RequestInfo requestInfo;

	public class RequestInfo {
		public int eventId;
	}
}
