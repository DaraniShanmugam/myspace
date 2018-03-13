package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import controller.DashboardController;
import lib.FrameView;

public class DashboardView extends FrameView {

	JSplitPane titleMain,navContents,logoControls;
	JLabel logo,diaryLabel,notesLabel,vaultLabel,close,defaultContent;
	JPanel controlPanel,menuPanel;
	DashboardController controller;
	public DashboardView() {
		super("MySpace");
		this.setName("Dashboard");
		this.setUndecorated(true);
		this.setVisible(true);
		this.setExtendedState(MAXIMIZED_BOTH);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
		initComponents();

		addComponents();
	}

	protected void initComponents() {
		controller = new DashboardController();
		this.addKeyListener(controller);
		
		titleMain = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		titleMain.setDividerSize(0);
		titleMain.setBorder(null);
		
		
		logoControls = new JSplitPane();
		logoControls.setDividerSize(0);
		logoControls.setBorder(null);
		logoControls.setMinimumSize(new Dimension(80,50));
		logoControls.setPreferredSize(new Dimension(80,50));
		logoControls.setBackground(new Color(52, 73, 94));
		logoControls.setOpaque(true);
		
		
		
		
		controlPanel = new JPanel();
		controlPanel.setOpaque(false);
		
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT,0,0);
		//flowLayout.setAlignment(FlowLayout.RIGHT);
		controlPanel.setLayout(flowLayout);
		controlPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		logo = new JLabel("MySpace");
		logo.setFont(new Font("Raleway Light", Font.PLAIN, 18));
		logo.setVerticalTextPosition(SwingConstants.CENTER);
		logo.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
		logo.setForeground(Color.WHITE);
		logo.setOpaque(false);
		close = new JLabel("X");
		close.setFont(new Font("Raleway", Font.PLAIN, 15));
		//close.setAlignmentX(CENTER_ALIGNMENT);
		close.setVerticalTextPosition(SwingConstants.CENTER);
		close.setAlignmentX(CENTER_ALIGNMENT);
		close.setAlignmentY(CENTER_ALIGNMENT);
		close.setHorizontalAlignment(SwingConstants.CENTER);
		close.setForeground(Color.WHITE);
		//close.setBorder(BorderFactory.createEmptyBorder(20, 15, 15, 0));
		close.setPreferredSize(new Dimension(50,50));
		controlPanel.revalidate();
		close.addMouseListener(controller);
		
		close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		close.setBackground(new Color(225,0,0));
		
		navContents = new JSplitPane();
		navContents.setDividerSize(0);
		navContents.setBorder(null);
		
		menuPanel = new JPanel();
		menuPanel.setMinimumSize(new Dimension(250,90));
		menuPanel.setBackground(new Color(200,200,200));
		menuPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(185,185,185)));
		
		//defaultContentPanel = new JPanel();
		//defaultContentPanel.setBackground(new Color(245,245,245));
		
		diaryLabel = new JLabel("Diary");
		diaryLabel.setFont(new Font("Raleway Medium", Font.PLAIN, 18));
		
		
		notesLabel = new JLabel("Notes");
		
		vaultLabel = new JLabel("Vault");
		
		
		
		defaultContent = new JLabel("No component selected");
		defaultContent.setFont(new Font("Raleway Bold", Font.PLAIN, 40));
		defaultContent.setForeground(new Color(200,200,200));
		defaultContent.setAlignmentX(CENTER_ALIGNMENT);
		defaultContent.setHorizontalAlignment(SwingConstants.CENTER);
		defaultContent.setBackground(new Color(245,245,245));
		defaultContent.setOpaque(true);
		
	}

	protected void addComponents() {
		this.add(titleMain);
		
		titleMain.setLeftComponent(logoControls);
		
		controlPanel.add(close);
		
		logoControls.setLeftComponent(logo);
		
		logoControls.setRightComponent(controlPanel);
		
		titleMain.setRightComponent(navContents);
		
		GridBagLayout menuGrid = new GridBagLayout();
		GridBagConstraints menuGridConstraints = new GridBagConstraints();
		menuPanel.setLayout(menuGrid);
		//addToGrid();
		
		navContents.setLeftComponent(menuPanel);
		//defaultContentPanel.add(defaultContent);
		
		navContents.setRightComponent(defaultContent);
		
		
	}
	
	public JComponent getCloseLabel() {
		return close;
	}

}
