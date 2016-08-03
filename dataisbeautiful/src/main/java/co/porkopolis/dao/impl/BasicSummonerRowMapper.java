package co.porkopolis.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.rithms.riot.api.endpoints.summoner.dto.Summoner;


public class BasicSummonerRowMapper implements RowMapper<Object> {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

		Summoner summoner = new Summoner();

		summoner.setId(rs.getInt("ID"));
		summoner.setName(rs.getString("NAME"));
		summoner.setProfileIconId(rs.getInt("PROFILEICONID"));
		summoner.setRevisionDate(rs.getLong("REVISIONDATE"));
		summoner.setSummonerLevel(rs.getInt("SUMMONERLEVEL"));

		return summoner;

	}

}
