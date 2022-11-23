package com.esprit.examen.services;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.CategorieProduitRepository;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProduitServiceImpl implements IProduitService {

	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	StockRepository stockRepository;
	@Autowired
	CategorieProduitRepository categorieProduitRepository;

	@Override
	public List<Produit> retrieveAllProduits() {
		List<Produit> produits = (List<Produit>) produitRepository.findAll();
		for (Produit produit : produits) {
			log.info(" Produit : " + produit);
		}
		return produits;
	}

	@Transactional
	public Produit addProduit(Produit p, Long idCategorieProduit, Long idStock) {
		CategorieProduit categorieProduit = categorieProduitRepository.findById(idCategorieProduit).orElse(null);
		Stock stock = stockRepository.findById(idStock).orElse(null);
		p.setCategorieProduit(categorieProduit);
		p.setStock(stock);
		p.setDateCreation(new Date());
		produitRepository.save(p);
		return p;
	}

	

	@Override
	public void deleteProduit(Long produitId) {
		produitRepository.deleteById(produitId);
	}

	@Override
	public Produit updateProduit(Produit p, Long idCategorieProduit, Long idStock) {
		CategorieProduit categorieProduit = categorieProduitRepository.findById(idCategorieProduit).orElse(null);
		Stock stock = stockRepository.findById(idStock).orElse(null);
		Produit produit = produitRepository.findById(p.getIdProduit()).orElse(null);
		log.info("categorieProduit: "+categorieProduit);
		log.info("stock: "+stock);
		p.setStock(stock);
		p.setCategorieProduit(categorieProduit);
		p.setDateCreation(produit.getDateCreation());
		p.setDateDerniereModification(new Date());
		return produitRepository.save(p);
	}

	@Override
	public Produit retrieveProduit(Long produitId) {
		Produit produit = produitRepository.findById(produitId).orElse(null);
		log.info("produit :" + produit);
		return produit;
	}

	@Override
	public void assignProduitToStock(Long idProduit, Long idStock) {
		Produit produit = produitRepository.findById(idProduit).orElse(null);
		Stock stock = stockRepository.findById(idStock).orElse(null);
		produit.setStock(stock);
		produitRepository.save(produit);

	}


}