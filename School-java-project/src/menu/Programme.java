package menu;

import java.util.ArrayList;

/* Permet d'accomplir les fonctionnalités du programme
   Indexer,inverser l'index,afficher les deux structures et faire la recherche */
public class Programme {

    private Index index = new Index();
    private Index indexInv = new Index();

	///////////////////////////////// Indexer :
    public String indexer(String source) {
    	
        Lecture lecture = new Lecture();
        String message = "";
        
        if(source.endsWith(".txt")) {        	
        	message = lecture.lireFichier(source);
            index = lecture.getIndex();
        }
        else{
        	message = lecture.directory(source);
            index = lecture.getIndex();
        }
        return message;
    }

    
	///////////////////////////////// Indexer inverse :
    public String indexerInv() {
    	
        return indexInv.inverser(index);
    }
    
    
	///////////////////////////////// Afficher :
    public ArrayList<String> afficher() {

    	ArrayList<String> tab = new ArrayList<>();
    	
        tab.add("\nLa premiere structure :\n");
        tab.addAll(index.afficher());
        
        tab.add("\nLa deuxieme structure :\n");
        tab.addAll(indexInv.afficher());
        
        return tab;
    }

    
	///////////////////////////////// Recherche :
    public ListeValeur rechercher(String requete) {
    	
        ListeValeur resultat = new ListeValeur();
        resultat = indexInv.rechercher(requete);

        return resultat;
    }
    
    
    
}