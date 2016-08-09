package co.porkopolis.dao;

import co.porkopolis.model.RankSummary;

public interface RankSummaryDAO {

	public void insert(RankSummary summary);

	public RankSummary findByName(String name);

	public RankSummary findById(long id);

}
