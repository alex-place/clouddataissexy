package co.porkopolis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.porkopolis.model.SummonerName;
import co.porkopolis.requests.Request;
import co.porkopolis.utils.AttributeConstants;
import co.porkopolis.utils.FileConstants;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;

@Controller
public class FrontPageController {

	@Autowired
	private Request request;

	public FrontPageController() {
		request = new Request();
	}

	@ModelAttribute("summonerName")
	public SummonerName getSummonerName() {
		return new SummonerName();
	}

	@RequestMapping(value = { "/", "/" + FileConstants.SEARCH }, method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute(AttributeConstants.SUMMONER_NAME, new SummonerName());
		return FileConstants.SEARCH;
	}

	@RequestMapping(value = { "/", "/" + FileConstants.SEARCH }, method = RequestMethod.POST)
	public String home(@ModelAttribute SummonerName summonerName, Model model) {
		if (summonerName == null || summonerName.getName().isEmpty()) {
			model.addAttribute(AttributeConstants.ERROR, "Summoner " + summonerName.getName() + " not found.");
			return FileConstants.SEARCH;
		}
		Summoner summoner = request.requestBasicSummoner(summonerName.getName());

		String rank = null;
		if (summoner != null) {
			rank = request.getSummonerRank(summoner.getId());
		}else{
			model.addAttribute(AttributeConstants.ERROR, "Summoner " + summonerName.getName() + " not found.");
			return FileConstants.SEARCH;
		}

		if (rank != null) {
			model.addAttribute(AttributeConstants.SUMMONER_RANK, rank);
		}

		model.addAttribute(AttributeConstants.BASIC_SUMMONER, summoner);

		return FileConstants.DASHBOARD;

	}

	@RequestMapping(value = { "/" + FileConstants.DASHBOARD })
	public String index(@ModelAttribute Summoner summoner, Model model) {
		if (summoner.getName() != null) {
			return FileConstants.SUMMONER_DISPLAY;
		} else {
			return FileConstants.SEARCH;
		}
	}

	@RequestMapping(value = { "/", "/" + FileConstants.GENERAL })
	public String general(Model model) {
		return FileConstants.GENERAL;
	}

	@RequestMapping(value = { "/", "/" + FileConstants.BASIC_TABLE })
	public String basic_table(Model model) {
		return FileConstants.BASIC_TABLE;
	}

	@RequestMapping(value = { "/", "/" + FileConstants.BLANK })
	public String blank(Model model) {
		return FileConstants.BLANK;
	}

	@RequestMapping(value = { "/", "/" + FileConstants.BUTTONS })
	public String buttons(Model model) {
		return FileConstants.BUTTONS;
	}

	@RequestMapping(value = { "/", "/" + FileConstants.CALENDAR })
	public String calendar(Model model) {
		return FileConstants.CALENDAR;
	}

	@RequestMapping(value = { "/", "/" + FileConstants.CHARTJS })
	public String chartjs(Model model) {
		return FileConstants.CHARTJS;
	}

	@RequestMapping(value = { "/", "/" + FileConstants.FORM_COMPONENT })
	public String form_component(Model model) {
		return FileConstants.FORM_COMPONENT;
	}

	@RequestMapping(value = { "/", "/" + FileConstants.GALLERY })
	public String gallery(Model model) {
		return FileConstants.GALLERY;
	}

	@RequestMapping(value = { "/", "/" + FileConstants.LOCK_SCREEN })
	public String lock_screen(Model model) {
		return FileConstants.LOCK_SCREEN;
	}

	@RequestMapping(value = { "/", "/" + FileConstants.LOGIN })
	public String login(Model model) {
		return FileConstants.LOGIN;
	}

	@RequestMapping(value = { "/", "/" + FileConstants.MORRIS })
	public String morris(Model model) {
		return FileConstants.MORRIS;
	}

	@RequestMapping(value = { "/", "/" + FileConstants.PANELS })
	public String panels(Model model) {
		return FileConstants.PANELS;
	}

	@RequestMapping(value = { "/", "/" + FileConstants.RESPONSIVE_TABLE })
	public String responsive_table(Model model) {
		return FileConstants.RESPONSIVE_TABLE;
	}

	@RequestMapping(value = { "/", "/" + FileConstants.TODO_LIST })
	public String todo_list(Model model) {
		return FileConstants.TODO_LIST;
	}

}
