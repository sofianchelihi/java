



import java.util.*;
import java.util.regex.*; 

public class class_pour_tester_le_tp {
	
	
	                 /* methode static pour v�rifier c'est le commande est valide ou pas 
	                   et si la forme de commande est juste alors lamethode retorner liste contient la commande et le variable et l'expression 
	                   sino exception  
	                  */
	
	               /*  par exemple  :
	                       let x = 5+6
	                       alor     liste = [ let , x , 5+6 ]
	                       
	                       
	                       
	                       print 5*x 
	                       alors liste = [ print , 5*x ] 
	                       
	                       
	                       end
	                       alors liste = [ end ]
	                */
	
	
	public static ArrayList<String> get_command(String commande) throws Commande_non_valide,Exception{
		ArrayList<String> com = new ArrayList<String>();
	    String expr1="";
	    String vrbl1="";
		if (Pattern.matches("\\ *[lL][eE][tT]\\ +\\w+\\ *[=]\\ *[\\w,+,\\-,*,/,^,),\\ ,(]*\\ *$",commande)) {   
			
			/*  si le commande est let est sous form juste alors la methode retorner 
			une liste de string contient la commande et le nom de variable et l'expression sinon exception */
			
			commande= commande.replaceAll("\\s", "");
		    com.add("let");
		    int i=3;
			while(commande.charAt(i)!='=') {
				vrbl1=vrbl1+commande.charAt(i);
				i++;
			}
			com.add(vrbl1);
			i++;
		
			while (i<commande.length()) {
				expr1=expr1+commande.charAt(i);
				i++;
			}
			com.add(expr1);	
			
		}else if (Pattern.matches("\\ *[pP][rR][iI][nN][tT]\\ +[\\w,+,\\-,*,/,^,),\\ ,(]+\\ *$",commande)) {
			
			
			/*  si le commande est print est sous form juste alors la methode retorner 
			une liste de string contient la commande et l'expression sinon exception */
			
			
			commande= commande.replaceAll("\\s", "");
			com.add("print");
			int j =5;
			while (j<commande.length()) {
				expr1=expr1+commande.charAt(j);
				j++;
			}
			com.add(expr1);
			
		}
		else if (Pattern.matches("\\ *[eE][nN][Dd]\\ *$",commande)) {
			
			
			/*  si le commande est end est sous form juste alors la methode retorner 
			une liste de string contient la commande sinon exception */
			commande= commande.replaceAll("\\s", "");
			com.add("end");
		}else {
			throw new Commande_non_valide();   // sinon exception commande non valide (fausse)
		}
		
		
		
		return com;
	}
	
	
   public static void main(String[] args) {
	   
	          Table_des_symboles t= new Table_des_symboles();  // tableau de symboles
	          Scanner sc = new Scanner(System.in);
	          String boucle="";
	          String vrbl="";
	          String eq="";
	          String exprs="";
	          String err="";
	          while (!boucle.equals("end")){
	        	  try {
	        		  
	        		  
	        		  
	        		  System.out.print(">   ");
		        	  boucle = sc.nextLine();
		        	  ArrayList<String> main =  get_command(boucle);
		        	  
		        		 
		        		  
		        	 if (main.size()==3) {  // let
		  
		        		  vrbl =main.get(1);
		        		  exprs = main.get(2);
		        		  Let let=new Let(exprs,vrbl);
		        		  let.executer(t);
		        	 }  
		        		  
		        	  if (main.size()==2) {   // print
		        		  exprs = main.get(1);
		        		  Print print = new Print(exprs);
		        		  print.executer(t);
		        		  
		        	  }
		        	  if (main.size()==1) {  // end
		        		  System.out.println("Fin du programme.");
		        		  break;  
		        	  } 
		        	  
		        	  
		        	  System.out.println(" ");
	        	  }
	        	  catch(Commande_non_valide e8) {
	        	    System.out.println("Erreur : "+"commande fausse .");
		   	        System.out.println(" ");
		   	       }
	        	  catch(Nom_var_non_valide e11) {
		   	        System.out.println("Erreur : "+"Nom de variable non valide .");
		   	        System.out.println(" ");
		   	       }
	        	  catch(Expression_vide e1) {
	   	    	   System.out.println("Erreur : "+"votre expr�ssion est vide .");
	   	    	   System.out.println(" ");
	   	           }
	   	           catch(Parametre_monq e2) {
	   	           System.out.println("Erreur : "+"parenth�se vide ou fonction avec une param�tre vide .");
	   	           System.out.println(" ");
	   	          }
	              catch(Parenthese_Ouv e3) {
	               System.out.println("Erreur : "+"parenth�se ouvrant manquant .");
	               System.out.println(" ");
	   	          }
	              catch(Parenthese_Fer e4) {
	            	System.out.println("Erreur : "+"parenth�se fermant manquant .");
	            	System.out.println(" ");
	   	          }
	   	          catch(Valeur_positive e5) {
	   	        	System.out.println("Erreur : "+"param�tre de log et de sqrt doit etre positive .");
	   	        	System.out.println(" ");
	   	          }
	   	          catch(var_Fonc_n_exist_pas e6) {
	   	        	System.out.println("Erreur : "+"variable ou fontion non declarer :  "+e6.get_err()+" .");
	   	        	System.out.println(" ");
	   	          }
	   	          catch(Valeur_tres_grand e7) {
	   	        	System.out.println("Erreur : "+"le resultat est tres grand que la valeur max du double .");
	   	        	System.out.println(" ");
	   	          }
	   	          catch(ArithmeticException e9) {
	   	        	System.out.println("Erreur : "+"division par 0 .");
	   	        	System.out.println(" ");
	   	          }
	   	          catch(Exception e10) {
	   	        	System.out.println("Erreur : "+"Expression erron�e .");
	   	        	System.out.println(" ");
	   	          }
	        	  	  
	        	  
	        	  
	          }  
	         
	        
	     
   }
}