package controller;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.JLabel;

import view.App;
import view.DashboardView;
import view.LoginWindow;

public class DashboardController implements KeyListener,MouseListener{

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.isControlDown() && e.getKeyCode() == 76) {
			App.lockedFromApp = true;
			App.lock = new LoginWindow();
			App.view.setVisible(false);
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == ((DashboardView)App.view).getCloseLabel()) {
			App.view.dispose();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//if(((DashboardView)App.view).getCloseLabel().getVisibleRect().contains(e.getPoint())) {
			((DashboardView)App.view).getCloseLabel().setOpaque(true);
			((DashboardView)App.view).getCloseLabel().repaint();
			
		//}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//if(!((DashboardView)App.view).getCloseLabel().getVisibleRect().contains(e.getPoint())) {
			((DashboardView)App.view).getCloseLabel().setOpaque(false);
			((DashboardView)App.view).getCloseLabel().repaint();
			
		//}
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
