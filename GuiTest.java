import javax.swing.*;
import java.awt.*;

public class GuiTest {

	public static void main(String[] args) {

		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		int largeur = tailleEcran.width;
		int hauteur = tailleEcran.height;
		JFrame frame = new JFrame("Ma première GUI Java");
		frame.setSize(largeur, hauteur);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); // millieu
		JButton boutonQuitter = new JButton("Arrêter le programme");
		boutonQuitter.addActionListener(e -> System.exit(0));
		frame.setLayout(new GridBagLayout());
		frame.add(boutonQuitter);
		frame.setVisible(true);
	}
}
