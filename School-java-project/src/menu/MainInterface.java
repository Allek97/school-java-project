package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainInterface extends JFrame {

	protected static JPanel pContainer;
    protected JButton index, exit, aide;
    protected JLabel lab;

    public MainInterface() {

    	super("Dictionnaire d'index");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/logo.jpg")).getImage());
        this.setUndecorated(isBackgroundSet());
        
        // Création du paneau
        pContainer = (JPanel) this.getContentPane();
        pContainer.setLayout(null);
        pContainer.setBackground(new Color(255, 243, 243));
        pContainer.getRootPane().setBorder(BorderFactory.createMatteBorder(6, 6, 6, 6, Color.black));
        
        // Création des boutons 
        index = buttonMain("OK", 620, 300, 65, 40);
        exit = buttonMain("Sortir", 450, 530, 90, 30);  
        aide = buttonAide();
        
        // Création du label
        lab =  createLabel("Bienvenue dans le moteur de recherche personnalisé", 300, 300, 320, 40);
        
        // Mettre logo personnalisé
        JLabel image = new JLabel();
        image.setIcon(new ImageIcon("./images/logo.png"));
        image.setBounds(250, 100, 500, 200);
        pContainer.add(image);        
        
        // Actions 
        index.addActionListener(new IndexAction());
        exit.addActionListener(new ExitAction());
        aide.addActionListener(new AideAction());
        
        mouvement();
                
        setVisible(true);        
    }
    
    
    ///////////////////////////////// Pour aller à l'interface d'index :
    @SuppressWarnings("unused")
    public class IndexAction implements ActionListener{
    	
    	public void actionPerformed(ActionEvent e) {
    		InterfaceOne index = new InterfaceOne() ;
    	}
    }
    
    
    ///////////////////////////////// Pour aller à l'interface d'aide :
    @SuppressWarnings("unused")
    public class AideAction implements ActionListener{
    	
    	public void actionPerformed(ActionEvent e) {
    		InterfaceAide help = new InterfaceAide() ;
    	}
    }

    
    ///////////////////////////////// Pour sortir du programme :
    public class ExitAction implements ActionListener{
    	
    	public void actionPerformed(ActionEvent e) {
			dispose();
    	}
    }
    
    
    ///////////////////////////////// Créer les boutons de cette interface :
    private static JButton buttonMain(String text, int x, int y, int width, int height) {
        
    	JButton button = new JButton(text);
        button.setForeground(Color.pink);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setBounds(x, y, width, height);
        button.setBackground(Color.black); 
        button.setFont(new java.awt.Font(Font.SERIF, Font.BOLD, 20));
        button.setBorder(BorderFactory.createLineBorder(Color.black));
        
        pContainer.add(button);
    	
    	return button;
    }
    
    
    ///////////////////////////////// Créer le bouton d'aide :
    private static JButton buttonAide() {
    	
    	ImageIcon iconAide = new ImageIcon(new ImageIcon("./images/help.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)); 
    	JButton button = new JButton("", iconAide);
        button.setBounds(950, 550, 30, 30);
        button.setBackground(Color.pink);
        pContainer.add(button);
        
        return button;
    }
    
    
    ///////////////////////////////// Créer les labels :
	private static JLabel createLabel(String text, int x , int y, int height, int width) {
		
		JLabel label = new JLabel(text);        
		label.setForeground(Color.black);
		label.setFont(new Font("Didot", Font.BOLD, 12));
		label.setBounds(x, y, height, width);
		label.setBounds(x, y, height, width);
		
		pContainer.add(label);		

		return label;
	}
	
	
	///////////////////////////////// Créer les changements lors des mouvements souris :
    private void mouvement() {
    	
    	index.addMouseListener(new MouseListener() {
        	
            public void mouseExited(MouseEvent e) {
            	index.setBackground(Color.black);
            }
            
            public void mouseEntered(MouseEvent e) {
            	index.setBackground(Color.white);
            } 
            
            public void mouseClicked(MouseEvent e) {}

			public void mousePressed(MouseEvent arg0) {}

			public void mouseReleased(MouseEvent arg0) {}
        });
        
        exit.addMouseListener(new MouseListener() {
        	
            public void mouseExited(MouseEvent e) {
            	exit.setBackground(Color.black);
            }
            
            public void mouseEntered(MouseEvent e) {
            	exit.setBackground(Color.white);
            } 
            
            public void mouseClicked(MouseEvent e) {}

			public void mousePressed(MouseEvent arg0) {}

			public void mouseReleased(MouseEvent arg0) {}
        });
        
        aide.addMouseListener(new MouseListener() {
        	
            public void mouseExited(MouseEvent e) {
            	aide.setBackground(Color.pink);
            }
            
            public void mouseEntered(MouseEvent e) {
            	aide.setBackground(Color.cyan);
            } 
            
            public void mouseClicked(MouseEvent e) {}

			public void mousePressed(MouseEvent arg0) {}

			public void mouseReleased(MouseEvent arg0) {}
        });
    }

    
    
    
}
