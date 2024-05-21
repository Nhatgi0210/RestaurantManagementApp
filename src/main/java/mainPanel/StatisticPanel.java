package mainPanel;

import java.awt.Rectangle;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class StatisticPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public StatisticPanel() {
		setBounds(new Rectangle( 1073, 587));
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Statistic");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.CENTER);
	}

}
