package co.porkopolis.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.porkopolis.model.BasicSummoner;

public class BasicSummonerRowMapper implements RowMapper<Object> {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

		BasicSummoner summoner = new BasicSummoner();

		summoner.setId(rs.getInt("ID"));
		summoner.setName(rs.getString("NAME"));
		summoner.setProfileIconId(rs.getInt("PROFILEICONID"));
		summoner.setRevisionDate(rs.getLong("REVISIONDATE"));
		summoner.setSummonerLevel(rs.getInt("SUMMONERLEVEL"));

		return summoner;

	}

}
