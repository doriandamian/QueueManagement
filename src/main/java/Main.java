import BusinessLogic.SimulationManager;
import GUI.SimulationFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SimulationFrame simulationFrame = new SimulationFrame();
        simulationFrame.setSize(1200, 800);
        simulationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simulationFrame.setResizable(false);
        simulationFrame.setVisible(true);
        simulationFrame.setContentPane(simulationFrame.JPanel);
    }
}