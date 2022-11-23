package com.esprit.examen.controllers;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.services.IProduitService;

import io.swagger.annotations.Api;


@RestController
@Api(tags = "Gestion des produits")
@RequestMapping("/produit")
public class ProduitRestController {

	@Autowired
	IProduitService produitService;

	// http://localhost:8089/SpringMVC/produit/retrieve-all-produits
	@GetMapping("/retrieve-all-produits")
	@ResponseBody
	public List<Produit> getProduits() {
		List<Produit> list = produitService.retrieveAllProduits();
		return list;
	}

	// http://localhost:8089/SpringMVC/produit/retrieve-produit/8
	@GetMapping("/retrieve-produit/{produit-id}")
	@ResponseBody
	public Produit retrieveRayon(@PathVariable("produit-id") Long produitId) {
		return produitService.retrieveProduit(produitId);
	}

	/* Ajouter en produit tout en lui affectant la catégorie produit et le stock associés */
	// http://localhost:8089/SpringMVC/produit/add-produit/{idCategorieProduit}/{idStock}
	@PostMapping("/add-produit/{idCategorieProduit}/{idStock}")
	@ResponseBody
	public Produit addProduit(@RequestBody Produit p, @PathVariable Long idCategorieProduit, @PathVariable Long idStock) {
		Produit produit = produitService.addProduit(p, idCategorieProduit, idStock);
		return produit;
	}

	// http://localhost:8089/SpringMVC/produit/remove-produit/{produit-id}
	@DeleteMapping("/remove-produit/{produit-id}")
	@ResponseBody
	public void removeProduit(@PathVariable("produit-id") Long produitId) {
		produitService.deleteProduit(produitId);
	}

	// http://localhost:8089/SpringMVC/produit/modify-produit/{idCategorieProduit}/{idStock}
	@PutMapping("/modify-produit/{idCategorieProduit}/{idStock}")
	@ResponseBody
	public Produit modifyProduit(@RequestBody Produit p, @PathVariable("idCategorieProduit") Long idCategorieProduit, @PathVariable("idStock") Long idStock) {
		return produitService.updateProduit(p, idCategorieProduit, idStock);
	}

	/*
	 * Si le responsable magasin souhaite modifier le stock du produit il peut
	 * le faire en l'affectant au stock en question
	 */
	// http://localhost:8089/SpringMVC/produit/assignProduitToStock/1/5
	@PutMapping(value = "/assignProduitToStock/{idProduit}/{idStock}")
	public void assignProduitToStock(@PathVariable("idProduit") Long idProduit, @PathVariable("idStock") Long idStock) {
		produitService.assignProduitToStock(idProduit, idStock);
	}

	/*
	 * Revenu Brut d'un produit (qte * prix unitaire de toutes les lignes du
	 * detailFacture du produit envoyé en paramètre )
	 */
	// http://localhost:8089/SpringMVC/produit/getRevenuBrutProduit/1/{startDate}/{endDate}
/*	@GetMapping(value = "/getRevenuBrutProduit/{idProduit}/{startDate}/{endDate}")
	public float getRevenuBrutProduit(@PathVariable("idProduit") Long idProduit,
			@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
			@PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

		return produitService.getRevenuBrutProduit(idProduit, startDate, endDate);
	}*/

}
