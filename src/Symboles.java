import java.math.*;
public class Symboles {
	private String nom ;  // champ contient le nom d'un varibale ou une fonction standard
	private double valeur ;  // champ pour la valeur de varibale
	
	
	public Symboles(String nom,double valeur) {  // une premier constricteur pour les variables 
		this.nom=nom;
		this.valeur=valeur;
	}
	
	public Symboles(String nom) {  // une deuxiem constricteur pour les fonctions standards  
		this.nom=nom;
	}
	
	public  void Set_Valeur(double valeur){ // pour modifier la valeur du variable
		 this.valeur=valeur;
	}
	
	public  String Get_Nom(){  // pour recuperer le nom de fonction standard ou un variable
		return this.nom;
	}
	
	public double  calcule(double valeur) throws  Valeur_positive // methode pour clasuler le resultat du fonction 
    {
		switch (nom) {
		
		case "sin":
		return Math.sin(valeur*Math.PI/180); // convertir de degré vers radian
		
		
		case "cos":
	    return Math.cos(valeur*Math.PI/180); // convertir de degré vers radian
	    
	    
		case "log":
	    if(valeur<=0) throw new  Valeur_positive(); // les valeur de log doit etre >0 sinon exception
		return Math.log(valeur);
		
		
		case "sqrt":
		if(valeur<0) throw new  Valeur_positive();// les valeur de sqrt doit etre >=0 sinon exception
		return Math.sqrt(valeur);
		
		case "abs":
	    return Math.abs(valeur);
	    
	    
		case "tan":
		return Math.tan(valeur*Math.PI/180); // convertir de degré vers radian
	    
			
		default :
	    return 0;	
		}
       
    }
	
	
	public double Get_Valeur() {  // pour recuperer la valeur du variable
		return valeur;
		
	}
	
}
