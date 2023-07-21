import java.util.*;
import java.math.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Expression {

private String exp;
	
	
	
	
	
	public Expression(String exp) throws Parenthese_Ouv , Parenthese_Fer ,Parametre_monq,Expression_vide{   // constricteur  
		exp = exp.replaceAll("\\s", "");  // elever les espaces
		int p=verifier(exp);   
		if (exp.length()==0) throw new Expression_vide();
		if(p==-2) throw new Parametre_monq();
		else if(p==-1) throw new Parenthese_Ouv();
		else if (p==1) throw new Parenthese_Fer();
		this.exp=exp;
	}
	
	
	
	
	
	
	
	
	public  int verifier(String exp) {	       // methode pour v�rifier les patenthes de l'expression
	   Deque<Character> pile = new ArrayDeque<Character>();
		int i=0,j=-1;
		char c;
		while ((i < exp.length())) 
		{
			c = exp.charAt(i);
			if (c == '(') {pile.addFirst(c); j=i;}
	        if ((c == ')') && (!pile.isEmpty())) { pile.removeFirst(); if (i==j+1) return -2;}    //  () ou fonction()
	        else if (pile.isEmpty() && (c == ')') ) return -1; //  manque  ( 
			i++;
		}
		if (pile.isEmpty()) {
			return 0 ;  // l'expression est juste
			}
		else return 1;	// manque ) 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	 
	
		 /* methode pour convertir l'expression de string  vers liste de string pour simplifier l'evaluation de l'expression
		   
		        par exemple : 
		        
		         -5*(sin(4*9))-6/2
		         alors  liste = [ - , 5 , * , ( , sin , ( , 4 , * , 9 , ) , ) , - , 6 , / , 2 ] 
		  */
	
	 
	 public  ArrayList<String> Convert_String_to_arraylist(){  
		 String expr=exp;
		 int ipr=0;
		 String imp="";
		 ArrayList<String> pilep = new ArrayList<String>();
	      while (ipr<expr.length()) 
	      {
	    	  char cr=expr.charAt(ipr);
	    	  
	    	  if(!is_operator(cr)) {
	    		  if(cr!='(' && cr!=')') {
	    			  imp=imp+cr;
	    			  if(ipr==expr.length()-1) {
	    				  pilep.add(imp);
						  imp="";
	    			  }
	    		  }
	    		  else {
	    			  if(cr=='(' ) {
	    				  if (ipr!=0) {
	    					  char s1=expr.charAt(ipr-1);
	    					  if(s1!='(' && !is_operator(s1)) {
	    						  pilep.add(imp);
	    						  imp="";
	    					  }
	    				  }
	    				  pilep.add("(");
	    			  }
	    			  else if(cr==')' ) {
	    				  char s2=expr.charAt(ipr-1);
	    				  if(s2!=')') {
	    					  pilep.add(imp);
    						  imp="";
	    				  }
	    				  pilep.add(")");
	    			  }
	    		  }
	    	  }
	    	  else {
	    		  if(cr=='-') {
	    			  if(ipr==0) {
	    				  pilep.add("-");
	    			  }
	    			  else {
	    				  char s4=expr.charAt(ipr-1);
	    				  if(s4!='/' && s4!='*' && s4!='^' && s4!='(' &&s4!=')') {
	    					  pilep.add(imp);
    						  imp="";
	    				  }
	    				  pilep.add("-"); 
	    			  }
	    		  }
	    		  else {
	    			  char s3=expr.charAt(ipr-1);
	    			  if(s3!=')') {
	    				  pilep.add(imp);
						  imp="";
	    			  }
	    			  pilep.add(Character.toString(cr));
	    		  }
	    		  
	    	  }
	    	  
	    	  ipr++;
	      }	  
	      return pilep;
	   }   	  
	    	  
	    	  
	    	  
	    	
	    	
	    	
	
	 
	 
	 
	 
	 
	 
	 
	 
	 public static boolean is_operator(char  co) {       // v�rifier si le caractere est une op�rateur   
			if ( (co=='+') || (co=='-') || (co=='/') || (co=='*') || (co=='^') ) return true;
			else return false;
	}
	 
	 
	 
	 public static boolean is_operator(String  cs) {      // v�rifier si le string est une op�rateur   
			if ( (cs.equals("+") ) || (cs.equals("-")) || (cs.equals("/")) || (cs.equals("*") ) || (cs.equals("^")) )return true;
			else return false;
	}
	 
	 
	 
	 
	
	 
	 
	 public static int[] verifier(Table_des_symboles sym , ArrayList<String> pile) throws Exception{    
		 
		 // methode pour v�rivier si la liste contient des variable ou fontion n'existent pas dans la table des symboles 
		 // si la methode trouve des variable ou fontion n'existent pas dans la table des symboles elle enregister dans un tableaux leurs indices dans la liste
		 
			int j[]= {-1};
			int p=0;
			for (int i = 0; i<pile.size(); i++){
				String v=pile.get(i);
				if (v.length()==1) {
					char c=v.charAt(0);
					if ((!is_operator(c)) && (c!='(') && (c!=')') && !Character.isDigit(c)) {
					   if(!sym.verifier_cle(v)) { j[p]= i;p++;}
					}
				}
				else if(!Character.isDigit(v.charAt(0))) {
					if(!sym.verifier_cle(v) )  { j[p]= i;p++;};
				} 
			}
			return j;
		}
	 
	 
	 
	 
	 
	 

		
		
		
		
		
		
	 
	
	

	
	
	    
	               // methode pour remplaser le variable dans la liste avec leurs valeurs 
	               // et pour elever le moins unaire
	 
	               /*  par exemple :
	                      
	                      on suppos x = 2 
	                      
	                      si liste = [ - ,  5 , - , x ] 
	                      alors liste = [ -5  ,  -  ,  2 ]
	                     
	                     
	                     
	                 */
	                      
	     public static ArrayList<String> Elver_le_moins_et_remplacer_les_var(ArrayList<String> listm,int pm, int qm,Table_des_symboles ts) throws Exception{
		    for(int u=qm ; u>=pm ; u-- ) {
			     String v=listm.get(u);
			    
			     if ( u==0 ){
			    	
				      if (v.charAt(0)=='-') {
				    	   String res1=listm.get(u+1);
					       if( Character.isDigit(res1.charAt(0)) || is_operator(res1.charAt(0)) ) {
					    	   double val=-Double.parseDouble(res1);
					    	   res1 = String.valueOf(val);
					    	   listm.remove(u+1);
					    	   listm.add(u+1,res1);
					    	   listm.remove(u);
					       }
					       else{
					    	   double val = -ts.get_valeur(res1).Get_Valeur();
					    	   res1 = String.valueOf(val);
					    	   listm.remove(u+1);
					    	   listm.add(u+1,res1);
					    	   listm.remove(u);
					       }
					       
				      } 
			     }
			    else {
				    if(v.charAt(0)=='-' && is_operator(v)) {
				    	String res3=listm.get(u-1);
				    	if(res3.charAt(0)=='(' || res3.charAt(0)=='*' || res3.charAt(0)=='/'  || res3.charAt(0)=='^') {
				    		 res3=listm.get(u+1);    
				    		 if( Character.isDigit(res3.charAt(0)) || is_operator(res3.charAt(0) )) {
						    	   double val=-Double.parseDouble(res3);
						    	   res3 = String.valueOf(val);
						    	   listm.remove(u+1);
						    	   listm.add(u+1,res3);
						    	   listm.remove(u);
						       }
						       else{
						    	   double valj =-ts.get_valeur(res3).Get_Valeur();
						    	   res3 = String.valueOf(valj);
						    	   listm.remove(u+1);
						    	   listm.add(u+1,res3);
						    	   listm.remove(u);
						       } 
				    	}
				    	
				    }
			    }
			     
			
			    if(!is_operator(v.charAt(0)) && !Character.isDigit(v.charAt(0)) && v.charAt(0)!='(' && v.charAt(0)!=')' ) { // alors variables
			    	 
				     double vals=ts.get_valeur(v).Get_Valeur(); 
				     if (ts.get_valeur(v).getClass().getName()=="Fonction_ST") throw new Exception();
				     String ress=String.valueOf(vals);
				     listm.remove(u);
				     listm.add(u, ress);
				     
			    }
			
		    }
		 return listm;
	  }
	    
	    
	    
	    
	    
	    
	    
	    
	    // prioret� des operateurs
	    
	    public static int precedence(char cht) 
	    {
	       if(cht=='+' || cht=='-')  return 1;
	       else if(cht=='*' || cht=='/') return 2; 
	       else if (cht=='^') return 3;
	       return 0;
	    }
	    
	    
	    
	    
	    
	    
	
	
	
	                        // convert la liste de infix vers unr pile de postfix 
	                         /* par exemple :
	                                
	                                liste = [ 5 , +  , 4 , * 2]
	                                alors pile = [ 5 , 4 , 2 , * , +]  
	                               
	                          */
	    
	    
	    
	public static  Stack<String> Infix_to_postfix( ArrayList<String> listj,int pj, int qj,Table_des_symboles symv) throws Exception{
		 Stack<String> operators = new Stack<String>();
		 Stack<String> postFix = new Stack<String>();
		 int aa=pj;
		 int bb=qj;
		 for(int i1=pj ; i1<=qj ; i1++ ) {
			 String ch1=listj.get(i1);
			 ch1 = ch1.replaceAll("\\s", ""); 
			 if(ch1.charAt(0)=='(')  operators.push(ch1);
			 if( !is_operator( ch1 ) )  postFix.push(ch1); 
			 else if(ch1.charAt(0)==')') {
				 while(operators.peek().charAt(0)!= '(') {
					 String op = operators.pop();
					 String first = postFix.pop();          
					 String second = postFix.pop();
		             postFix.push(second);
		             postFix.push(first);
		             postFix.push(op);
				 }
				 operators.pop(); 
			 }
			 else if( is_operator( ch1 ) ) {
				 while(operators.size()>0 && operators.peek().charAt(0)!='(' && precedence(ch1.charAt(0)) <= precedence(operators.peek().charAt(0)))
				   {
					     String op = operators.pop();
					     String first = postFix.pop();
					     String second = postFix.pop();
				         postFix.push(second);
			             postFix.push(first);
			             postFix.push(op);
			             
				   }
				 operators.push(ch1);
			 }
		 }
		 while(operators.size()>0)
		 {
			 String op = operators.pop();
			 String first = postFix.pop();
			 String second = postFix.pop();
	         postFix.push(second);
             postFix.push(first);
             postFix.push(op);
		  }
		 return postFix; 	 
	}
	
	
	
	
	
	
	
	           // pour calculer le resultat de l'expression ecrire sous form fostfix
	            /* par exemple :
    
                    
                     pile = [ 5 , 4 , 2 , * , +]  
                     alors resultat = 13
   
*/
	                        
	
	
	public static double Postfix_to_Answer( Stack<String> pi1 ) throws ArithmeticException,Exception {
		
		Stack<Double> stack=new Stack<>();
        
        for(int i2=0;i2<pi1.size();i2++)
        {
            String c2=pi1.elementAt(i2);
            if(!is_operator( c2 )   ) {
            	stack.push(Double.valueOf(c2));
            } 
            else
            {
                double val1 = stack.pop();
                double val2 = stack.pop();
                  
                switch(c2.charAt(0))
                {
                    case '+':
                    stack.push(val2+val1);
                    break;
                      
                    case '-':
                    stack.push(val2- val1);
                    break;
                      
                    case '/':
                    if (val1==0) throw new ArithmeticException();
                    stack.push(val2/val1);
                    break;
                      
                    case '*':
                    stack.push(val2*val1);
                    break;
                    
                    case '^':
                    stack.push(Math.pow(val2, val1));
                    break;
              }
            }
        }
        return stack.pop();
	}
	
	
	
	
	
	
	
	
	
	                // remplases des element dans la liste par une seul valeur
	                 
	
	
	
	public static ArrayList<String> remplacer(ArrayList<String> r , double valeur , int a  , int b) throws Exception {
		while (b>=a) {
			r.remove(b);
			b--;
		}
		r.add(a, String.valueOf(valeur));
		return r;
	}
	
	
	
	
	
	
	
	          /* l'id�e de cette methode est de calculer le resultat de l'interier du parenthes le plus profond dans l'expression et apres le remplser
	            avec ca valeur en on repeter l'opperation jusqu'a l'obtention d'un expression sans parenthes  
	            
	                 ( en calcule tout les resultat avec la methode de fostfix )
	              
	                  par exemple : 
	                  
	                 1)    liste = [ - , 5 , * , ( , sin , ( , 4 , * , 9 , ) , ) , - , 6 , / , 2 ] 
	                 2)    liste = [ - , 5 , * , ( , sin , ( ,  36  , ) , ) , - , 6 , / , 2 ]
	                 3)    liste = [ - , 5 , * , ( , 0.58  , ) , - , 6 , / , 2 ]
	                 4)    liste = [ - , 5 , * , 0.58   , - , 6 , / , 2 ]
	                 5)    liste = [ -5 , * , 0.58  ,  -6 , / , 2 ]
	                 
	                 apres on calcules le resultat avec la methode de postfix
	            
	            */
	public  double Evaluer_exp(Table_des_symboles sym1) throws var_Fonc_n_exist_pas ,ArithmeticException, Valeur_positive,Exception ,Valeur_tres_grand{
		ArrayList<String> listce= Convert_String_to_arraylist();
		int[] table = verifier(sym1,listce);
		if (table[0]!=-1) throw new var_Fonc_n_exist_pas(listce.get(table[0]));
		boolean w1=true;
		while (w1) {
		int cpt=0,iw=listce.size()-1,ens=0,ene=0,j=listce.size();	
		while (iw>=0 && cpt==0) {
			String o=listce.get(iw);
			if (o.equals(")")) ens=iw;
			if(o.equals("(")) {
				cpt++;
			    ene=iw;
			}		
			iw--;	
		}
		double x=0;
		if (cpt!=0) {
			
		listce=Expression.Elver_le_moins_et_remplacer_les_var(listce, ene+1,ens-1, sym1);
		int d=listce.size();
		ens=ens-j+d;
		Stack<String> ss= Infix_to_postfix(listce,ene+1,ens-1,sym1);
		x= Expression.Postfix_to_Answer(ss);
		if(ene==0) {
			remplacer(listce,x,ene,ens);
			
		}
		else {
			String jk = listce.get(ene-1);
			if(jk.equals("(") || is_operator(jk)) {
				remplacer(listce,x,ene,ens);
			}
			else {
				double y=sym1.get_valeur(jk).calcule(x);
				remplacer(listce,y,ene-1,ens);
			}
		}
		}
		else {
			w1=false;
		}
		
	   }  
	
	
		Expression.Elver_le_moins_et_remplacer_les_var(listce,0,listce.size()-1, sym1);
		Stack<String> pf = Infix_to_postfix(listce,0,listce.size()-1,sym1);
		double fin= Expression.Postfix_to_Answer(pf);
		if (fin>Double.MAX_VALUE) throw new Valeur_tres_grand();
	    return fin;
   }
	
	
	
	
}
