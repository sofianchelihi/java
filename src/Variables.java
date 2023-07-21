import java.util.regex.*; 



public class Variables extends Symboles{
	
	
	public Variables(String nom , double valeur ) throws Nom_var_non_valide //  constrecteur pour la class variables  
    {
		super(nom,valeur);
		if (Pattern.matches("^[a-zA-Z_]+[a-zA-Z0-9_]*$",nom)==false || Pattern.matches("[lL][eE][tTE]$",nom)==true  || Pattern.matches("[pP][rR][iI][nN][tT]$",nom)==true) throw new Nom_var_non_valide(); 
		 // lancer une exception si le nom du varibale possède une espace ou commance par un digit ou le nom contient des caractere specieux
    }
	
}
