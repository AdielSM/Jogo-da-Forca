import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TelaJogo {

	private JFrame frame;
	private JLabel palavraLabel;
	private JogoDaForca jogo;
	private JTextField letraInput;
	private JButton checarBtn;
	private JLabel checkLabel;
	private JLabel lblTentativas;
	private JLabel imgLabel;
	private JLabel gameOverLabel;
	private JLabel dicaLabel;

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public TelaJogo() throws Exception {
		this.jogo = new JogoDaForca();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	
	private void reset() throws Exception {
		this.jogo = new JogoDaForca();
		this.palavraLabel.setText("Palavra: " + jogo.getPalavraAdvinhada());
		this.lblTentativas.setText("Tentativas: " + String.valueOf(jogo.getTentativas()));	
		this.dicaLabel.setText("Dica: " + jogo.getDica());
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(200, 200, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Jogo da Forca");
		
		JPanel panel = new JPanel();
		panel.setBounds(45, 53, 698, 486);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		palavraLabel = new JLabel();
		palavraLabel.setFont(new Font("MesloLGS NF", Font.BOLD, 22));
		palavraLabel.setBounds(51, 142, 450, 83);
		palavraLabel.setVisible(false);
		panel.add(palavraLabel);
		palavraLabel.setText("Palavra: " + jogo.getPalavraAdvinhada());
		
		dicaLabel = new JLabel("");
		dicaLabel.setFont(new Font("MesloLGS NF", Font.BOLD, 15));
		dicaLabel.setBounds(51, 107, 424, 35);
		panel.add(dicaLabel);
		dicaLabel.setText("Dica: " + jogo.getDica());
		dicaLabel.setVisible(false);
		
		letraInput = new JTextField();
		letraInput.setFont(new Font("MesloLGS NF", Font.PLAIN, 50));
		letraInput.setBounds(71, 244, 61, 57);
		panel.add(letraInput);
		letraInput.setColumns(10);
		letraInput.setVisible(false);
		
		lblTentativas = new JLabel("Tentativas: " + String.valueOf(jogo.getTentativas()));
		lblTentativas.setBounds(532, 246, 124, 15);
		panel.add(lblTentativas);
		lblTentativas.setVisible(false);
		
		imgLabel = new JLabel("");
		imgLabel.setBounds(487, 61, 174, 145);
		setImage(imgLabel);
		panel.add(imgLabel);
		imgLabel.setVisible(false);
				
		checkLabel = new JLabel("");
		checkLabel.setBounds(268, 353, 382, 95);
		panel.add(checkLabel);
		checkLabel.setVisible(false);
		
		gameOverLabel = new JLabel("");
		gameOverLabel.setFont(new Font("MesloLGS NF", Font.BOLD, 27));
		gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameOverLabel.setBounds(97, 223, 509, 225);
		panel.add(gameOverLabel);
		gameOverLabel.setVisible(false);
		
		
		JButton btnIniciar = new JButton("Iniciar");
		checarBtn = new JButton("Checar");
		
		ArrayList<Component> componentes = new ArrayList<>(
				List.of(palavraLabel, dicaLabel, letraInput, checarBtn, lblTentativas, imgLabel)
				);

		
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnIniciar.setBounds(268, 222, 165, 63);
					gameOverLabel.setVisible(false);
					imgLabel.setBounds(487, 36, 174, 145);
					setImage(imgLabel);
					btnIniciar.setVisible(false);
					ComponentVisibilityHelper.setVisibility(componentes, true);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnIniciar.setBounds(268, 222, 165, 63);
		btnIniciar.setVisible(true);
		imgLabel.setBounds(487, 36, 174, 145);
		panel.add(btnIniciar);	
		
		checarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					checkLabel.setText("");
					var checkResult = jogo.getOcorrencias(letraInput.getText());
					if (checkResult.size() == 0) {
						setImage(imgLabel);
					} else {
						palavraLabel.setText("Palavra: " + jogo.getPalavraAdvinhada());
					}
					lblTentativas.setText("Tentativas: " + String.valueOf(jogo.getTentativas()));
					if (jogo.terminou()) {
						ComponentVisibilityHelper.setVisibility(componentes, false);
						gameOverLabel.setText(jogo.getResultado());
						gameOverLabel.setVisible(true);
						imgLabel.setBounds(268, 81, 174, 145);
						imgLabel.setVisible(true);
						btnIniciar.setBounds(268, 400, 165, 63);
						btnIniciar.setVisible(true);
						btnIniciar.setText("Novo Jogo");
						reset();
					}
				} catch (Exception e1) {
					checkLabel.setText(e1.getMessage());
					checkLabel.setVisible(true);
				} finally {
					letraInput.setText("");
				}
			}
		});
		checarBtn.setBounds(139, 260, 117, 25);
		panel.add(checarBtn);
		checarBtn.setVisible(false);
	}
	
	private void setImage(JLabel label) {
		ImageIcon icon =
				new ImageIcon(TelaJogo.class.getResource(String.format("/img/%d.png", jogo.getNumeroPenalidades())));
		icon.setImage(icon.getImage().getScaledInstance(
						label.getWidth(),
						label.getHeight(),
						Image.SCALE_DEFAULT
						));
		label.setIcon(icon);
	}
	
	private class ComponentVisibilityHelper {
	    public static void setVisibility(ArrayList<Component> components, boolean visibility) {
	        for (Component component : components) {
	            component.setVisible(visibility);
	        }
	    }

	}	
}