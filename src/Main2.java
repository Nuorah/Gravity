
	import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.geom.Ellipse2D;

	@SuppressWarnings("serial")
	public class Main2 extends JPanel {
		
		double x0 = 300;
		double y0 = 200;
		
		double cosTheta;
		double sinTheta;
		double D;
		double F;
		double G = 1.5;
		
		double x = 400;
		double y = 400;
		
		double f = 0.00;
		
		double m = 200;
		double m0 = 200;
		
		double r = m/10;
		double r0 = m0/10;
		
		double g = 0;
		double g0 = 0;
		
		double dx = -1;
		double dy = 0;
		
		double dx0 = 1;
		double dy0 = 0;
	
		double ax = 0;
		double ay = 0;
		
		double ax0 = 0;
		double ay0 = 0;
		
		double deltax = x - x0;
		double deltay = y - y0;
		
		double v;
		double v0;
		
		double cosVTheta;
		double sinVTheta;
		
		double cosV0Theta;
		double sinV0Theta;
		
		private double g(double x, double y){
			return 0;
		}

		private void moveBall() {	
			
			deltax = x - x0;
			deltay = y - y0;
			
			v = Math.sqrt(dx*dx + dy*dy);
			v0 = Math.sqrt(dx0*dx0 + dy0*dy0);
			
						

			D = Math.sqrt(deltax*deltax+deltay*deltay);
			g = 0.1;
			g0 = 0.1;
			
			if (y > 600 - r/2){ y = 600 - r/2; dy = -0.99*dy;}
			if (y < r/2){ y = r/2; dy = -dy;}
			if (x > 800 - r/2 ){ x = 800 - r/2 ; dx = -0.99*dx;}
			if (x < r/2){ x = r/2 ; dx = -dx;}
			
			if (y0 > 600 - r0/2){ y0 = 600 - r0/2; dy0 = -0.99*dy0;}
			if (y0 < r0/2){ y0 = r0/2; dy0 = -dy0;}
			if (x0 > 800 - r0/2){ x0 = 800-r0/2 ; dx0 = -0.99*dx0;}
			if (x0 < r0/2){ x0 = r0/2 ; dx0 = -dx0;}
			
			
			
			if (D <= (r + r0)/2){
				double olddx = dx;
				double olddy = dy;
				double olddx0 = dx0;
				dx =  dx - (2*m0/(m+m0))*((dx - dx0)*deltax + (dy-dy0)*deltay)*(deltax/(D*D));
				dy =  dy - (2*m0/(m+m0))*((olddx - dx0)*deltax + (dy-dy0)*deltay)*(deltay/(D*D));
				
				dx0 = dx0 + (2*m/(m+m0))*((olddx - dx0)*deltax + (olddy-dy0)*deltay)*(deltax/(D*D));
				dy0 = dy0 + (2*m/(m+m0))*((olddx - olddx0)*deltax + (olddy - dy0)*deltay)*(deltay/(D*D));
			}
			
			//if(D < (r + r0)/2 - 1 ){D = (r + r0)/2 ;}
			
			F = G/(D*D);
			if(D < (r + r0)/2){F =0;}// -0.5*G/(D*D);}
			
			cosTheta = deltax/D;
			sinTheta = deltay/D;
			
			cosVTheta = dx/v;
			sinVTheta = dy/v;
			
			cosV0Theta = dx0/v0;
			sinV0Theta = dy0/v0;
			
			ax = -f*dx -m0*F*cosTheta;
			ay = this.g(x, y) -f*dy-m0*F*sinTheta ;
			
			ax0 = -f*dx0 + m*F*cosTheta;
			ay0 = this.g(x0, y0) -f*dy0 + m*F*sinTheta;
			
			dx0 += ax0;
			dy0 += ay0;
			
			dx += ax;
			dy += ay;
			
			x+= dx;
			y+= dy;
			
			x0 += dx0;
			y0 += dy0;
			
			
			
			

		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.draw(new Ellipse2D.Double(x-r/2, y-r/2, r, r));
			g2d.draw(new Ellipse2D.Double(x0-r0/2, y0-r0/2, r0, r0));
			
		}

		public static void main(String[] args) throws InterruptedException {
			JFrame frame = new JFrame("Mini Tennis");
			Main2 game = new Main2();
			game.setPreferredSize(new Dimension(800, 600));
			frame.add(game);
			
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
		
			while (true) {
				game.moveBall();
				game.repaint();
				Thread.sleep(10);
			}
		}
	}

