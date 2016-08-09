package co.porkopolis.dao.impl.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.porkopolis.model.RankSummary;


public class RankSummaryRowMapper implements RowMapper<Object> {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

		RankSummary summary = new RankSummary();

		summary.setId(rs.getInt("ID"));
		summary.setName(rs.getString("NAME"));
		summary.setWins(rs.getInt("WINS"));
		summary.setLosses(rs.getInt("LOSES"));
		summary.setDivision(rs.getString("DIVISION"));
		summary.setLeaguePoints(rs.getInt("LEAGUEPOINTS"));
		summary.setRank(rs.getString("RANK"));

		return summary;

	}

}
