package co.porkopolis.dao;

import co.porkopolis.model.BasicSummoner;

public interface BasicSummonerDAO {

	public void insert(BasicSummoner summoner);

	public BasicSummoner findByName(String name);

	public BasicSummoner findById(int id);

}
