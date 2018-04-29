package org.footballregistration.response;

public class CommonResponse {

	public String responseCode;
	public ErrorInfo errorInfo;

	public class ErrorInfo {
		public String message;
	}

}
