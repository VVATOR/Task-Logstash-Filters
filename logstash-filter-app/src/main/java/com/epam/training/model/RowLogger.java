package com.epam.training.model;

public class RowLogger {
	private String messageId;
	private String uuid;
	private String mapKV;
	private String processId;
	private String componentName;

	private String message;

	public RowLogger() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RowLogger(String messageId, String uuid, String mapKV, String processId, String componentName,
			String message) {
		super();
		this.messageId = messageId;
		this.uuid = uuid;
		this.mapKV = mapKV;
		this.processId = processId;
		this.componentName = componentName;
		this.message = message;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMapKV() {
		return mapKV;
	}

	public void setMapKV(String mapKV) {
		this.mapKV = mapKV;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return String.format("%s [%s%s] %s %s: %s\r\n", messageId, uuid, mapKV, processId, componentName, message);
	}

}
