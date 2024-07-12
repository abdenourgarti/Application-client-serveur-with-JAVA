package central;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class servCent implements IservCent {
	@PersistenceContext
	EntityManager em;
	/*public Service GetService(String service, int id) {
		int numService = 0;		
		switch (service) {
	        case "S0": numService = 1; break;
	        case "S1": numService = 2; break;
	        case "S2": numService = 3; break;
	        case "S3": numService = 4; break;
	        case "S4": numService = 5; break;
            case "S5": numService = 6; break;
            case "S6": numService = 7; break;
            case "S7": numService = 8; break;
            case "S8": numService = 9; break;
            case "S9": numService = 10; break;
            case "S10": numService = 11; break;
            case "S11": numService = 12; break;
            case "S12": numService = 13; break;
            case "S13": numService = 14; break;
            case "S14": numService = 15; break;
            case "S15": numService = 16; break;
		}
		return em.find(Service.class, numService);
	}
	public boolean IsServeurExsit(String nom) {
		serveur s = em.find(serveur.class, nom);
		if(s != null) {
			  // le produit a été trouvé
			return true;
		} else {
			  // le produit n'a pas été trouvé
			return false;
		}
	}*/
	public void ajouterServeur(serveur s) {
		em.persist(s);
	}
	public int add(int a, int b){
		return a+b;
	}
}
