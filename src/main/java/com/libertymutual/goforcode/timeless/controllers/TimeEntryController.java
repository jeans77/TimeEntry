package com.libertymutual.goforcode.timeless.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.libertymutual.goforcode.timeless.models.TimeEntryItem;
import com.libertymutual.goforcode.timeless.services.TimeEntryRepository;

@Controller
@RequestMapping("/")
public class TimeEntryController {
	private String buttonChoice;
	private TimeEntryRepository repository;
	private String date = "yyyy-MM-dd";
	private String status;
	private boolean hasSubmittedItems;

	

	public TimeEntryController(TimeEntryRepository repository) {
		this.repository = repository;
	}

	@GetMapping("")
	public String redirectToApplication() {
		return "redirect:/timeless";
	}

	@GetMapping("timeless")
	public ModelAndView displayList() {
		ModelAndView mv = new ModelAndView("timeless/default");
		List<TimeEntryItem> timeEntryItems = repository.getAll();
		ArrayList<TimeEntryItem> submittedItems = new ArrayList<TimeEntryItem>();
		hasSubmittedItems = false;
		
		TimeEntryItem item = new TimeEntryItem();

		for (int i= 0; i < timeEntryItems.size(); i++) {
			
			if (timeEntryItems.get(i).getStatus().equals("S")) {
				hasSubmittedItems = true;
//				item = timeEntryItems.get(i);
				submittedItems.add(timeEntryItems.get(i));
			}
			
			if (timeEntryItems.get(i).getStatus().equals("U")) {

				mv.addObject("date", timeEntryItems.get(i).getDate());
				mv.addObject("monday", timeEntryItems.get(i).getMonday());
				mv.addObject("tuesday", timeEntryItems.get(i).getTuesday());
				mv.addObject("wednesday", timeEntryItems.get(i).getWednesday());
				mv.addObject("thursday", timeEntryItems.get(i).getThursday());
				mv.addObject("friday", timeEntryItems.get(i).getFriday());
				mv.addObject("total", timeEntryItems.get(i).getTotal());
			}

		}
		mv.addObject("hasSubmittedItems", hasSubmittedItems);	
		mv.addObject("timeEntryItems", submittedItems);
		return mv;	
	}

	@PostMapping("timeless")
	public ModelAndView update(TimeEntryItem item, String button) {
		
		ModelAndView mv = new ModelAndView("redirect:/timeless");
				System.out.println("Controller Post Update Starting");
			List<TimeEntryItem> timeEntryItems = null;

			if (button.equals("update")) {
				buttonChoice = "update";
			}else {
				buttonChoice = "submit";
			}
			
			repository.update(item, buttonChoice);
			return mv;
	}
}
