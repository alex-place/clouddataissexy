package co.porkopolis.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import co.porkopolis.dao.BasicSummonerDAO;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Region;

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

	@Autowired
	RiotApi api;

	public Summoner requestBasicSummoner(String name) {
		Summoner summoner = null;
		try {
			summoner = api.getSummonerByName(Region.NA, name);
		} catch (RiotApiException e) {
			e.printStackTrace();
		}
		return summoner;
	}

	// public Summoner requestBasicSummoner(String name) {
	// Summoner summoner = null;
	//
	// summoner = getBasicSummonerFromDAO(name);
	//
	// if (summoner == null) {
	// // Not found in db
	// try {
	// summoner = getBasicSummonerJSON(name);
	// } catch (Exception e) {
	// // do nothing
	// }
	//
	// }
	//
	// return summoner;
	// }
	//
	// public Summoner getBasicSummonerJSON(String name) {
	// RestTemplate template = new RestTemplate();
	// String mes = "https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/" +
	// name
	// + "?api_key=7d62f8ef-aceb-4d1d-b2ba-9f7946f0aa29";
	//
	// HashMap<String, Object> map = null;
	//
	// try {
	// map = template.getForObject(mes, HashMap.class);
	// } catch (HttpClientErrorException e) {
	// e.printStackTrace();
	// }
	//
	// log.info(map.toString());
	// return mapToBasicSummoner(map, name);
	// }
	//
	// public Summoner getBasicSummonerFromDAO(String name) {
	// Summoner summoner = null;
	//
	// try {
	// summoner = basicSummonerDAO.findByName(name);
	// } catch (Exception e) {
	//
	// }
	//
	// return summoner;
	// }

}