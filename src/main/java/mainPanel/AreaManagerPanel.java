package mainPanel;

import java.awt.Rectangle;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class AreaManagerPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AreaManagerPanel() {
		setBounds(new Rectangle( 1073, 587));
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("area");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(lblNewLabel, BorderLayout.CENTER);
	}

}
