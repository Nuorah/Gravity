import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.geom.Ellipse2D;

@SuppressWarnings("serial")
public class Main extends JPanel {
	
	double x0 = 400;
	double y0 = 300;
	
	double m0 = 200;
	double m1 = 50;
	
	double size0 = m0/10;
	double size1 = m1/10;
	
	double cosTheta;
	double sinTheta;
	double r;
	double F;
	double G = 1;
	
	double dx0 = -0.25;
	double dy0 = 0;
	
	double dx1 = 1;
	double dy1 = 0;
	
	double ax0 = 0;
	double ay0 = 0;
	
	double ax1 = 0;
	double ay1 = 0;
	
	double x1 = 400;
	double y1 = 400;
	
	double deltax = x0 - x1;
	double deltay = y0 - y1;

	private void moveBall() {		
		
		
		r = Math.sqrt(deltax*deltax+deltay*deltay);
		
		F = G/(r*r);
		
		cosTheta = deltax/r;
		sinTheta = deltay/r;
		
		ax0 = -m1*F*cosTheta;
		ay0 = -m1*F*sinTheta;
		
		dx0+=ax0;
		dy0+=ay0;
		
		x0+=dx0;
		y0+=dy0;	
		
		ax1 = m0*F*cosTheta;
		ay1 = m0*F*sinTheta;
		
		dx1+=ax1;
		dy1+=ay1;
		
		x1+=dx1;
		y1+=dy1;
		
		deltax = x0 - x1;
		deltay = y0 - y1;

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.draw(new Ellipse2D.Double(x0, y0, size0, size0));
		g2d.draw(new Ellipse2D.Double(x1, y1, size1, size1));
		
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Mini Tennis");
		Main game = new Main();
		frame.add(game);
		frame.setSize(800, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			game.moveBall();
			game.repaint();
			Thread.sleep(10);
		}
	}
}