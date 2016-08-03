package co.porkopolis.dao;

import org.springframework.dao.EmptyResultDataAccessException;

import net.rithms.riot.api.endpoints.summoner.dto.Summoner;

public interface BasicSummonerDAO {

	public void insert(Summoner summoner);

	public Summoner findByName(String name) throws EmptyResultDataAccessException;

	public Summoner findById(int id);

}
