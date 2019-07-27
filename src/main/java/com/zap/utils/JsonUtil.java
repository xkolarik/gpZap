package com.zap.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonUtil {
	
	public static JsonObject getJsonObject(final Object pObj) {
		JsonObject result = null;
		if (pObj != null) {
			JsonElement el = (JsonElement) pObj;
			if (el.isJsonObject()) {
				result = el.getAsJsonObject();
			}
		}
		return result;
	}
	
	public static JsonArray getJsonArray(final JsonElement pVal) {
		JsonArray result = null;
		if (pVal != null && !pVal.isJsonPrimitive()) {
			JsonElement el = (JsonElement) pVal;
			if (el.isJsonObject()) {
				result = new JsonArray();
				result.add(el);
			} else {
				result = el.getAsJsonArray();
			}
		}
		return result;
	}
	
	public static String getAsString(final JsonElement pVal, final String pKey, final String pCallback) {
		String val = "";
		String aux = "";
		if (pCallback != null) {
			val = pCallback;
		}
		if (pVal != null) {
			JsonElement el = (JsonElement) pVal;
			if (el.isJsonObject()) {
				JsonElement obj = el.getAsJsonObject().get(pKey);
				if (obj != null) {
					if (obj.isJsonArray()) {
						aux = obj.getAsJsonArray().get(0).getAsString();
					} else if (obj.isJsonPrimitive()) {
						aux = obj.getAsString();
					}
				}
			}
		}
		return val;
	}
}
