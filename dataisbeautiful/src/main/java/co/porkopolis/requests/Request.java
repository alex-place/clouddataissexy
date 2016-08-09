package co.porkopolis.requests;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import co.porkopolis.dao.BasicSummonerDAO;
import co.porkopolis.dao.RankSummaryDAO;
import co.porkopolis.model.RankSummary;
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

}