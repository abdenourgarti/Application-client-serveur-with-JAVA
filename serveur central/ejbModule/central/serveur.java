package central;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Serveurs")
public class serveur implements Serializable{
	@Id
		private String nom;
		private String adressIP;
		private int port;
	public String getAdressIP() {
		return adressIP;
	}
	public void setAdressIP(String adressIP) {
		this.adressIP = adressIP;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public serveur() {
		super();		
	}
	public serveur(String nom, String adressIP, int port) {
		
		this.nom = nom;
		this.adressIP = adressIP;
		this.port = port;
	}
	   
}
