package com.marketingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketingapp.entities.Lead;
import com.marketingapp.services.LeadServices;
import com.marketingapp.util.EmailService;


@Controller
public class LeadController {
	
	@Autowired 
	private EmailService emailService;
	
	@Autowired 
	private LeadServices leadService;
	
	@RequestMapping("/viewLead")
	public String createNewLead() {
		return "create_lead";
	}
	
	@RequestMapping("/saveLead")
	public String saveOneLead(@ModelAttribute("lead") Lead l, ModelMap model) {
		leadService.saveOneLead(l);
		emailService.sendSimpleMail(l.getEmail(), "Test", "Test Email");
		model.addAttribute("msg","Lead is saved");
		return "create_lead";
	}
	@RequestMapping("/listall")
	public String listAllLeads(ModelMap model) {
		List<Lead> leads = leadService.listAllLeads();
		model.addAttribute("leads", leads);
		return "list_leads";
	}
	@RequestMapping("/deleteLead")
	public String deleteLead(@RequestParam("id") Long id, ModelMap model) {
		leadService.deleteOneLead(id);
		List<Lead> leads = leadService.listAllLeads();
		model.addAttribute("leads", leads);
		return "list_leads";
	}
	@RequestMapping("/updateLead")
	public String updateLead(@RequestParam("id") Long id, Model model){
			Lead lead = leadService.getById(id);
			model.addAttribute("lead",lead);
				return "update_lead";
			}
	@RequestMapping("/updateOneLead")
	public String updaeOneLead(@ModelAttribute("lead") Lead l, ModelMap model) {
		leadService.saveOneLead(l);
		List<Lead> leads = leadService.listAllLeads();
		model.addAttribute("leads", leads);
		return "list_leads";
	}
}
	

