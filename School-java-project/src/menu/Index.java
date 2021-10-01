package menu;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Index {

    protected ListeCle verticale = new ListeCle();

    
	///////////////////////////////// Insérer un index complét :
    public void inserer(String cle, String valeur, int freq) {

        NoeudCle key = verticale.firstCle;

        if (verticale.firstCle == null) {
        	
            ListeValeur val = new ListeValeur();
            val.inserer(valeur, freq);
            verticale.firstCle = new NoeudCle(cle, null, val);
            return;
        }

        while (key != null) {

            if (key.cle.compareTo(cle) == 0) {
                key.firstListeValeur.inserer(valeur, freq);
                return;
            }
            key = key.nextCle;
        }

        // Insérer un nouveau document
        key = verticale.firstCle;
        ListeValeur val = new ListeValeur();

        while (key.nextCle != null)
            key = key.nextCle;
        
        val.inserer(valeur, freq);
        key.nextCle = new NoeudCle(cle, null, val);
    }

    
	///////////////////////////////// Inverser :
    public String inverser(Index documents) {

        NoeudCle key = documents.verticale.firstCle;
        NoeudValeur horizon;

        if (key == null)
            return "L'index est vide.";        

        while (key != null) {        	
            horizon = key.firstListeValeur.firstValeur;
            
            while (horizon != null) {
                inserer(horizon.valeur, key.cle, horizon.freq);
                horizon = horizon.nextVal;
            }
            key = key.nextCle;
        }
        return "";
    }

    
	///////////////////////////////// Afficher :
    public ArrayList<String> afficher() {
    	
    	ArrayList<String> ligne = new ArrayList<>();
        NoeudCle n = verticale.firstCle;
        
        while (n != null) {

            ligne.add(n.cle + " → ");
            ListeValeur v = n.firstListeValeur;
            ligne.add(v.printVal());
            ligne.add("\n");
            n = n.nextCle;
        }
        return ligne;
    }

    
	///////////////////////////////// Recherche :
    @SuppressWarnings("unused")
	public ListeValeur rechercher(String requete)  {

        ListeValeur resultats = new ListeValeur();
        NoeudCle key = verticale.firstCle;

        StringTokenizer st = new StringTokenizer(requete, " \t\n\r\f,?!.;:\"  \"~@#$%^&*()_+|-/\\{}[]\'<>=`â€œ ");

        String firstToken = st.nextToken();
        while (key != null) {
        	
            if ((firstToken.equals(key.cle)) == true) {            	
                NoeudValeur horizon = key.firstListeValeur.firstValeur;
                
                while (horizon != null) {
                    resultats.inserer(horizon.valeur, horizon.freq);
                    horizon = horizon.nextVal;
                }
            }
            key = key.nextCle;
        }
        if (resultats == null)
            return resultats;

        key = verticale.firstCle;
        st = new StringTokenizer(requete, " \t\n\r\f,?!.;:\"  \"~@#$%^&*()_+|-/\\{}[]\'<>=`â€œ");
        st.nextToken() ;
        while(st.hasMoreTokens()) {
        	
            String temp = st.nextToken();
            key = verticale.firstCle;
            int cleDispo = 0 ; // compteur qui verifie si le mot dans la requete possede une cle dans mon index inversÃ©
            
            while(key != null) {
                int trouve = 0; // si on trouve la cle on arrete on cherche pas les autres cle
               
                if(temp.equals(key.cle)) {
                    cleDispo++;
                    trouve++;
                    NoeudValeur valRes = resultats.firstValeur;
                    
                    while(valRes != null)  {
                        NoeudValeur horizon = key.firstListeValeur.firstValeur ;
                        int unique = 0 ; // compteur qui nous permettre de savoir si un certain
                                        // document est unique dans la ListeValeur resultats
                        while(horizon != null) {
                            if(horizon.valeur.equals(valRes.valeur)) {
                                resultats.inserer(horizon.valeur,horizon.freq);
                                unique++ ;
                            }
                            horizon = horizon.nextVal;
                        }
                        if(unique == 0)
                        	resultats.enleverVal(valRes.valeur);
                        
                        valRes = valRes.nextVal;
                    }
                }
                if(trouve != 0)
                	break ;
                else 
                    key = key.nextCle;
            }
            if(cleDispo == 0) {
                //On retourne une liste vide si le mot de la requete n'est pas dans la liste
                resultats = new ListeValeur();
                return resultats ;
            }
            if(resultats == null)
                return resultats;
        }
    return resultats ;
    }
}




class ListeCle {
    protected NoeudCle firstCle ;

}




class ListeValeur {
	
    NoeudValeur firstValeur ;
    
	///////////////////////////////// Afficher les valeurs :
    public String printVal() {
    	
        NoeudValeur n = firstValeur;
        String ligne = "";
        
        while(n != null) {
            ligne += "(" + n.valeur + ", " + n.freq + ")" + " → ";
            n = n.nextVal ;
        }
        ligne += "∅";
        return ligne;
    }

    
	///////////////////////////////// Longueur :
    public int longueur() {
    	
        int nb = 0;
        NoeudValeur n = firstValeur;
        
        if(firstValeur == null)
        	return 0 ;        

        while(n != null) {        	
            nb++ ;
            n = n.nextVal;
        }
        return nb;
    }


