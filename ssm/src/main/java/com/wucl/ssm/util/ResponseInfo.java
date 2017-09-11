package com.wucl.ssm.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 响应信息
 * @author lj
 * @createTime 2017年1月1日 下午12:48:49
 */
public class ResponseInfo {

	private boolean success = true; // 默认为true
	
	private String message;
	
	private String code = "0000";
	
	private Object info;
	
	public ResponseInfo(){
		
	}
	public ResponseInfo(boolean success,String message){
		this.message = message;
		this.success = success;
	}
	
	/*public ResponseInfo(@SuppressWarnings("rawtypes") Page page){
		Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("count", page.getCount());
		infoMap.put("data", page.getList());
		this.info=infoMap;
	}
	
	public ResponseInfo(@SuppressWarnings("rawtypes") Page page,List<?> list){
		Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("count", page.getCount());
		infoMap.put("data", list);
		this.info = infoMap;
	}*/
	
	public ResponseInfo(List<?> list){
		Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("count", list.size());
		infoMap.put("data", list);
		this.info = infoMap;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String toJson(){
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			String json = objectMapper.writeValueAsString(this);
			return json;
		} catch (JsonProcessingException e) {
			this.message = "ResponseInfo 转化 JSON 失败，失败原因： " + e.getMessage();
			e.printStackTrace();
		}
		return errorJson(this.message);
	}
	
	/**
	 * 当转化json失败时，直接返回一个错误信息
	 * @createTime 2017年1月2日 下午8:14:42
	 * @param message
	 * @return
	 */
	public static String errorJson(String message) {
		return errorInfo(message).toJson();
	}
	
	public static ResponseInfo errorInfo(String message) {
		ResponseInfo info = new ResponseInfo();
		info.message=message;
		info.success=false;
		info.info=null;
		return info;
	}
	
	public static String errorJson(String message, String code) {
		return errorInfo(message, code).toJson();
	}
	
	public static ResponseInfo errorInfo(String message, String code) {
		ResponseInfo info = new ResponseInfo();
		info.message=message;
		info.success=false;
		info.info=null;
		info.code = code;
		return info;
	}
	
	public static String errorJson(String message, String code, Object info) {
		return errorInfo(message, code, info).toJson();
	}
	
	public static ResponseInfo errorInfo(String message, String code, Object info) {
		ResponseInfo ri = new ResponseInfo();
		ri.message = message;
		ri.success = false;
		ri.info = info;
		ri.code = code;
		return ri;
	}
	
	public static ResponseInfo successInfo(Object info) {
		ResponseInfo ri = new ResponseInfo();
		ri.success = true;
		ri.info = info;
		return ri;
	}
	
/*	public static ResponseInfo successInfo(List<?> list, Page<?> page) {
		ResponseInfo ri = new ResponseInfo();
		ri.success = true;
		Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("count", page.getCount());
		infoMap.put("data", list);
		ri.info = infoMap;
		return ri;
	}*/
	
}