public class Let implements Commande{
	
	private String expl; // l'expression 
	private String vari;  // le vabiable qui resoit le resultat finale de l'expression
	
	public Let(String exp,String vari) { // constricteur 
		this.expl=exp;
		this.vari=vari;
		
	}
	
	public void executer(Table_des_symboles table_sym) throws Expression_vide,Parametre_monq,Parenthese_Ouv,Parenthese_Fer,Valeur_positive,var_Fonc_n_exist_pas,Valeur_tres_grand,ArithmeticException,Exception,Nom_var_non_valide
    {
		Expression en= new Expression(expl);		
		table_sym.Ajouter_val(vari,en.Evaluer_exp(table_sym));
		System.out.println("Ok");
    }

}
