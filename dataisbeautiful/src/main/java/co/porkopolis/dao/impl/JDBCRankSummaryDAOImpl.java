package co.porkopolis.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.porkopolis.dao.RankSummaryDAO;
import co.porkopolis.dao.impl.mappers.RankSummaryRowMapper;
import co.porkopolis.model.RankSummary;

@Component
public class JDBCRankSummaryDAOImpl implements RankSummaryDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void insert(RankSummary summary) throws DataAccessException{
		String sql = "INSERT INTO RANKSUMMARY" + "(ID, NAME, WINS, LOSES, DIVISION, LEAGUEPOINTS, RANK)"
				+ "VALUES( ?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(sql, new Object[] { summary.getId(), summary.getName(), summary.getWins(),
				summary.getLosses(), summary.getDivision(), summary.getLeaguePoints(), summary.getRank() });
	}

	@Override
	public RankSummary findByName(String name) throws EmptyResultDataAccessException {
		RankSummary summary = null;
		String sql = "SELECT * FROM RANKSUMMARY WHERE NAME = ?";
		summary = (RankSummary) jdbcTemplate.queryForObject(sql, new Object[] { name }, new RankSummaryRowMapper());
		return summary;
	}

	@Override
	public RankSummary findById(long id) throws DataAccessException{
		RankSummary summary = null;
		String sql = "SELECT * FROM RANKSUMMARY WHERE ID = ?";
		summary = (RankSummary) jdbcTemplate.queryForObject(sql, new Object[] { id }, new RankSummaryRowMapper());
		return summary;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
