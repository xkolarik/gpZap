package com.zap.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.zap.utils.JsonUtil;

@Service
public class ImoveisServiceImpl implements ImoveisService {

	public static double ZAPVALUE = 3.500;
	public static double ZAPVALUESALE = 600.000;

	public static String ZAPRENTAL = "RENTAL";
	public static String ZAPSALE = "SALE";

	public static double VIVAVALUE = 4.000;
	public static double VIVAVALUESALE = 700.000;

	public static String VIVARENTAL = "RENTAL";
	public static String VIVASALE = "SALE";
	public String zapOrViva = "";

	@Override
	public JsonObject findImoveis(JsonObject params) {
		JsonObject imoveis = new JsonObject();
		try {
			JsonObject jsonResponse = callApi(params);
			if (jsonResponse != null) {
				imoveis = vivaOrZap(jsonResponse, params);
			} else {
				imoveis.addProperty("errorMessage", "Ocorreu um erro");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Ocorreu um erro ao consultar os imoveis", e);
		}
		return imoveis;
	}

	public JsonObject callApi(JsonObject params) throws Exception {
		JsonObject myResponse = new JsonObject();
		String readLine = null;
		URL imoveis = null;
		try {
			imoveis = new URL(
					"http://grupozap-code-challenge.s3-website-us-east-1.amazonaws.com/sources/source-2.json");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HttpURLConnection conexao = (HttpURLConnection) imoveis
				.openConnection();
		conexao.setRequestMethod("GET");
		int responseCode = conexao.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) {

			BufferedReader in = new BufferedReader(new InputStreamReader(
					conexao.getInputStream()));
			StringBuffer response = new StringBuffer();

			while ((readLine = in.readLine()) != null) {
				response.append(readLine);
			}
			in.close();

		}
		return myResponse;
	}

	public JsonObject vivaOrZap(JsonObject myResponse, JsonObject params) {

		JsonObject vivaImoveis = new JsonObject();
		JsonArray arrImoveis = new JsonArray();

		JsonArray collectionNode = JsonUtil.getJsonArray(myResponse
				.get("collection"));

		if (collectionNode != null) {

			for (JsonElement arrEle : collectionNode) {
				JsonObject r = JsonUtil.getJsonObject(arrEle);

				double result = Double.parseDouble(JsonUtil.getAsString(r,
						"price"));
				String businessType = JsonUtil.getAsString(r, "businessType");

				if (result <= ZAPVALUE && businessType == ZAPRENTAL) {
					if (result <= ZAPVALUESALE && businessType == ZAPSALE) {
						vivaImoveis.addProperty("zap", params.getAsString());
						vivaImoveis = callReflect(r);
					} else {
						if (result <= VIVAVALUE && businessType == VIVARENTAL) {
							if (result <= VIVAVALUESALE
									&& businessType == VIVASALE) {
								vivaImoveis.addProperty("viva", params.getAsString());
								vivaImoveis = callReflect(r);
							}
						}
					}
				}
			}
		}
		return myResponse;
	}

	public JsonObject callReflect(JsonObject r) {
		JsonObject lstImoveis = new JsonObject();

		lstImoveis.addProperty("usableAreas",
				JsonUtil.getAsString(r, "usableAreas"));
		lstImoveis.addProperty("listingType",
				JsonUtil.getAsString(r, "listingType"));
		lstImoveis.addProperty("createdAt",
				JsonUtil.getAsString(r, "createdAt"));
		lstImoveis.addProperty("listingStatus",
				JsonUtil.getAsString(r, "listingStatus"));
		lstImoveis.addProperty("id", JsonUtil.getAsString(r, "id"));
		lstImoveis.addProperty("parkingSpaces",
				JsonUtil.getAsString(r, "parkingSpaces"));
		lstImoveis.addProperty("updatedAt",
				JsonUtil.getAsString(r, "updatedAt"));
		lstImoveis.addProperty("owner", JsonUtil.getAsString(r, "owner"));

		lstImoveis.addProperty("images", JsonUtil.getAsString(r, "images"));

		lstImoveis.addProperty("address", JsonUtil.getAsString(r, "address"));
		lstImoveis.addProperty("neighborhood",
				JsonUtil.getAsString(r, "neighborhood"));
		lstImoveis.addProperty("geoLocation",
				JsonUtil.getAsString(r, "geoLocation"));
		lstImoveis.addProperty("precision",
				JsonUtil.getAsString(r, "precision"));
		lstImoveis.addProperty("location", JsonUtil.getAsString(r, "location"));
		lstImoveis.addProperty("lon", JsonUtil.getAsString(r, "lon"));
		lstImoveis.addProperty("let", JsonUtil.getAsString(r, "let"));

		lstImoveis.addProperty("bathrooms",
				JsonUtil.getAsString(r, "bathrooms"));
		lstImoveis.addProperty("bedrooms", JsonUtil.getAsString(r, "bedrooms"));
		lstImoveis.addProperty("pricingInfos",
				JsonUtil.getAsString(r, "pricingInfos"));
		lstImoveis.addProperty("yearlyIptu",
				JsonUtil.getAsString(r, "yearlyIptu"));
		lstImoveis.addProperty("price", JsonUtil.getAsString(r, "price"));
		lstImoveis.addProperty("businessType",
				JsonUtil.getAsString(r, "businessType"));
		lstImoveis.addProperty("monthlyCondoFee",
				JsonUtil.getAsString(r, "monthlyCondoFee"));
		return lstImoveis;
	}
}
