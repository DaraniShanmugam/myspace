package lib;

import java.awt.Component;
import java.awt.GridBagConstraints;

import javax.swing.*;

abstract public class FrameView extends JFrame {
	public FrameView(String title) {
		super(title);
	}
	
	protected abstract void initComponents();

	protected abstract void addComponents();
	
	public void addToGridBag(JComponent parent, Component child, GridBagConstraints constraints, int x, int y,
			int width, int height, int fill) {
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		constraints.fill = fill;
		parent.add(child, constraints);
	}

	public void addToGridBag(Component parent, Component child, GridBagConstraints constraints, int x, int y,
			int fill) {
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.fill = fill;
		((JComponent) parent).add(child, constraints);
	}
}
