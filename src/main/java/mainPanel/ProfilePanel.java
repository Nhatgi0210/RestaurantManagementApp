package mainPanel;

import java.awt.Rectangle;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ProfilePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ProfilePanel() {
		setBounds(new Rectangle( 1073, 587));
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Profile");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblNewLabel, BorderLayout.CENTER);
	}

}
