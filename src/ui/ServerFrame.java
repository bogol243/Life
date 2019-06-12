package ui;

import java.awt.*;
import java.awt.event.*;

public class ServerFrame extends Frame {
	Button startButton;
	Button stopButton;
	Button exitButton;
	Label portLabel;
	TextField portTextField;
	
	// get-set
	public Button getStartButton() {
		return startButton;
	}


	public void setStartButton(Button startButton) {
		this.startButton = startButton;
	}


	public Button getStopButton() {
		return stopButton;
	}


	public void setStopButton(Button stopButton) {
		this.stopButton = stopButton;
	}


	public Button getExitButton() {
		return exitButton;
	}


	public void setExitButton(Button exitButton) {
		this.exitButton = exitButton;
	}


	public Label getPortLabel() {
		return portLabel;
	}


	public void setPortLabel(Label portLabel) {
		this.portLabel = portLabel;
	}

	//Constructor
	public ServerFrame(){
		// close window
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });
		
		setLayout(null);
		
		ServerFormClickListner sfcl = new ServerFormClickListner();
		
		startButton = new Button("start");
		startButton.setBounds(80,130,100,30);
		startButton.setVisible(true);
		startButton.addActionListener(sfcl);
		add(startButton);
		
		stopButton = new Button("stop");
		stopButton.setBounds(80+102,130,100,30);
		stopButton.setVisible(true);
		stopButton.addActionListener(sfcl);
		add(stopButton);
		
		exitButton = new Button("exit");
		exitButton.setBounds(80+102+102,130,100,30);
		exitButton.setVisible(true);
		exitButton.addActionListener(sfcl);
		add(exitButton);
		
		portLabel = new Label("port:");
		portLabel.setFont(new Font("Arial",Font.PLAIN, 40));
		portLabel.setBounds(10,30,100,40);
		portLabel.setVisible(true);
		add(portLabel);
		
		portTextField = new TextField("tf");
		portTextField.setFont(new Font("Arial",Font.PLAIN, 30));
		portTextField.setText("1337");
		portTextField.setBounds(10,40+40,100,40);
		portTextField.setVisible(true);
		add(portTextField);
		
		
		serverDown();
		this.setSize(80+102+102+110,130+40);
		this.setVisible(true);
		
	}
	
	void serverUp(){
		startButton.setEnabled(false);
		stopButton.setEnabled(true);
		exitButton.setEnabled(true);
		portLabel.setEnabled(false);
		portTextField.setEnabled(false);
	}
	
	void serverDown(){
		startButton.setEnabled(true);
		stopButton.setEnabled(false);
		exitButton.setEnabled(true);
		portLabel.setEnabled(true);
	}
	
	class ServerFormClickListner implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source == startButton){
				serverUp();
			}
			
			if(source == stopButton){
				serverDown();
			}
			
			if(source == exitButton){
				dispose();
			}
			
		}
		
	}
}
