package menu;
import java.io.*;
import java.util.*;

/* Il s'agit de notre classe qui permet de faire la lecture des fichier et
de tockeniser les textes et les repertoire */

public class Lecture {

    Index index = new Index();

	///////////// Lire un fichier singulier :
    public String lireFichier(String nom) {
    	
        String text = "", message = "";
        try {
            BufferedReader input = new BufferedReader(new FileReader(nom));
            String ligne;
            ligne = input.readLine();

            while (ligne != null) {
                text += ligne;
                ligne = input.readLine();
            }
            input.close();
        }        
        catch (IOException e) {// s'il y a une erreur dans le fichier
            return "Erreur fichier: " + e.getMessage();
        }

        StringTokenizer st = new StringTokenizer(text, " \t\n\r\f,?!.;:\"  \"~@#$%^&*()_+|-/\\{}[]\'’<>=`“ ");
        while (st.hasMoreTokens()) 
            index.inserer(nom, st.nextToken(), 1);
        return message;
    }
    

	///// Trouver le repertoire et lire les fichier qu'il contient :
    public String directory(String path) {
    	
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        String message = "";

        for (File file : listOfFiles) {
            String text = "";
            
            if (file.isFile() && file.getName().endsWith(".txt")) {
                
                try {
                    String nameFile = file.getName();
                    BufferedReader input = new BufferedReader(new FileReader(nameFile));
                    String ligne;
                    ligne = input.readLine();

                    while (ligne != null) {
                        text += ligne;
                        ligne = input.readLine();
                    }
                    input.close();
                }
                catch(FileNotFoundException exception) {
                	 return "The file : " + file.getName() + " was not found.\n";
                }catch (IOException e) {
                	 return "Erreur fichier " + e.toString() + "\n";
                }
            }
            StringTokenizer st = new StringTokenizer(text, " \t\n\r\f,?!.;:\"  \"~@#$%^&*()_+|-/\\{}[]\'<>=`“ ");
            while (st.hasMoreTokens()) 
                index.inserer(file.getName(), st.nextToken(), 1);
        }
		return message;
    }

    
	///// Récupérer l'index :
    Index getIndex() {
        return this.index;
    }
}



















































