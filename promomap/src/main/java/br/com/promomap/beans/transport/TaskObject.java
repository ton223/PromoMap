package br.com.promomap.beans.transport;

import java.util.Collection;

public class TaskObject {

	private Collection<Object> dataList;

	private Object data;

	private boolean success;

	private int errorCode;
	
	private String errorMessage;
	
	private String info;

	public Collection<Object> getDataList() {
		return dataList;
	}

	public void setDataList(Collection<Object> dataList) {
		this.dataList = dataList;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "TaskObject [dataList=" + dataList + ", data=" + data + ", success=" + success + ", errorCode="
				+ errorCode + ", errorMessage=" + errorMessage + ", info=" + info + "]";
	}

}
