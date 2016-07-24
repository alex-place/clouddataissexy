package co.porkopolis.requests;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import co.porkopolis.model.BasicSummoner;

public class Request {

	private static final Logger log = LoggerFactory.getLogger(Request.class);

	public static final String API_KEY = "7d62f8ef-aceb-4d1d-b2ba-9f7946f0aa29";

	// Summoner name goes between these and the api key after
	public static final String REQUEST_BASIC_SUMMONER_PART_1 = "https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/";
	public static final String REQUEST_BASIC_SUMMONER_PART_2 = "?api_key=";

	public static BasicSummoner requestBasicSummoner(String name) {
		RestTemplate template = new RestTemplate();
		String mes = "https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/" + name
				+ "?api_key=7d62f8ef-aceb-4d1d-b2ba-9f7946f0aa29";
		HashMap<String, Object> map = template.getForObject(mes, HashMap.class);
		log.info(map.toString());
		return mapToBasicSummoner(map, name);
	}

	private static BasicSummoner mapToBasicSummoner(HashMap map, String name) {
		HashMap realMap = (HashMap) map.get(name);
		BasicSummoner summoner = new BasicSummoner((int) realMap.get("id"), (String) realMap.get("name"),
				(int) realMap.get("profileIconId"), (int) realMap.get("summonerLevel"),
				(long) realMap.get("revisionDate"));
		
		return summoner;
	}

}
