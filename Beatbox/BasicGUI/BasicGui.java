import javax.swing.*;
import java.awt.event.*;

public class BasicGui implements ActionListener{

	JButton button;
	JFrame frame;

	public static void main(String[] args){
		BasicGui gui = new BasicGui();
		gui.go();
	}

	public void go(){
		frame = new JFrame();
		button = new JButton("Click me");
		button.addActionListener(this);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		frame.getContentPane().add(button);
		frame.setSize(300,300);
		frame.setVisible(true);	
	}

	public void actionPerformed(ActionEvent action){
		button.setText("I've been clicked");
	}

}