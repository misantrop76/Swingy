public class GuiView implements View
{

	private JFrame frame;

	public void start()
	{
		frame = new JFrame("Swingy");
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void render()
	{
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(frame, message);
	}

	public void askNextAction()
	{
	}

	public void close()
	{
		frame.dispose();
	}
}
