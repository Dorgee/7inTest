import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.fazecast.jSerialComm.*;

public class Screen extends JFrame {
    public JPanel panelMain;
    private JComboBox listOfComports;
    private JButton dataOne;
    private JButton dataThree;
    private JButton data2;
    private JLabel baudRate;
    private JButton connect;
    private JLabel conn;
    private JTextField connectionStatus;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextField baudRateValue;
    private JTextField baudrateMessage;
    private JButton setBaudrate;
    private String comport;


    public Screen() {
        SerialPort[] ports = SerialPort.getCommPorts();

        dataOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("0x00");
            }
        });
        data2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("0x10");
            }
        });
        dataThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("0x20");
            }
        });


        ArrayList<String> portList = new ArrayList<>();
        for (SerialPort port : ports) {
            portList.add(port.getSystemPortName());
        }
        for (String port : portList) {
            listOfComports.addItem(port);
        }
        listOfComports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comport = listOfComports.getSelectedItem().toString();
                //  System.out.println(comport);


            }
        });


        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SerialPort port = getPort(comport, ports);
                port.openPort();
//                System.out.println(port.isOpen());
                if (port.isOpen()) {
                    connectionStatus.setText("Connected and port is now open");
                }
            }
        });
        setBaudrate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SerialPort port = getPort(comport, ports);
                if (baudRateValue.getText().equals("")) {
                    baudrateMessage.setText("Please enter a Proper value");
                }
                if (port == null) {
                    baudRateValue.setText("You must connect to a COM port first");
                }
                baudrateMessage.setText("Baudrate has been set to: " + baudRateValue.getText());
                baudRateValue.setText("");


            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static SerialPort getPort(String portName, SerialPort[] ports) {
        for (SerialPort port : ports) {
            if (port.getSystemPortName().equals(portName)) {
                SerialPort comPort = port;
                return comPort;
            }
        }
        return null;
    }
}
