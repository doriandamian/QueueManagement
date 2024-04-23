package GUI;

import BusinessLogic.SimulationData;
import BusinessLogic.SimulationManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame{
    public JTextField clientsTextField;
    public JButton validateButton;
    public JTextField queuesTextField;
    public JTextField intervalTextField;
    public JTextField minArrivalTextField;
    public JTextField maxArrivalTextField;
    public JTextField minServiceTextField;
    public JTextField maxServiceTextField;
    public javax.swing.JPanel JPanel;
    public JButton simulateButton;
    public JTextArea outputArea;
    public JRadioButton shortestQueueRadioButton;
    public JRadioButton shortestTimeRadioButton;
    private JScrollPane scrollPane;

    public static SimulationData data;

    private boolean containsOnlyNumbers(String str){
        return str.matches("[0-9]+");
    }

    public SimulationFrame() {
        outputArea.setText("");
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        outputArea.setLineWrap(true);
//        outputArea.setWrapStyleWord(true);
        outputArea.getDocument().putProperty("lineLimit",1000);
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dataVerify()){
                    if(shortestQueueRadioButton.isSelected())
                        data = new SimulationData(clientsTextField.getText(),queuesTextField.getText(),intervalTextField.getText(),minArrivalTextField.getText(), maxArrivalTextField.getText(), minServiceTextField.getText(), maxServiceTextField.getText(), 1);
                    else
                        data = new SimulationData(clientsTextField.getText(),queuesTextField.getText(),intervalTextField.getText(),minArrivalTextField.getText(), maxArrivalTextField.getText(), minServiceTextField.getText(), maxServiceTextField.getText(), 2);
                    simulateButton.setEnabled(true);
                    System.out.println("DATA SAVED SUCCESSFULLY");
                }
                else
                    simulateButton.setEnabled(false);
            }
        });
        simulateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimulationManager gen = new SimulationManager(SimulationData.noClients, SimulationData.noQueues,SimulationData.simulationInterval, SimulationData.minArrivalTime,SimulationData.maxArrivalTime,SimulationData.minServiceTime,SimulationData.maxServiceTime,SimulationData.selectionPolicy,SimulationFrame.this);
                Thread t = new Thread(gen);
                t.start();
            }
        });
    }

    private boolean dataVerify(){
        boolean ok = true;
        outputArea.setText("");
        if(!containsOnlyNumbers(clientsTextField.getText())){
            outputArea.append("Invalid no. of clients\n");
            ok = false;
        }
        if(!containsOnlyNumbers(queuesTextField.getText())){
            outputArea.append("Invalid no. of queues\n");
            ok = false;
        }

        if(!containsOnlyNumbers(intervalTextField.getText())){
            outputArea.append("Invalid simulation interval\n");
            ok = false;
        }

        if(!containsOnlyNumbers(minArrivalTextField.getText())){
            outputArea.append("Invalid minimum arrival time\n");
            ok = false;
        }

        if(!containsOnlyNumbers(maxArrivalTextField.getText())){
            outputArea.append("Invalid maximum arrival time\n");
            ok = false;
        }

        if((containsOnlyNumbers(minArrivalTextField.getText()) && containsOnlyNumbers(maxArrivalTextField.getText())) && Integer.parseInt(minArrivalTextField.getText()) > Integer.parseInt(maxArrivalTextField.getText())){
            outputArea.append("Minimum arrival time bigger then maximum\n");
            ok = false;
        }

        if(!containsOnlyNumbers(minServiceTextField.getText())){
            outputArea.append("Invalid minimum service time\n");
            ok = false;
        }

        if(!containsOnlyNumbers(maxServiceTextField.getText())){
            outputArea.append("Invalid maximum serivce time\n");
            ok = false;
        }
        if((containsOnlyNumbers(minServiceTextField.getText()) && containsOnlyNumbers(maxServiceTextField.getText())) && Integer.parseInt(minServiceTextField.getText()) > Integer.parseInt(maxServiceTextField.getText())){
            outputArea.append("Minimum service time bigger then maximum\n");
            ok = false;
        }
        if(!shortestQueueRadioButton.isSelected() && !shortestTimeRadioButton.isSelected()){
            outputArea.append("Choose one selection policy\n");
            ok = false;
        }
        return ok;
    }
}
