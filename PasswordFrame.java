package passwordStrengthCheckerAndGenerator;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class PasswordFrame extends JFrame implements ActionListener, ItemListener {
	private static final long serialVersionUID = 1L;
	private boolean includeSpecialCharacters;
	private char[] passwordCharacters;
	private JCheckBox withSpecialCharacters;
	private JLabel genLabel;
	private JTextField passLenBox;
	private JButton genButton;
	private JButton strengthButton;
	private JTextField generatedPassword;
	private JLabel passwordStrengthResults;
	JPasswordField passwordBox;

	public PasswordFrame() {
		includeSpecialCharacters = false;
		passwordCharacters = PasswordFunctions.passwordArrayPrep();
		JPanel everything = new JPanel();
		JTabbedPane tabPanel = new JTabbedPane();

		// *** password generator stuff ***
		JPanel genTab = new JPanel();
		genTab.setPreferredSize(new Dimension(300, 350));
		genTab.setLayout(new GridLayout(5, 1));

		genLabel = new JLabel("Select a password length:");
		passLenBox = new JTextField("", 20);
		genButton = new JButton("Generate new password!");
		withSpecialCharacters = new JCheckBox("Include special characters");
		withSpecialCharacters.addItemListener(this);
		generatedPassword = new JTextField();
		generatedPassword.setEditable(false);
		generatedPassword.setBackground(null);
		generatedPassword.setBorder(null);

		genTab.add(genLabel);
		genTab.add(passLenBox);
		genTab.add(withSpecialCharacters);
		genTab.add(genButton);
		genTab.add(generatedPassword);

		// *** password strength checker stuff ***
		JPanel checkTab = new JPanel();
		checkTab.setPreferredSize(new Dimension(300, 350));
		checkTab.setLayout(new GridLayout(3, 1));
		passwordBox = new JPasswordField();
		strengthButton = new JButton("Check the strength of your password: ");
		passwordStrengthResults = new JLabel();
		
		checkTab.add(passwordBox);
		checkTab.add(strengthButton);
		checkTab.add(passwordStrengthResults);

		genButton.addActionListener(this);
		strengthButton.addActionListener(this);
		
		// add everything
		tabPanel.add("Password Checker", checkTab);
		tabPanel.add("Password Generator", genTab);
		everything.add(tabPanel);

		this.setContentPane(everything);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == genButton) {
			try{
				int passLen = Integer.parseInt(passLenBox.getText());
				String newPass = PasswordFunctions.passwordGenerator(passLen, includeSpecialCharacters, passwordCharacters);
				generatedPassword.setText(newPass);
			}
			catch (NumberFormatException nfe) {
				generatedPassword.setText("Please enter a number greater than 0.");
			}
			
		}
		else if (src == strengthButton) {
			String password = new String(passwordBox.getPassword());
			int[] results = PasswordFunctions.passwordStrengthChecker(password);
			passwordStrengthResults.setText(resultsProcessing(results));
		}
	}

	private String resultsProcessing(int[] results) {
		String processed = "<html>";
		
		// length
		if (results[0] < 8)
			processed += "Password length: Poor<br>";
		else if (results[0] >= 8 && results[0] < 12)
			processed += "Password length: Great<br>";
		else if (results[0] >= 12)
			processed += "Password length: Amazing!<br>";
		
		// lowercase
		if (results[1] == 1)
			processed += "Contains lowercase letter(s): Yes<br>";
		else
			processed += "Contains lowercase letter(s): No<br>";
		
		// uppercase
		if (results[2] == 1)
			processed += "Contains uppercase letter(s): Yes<br>";
		else
			processed += "Contains uppercase letter(s): No<br>";
		
		// numbers
		if (results[3] == 1)
			processed += "Contains number(s): Yes<br>";
		else
			processed += "Contains number(s): No<br>";
		
		// special characters
		if (results[4] == 1)
			processed += "Contains special character(s): Yes<br>";
		else
			processed += "Contains special character(s): No<br>";
		
		
		// duplicates
		if (results[5] == 1)
			processed += "No duplicate characters?: No<br>";
		else
			processed += "No duplicate characters: Yes<br>";
			
		processed += "</html>";
		return processed;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		JCheckBox box = (JCheckBox) e.getSource();
		includeSpecialCharacters = box.isSelected();
	}

}
