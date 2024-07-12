package central;

import java.util.List;

import jakarta.ejb.Remote;

@Remote
public interface IservCent {
	//public Service GetService(String service, int id);
	public void ajouterServeur(serveur serveur);
	//public boolean IsServeurExsit(String nom);
	public int add(int a, int b);
}
