package rockPaperScissors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * @author Aldo R Valdes
 *
 */


@SuppressWarnings("serial")
public class RPSGui extends JFrame {

	private JPanel contentPane;
	private JPanel gameChoicesPanel;
	private JPanel selectionPanel;
	private JPanel titlePanel;
	private JPanel infoPanel;
	private JPanel bottomPanel;
	
	private JButton userSelectionButton;
	private JButton computerSelectionButton;
	private JButton playButton;
	
	private JLabel titleLabel;
	private JLabel userLabel;
	private JLabel computerLabel;
	private JLabel userScoreLabel;
	private JLabel computerScoreLabel;
	private JLabel chooseGamePieceLabel;
	
	private JTextField gameResultTextField;
	private JTextField gameProcessTextField;
	
	private GameChoices userChoice;
	
	private JComboBox<GameChoices> gameChoicesComboBox;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RPSGui frame = new RPSGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RPSGui() {
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		
		//Content Pane
		createContentPane();
		
		//Selection Pane
		createSelectionPane();
		
		//Title Pane
		createTitlePane();
		
		//Info Pane
		createInfoPane();
		
		//Result Pane
		createControlPane();
		
	}

	
	private class gamePieceSelectionHandler implements ItemListener {
		
		public void itemStateChanged(ItemEvent itemEvent) {
			
			if(itemEvent.getStateChange() == ItemEvent.SELECTED) {
				
				userChoice = (GameChoices) itemEvent.getItem();
				userSelectionButton.setIcon(userChoice.icon);
				gameProcessTextField.setText(userChoice.toString());
				
			}
		}
	}
	
	
	private class playButtonHandler implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			
			try {
				
				GameChoices randomChoice = GameChoices.randomChoiceGenerator();
				String resultText = userChoice.gameChoicesEvaluation(userChoice, randomChoice);
				
				if(resultText == "You Won!") {
					
					gameResultTextField.setForeground(Color.GREEN);
					gameResultTextField.setText(resultText);
				}
				else {
			
					gameResultTextField.setForeground(Color.RED);
					gameResultTextField.setText(resultText);
				}
				
				computerSelectionButton.setIcon(randomChoice.icon);
				gameProcessTextField.setText(userChoice.toString() + randomChoice.name());
				
				userScoreLabel.setText(Integer.toString(GameChoices.userScore) + " pts");
				computerScoreLabel.setText(Integer.toString(GameChoices.computerScore) + " pts");
				
				
			} catch(NullPointerException exception) {
				
				JOptionPane.showMessageDialog(null, "You have not chosen a game piece! Please select one from the list.");
				
			}
		}
	}
	
	
	private void createContentPane() {
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}
	
	
	private void createSelectionPane() {
		
		gameChoicesPanel = new JPanel();
		contentPane.add(gameChoicesPanel, BorderLayout.CENTER);
		gameChoicesPanel.setLayout(new GridLayout(1, 3, 0, 0));
		
		selectionPanel = new JPanel();
		selectionPanel.setLayout(new GridLayout(3, 1, 0, 0));
		
		gameChoicesComboBox = new JComboBox<GameChoices>();
		gameChoicesComboBox.setFocusable(false);
		gameChoicesComboBox.setModel(new DefaultComboBoxModel<>(GameChoices.values()));
		gameChoicesComboBox.setSelectedIndex(-1);
		gameChoicesComboBox.addItemListener(new gamePieceSelectionHandler());
		
		userSelectionButton = new JButton("");
		userSelectionButton.setFocusable(false);
		userSelectionButton.setContentAreaFilled(false);
		
		computerSelectionButton = new JButton("");
		computerSelectionButton.setFocusable(false);
		computerSelectionButton.setContentAreaFilled(false);
		
		gameProcessTextField = new JTextField();
		gameProcessTextField.setFocusable(false);
		gameProcessTextField.setEditable(false);
		gameProcessTextField.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		gameProcessTextField.setHorizontalAlignment(SwingConstants.CENTER);
		
		gameChoicesPanel.add(userSelectionButton);
		gameChoicesPanel.add(selectionPanel);
		gameChoicesPanel.add(computerSelectionButton);
		
		chooseGamePieceLabel = new JLabel("Choose a Game Piece:");
		chooseGamePieceLabel.setBorder(new EmptyBorder(0, 0, 40, 0));
		chooseGamePieceLabel.setFont(new Font("Papyrus", Font.BOLD, 12));
		chooseGamePieceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		selectionPanel.add(chooseGamePieceLabel);
		selectionPanel.add(gameChoicesComboBox);
		selectionPanel.add(gameProcessTextField);
		
	}

	
	private void createControlPane() {
		
		bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		gameResultTextField = new JTextField("");
		gameResultTextField.setFocusable(false);
		gameResultTextField.setEditable(false);
		gameResultTextField.setFont(new Font("Forte", Font.PLAIN, 26));
		gameResultTextField.setHorizontalAlignment(SwingConstants.CENTER);
		
		playButton = new JButton("Hit to Play!");
		playButton.setFocusable(false);
		playButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		playButton.addActionListener(new playButtonHandler());
		
		bottomPanel.add(gameResultTextField);
		bottomPanel.add(playButton);
		
	}
	
	
	private void createTitlePane() {
		
		titlePanel = new JPanel();
		contentPane.add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(new BorderLayout(0, 0));
		
		titleLabel = new JLabel("R.P.S. Game");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		titlePanel.add(titleLabel, BorderLayout.CENTER);
		
	}

	
	private void createInfoPane() {
		
		infoPanel = new JPanel();
		titlePanel.add(infoPanel, BorderLayout.EAST);
		infoPanel.setLayout(new GridLayout(2, 2, 0, 0));
		
		userLabel = new JLabel("User Score:");
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		userScoreLabel = new JLabel("0 pts");
		userScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		computerLabel = new JLabel("Computer Score");
		computerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		computerScoreLabel = new JLabel("0 pts");
		computerScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		infoPanel.add(userLabel);
		infoPanel.add(userScoreLabel);
		infoPanel.add(computerLabel);
		infoPanel.add(computerScoreLabel);
		
	}	
}
