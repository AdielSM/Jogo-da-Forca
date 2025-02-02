import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class TelaJogo {

    private JLabel palavraLabel;
	private JogoDaForca jogo;
	private JTextField letraInput;
    private JButton btnIniciar;
	private JLabel checkLabel;
	private JLabel lblTentativas;
	private JLabel imgLabel;
	private JLabel gameOverLabel;
	private JLabel dicaLabel;
	private JLabel lblAcertos;
	private JLabel lblErros;

	/**
	 * Create the application.
     */
	public TelaJogo() throws Exception {
		this.jogo = new JogoDaForca();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
     */
	
	private void reset() throws Exception {
		this.jogo = new JogoDaForca();
		this.palavraLabel.setText("Palavra: " + jogo.getPalavraAdvinhada());
		this.lblTentativas.setText("Tentativas: " + jogo.getTentativas());
		this.lblAcertos.setText("Acertos: " + jogo.getAcertos());
		this.lblErros.setText("Erros: " + jogo.getNumeroPenalidades());
		this.dicaLabel.setText("Dica: " + jogo.getDica());
	}
	
	private void initialize() {
        JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(200, 200, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Jogo da Forca");

        JPanel panel = new JPanel();
		panel.setBounds(12, 29, 776, 559);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		btnIniciar = new JButton("Iniciar");
		btnIniciar.setBounds(296, 250, 165, 63);
		btnIniciar.setVisible(true);
		panel.add(btnIniciar);	
		
		
		palavraLabel = new JLabel();
		palavraLabel.setFont(new Font("MesloLGS NF", Font.BOLD, 22));
		palavraLabel.setBounds(79, 170, 450, 83);
		palavraLabel.setVisible(false);
		panel.add(palavraLabel);
		palavraLabel.setText("Palavra: " + jogo.getPalavraAdvinhada());

		dicaLabel = new JLabel("");
		dicaLabel.setFont(new Font("MesloLGS NF", Font.BOLD, 15));
		dicaLabel.setBounds(79, 135, 424, 35);
		panel.add(dicaLabel);
		dicaLabel.setText("Dica: " + jogo.getDica());
		dicaLabel.setVisible(false);
		
		letraInput = new JTextField();
		letraInput.setHorizontalAlignment(SwingConstants.CENTER);
		letraInput.setFont(new Font("MesloLGS NF", Font.PLAIN, 50));
		letraInput.setBounds(99, 272, 61, 57);
		panel.add(letraInput);
		letraInput.setColumns(10);
		letraInput.setVisible(false);
		
		lblTentativas = new JLabel("Tentativas: " + jogo.getTentativas());
		lblTentativas.setFont(new Font("MesloLGS NF", Font.BOLD, 20));
		lblTentativas.setBounds(485, 272, 193, 15);
		panel.add(lblTentativas);
		lblTentativas.setVisible(false);
		
		lblAcertos = new JLabel("Acertos: " + jogo.getAcertos());
		lblAcertos.setBounds(495, 301, 140, 15);
		lblAcertos.setFont(new Font("MesloLGS NF", Font.PLAIN, 12));
		panel.add(lblAcertos);
		lblAcertos.setVisible(false);
		
		lblErros = new JLabel("Erros: " + jogo.getNumeroPenalidades());
		lblErros.setBounds(495, 324, 140, 15);
		lblErros.setFont(new Font("MesloLGS NF", Font.PLAIN, 12));
		panel.add(lblErros);
		lblErros.setVisible(false);
		
		imgLabel = new JLabel("");
		imgLabel.setBounds(494, 36, 174, 145);
		setImage(imgLabel);
		panel.add(imgLabel);
		imgLabel.setVisible(false);
				
		checkLabel = new JLabel("");
		checkLabel.setFont(new Font("MesloLGS NF", Font.PLAIN, 15));
		checkLabel.setHorizontalAlignment(SwingConstants.LEFT);
		checkLabel.setBounds(99, 352, 362, 63);
		panel.add(checkLabel);
		checkLabel.setVisible(false);
		
		gameOverLabel = new JLabel("");
		gameOverLabel.setFont(new Font("MesloLGS NF", Font.BOLD, 27));
		gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameOverLabel.setBounds(251, 265, 253, 102);
		panel.add(gameOverLabel);
		gameOverLabel.setVisible(false);

        JButton checarBtn = new JButton("Checar");
		checarBtn.setBounds(167, 288, 117, 25);
		panel.add(checarBtn);
		checarBtn.setVisible(false);
		
		
		
		ArrayList<Component> componentes = new ArrayList<>(
				List.of(palavraLabel, dicaLabel, 
						letraInput, checarBtn, 
						lblTentativas, imgLabel,
						lblErros, lblAcertos)
				);
		ArrayList<Component> componentesJogoNovo = new ArrayList<>(
				List.of(btnIniciar, imgLabel, gameOverLabel)
				);
		imgLabel.setBounds(487, 36, 174, 145);
		
		
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnIniciar.setBounds(296, 250, 165, 63);
					imgLabel.setBounds(487, 36, 174, 145);
					setImage(imgLabel);
					ComponentVisibilityHelper.setVisibility(componentesJogoNovo, false);
					ComponentVisibilityHelper.setVisibility(componentes, true);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		checarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					checkLabel.setText("");
					var checkResult = jogo.getOcorrencias(letraInput.getText());
					if (checkResult.isEmpty()) {
						setImage(imgLabel);
					} else {
						palavraLabel.setText("Palavra: " + jogo.getPalavraAdvinhada());
					}
					lblTentativas.setText("Tentativas: " + jogo.getTentativas());
					lblAcertos.setText("Acertos: " + jogo.getAcertos());
					lblErros.setText("Erros: " + jogo.getNumeroPenalidades());
					if (jogo.terminou()) {
						ComponentVisibilityHelper.setVisibility(componentes, false);
						gameOverLabel.setText(jogo.getResultado());
						imgLabel.setBounds(285, 81, 174, 145);
						btnIniciar.setBounds(296, 400, 165, 63);
						btnIniciar.setText("Novo Jogo");
						ComponentVisibilityHelper.setVisibility(componentesJogoNovo, true);
						reset();
					}
					letraInput.setText("");
				} catch (Exception e1) {
					checkLabel.setText(e1.getMessage());
					checkLabel.setVisible(true);
				}
			}
		});
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
	
	private static class ComponentVisibilityHelper {
	    public static void setVisibility(ArrayList<Component> components, boolean visibility) {
	        for (Component component : components) {
	            component.setVisible(visibility);
	        }
	    }
	}	
}