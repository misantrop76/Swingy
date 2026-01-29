import javax.swing.*;
import java.awt.*;

public class GuiTest {


	public static void test()
	{
		System.out.println("heysalut toi");
	}

	public static void main(String[] args)
	{
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		int largeur = tailleEcran.height;
		int hauteur = tailleEcran.height;
		JFrame frame = new JFrame("Ma première GUI Java");
		frame.setSize(largeur, hauteur);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); // millieu
		JButton boutonQuitter = new JButton("Arrêter le programme");
		boutonQuitter.addActionListener(e -> test());
		frame.setLayout(new GridBagLayout());
		frame.add(boutonQuitter);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
