package co.porkopolis.requests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.porkopolis.dao.BasicSummonerDAO;
import co.porkopolis.dao.RankSummaryDAO;
import co.porkopolis.model.RankSummary;
import co.porkopolis.model.RedditFeed;
import co.porkopolis.model.RedditFeedItem;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.league.dto.League;
import net.rithms.riot.api.endpoints.league.dto.LeagueEntry;
import net.rithms.riot.api.endpoints.matchlist.dto.MatchList;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Region;

public class Request {

	private final Logger log = LoggerFactory.getLogger(Request.class);

	public final String API_KEY = "7d62f8ef-aceb-4d1d-b2ba-9f7946f0aa29";

	@Autowired
	BasicSummonerDAO basicSummonerDAO;

	@Autowired
	RankSummaryDAO rankSummaryDAO;

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
				if (summoner != null) {
					summoner.setName(summoner.getName().replace(" ", ""));
					basicSummonerDAO.insert(summoner);
				}
			} catch (RiotApiException e) {
				e.printStackTrace();
			}
		}
		return summoner;
	}

	public LeagueEntry getLeagueEntry(long id) {
		LeagueEntry entry = null;
		List<League> leagues = null;
		String rank = null;

		try {
			leagues = api.getLeagueBySummoner(Region.NA, id);

			List entries = leagues.get(0).getEntries();

			for (int i = 0; i < entries.size(); i++) {
				entry = (LeagueEntry) entries.get(i);
				if (Long.parseLong(entry.getPlayerOrTeamId()) == id) {
					rank = leagues.get(0).getTier() + "_" + entry.getDivision();
					return entry;
				}
			}

		} catch (RiotApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return entry;
	}

	public String getSummonerRank(long id) {

		List<League> leagues = null;
		String rank = null;

		try {
			leagues = api.getLeagueBySummoner(Region.NA, id);

			List entries = leagues.get(0).getEntries();

			LeagueEntry entry = null;
			for (int i = 0; i < entries.size(); i++) {
				entry = (LeagueEntry) entries.get(i);
				if (Long.parseLong(entry.getPlayerOrTeamId()) == id) {
					rank = leagues.get(0).getTier() + "_" + entry.getDivision();
					return rank;
				}
			}

		} catch (RiotApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MatchList matches = null;
		try {
			matches = api.getMatchList(Region.NA, id);
		} catch (RiotApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rank;

	}

	public RankSummary getRankSummary(long id) {
		RankSummary summary = null;

		try {
			return rankSummaryDAO.findById(id);
		} catch (DataAccessException e) {

		}

		if (summary == null) {

			LeagueEntry entry = null;
			List<League> leagues = null;
			String rank = null;

			try {
				leagues = api.getLeagueBySummoner(Region.NA, id);

				List entries = leagues.get(0).getEntries();

				for (int i = 0; i < entries.size(); i++) {
					entry = (LeagueEntry) entries.get(i);
					if (Long.parseLong(entry.getPlayerOrTeamId()) == id) {
						rank = leagues.get(0).getTier() + "_" + entry.getDivision();
						rank = rank.toLowerCase();
						summary = new RankSummary(id, entry.getPlayerOrTeamName(), entry.getWins(), entry.getLosses(),
								entry.getDivision(), entry.getLeaguePoints(), rank);
						rankSummaryDAO.insert(summary);
						return summary;
					}
				}

			} catch (RiotApiException e) {
				return null;
			}

		}

		return summary;
	}

	public RedditFeed requestRedditFeed() throws JsonProcessingException, MalformedURLException, IOException {

		try {
			RedditFeed result = new RedditFeed();
			ObjectMapper mapper = new ObjectMapper();
			URL url = new URL("https://www.reddit.com/r/leagueoflegends/top.json?limit=5");
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-Agent", "LeagueDashboardBot v0.1.1");
			JsonNode root = mapper.readTree(conn.getInputStream());
			JsonNode urlNode = root.get("data").get("children");
			List<JsonNode> children = urlNode.findValues("url");
			List<JsonNode> title = urlNode.findValues("title");
			List<JsonNode> permalink = urlNode.findValues("permalink");

			int itemIndex = 0;

			for (int i = 0; i < children.size(); i++) {
				if (!children.get(i).asText().contains("i.redditmedia.com")) {

					result.add(new RedditFeedItem(0, children.get(i).asText(), title.get(itemIndex).asText(), "",  "https://www.reddit.com" + permalink.get(itemIndex).asText()),
							itemIndex);
					itemIndex++;

					// https://i.redditmedia.com/ip9a43enwQkinfW_gS6aIeo7cygoayJMqYrQHVo9FJE.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=960&amp;s=895b2a849be47f8ca67d72a0fc2b954e
				}
			}

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}