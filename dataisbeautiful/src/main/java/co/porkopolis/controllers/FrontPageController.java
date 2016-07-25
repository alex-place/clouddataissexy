package co.porkopolis.controllers;

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
		return FileConstants.SUMMONER_DISPLAY;
	}

}
