package co.porkopolis.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import co.porkopolis.dao.BasicSummonerDAO;
import co.porkopolis.model.BasicSummoner;

public class JDBCBasicSummonerDAOImpl implements BasicSummonerDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void insert(BasicSummoner summoner) {
		String sql = "INSERT INTO SUMMONER" + "(ID, NAME, PROFILEICONID, SUMMONERLEVEL, REVISIONDATE)"
				+ "VALUES( ?, ?, ?, ?, ?)";

		jdbcTemplate.update(sql, new Object[] { summoner.getId(), summoner.getName(), summoner.getProfileIconId(),
				summoner.getSummonerLevel(), summoner.getRevisionDate() });
	}

	@Override
	public BasicSummoner findByName(String name) {
		BasicSummoner summoner = null;
		String sql = "SELECT * FROM SUMMONERS WHERE NAME = ?";
		summoner = (BasicSummoner) jdbcTemplate.queryForObject(sql, new Object[] { name },
				new BasicSummonerRowMapper());
		return summoner;
	}

	@Override
	public BasicSummoner findById(int id) {
		BasicSummoner summoner = null;
		String sql = "SELECT * FROM SUMMONERS WHERE ID = ?";
		summoner = (BasicSummoner) jdbcTemplate.queryForObject(sql, new Object[] { id }, new BasicSummonerRowMapper());
		return summoner;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
