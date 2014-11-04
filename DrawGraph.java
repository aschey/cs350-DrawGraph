import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Austin Schey
 * CS350
 * Project #1
 * 
 * DrawGraph.java creates a graph window using the FunctionGraph class
 */

public class DrawGraph {
    public static void main(String[] args) {
        int choice = 0;
        int numLines = 0;

        try (Scanner input = new Scanner(new File("curve.txt"))) {
            choice = input.nextInt();
            numLines = input.nextInt();
        }

        catch (IOException e) {
        	System.err.println(e);
            System.err.println("Error: File not found");
            System.exit(1);
        }

        catch (NoSuchElementException e) {
        	System.err.println(e);
            System.err.println("Error: Missing or invalid argument");
            System.exit(1);
        }

        JLabel title = new JLabel();
        
        switch (choice) {
            case 2:
                title.setText("<html>Graph of e<sup>-0.25x</sup></html>");
                break;
            case 3:
                title.setText("<html>Graph of e<sup>-0.5x</sup></html>");
                break;
            case 4:
                title.setText("<html>Graph of e<sup>-0.75x</sup></html>");
                break;
            case 5:
                title.setText("<html>Graph of e<sup>-x</sup></html>");
                break;
            default:
                title.setText("<html>Graph of 1 - x</html>");
        }

        FunctionGraph graph = new FunctionGraph(choice, numLines);
        graph.setBackground(new Color(0xA9FFFC));
        JFrame frame = new JFrame("Graph");
        frame.setLayout(new GridLayout());
        frame.setContentPane(graph);
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xA9FFFC));
        panel.add(title);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(350, 350));
        frame.setVisible(true);
    }
}
