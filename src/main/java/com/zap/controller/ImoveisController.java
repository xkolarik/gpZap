package com.zap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.zap.service.ImoveisService;
import com.zap.utils.AbstractController;

@RestController
@RequestMapping("/api")
public class ImoveisController extends AbstractController {
	
  @Autowired
  private ImoveisService imoveisService;

	
  @RequestMapping(value = {"/find/{id_imob}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public final ResponseEntity<?> findImoveis(@RequestParam(value = "id_imob", required = false) String imob ){
  	 JsonObject params = new JsonObject();
  	 params.addProperty("id_imob", imob);
      return this.checkHttpResponseGET(imoveisService.findImoveis(params).toString());
  }
}
