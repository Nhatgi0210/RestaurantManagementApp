package mainPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import controller.ClientController;
import models.Area;
import models.Restaurant;
import models.Table;
import view.OrderFrame;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;

public class listTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton buttonSelected;
	private ArrayList<Table> tables;
	private ArrayList<Area> areas;
	private ClientController clientController;
	private Restaurant restaurant;
	private ImageIcon freeTable = new ImageIcon(getClass().getResource("/image/tableFree.png"));
	private ImageIcon activeTable = new ImageIcon(getClass().getResource("/image/tableActive.png"));
	private JPanel tablePanel;
	private OrderFrame orderFrame;
	private JPanel areaPanel;

	/**
	 * Create the panel.
	 */
	public listTablePanel(OrderFrame orderFrame,ClientController clientController, Restaurant restaurant) {
		this.clientController = clientController;
		this.restaurant = restaurant;
		this.orderFrame = orderFrame;
		UIManager.put("ScrollBar.width", 5);
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(211, 691));
		add(scrollPane, BorderLayout.WEST);

		areaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		areaPanel.setBackground(Color.decode("#0F433D"));
		areaPanel.setPreferredSize(new Dimension(211, 900));

		getAllArea();

		for (Area area : areas) {
			JButton button = new JButton(area.getName());

			button.setPreferredSize(new Dimension(211, 40));
			button.setBackground(Color.decode("#0F433D"));
			button.setForeground(Color.white);
			button.setBorderPainted(false);
			if (area == areas.get(0)) {
				buttonSelected = button;
				buttonSelected.setBackground(Color.decode("#145DA0"));
			}
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonSelected.setBackground(new Color(15, 67, 61));
					buttonSelected = button;
					buttonSelected.setBackground(Color.decode("#145DA0"));
					filter(area.getId());
				}
			});
			areaPanel.add(button);
		}
		
		scrollPane.setViewportView(areaPanel);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(1073, 587));
		add(scrollPane_1, BorderLayout.CENTER);

		tablePanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) tablePanel.getLayout();
		flowLayout.setVgap(40);
		flowLayout.setHgap(40);
		flowLayout.setAlignment(FlowLayout.LEFT);
		tablePanel.setPreferredSize(new Dimension(1073, 900));
		scrollPane_1.setViewportView(tablePanel);
		
		filter(areas.get(0).getId());

	}

	public void getAllArea() {

		Gson gson = new Gson();
		String[] control = { "getAllArea", Integer.toString(restaurant.getId()) };
		areas = gson.fromJson(clientController.sendRequest(control), new TypeToken<ArrayList<Area>>() {
		}.getType());
	}

	public void filter(int idArea) {
		Gson gson = new Gson();
		String[] control = { "getTableByArea", Integer.toString(idArea) };
		tables = gson.fromJson(clientController.sendRequest(control), new TypeToken<ArrayList<Table>>() {
		}.getType());
		loadTable();
	}

	public void loadTable() {
		tablePanel.removeAll();
		for (Table table : tables) {
			JLabel lb = new JLabel(table.getName());
			if (table.getStatus().getId().equals("free"))
				lb.setIcon(new ImageIcon(freeTable.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			else
				lb.setIcon(new ImageIcon(activeTable.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			lb.setVerticalTextPosition(SwingConstants.BOTTOM);
			lb.setHorizontalTextPosition(SwingConstants.CENTER);
			lb.setFont(new Font("Tahoma", Font.BOLD, 15));
			lb.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					orderFrame.showOrderPanel(table);
				}
			});
			tablePanel.add(lb);

		}
		revalidate();
		repaint();
	}
	public void loadAll() {
		getAllArea();
		areaPanel.removeAll();
		for (Area area : areas) {
			JButton button = new JButton(area.getName());

			button.setPreferredSize(new Dimension(211, 40));
			button.setBackground(Color.decode("#0F433D"));
			button.setForeground(Color.white);
			button.setBorderPainted(false);
			if (area == areas.get(0)) {
				buttonSelected = button;
				buttonSelected.setBackground(Color.decode("#145DA0"));
			}
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonSelected.setBackground(new Color(15, 67, 61));
					buttonSelected = button;
					buttonSelected.setBackground(Color.decode("#145DA0"));
					filter(area.getId());
				}
			});
			areaPanel.add(button);
		}
		filter(areas.get(0).getId());
	}
}
