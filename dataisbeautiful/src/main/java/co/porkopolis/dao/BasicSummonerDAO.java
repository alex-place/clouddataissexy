package co.porkopolis.dao;

import net.rithms.riot.api.endpoints.summoner.dto.Summoner;

public interface BasicSummonerDAO {

	public void insert(Summoner summoner);

	public Summoner findByName(String name);

	public Summoner findById(int id);

}
