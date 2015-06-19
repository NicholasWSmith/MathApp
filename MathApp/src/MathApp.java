import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.Arrays;
import java.util.Random;

public class MathApp extends JFrame {

	private JFrame thisFrame = this;
	
	

	
	public static void main(String[] args) {
	
		new MathApp();
	

	}
	
	public MathApp() {
		JPanel mainPanel = new JPanel();
		JPanel sign = new JPanel(new GridBagLayout());
		JPanel questions = new JPanel();
	
		setVisible(true); 
		setLocation(new Point(400,100));
		setSize(800,500);
		setTitle("Math App");
		JButton create = new JButton("Create");
		JLabel firstLabel = new JLabel("How many equations do you need?");
		JLabel maxNumber = new JLabel("What is the max number?");
		JTextField numOfQuestions = new JTextField("How many?");
		JTextField maxNum = new JTextField("Max Num");
		
		JCheckBox add = new JCheckBox("Addition");
		JCheckBox sub = new JCheckBox("Subtraction");
		JCheckBox div = new JCheckBox("Division");
		JCheckBox mult = new JCheckBox("Multiplication");
		
		
		JCheckBox carryOver = new JCheckBox("Carry Over?");
		JCheckBox negs = new JCheckBox("Negatives?");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(15, 15, 15, 15);
		
		questions.add(firstLabel, gbc);
		questions.add(numOfQuestions, gbc);
	
		questions.add(maxNumber, gbc);
		questions.add(maxNum, gbc);
		
		
		
		
		
		
		
		sign.add(add, gbc);
		sign.add(sub, gbc);
		sign.add(div, gbc);
		sign.add(mult, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		sign.add(carryOver, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		sign.add(negs, gbc);
		
		
		
		mainPanel.add(create);
		
		add(questions, BorderLayout.NORTH);
		add(mainPanel, BorderLayout. SOUTH);
		add(sign, BorderLayout.CENTER);
		
		
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String m = maxNum.getText();
				int maxNum1 =  Integer.parseInt(m);	
				
				String q = numOfQuestions.getText();
				int questions =  Integer.parseInt(q);
				
				String[] equations = new String[questions];
				int[] answers = new int[questions];
				
				for(int i = 0; i < questions; i++) {
					Random random = new Random();
					int operator = random.nextInt(4);
					
					
					
					
					String[] arr = new String[4];
					
					
					
					if (add.isSelected() == true)  {
						arr[0] = "+";
					}
					
					if (sub.isSelected() == true) {
						arr[1] = "-";
					}
					
					if (mult.isSelected() == true){
						arr[2] = "X";
					}
						
					if (div.isSelected() == true) {
						arr[3] = "/";
					}
						
					
					
					
					
					int firstNum = random.nextInt(maxNum1 + 1);
					int secNum = random.nextInt(maxNum1 + 1);
					
					while (arr[operator] == null){
						operator = random.nextInt(4);
					}
					
					
					
					if (carryOver.isSelected() == false){
						char firstDig = String.valueOf(Math.abs((long)firstNum)).charAt(0);
						char secondDig = String.valueOf(Math.abs((long)secNum)).charAt(0);
						
					
						
						if(firstDig + secondDig >= 10){
							firstDig /= 2;
							secondDig /= 2;
						}
						
					}
					
					
					
					
					String equation = (firstNum + " " + arr[operator] + " " + secNum + " :");
					equations[i] = equation;
					System.out.println(equation);
					System.out.print("\n");
					
					if (operator == 0){
						answers[i] = firstNum + secNum;
					} else if (operator == 1){
						answers[i] = firstNum - secNum;
					} else if (operator == 2){
						answers[i] = firstNum * secNum;
					} else if (operator == 3){
						answers[i] = firstNum / secNum;
					}
					
				
			}
						
				
				
				String fileName = "Equations.txt";
				String fileName1 = "Answers.txt";
				try {
					PrintWriter outputStream = new PrintWriter(fileName);
					for(int k = 0; k < equations.length; k++){
						outputStream.println(equations[k]);
						outputStream.println("\n");
						outputStream.println("\n");
					}
					
					PrintWriter outputStream1 = new PrintWriter(fileName1);
					for(int l = 0; l < answers.length; l++){
						outputStream1.println(answers[l]);
						outputStream1.println("\n");
						outputStream1.println("\n");
					}
					
					outputStream.close();
					outputStream1.close();
					
					outputStream.close();
						
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
				
			}
		});
		
		
		validate();
	}
	



}
