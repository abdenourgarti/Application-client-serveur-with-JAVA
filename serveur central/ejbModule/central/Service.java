package central;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Services")
public class Service implements Serializable {
	@Id
	   private int num;
	   private String nom;
	   private String description;
	
	public Service() {
		super();
		
	}
	public Service(int num, String nom, String description) {
		
		this.num = num;
		this.nom = nom;
		this.description = description;
	}
	public String toString() {
		return "Service [num=" + num + ", nom=" + nom + ", description=" + description + "]";
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
