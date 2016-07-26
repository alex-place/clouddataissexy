package co.porkopolis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.porkopolis.model.BasicSummoner;
import co.porkopolis.model.SummonerName;
import co.porkopolis.requests.Request;
import co.porkopolis.utils.AttributeConstants;
import co.porkopolis.utils.FileConstants;

@Controller
public class FrontPageController {

	private Request request;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public FrontPageController() {
		request = new Request();
	}

	@RequestMapping(value = { "/", "/" + FileConstants.HOME_PAGE }, method = RequestMethod.GET)
	public String greetingForm(Model model) {
		model.addAttribute(AttributeConstants.SUMMONER_NAME, new SummonerName());
		return FileConstants.HOME_PAGE;
	}

	@RequestMapping(value = { "/", "/" + FileConstants.HOME_PAGE }, method = RequestMethod.POST)
	public String greetingSubmit(@ModelAttribute SummonerName summonerName, Model model) {
		BasicSummoner summoner = request.requestBasicSummoner(summonerName.getName());
		model.addAttribute(AttributeConstants.BASIC_SUMMONER, summoner);
		
		System.out.println("Creating summoner table");

		jdbcTemplate.execute("DROP TABLE IF EXISTS SUMMONERS");
		jdbcTemplate.execute("CREATE TABLE SUMMONERS"
				+ "(ID INT NOT NULL,NAME varchar(255) NOT NULL,PROFILEICONID INT NOT NULL,SUMMONERLEVEL INT NOT NULL,REVISIONDATE BIGINT NOT NULL,UPDATED TIMESTAMP NOT NULL default now() on update now(),PRIMARY KEY (ID))"

		);
		
		System.out.println("Summoner table created!");
		
		
		return FileConstants.SUMMONER_DISPLAY;
	}

}
