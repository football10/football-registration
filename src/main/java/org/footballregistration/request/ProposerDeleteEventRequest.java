package org.footballregistration.request;

public class ProposerDeleteEventRequest extends CommonRequest {
	public RequestInfo requestInfo;

	public class RequestInfo {
		public int eventId;
	}
}
