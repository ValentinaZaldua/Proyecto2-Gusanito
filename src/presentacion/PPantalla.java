package presentacion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import logica.JGusanito;

public class PPantalla extends JPanel{
	private JGusanito jGusanito;

	public void setjGusanito(JGusanito jGusanito) {
		this.jGusanito = jGusanito;
	}
	
	public PPantalla() {
		
	}
	
	@Override
	public void paint(Graphics g) {
		int dH = this.getHeight()/20;
		int dW = this.getWidth()/20;
		Graphics g2d = (Graphics2D)g;
		g2d.setColor(Color.PINK);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(Color.WHITE);
		for(int i=0; i<=this.getWidth(); i+=this.getWidth()/20) {
			g2d.drawLine(i, 0, i, this.getHeight());	
		}
		for(int i=0; i<=this.getHeight(); i+=this.getHeight()/20) {
			g2d.drawLine(0, i, this.getWidth(), i);	
		}		
		if(this.jGusanito !=null) {
			for(int i=0; i<20;i++) {
				for(int j=0;j<20;j++) {
					if(this.jGusanito.getMatriz()[i][j] ==1) {
						g2d.setColor(Color.RED);
						g2d.fillRect((j*dW)+1,(i*dH)+1, dW-1, dH-1);
					}else if(this.jGusanito.getMatriz()[i][j] ==2) {
						g2d.setColor(Color.GRAY);
						g2d.fillRect((j*dW)+1,(i*dH)+1, dW-1, dH-1);
					}
				}
			}
		}
	}
}
