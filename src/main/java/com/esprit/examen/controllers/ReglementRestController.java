package com.esprit.examen.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.examen.entities.Reglement;
import com.esprit.examen.services.IReglementService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "Gestion des reglements")
@RequestMapping("/reglement")
public class ReglementRestController {

	@Autowired
	IReglementService reglementService;
	

	// http://localhost:8089/SpringMVC/reglement/add-reglement/{idFacture}
	@PostMapping("/add-reglement/{idFacture}")
	@ResponseBody
	public Reglement addReglement(@RequestBody Reglement r, @PathVariable Long idFacture) {
		Reglement reglement = reglementService.addReglement(r, idFacture);
		return reglement;
	}
	
	// http://localhost:8089/SpringMVC/reglement/retrieve-reglement/8
		@GetMapping("/retrieve-reglement/{reglement-id}")
		@ResponseBody
		public Reglement retrieveReglement(@PathVariable("reglement-id") Long reglementId) {
			return reglementService.retrieveReglement(reglementId);
		}
		
		// http://localhost:8089/SpringMVC/reglement/retrieveReglementByFacture/8
				@GetMapping("/retrieveReglementByFacture/{facture-id}")
				@ResponseBody
				public List<Reglement> retrieveReglementByFacture(@PathVariable("facture-id") Long factureId) {
					return reglementService.retrieveReglementByFacture(factureId);
				}
	
				/*
				 * Chiffre d'affaire ( somme des réglements sur facture active entre deux dates)
				 * 
				 */
				// http://localhost:8089/SpringMVC/reglement/getChiffreAffaireEntreDeuxDate/{startDate}/{endDate}
				@GetMapping(value = "/getChiffreAffaireEntreDeuxDate/{startDate}/{endDate}")
				public float getChiffreAffaireEntreDeuxDate(
						@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
						@PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
                      try
                      {
					return reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);
                      }
                      catch(Exception e)
                      {
                    	  return 0;
                      }
                      }
}
