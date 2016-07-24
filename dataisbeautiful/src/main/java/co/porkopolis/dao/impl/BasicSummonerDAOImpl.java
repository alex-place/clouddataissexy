package co.porkopolis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import co.porkopolis.dao.BasicSummonerDAO;
import co.porkopolis.model.BasicSummoner;

public class BasicSummonerDAOImpl implements BasicSummonerDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(BasicSummoner summoner) {
		String sql = "INSERT INTO summoners (ID, NAME, PROFILEICONID, SUMMONERLEVEL, REVISIONDATE)"
				+ "VALUES('51616092','bradelfield','948','30','1469247442000')";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, summoner.getId());
			ps.setString(2, summoner.getName());
			ps.setInt(3, summoner.getProfileIconId());
			ps.setInt(4, summoner.getSummonerLevel());
			ps.setLong(1, summoner.getRevisionDate());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}

		}

	}

	@Override
	public BasicSummoner findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicSummoner findById(int id) {
		String sql = "SELECT * FROM summoners WHERE ID = ?";
		
		Connection conn = null;
		
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			BasicSummoner summoner = null;
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				summoner = new BasicSummoner(
						rs.getInt("ID"),
						rs.getString("SUMMONERNAME"),
						rs.getInt("PROFILEICONID"),
						rs.getInt("SUMMONERLEVEL"),
						rs.getLong("REVISIONDATE")
						);
			}
			rs.close();
			ps.close();
			return summoner;
			
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){}
			}
		}
		
	}

}
