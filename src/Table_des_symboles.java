import java.util.*;

public class Table_des_symboles {
	private Map< String ,Symboles > map; 
	// on va utiliser une map pour sauvgarder le symboles (variable et fonction standard)
	// le cle c'est le nom de variable ou fonction standard
	// la valeur c'est les objet de type symboles (variable et fonction standard)
	
	public Table_des_symboles()
    {
        map=new HashMap< String,Symboles>();   // initialiser le map avec tout les fonctions standards
        Fonction_ST sin = new Fonction_ST("sin");
        map.put("sin",sin);
        Fonction_ST cos = new Fonction_ST("cos");
        map.put("cos",cos);
        Fonction_ST tan = new Fonction_ST("tan");
        map.put("tan",tan);
        Fonction_ST log = new Fonction_ST("log");
        map.put("log",log);
        Fonction_ST sqrt = new Fonction_ST("sqrt");
        map.put("sqrt",sqrt);
        Fonction_ST abs = new Fonction_ST("abs");
        map.put("abs",abs);
    }
	
	
	
	public boolean verifier_cle(String cle) // v�rifier l'existence d'une cl� dans le map 
    {
        return map.containsKey(cle);
    }
	
	
	
	public Symboles get_valeur(String cle) //On utilisant une cle pour recuperer objet de type symboles (variable et fonction standard) associer a cette cle
    {
        return map.get(cle);
    }
	
	
	public void Ajouter_val(String cle , double val ) throws  Nom_var_non_valide // ajouter une variable dans le map
    {
		if(verifier_cle(cle)) {  // ancien variable alors on le modifier 
			Symboles smb = get_valeur(cle);
			if (smb.getClass().getName()=="Fonction_ST") {
				throw new Nom_var_non_valide();
			}else {
				Variables vr= new Variables(cle,val);
				map.put(cle, vr);
			}
			
		}
		else {  // nouvelle variable 
			Variables vr= new Variables(cle,val);
			map.put(cle, vr);
		}
    }
    
   }
