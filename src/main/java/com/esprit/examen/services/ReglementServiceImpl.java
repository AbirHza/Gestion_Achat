package com.esprit.examen.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.ReglementRepository;

@Service
public class ReglementServiceImpl implements IReglementService {

	@Autowired
	FactureRepository factureRepository;
	@Autowired
	ReglementRepository reglementRepository;
	@Override
	public List<Reglement> retrieveAllReglements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reglement addReglement(Reglement r, Long idFacture) {
		Facture facture = factureRepository.findById(idFacture).orElse(null);
		List<Reglement> reglementsDejaFait=retrieveReglementByFacture(idFacture);
		float montantDejaPaye=0;
		float montanttotalPaye=0;
		for (int i = 0; i < reglementsDejaFait.size(); i++) {
			montantDejaPaye=+reglementsDejaFait.get(i).getMontantPaye();
		}
		System.out.println("montantDejaPaye: "+montantDejaPaye);
		r.setFacture(facture);
		System.out.println("montantPaye: "+r.getMontantPaye());
		montanttotalPaye=montantDejaPaye+r.getMontantPaye();
		System.out.println("montanttotalPaye: "+montanttotalPaye);
		r.setMontantRestant(facture.getMontantFacture()-montanttotalPaye);
		System.out.println("MontantRestant: "+r.getMontantRestant());

		if(montanttotalPaye==facture.getMontantFacture())
        {
        	r.setPayee(true);	
        }
		
        r.setDateReglement(new Date());
        reglementRepository.save(r);
		return r;
	}

	@Override
	public Reglement retrieveReglement(Long id) {
		Reglement reglement = reglementRepository.findById(id).orElse(null);
		
		return reglement;
	}

	@Override
	public List<Reglement> retrieveReglementByFacture(Long idFacture) {
		Facture facture = factureRepository.findById(idFacture).orElse(null);
		List<Reglement> reglements= reglementRepository.retrieveReglementByFacture(facture);
		return reglements;
	}

	@Override
	public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate) {
		return reglementRepository.getChiffreAffaireEntreDeuxDate( startDate, endDate);
	}

}
