public class Print implements Commande{

    private String expp; // pour enregistrer l'exprestion
    
	public Print(String expp ) {  // constricteur
		this.expp=expp;
		
	}
	
	public void executer(Table_des_symboles table_sym1) throws Expression_vide,Parametre_monq,Parenthese_Ouv,Parenthese_Fer,Valeur_positive,var_Fonc_n_exist_pas,Valeur_tres_grand,ArithmeticException,Exception
    {
		Expression enp= new Expression(expp);
		System.out.println("La valeur est : "+enp.Evaluer_exp(table_sym1)); // afficher le resultat finale de l'expression
    }
}
