package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class InterfaceOne extends JFrame {
	
	protected static JPanel pContainer;
	protected String texte;	
	protected JButton rechercher, affichage, indexer, indexInverse, nouveauDoc, retour, clear;
    protected JTextArea champsTexte, champsRecherche;
    protected JTextField rechercheMots, nouveauChemin;
    protected JLabel un, deux, trois, quatre;
    protected String path;
    protected boolean ind, indInv;
    
    // Pour aller dans la classe de fontionnement des programmes
    protected Programme programme = new Programme();
    protected ListeValeur resultat = new ListeValeur(); 
    protected Lecture lecture = new Lecture();
    
    
	public InterfaceOne() {
		
		super("Dictionnaire d'indexage");
		setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/logo.jpg")).getImage());
        this.setUndecorated(isBackgroundSet());
        
        // Création du paneau
        pContainer = (JPanel) this.getContentPane();
        pContainer.setLayout(null);
        pContainer.setBackground(new Color(20, 20, 20));
        
        // Création des boutons 
        rechercher = buttonInterface("Rechercher un ou plusieurs mots", 9, 10, 230, 30);
        indexer = buttonInterface("Indexer les mots", 300, 10, 180, 30);
        affichage = buttonInterface("Afficher les mots", 740, 10, 180, 30);
        indexInverse = buttonInterface("Index inverse", 520, 10, 180, 30);
        nouveauDoc  = buttonInterface("Nouveau chemin :", 300, 50, 180, 30);
        retour = buttonInterface("◄", 935, 10, 57, 20);
        clear = buttonInterface("⌫", 935, 40, 57, 20);
        
        // Création des labels
        un = createLabel("1.", 280, 10, 180, 30);
        deux = createLabel("2.", 500, 10, 180, 30);
        trois = createLabel("3.", 720, 10, 180, 30);
        quatre = createLabel("4.", 280, 50, 180, 30);
        
        // Création des textes fields
        rechercheMots = createJtextField("mots", 9, 50, 230, 30);
        nouveauChemin = createJtextField("./fichiers/1.txt", 480, 50, 440, 30);
        
        // Création des champs de texte
        champsTexte = createTextArea(250, 90, 740, 500);
        champsRecherche = createTextAreaRecherche(9, 90, 230, 500);
        
        // Pour le nom du chemin à utiliser en perfamence
        this.path = new String();
        
        // Pour savoir si l'indexage a été réalisé
        this.ind = false;
        this.indInv = false;
        
        // Actions des boutons 
        rechercher.addActionListener(new addDisplayAction());
        indexer.addActionListener(new addDisplayAction());
        affichage.addActionListener(new addDisplayAction());
        nouveauDoc.addActionListener(new addDisplayAction());
        indexInverse.addActionListener(new addDisplayAction());
        retour.addActionListener(new addDisplayAction());
        clear.addActionListener(new addDisplayAction());
        
        mouvement();
        
        setVisible(true); 
	}


	///////////////////////////////// Pour éxécuter les actions de l'utilisateur :
	private class addDisplayAction implements ActionListener {
	    
		public void actionPerformed(ActionEvent e) {
        	
        	///////////////////////////////// Rechercher un ou plusieurs mots
        	if(e.getSource() == rechercher){  
        		
        		if(path.equals(""))
        			champsRecherche.append("Chemin vide !\n");
        		else {
        			String requete = rechercheMots.getText();
        			if(requete.equals(""))
        				champsRecherche.append("Rentrer un mot !\n");
        			else {
	        			rechercheMots.setText("");
	                    resultat = programme.rechercher(requete);
	                    champsRecherche.append("Voici le résultat de votre requète :\n");
	                    champsRecherche.append(resultat.printVal() + "\n");
        			}
        		}
                
        	}///////////////////////////////// Indexer
        	else if(e.getSource() == indexer){   
        		
        		if(path.equals(""))
            		champsTexte.append("Chemin vide !\n");
        		else {
	        		String message = programme.indexer(path);
	        		if(message.equals("")) {
	        			champsTexte.append("Indexage réussi !\n");
	        			ind = true;
	        		}
	        		else
		        		champsTexte.append(message);
        		}
        		
        	}///////////////////////////////// Indexer ineverse
        	else if(e.getSource() == indexInverse){ 
        		
        		if(path.equals(""))
            		champsTexte.append("Chemin vide !\n");
        		else {
        			String message2 = programme.indexerInv();
	        		if(message2.equals("")) {
	        			champsTexte.append("Indexage inversé réussi !\n");
	        			indInv = true;
	        		}
	        		else
	        			champsTexte.append(message2 + "\n");
        		}
        		
        	}///////////////////////////////// Afficher les mots et leur féquence (des deux structures) 
        	else if(e.getSource() == affichage){  
        		
        		if(path.equals(""))
            		champsTexte.append("Chemin vide !\n");
        		else {
	        		ArrayList<String> tab = programme.afficher();
	        		// Pour la première structure :
	        		if(ind == false || indInv == false)
	        			champsTexte.append("Effectuer les deux indexages !\n");
	        		else {
	        			for(int i=0 ; i<tab.size() ; i++)
	        				champsTexte.append(tab.get(i));
	        		}	        		
        		}        		

        	}///////////////////////////////// Pour un nouveau chemin de dossier texte
        	else if(e.getSource() == nouveauDoc){ 
        		
        		path = nouveauChemin.getText();
        		
        		if(path.equals(""))
        			champsTexte.append("Entrer un répertoire !\n");
        		else {
        			if(path.endsWith(".txt")) {
    					champsTexte.append("Le fichier entré est : " + path + "\n");
    					
	        			if(lecture.lireFichier(path).equals("")) { // Procédure du fichier réussi
	        				nouveauChemin.setText("./fichiers");
	        				ind = false;
	        				indInv = false;
	        			}
	        			else if(lecture.lireFichier(path).equals("") == false) { // Le fichier n'est pas fonctionnel
	            			champsTexte.append(lecture.lireFichier(path) + "\n");
	            			path = ""; // Si un fichier été valide auparavant
	        				champsTexte.append("Rentrer un chemin valide !\n");
	        			}
        			}
        			else { // Procédure pour un répertoire
		                	champsTexte.append("Le répertoire entré est : " + path + "\n");
		                	champsTexte.append(lecture.directory(path));
		            }	
        		}     			
	        	        		
        	}///////////////////////////////// Pour un retour à la page d'acceuil
        	else if(e.getSource() == retour) 
        		dispose();
        	
        	///////////////////////////////// Pour un effacement du texte
        	else if(e.getSource() == clear) {
        		champsTexte.setText("");
        		champsRecherche.setText("");
        	}
        }
    }
    

    
	///////////////////////////////// Créer les boutons de cette interface :
	 private static JButton buttonInterface(String text, int x , int y, int height, int width) {
	    	
	    	JButton button = new JButton(text);
	        button.setForeground(Color.BLACK);
	        button.setBackground(Color.pink);
	        button.setBorderPainted(false);
	        button.setFocusPainted(false);
	        button.setBounds(x, y, height, width);
	        button.setFont(new java.awt.Font(Font.SANS_SERIF, Font.BOLD, 10));
	        
	        pContainer.add(button);
	    	
	    	return button;
	 }
	 
	 
	///////////////////////////////// Créer le champs de texte :
	private static JTextArea createTextArea(int x , int y, int height, int width) {
	
		JTextArea textArea = new JTextArea(""); 
		textArea.setBorder(BorderFactory.createLineBorder(Color.pink));
		textArea.setBounds(x , y, height, width);
		textArea.setEditable(false);
		textArea.setBackground(new Color(255, 240, 240));
		textArea.setSelectionColor(Color.black);
		textArea.setSelectedTextColor(Color.pink);
		
		pContainer.add(textArea);	
		
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(x , y, height, width);
        pContainer.add(scroll);
		
		return textArea;
	}
	
	
	///////////////////////////////// Créer le champs de texte de recherche :
	private static JTextArea createTextAreaRecherche(int x , int y, int height, int width) {
		
		JTextArea textArea = new JTextArea("");
		textArea.setBorder(BorderFactory.createLineBorder(Color.pink));
		textArea.setBounds(x , y, height, width);
		textArea.setEditable(false);
		textArea.setBackground(new Color(255, 240, 240));
		textArea.setLineWrap(true);
		textArea.setSelectionColor(Color.black);
		textArea.setSelectedTextColor(Color.pink);
		
		pContainer.add(textArea);	
		
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(x , y, height, width);
        pContainer.add(scroll);
		
		return textArea;
	}
	
	
	///////////////////////////////// Créer les textes field :
	private static JTextField createJtextField(String mot, int x , int y, int height, int width) {
	
		JTextField text = new JTextField("");        
		text.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		text.setText(mot);
		text.setBounds(x, y, height, width);
		text.setBounds(x, y, height, width);
		text.setSelectedTextColor(Color.magenta);
		text.setSelectionColor(Color.lightGray);
		
		pContainer.add(text);
		
		return text;
	}
    
	
	///////////////////////////////// Créer les labels :
	private static JLabel createLabel(String text, int x , int y, int height, int width) {
	
		JLabel label = new JLabel(text);        
		label.setForeground(Color.white);
		label.setBounds(x, y, height, width);
		label.setBounds(x, y, height, width);
		
		pContainer.add(label);
		
		return label;
	}
	    
    
	///////////////////////////////// Créer les changements lors des mouvements souris :
	private void mouvement() {
		
		retour.addMouseListener(new MouseListener() {
        	
            public void mouseExited(MouseEvent e) {
            	retour.setBackground(Color.pink);
            	retour.setText("◄");
            }
            
            public void mouseEntered(MouseEvent e) {
            	retour.setBackground(Color.white);
            	retour.setText("back");
            } 
            
            public void mouseClicked(MouseEvent e) {}

			public void mousePressed(MouseEvent arg0) {}

			public void mouseReleased(MouseEvent arg0) {}
        });

		clear.addMouseListener(new MouseListener() {
        	
            public void mouseExited(MouseEvent e) {
            	clear.setBackground(Color.pink);
            	clear.setText("⌫");
            }
            
            public void mouseEntered(MouseEvent e) {
            	clear.setBackground(Color.white);
            	clear.setText("clear");
            } 
            
            public void mouseClicked(MouseEvent e) {}

			public void mousePressed(MouseEvent arg0) {}

			public void mouseReleased(MouseEvent arg0) {}
        });

		rechercher.addMouseListener(new MouseListener() {
        	
            public void mouseExited(MouseEvent e) {
            	rechercher.setBackground(Color.pink);
            }
            
            public void mouseEntered(MouseEvent e) {
            	rechercher.setBackground(new Color(230, 125, 125));
            } 
            
            public void mouseClicked(MouseEvent e) {}

			public void mousePressed(MouseEvent arg0) {}

			public void mouseReleased(MouseEvent arg0) {}
        });
        
		indexer.addMouseListener(new MouseListener() {
        	
            public void mouseExited(MouseEvent e) {
            	indexer.setBackground(Color.pink);
            }
            
            public void mouseEntered(MouseEvent e) {
            	indexer.setBackground(new Color(230, 125, 125));               
            } 
            
            public void mouseClicked(MouseEvent e) {}

			public void mousePressed(MouseEvent arg0) {}

			public void mouseReleased(MouseEvent arg0) {}
        });
		
		indexInverse.addMouseListener(new MouseListener() {
        	
            public void mouseExited(MouseEvent e) {
            	indexInverse.setBackground(Color.pink);
            }
            
            public void mouseEntered(MouseEvent e) {
            	indexInverse.setBackground(new Color(230, 125, 125));
            } 
            
            public void mouseClicked(MouseEvent e) {}

			public void mousePressed(MouseEvent arg0) {}

			public void mouseReleased(MouseEvent arg0) {}
        });

		nouveauDoc.addMouseListener(new MouseListener() {
			
		    public void mouseExited(MouseEvent e) {
		    	nouveauDoc.setBackground(Color.pink);
		    }
		    
		    public void mouseEntered(MouseEvent e) {
		    	nouveauDoc.setBackground(new Color(230, 125, 125));
		    } 
		    
		    public void mouseClicked(MouseEvent e) {}
		
			public void mousePressed(MouseEvent arg0) {}
		
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		affichage.addMouseListener(new MouseListener() {
			
		    public void mouseExited(MouseEvent e) {
		    	affichage.setBackground(Color.pink);
		    }
		    
		    public void mouseEntered(MouseEvent e) {
		    	affichage.setBackground(new Color(230, 125, 125));
		    } 
		    
		    public void mouseClicked(MouseEvent e) {}
		
			public void mousePressed(MouseEvent arg0) {}
		
			public void mouseReleased(MouseEvent arg0) {}
		});
	}
    
}


