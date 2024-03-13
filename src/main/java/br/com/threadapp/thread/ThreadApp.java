package br.com.threadapp.thread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class ThreadApp extends JDialog {

    private JPanel jPanel = new JPanel(new GridBagLayout()); // fazemos os controle dos componentes com o grid bag
    private JLabel fieldLabel1 = new JLabel("Time Thread1");

    private JLabel fieldLabel2 = new JLabel("Time Thread2");
    private JTextField textField1 = new JTextField();
    private JTextField textField2 =  new JTextField();
    private JButton  startButton = new JButton("Start");
    private JButton  endButton = new JButton("Stop ");


    private Runnable thread =  new Runnable(){

        @Override
        public void run() {
            while(true){

                try {
                    textField1.setText(new SimpleDateFormat("dd/MM/YYY hh:MM:ss").format(Calendar.getInstance().getTime()));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };


    private Runnable thread2 = new Runnable() {
        @Override
        public void run() {
            while(true){
                textField2.setText(new SimpleDateFormat("dd/MM/yyyy hh:MM:ss").format(new Date()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };
    private Thread thread1Time;

    private Thread thread2Time;
    public ThreadApp(){
        setTitle("Thread Application");
        setSize(new Dimension(300, 500));
        setLocationRelativeTo(null);
        setResizable(false);

        GridBagConstraints gridBagConstraint = new GridBagConstraints();
        gridBagConstraint.gridx = 0;
        gridBagConstraint.gridy = 0;
        gridBagConstraint.gridwidth = 2;
        gridBagConstraint.insets = new Insets(5, 10,5,5);
        gridBagConstraint.anchor = GridBagConstraints.WEST;

        fieldLabel1.setPreferredSize(new Dimension(200, 20));

        gridBagConstraint.gridy ++;
        jPanel.add(fieldLabel1,gridBagConstraint);

        textField1.setEditable(false);
        textField1.setPreferredSize(new Dimension(200, 20));

        gridBagConstraint.gridy ++;

        jPanel.add(textField1,gridBagConstraint);

        gridBagConstraint.gridy++;
        fieldLabel2.setPreferredSize(new Dimension(200, 20));
        jPanel.add(fieldLabel2, gridBagConstraint);

        textField2.setEditable(false);
        textField2.setPreferredSize(new Dimension(200, 20));
        gridBagConstraint.gridy++;
        jPanel.add(textField2, gridBagConstraint);

        gridBagConstraint.gridwidth = 1;
        startButton.setPreferredSize(new Dimension(100, 20));
        gridBagConstraint.gridy++;
        jPanel.add(startButton, gridBagConstraint);
        startButton.setPreferredSize(new Dimension(100, 20));
        gridBagConstraint.gridx++;
        endButton.setEnabled(false);
        startButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thread1Time = new Thread(thread);
                thread2Time = new Thread(thread2);
                thread1Time.start();
                thread2Time.start();
                startButton.setEnabled(false);
                endButton.setEnabled(true);

            }
        });

        endButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thread1Time.stop();
                thread2Time.stop();
                startButton.setEnabled(true);
                endButton.setEnabled(false);

            }
        });

        jPanel.add(endButton, gridBagConstraint);





        add(jPanel, BorderLayout.CENTER);
        setVisible(true);
    }

}
