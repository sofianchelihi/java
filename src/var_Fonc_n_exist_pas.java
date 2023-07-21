public class var_Fonc_n_exist_pas extends Exception { // exception pour une variable au fonction standars n'exist pas dons l'expression
	
	private String symb;  // pour enregistrer la fonction standard ou le variable qui n'existent pas dans l'expression
	
	public var_Fonc_n_exist_pas(String symb) {  // constricteur
		this.symb=symb;
	}
	
	
	public String get_err() {  // pour recuperer l'erreur
		return symb;
	}

}
