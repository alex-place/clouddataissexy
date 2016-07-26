package co.porkopolis.requests;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import co.porkopolis.dao.BasicSummonerDAO;
import co.porkopolis.model.BasicSummoner;

public class Request {

	private final Logger log = LoggerFactory.getLogger(Request.class);

	public final String API_KEY = "7d62f8ef-aceb-4d1d-b2ba-9f7946f0aa29";

	// Summoner name goes between these and the api key after
	public final String REQUEST_BASIC_SUMMONER_PART_1 = "https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/";
	public final String REQUEST_BASIC_SUMMONER_PART_2 = "?api_key=";

	// @Autowired
	// JdbcTemplate jdbcTemplate;

	@Autowired
	BasicSummonerDAO basicSummonerDAO;

	public BasicSummoner requestBasicSummoner(String name) {
		BasicSummoner summoner = null;

		summoner = getBasicSummonerFromDAO(name);

		if (summoner == null) {
			// Not found in db
			summoner = getBasicSummonerJSON(name);
		}

		return summoner;
	}

	public BasicSummoner getBasicSummonerJSON(String name) {
		RestTemplate template = new RestTemplate();
		String mes = "https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/" + name
				+ "?api_key=7d62f8ef-aceb-4d1d-b2ba-9f7946f0aa29";
		HashMap<String, Object> map = template.getForObject(mes, HashMap.class);
		log.info(map.toString());
		return mapToBasicSummoner(map, name);
	}

	public BasicSummoner getBasicSummonerFromDAO(String name) {
		BasicSummoner summoner = null;

		try {
			summoner = basicSummonerDAO.findByName(name);
		} catch (Exception e) {

		}

		return summoner;
	}

	public BasicSummoner mapToBasicSummoner(HashMap map, String name) {
		HashMap realMap = (HashMap) map.get(name);
		BasicSummoner summoner = new BasicSummoner((int) realMap.get("id"), (String) realMap.get("name"),
				(int) realMap.get("profileIconId"), (int) realMap.get("summonerLevel"),
				(long) realMap.get("revisionDate"));

		basicSummonerDAO.insert(summoner);

		return summoner;
	}

}