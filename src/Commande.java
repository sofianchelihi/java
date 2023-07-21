public interface Commande  
{
    public void executer(Table_des_symboles table_) throws Expression_vide,Parametre_monq,Parenthese_Ouv,Parenthese_Fer,Valeur_positive,var_Fonc_n_exist_pas,Valeur_tres_grand,ArithmeticException,Exception;
}

