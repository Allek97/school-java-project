package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class InterfaceAide extends JFrame{
	
	protected static JPanel pContainer;
	protected JButton retour;
	protected JTextArea infos, version;
	
	public InterfaceAide() {
		
		super("Aide");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/logo.jpg")).getImage());
        this.setUndecorated(isBackgroundSet());
        
        // Création du paneau
        pContainer = (JPanel) this.getContentPane();
        pContainer.setLayout(null);
        pContainer.setBackground(new Color(255, 243, 243));
        pContainer.getRootPane().setBorder(BorderFactory.createMatteBorder(6, 6, 6, 6, Color.black));
        
        // Création du bouton
        retour = buttonRetour("Retour");
        
        // Création des champs d'informations
        infos = champInfo();
        version = champVersion();
        
        // Mettre logo personnalisé
        ImageIcon iconLogo = new ImageIcon(new ImageIcon("./images/logo.png").getImage().getScaledInstance(150, 60, Image.SCALE_DEFAULT)); 
        JLabel image = new JLabel(iconLogo);
        image.setBounds(10, 520, 150, 60);
        pContainer.add(image);   

        // Actions
        retour.addActionListener(new RetourAction());
        
        setVisible(true); 
	}

	
    ///////////////////////////////// Pour sortir de la page :
    public class RetourAction implements ActionListener{
    	
    	public void actionPerformed(ActionEvent e) {
			dispose();
    	}
    }
    
    
	///////////////////////////////// Créer les boutons de cette interface :
	 private static JButton buttonRetour(String text) {
	    	
	    	JButton button = new JButton(text);
	        button.setForeground(Color.pink);
	        button.setBackground(Color.black);
	        button.setBorderPainted(false);
	        button.setFocusPainted(false);
	        button.setBounds(450, 530, 90, 30);
	        button.setFont(new java.awt.Font(Font.SANS_SERIF, Font.BOLD, 10));
	        
	        pContainer.add(button);
	    	
	    	return button;
	 }
	 
	 
	///////////////////////////////// Créer le champs de texte :
	private static JTextArea champInfo() {
	
		JTextArea textArea = new JTextArea("Mettre un fichier ou un répertoire dans le champ apprêté. Puis cliquez sur \"nouveau chemin\". "
				+ "Faîte l'indexage des mots en cliquant sur\n\"indexer les mots\" et \"indexe inverser\"."
				+ " Et enfin cliquez sur \" afficher les mots\" pour faire apparaître les deux structures."
				+ "\nPour faire une recherche, il vous suffit de rentrer un ou plusieurs mots dans la barre dédiée."
				+ " L'algorithme fera la recherche puis affichera le résultat dans un champ texte séparé au précédent."
				+ "\n\nLégende :"
				+ "\n╟ La première structure : nom du fichier → (valeur, fréquence) → ..."
				+ "\n╟ La deuxième structure : valeur → (nom du fichier) → ∅"
				+ "\n╟ ∅ : ne contiens aucun élément"
				+ "\n╟ Les valeurs peuvent être des mots ou des entiers."
				+ "\n╟ L'interface d'indexage contient deux boutons supplémentaire :"
				+ "\n   1. clear \"⌫\" : pour effacer les recherches précédentes"
				+ "\n   2. back \"◄\" : pour revenir sur la page d'acceuil."
				+ "\n\n(Mettre l'IDE avec le bon format endocage)"
				+ "\n\n\nLa séparation des deux champs de texte permette un meilleur confirt pour une recherche optimisée !"); 

		textArea.setBounds(100, 100, 800, 300);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBackground(new Color(255, 240, 240));
		textArea.setSelectionColor(Color.black);
		textArea.setSelectedTextColor(Color.pink);
		textArea.setFont(new java.awt.Font(Font.DIALOG, Font.CENTER_BASELINE, 12));
		
		pContainer.add(textArea);	
		
		return textArea;
	}

	
	///////////////////////////////// Créer le champs de texte :
	private static JTextArea champVersion() {
	
		JTextArea textArea = new JTextArea("Auriane Egal - Ilias Allek\n            VERSION 1.0");
		textArea.setBounds(850, 550, 150, 100);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBackground(new Color(255, 240, 240));
		textArea.setSelectionColor(Color.gray);
		textArea.setSelectedTextColor(Color.pink);
		textArea.setFont(new java.awt.Font(Font.SANS_SERIF, Font.BOLD, 10));
		textArea.setOpaque(false);
		
		pContainer.add(textArea);	
		
		return textArea;
	}
	
}
