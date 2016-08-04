package co.porkopolis.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.porkopolis.dao.BasicSummonerDAO;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;

@Component
public class JDBCBasicSummonerDAOImpl implements BasicSummonerDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void insert(Summoner summoner) {
		String sql = "INSERT INTO SUMMONERS" + "(ID, NAME, PROFILEICONID, SUMMONERLEVEL, REVISIONDATE)"
				+ "VALUES( ?, ?, ?, ?, ?)";

		jdbcTemplate.update(sql, new Object[] { summoner.getId(), summoner.getName(), summoner.getProfileIconId(),
				summoner.getSummonerLevel(), summoner.getRevisionDate() });
	}

	@Override
	public Summoner findByName(String name) throws EmptyResultDataAccessException {
		Summoner summoner = null;
		String sql = "SELECT * FROM SUMMONERS WHERE NAME = ?";
		summoner = (Summoner) jdbcTemplate.queryForObject(sql, new Object[] { name }, new BasicSummonerRowMapper());
		return summoner;
	}

	@Override
	public Summoner findById(int id) {
		Summoner summoner = null;
		String sql = "SELECT * FROM SUMMONERS WHERE ID = ?";
		summoner = (Summoner) jdbcTemplate.queryForObject(sql, new Object[] { id }, new BasicSummonerRowMapper());
		return summoner;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
