package com.otod.server.otod.market.pojo;

public class ResultVo {
	
	private int errorCode;
	private String errorMsg;
	private Object data;
	
	public void setError(int errorCode, String errorMsg){
		this.setErrorCode(errorCode);
		this.setErrorMsg(errorMsg);
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	 
	 
}

