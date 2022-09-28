import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JogoDaVelha extends JFrame{

	ImageIcon laranja = new ImageIcon(getClass().getResource("laranja.png"));
	ImageIcon maca = new ImageIcon(getClass().getResource("maçã.png"));

	
	JPanel pTela = new JPanel(new GridLayout(3, 3, 10, 10));
	
	Bloco[] blocos = new Bloco[9];
	
	int rodadas = 0;
	
	final int JOGADOR_1 = 1;
	final int JOGADOR_2 = 2;
	
	int jogadorVez = JOGADOR_1;
	
	JLabel lInformacao = new JLabel("Jogador " +JOGADOR_1);
	
	public JogoDaVelha() {
		configurarJanela();
		configurarTela();
	}
	
	public void configurarTela() {
		add(BorderLayout.CENTER, pTela);
		add(BorderLayout.NORTH,lInformacao);
		pTela.setBackground(Color.BLACK);
		lInformacao.setFont(new Font("Arial", Font.BOLD, 30));
		lInformacao.setForeground(Color.ORANGE);
		lInformacao.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < 9; i++) {
			Bloco bloco = new Bloco();
			blocos[i] = bloco;
			pTela.add(bloco);
		}
	}
	
	public void mudarVez() {
		if (jogadorVez == 1) {
			jogadorVez = 2;
			lInformacao.setText("Jogador 2");
			lInformacao.setForeground(Color.RED);
		}
		else {
			jogadorVez = 1;
			lInformacao.setText("Jogador 1");
			lInformacao.setForeground(Color.ORANGE);
		}
	}
	
	public boolean testarVitoria(int jog) {
		if(blocos[0].jogador==jog && blocos[1].jogador==jog && blocos[2].jogador==jog) {
			return true;
		}
		if(blocos[3].jogador==jog && blocos[4].jogador==jog && blocos[5].jogador==jog) {
			return true;
		}
		if(blocos[6].jogador==jog && blocos[7].jogador==jog && blocos[8].jogador==jog) {
			return true;
		}
		if(blocos[0].jogador==jog && blocos[3].jogador==jog && blocos[6].jogador==jog) {
			return true;
		}
		if(blocos[1].jogador==jog && blocos[4].jogador==jog && blocos[7].jogador==jog) {
			return true;
		}
		if(blocos[2].jogador==jog && blocos[5].jogador==jog && blocos[8].jogador==jog) {
			return true;
		}
		if(blocos[0].jogador==jog && blocos[4].jogador==jog && blocos[8].jogador==jog) {
			return true;
		}
		if(blocos[2].jogador==jog && blocos[4].jogador==jog && blocos[6].jogador==jog) {
			return true;
		}
		return false;
	}
	
	public void configurarJanela() {
		setTitle("Jogo da Velha");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new JogoDaVelha();
	}
	
	public class Bloco extends JButton {
		int jogador = 0;
		public Bloco() {
			setBackground(Color.WHITE);
			addActionListener(e->{
				if(jogador == 0) {
					if(jogadorVez == JOGADOR_1) {
						setIcon(laranja);
						jogador = JOGADOR_1;
					}
					else {
						setIcon(maca);
						jogador = JOGADOR_2;
					}
					if(testarVitoria(jogador)) {
						JOptionPane.showMessageDialog(null, "Jogador " +jogador+ " venceu! Parabéns!");
						System.exit(0);
					}
					rodadas++;
					if(rodadas == 9) {
						JOptionPane.showMessageDialog(null, "Empate!");
						System.exit(0);
					}
					mudarVez();
				}
			});
		}
	}

}
