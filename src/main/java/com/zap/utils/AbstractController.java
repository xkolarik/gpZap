package com.zap.utils;

import org.springframework.http.ResponseEntity;

public class AbstractController {
	
	public final <E> ResponseEntity<?> checkHttpResponseGET(final E pObj) {
		return ResponseEntity.ok(pObj);
	}

}
