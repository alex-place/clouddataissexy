package co.porkopolis.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

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

	@Autowired
	BasicSummonerDAO basicSummonerDAO;

	@Autowired
	RiotApi api;

	public Summoner requestBasicSummoner(String name) {
		Summoner summoner = null;

		try {
			summoner = basicSummonerDAO.findByName(name);
		} catch (EmptyResultDataAccessException e) {
			// Do nothing
		}
		
		if (summoner == null) {

			try {
				summoner = api.getSummonerByName(Region.NA, name);
				basicSummonerDAO.insert(summoner);

			} catch (RiotApiException e) {
				e.printStackTrace();
			}
		}
		return summoner;
	}

}