	///////////////////////////////// Ajouter valeur et fréquence :
    public void inserer(String valeur,int freq) {

        NoeudValeur n = firstValeur;

        if(n == null) {
            firstValeur = new NoeudValeur(valeur,freq,firstValeur) ;
            return;
        }

        if(valeur.compareTo(firstValeur.valeur) <= 0) {
        	
            if(valeur.compareTo(firstValeur.valeur) == 0) {
                n.freq = n.freq + freq ; 
                return ;
            }else {
                firstValeur = new NoeudValeur(valeur,freq,firstValeur) ;
                return;
            }
        }
        while( n.nextVal != null && (valeur.compareTo(n.nextVal.valeur) >= 0) ) {

            if(valeur.compareTo(n.nextVal.valeur) == 0) {
                n.nextVal.freq += freq;
                return;
            }
            n = n.nextVal;
        }
        n.nextVal = new NoeudValeur(valeur,freq,n.nextVal);
    }

    
	///////////////////////////////// Enlever une valeur :
    public void enleverVal(String valeur) {
    	
        NoeudValeur val = firstValeur;

        if(firstValeur == null)
        	return;
        
        if(firstValeur.valeur == valeur)  {
            firstValeur = firstValeur.nextVal;
            return;
        }
        while(val.nextVal != null && val.nextVal.valeur != valeur)
            val = val.nextVal;
        
        if(val.nextVal != null)
            val.nextVal =val.nextVal.nextVal;
    }
    
    
	///////////////////////////////// Enlever une position :
    public String enleverPos(int position) {
    	
        int j = 0;
        String message = "";
        
        ListeValeur l = new ListeValeur();
        l.firstValeur = firstValeur;
        NoeudValeur n =firstValeur;

        if(position >= l.longueur() || position < 0)
            return "Position hors bandes\n";

        if(position == 0) {
            firstValeur = firstValeur.nextVal ;
            return message;
        }

        while(j != position - 1 ) {
            n = n.nextVal ;
            j++ ;
        }
        n.nextVal = n.nextVal.nextVal;
        return message;
    }

    
	///////////////////////////////// Ajouter une position :
    public String ajouterPos(String valeur, int freq, int position) {
    	
        int trav = 0;
        
        ListeValeur l = new ListeValeur() ;
        l.firstValeur = this.firstValeur ;
        NoeudValeur n = l.firstValeur ;

        if(position > l.longueur() || position < 0 )
	        return "Position hors bandes\n";

        if(firstValeur == null && position == 0) {
            firstValeur = new NoeudValeur(valeur, freq, null);
            return "";
        }
        if (position == 0) {
            firstValeur = new NoeudValeur(valeur,freq,firstValeur);
            return "";
        }
        while(trav != position-1 ){
            n = n.nextVal;
            trav++;
        }
        n.nextVal = new NoeudValeur(valeur, freq, n.nextVal);
        return "";
    }
	
    
    ///////////////////////////////// Retourne la valeur de l'indice dans la liste :
    public String whatValue(int position) {
    	
        int trav = 0;
        ListeValeur l = new ListeValeur() ;
        l.firstValeur = this.firstValeur ;
        NoeudValeur n = l.firstValeur ;

        if(position >= l.longueur() || position < 0 )
            return "Position hors bandes ";

        while(trav != position) {
            n = n.nextVal;
            trav++;
        }
        return n.valeur;
    }

    
	///////////////////////////////// Retourne la fréquence :
    public int whatFreq(int position) {
    	
        int trav = 0;
        ListeValeur l = new ListeValeur();
        l.firstValeur = this.firstValeur;
        NoeudValeur n = l.firstValeur;

        if(position >= l.longueur() || position < 0 )
            return -1 ;
        
        while(trav != position) {
            n = n.nextVal;
            trav++;
        }
        return n.freq;
    }
    
    
	///////////////////////////////// La valeur se trouve dans la liste :
    public Boolean isThere(String valeur) {
    	
        NoeudValeur val = firstValeur;

        if (firstValeur == null)
            return false;
        
        while(val != null) {
        	
            if(val.valeur == valeur)
                return true;
            
            val = val.nextVal;
        }
        return false ;
    }
    

	///////////////////////////////// Trier dans l'ordre decroissant des fréquences :
    public void trierFreq(int startIndex) {
    	
        if(startIndex >= longueur())
            return;

        trierFreq(startIndex + 1);
        inserer(startIndex);
    }

    
	///////////////////////////////// Insérer un index :
    public void inserer(int index) {
    	
        if(index >= longueur() -1)
            return;

        if(whatFreq(index) >= whatFreq(index + 1))
            return;

        swap(index,index+1);
        inserer(index + 1);
    }

    
	///////////////////////////////// Echange :
    public String swap(int i, int j) {

        String valI = whatValue(i);
        String valJ = whatValue(j);
        int freqI = whatFreq(i);
        int freqJ = whatFreq(j);
        String message = "";

        message = enleverPos(i);
        message = ajouterPos(valJ,freqJ,i);

        message = enleverPos(j);
        message = ajouterPos(valI,freqI,j);
        
        return message;
    }

    
    
}




class NoeudCle {
	
    protected String cle;
    protected NoeudCle nextCle;
    protected ListeValeur firstListeValeur;

	///////////////////////////////// Constructeur :
    public NoeudCle(String cle,NoeudCle nextCle, ListeValeur firstValeur) {
    	
        this.cle = cle ;
        this.nextCle = nextCle ;
        this.firstListeValeur = firstValeur ;
    }
    
    
    
}




class NoeudValeur
{
    protected String valeur ; // Mot ou lien-document
    protected int freq ;
    protected NoeudValeur nextVal ; // Le lien document ou le mot c-a-d liste horizontale

	///////////////////////////////// Constructeur :
    public NoeudValeur (String valeur,int freq,NoeudValeur nextVal) {
    	
        this.valeur = valeur;
        this.freq = freq;
        this.nextVal = nextVal;
    }
    
    

}


