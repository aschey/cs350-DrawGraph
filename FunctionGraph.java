import javax.swing.*;
import java.awt.*;

/**
 * Austin Schey
 * CS350
 * Project #1
 * 
 * FunctionGraph.java creates a JPanel with a graph on it
 */

public class FunctionGraph extends JPanel{
    private int choice;
    private int numLines;
    private int[][] coords;
    private final int Y_AXIS_MARGIN = 40;
    private final int LINE_SIZE = 240;
    private static final long serialVersionUID = 0L;

    public FunctionGraph(int choice, int numLines) {
        this.choice = choice;
        this.numLines = numLines;
        this.coords = new int[numLines + 1][2];
    }

    @Override
    protected void paintComponent(Graphics g) {
        final int X_AXIS_MARGIN = LINE_SIZE / 2 + Y_AXIS_MARGIN;
        final int LINE_TAIL = 5;
        final int TICK_LENGTH = 5;
        final int X_TICK_INTERVAL = this.LINE_SIZE / 10;
        final double X_LABEL_INTERVAL = 0.1;
        final int Y_TICK_INTERVAL = this.LINE_SIZE / 4;
        final double Y_LABEL_INTERVAL = 0.5;
        final int X_TICK_Y2 = this.Y_AXIS_MARGIN + this.LINE_SIZE / 2 + TICK_LENGTH;
        final int X_TICK_Y1 = this.Y_AXIS_MARGIN + this.LINE_SIZE / 2 - TICK_LENGTH;
        final int Y_TICK_X2 = TICK_LENGTH + this.Y_AXIS_MARGIN;
        final int Y_TICK_X1 = Y_TICK_X2 - 2 * TICK_LENGTH;
        final int X_LABEL_Y = X_TICK_Y1 + 4 * TICK_LENGTH;
        final int Y_LABEL_X = Y_TICK_X1 - 4 * TICK_LENGTH;

        super.paintComponent(g);
        
        g.setColor(new Color(0x3A4853));

        // draw the axes
        g.drawLine(this.Y_AXIS_MARGIN, this.Y_AXIS_MARGIN - LINE_TAIL,
                this.Y_AXIS_MARGIN, this.Y_AXIS_MARGIN + this.LINE_SIZE + LINE_TAIL);
        
        g.drawLine(this.Y_AXIS_MARGIN - LINE_TAIL, X_AXIS_MARGIN,
                this.Y_AXIS_MARGIN + this.LINE_SIZE + LINE_TAIL, X_AXIS_MARGIN);

        // draw tick marks on the x axis
        for (int i = 0; i <= 10; i++) {
            int xTickX = i * X_TICK_INTERVAL + this.Y_AXIS_MARGIN;
            double xLabel = Math.round(i * X_LABEL_INTERVAL * 10) / 10.0;
            g.drawLine(xTickX, X_TICK_Y1, xTickX, X_TICK_Y2);
            // subtracting six aligns the numbers with the y axis nicely
            g.drawString(Double.toString(xLabel), xTickX - 6, X_LABEL_Y);
        }
        // draw tick marks on the y axis
        for (int j = 0; j <= 4; j++) {
            int yTickY = j * Y_TICK_INTERVAL + this.Y_AXIS_MARGIN;
            double yLabel = Math.round((1 - j * Y_LABEL_INTERVAL) * 10) / 10.0;
            g.drawLine(Y_TICK_X1, yTickY, Y_TICK_X2, yTickY);
            g.drawString(Double.toString(yLabel), Y_LABEL_X, yTickY);
        }

        this.generatePoints();

        // draw the function
        Color[] colors = {new Color(0xBBBBBB), new Color(0x499EFF), new Color(0x0000FF)};
        int i = 0;
        for (int k = 0; k < this.coords.length - 1; k++) {
            g.setColor(colors[i++ % 3]);
            g.drawLine(this.coords[k][0], this.coords[k][1],
                    this.coords[k + 1][0], this.coords[k + 1][1]);
        }
    }

    private void generatePoints() {
        double increment = 1.0 / this.numLines;
        double y;
        int xVal;
        double xCoord;
        int yVal;
        
        for (int x = 0; x <= this.numLines; x++) {
            xCoord = x * increment;
            y = this.getYVal(xCoord);
            xVal = (int) (xCoord * this.LINE_SIZE + this.Y_AXIS_MARGIN);
            // Invert y coordinates because of flipped coordinate system
            yVal = (int) (this.LINE_SIZE / 2 - (y * this.LINE_SIZE / 2 - this.Y_AXIS_MARGIN));
            this.coords[x] = new int[]{xVal, yVal};
        }
    }

    private double getYVal(double x) {
        double result;
        switch (this.choice) {
            case 2:
                result = Math.pow(Math.E, -0.25 * x);
                break;
            case 3:
                result = Math.pow(Math.E, -0.5 * x);
                break;
            case 4:
                result = Math.pow(Math.E, -0.75 * x);
                break;
            case 5:
                result = Math.pow(Math.E, -1.0 * x);
                break;
            default:
                result =  1.0 - x;
                break;
        }
        return result;
    }
}
