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
		
		//creating the GUI
		JPanel mainPanel = new JPanel();
		JPanel sign = new JPanel(new GridBagLayout());
		JPanel questions = new JPanel();
		
		//setting names, and dimensions
		setVisible(true); 
		setLocation(new Point(400,100));
		setSize(800,500);
		setTitle("Math App");
		
		//button for cretaing equations
		JButton create = new JButton("Create");
		
		//max # of equations
		JLabel firstLabel = new JLabel("How many equations do you need?");
		
		//maximum number the generator can use in an equation
		JLabel maxNumber = new JLabel("What is the max number?");
		
		//filling textfield
		final JTextField numOfQuestions = new JTextField("How many?");
		
		//filling textfield
		final JTextField maxNum = new JTextField("Max Num");
		
		//checkboxes for operators and other specifications
		final JCheckBox add = new JCheckBox("Addition");
		final JCheckBox sub = new JCheckBox("Subtraction");
		final JCheckBox div = new JCheckBox("Division");
		final JCheckBox mult = new JCheckBox("Multiplication");
		final JCheckBox carryOver = new JCheckBox("Carry Over?");
		final JCheckBox negs = new JCheckBox("Negatives?");
		final JCheckBox dividesEven = new JCheckBox("Divides Evenly?");
		
		//layout
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(15, 15, 15, 15);
		
		questions.add(firstLabel, gbc);
		questions.add(numOfQuestions, gbc);
	
		questions.add(maxNumber, gbc);
		questions.add(maxNum, gbc);
		
		
		
		
		
		
		//adding the checkboxes to the gui and setting them apart
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
		gbc.gridx = 2;
		gbc.gridy = 1;
		sign.add(dividesEven, gbc);
		
		
		
		mainPanel.add(create);
		
		//more layout
		add(questions, BorderLayout.NORTH);
		add(mainPanel, BorderLayout. SOUTH);
		add(sign, BorderLayout.CENTER);
		
		
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//getting the variables from the textfields
				String m = maxNum.getText();
				int maxNum1 =  Integer.parseInt(m);	
				
				String q = numOfQuestions.getText();
				int questions =  Integer.parseInt(q);
				
				String[] equations = new String[questions];
				int[] answers = new int[questions];
				
				
				//for loop for numberof questions they want
				for(int i = 0; i < questions; i++) {
					//randomizing which operator is chosen
					Random random = new Random();
					int operator = random.nextInt(4);
					
					
					
					//creating the array of operators to choose from. 
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
						
					
					
					
					//creating the 2 numbers for the equation
					int firstNum = random.nextInt(maxNum1 + 1);
					int secNum = random.nextInt(maxNum1 + 1);
					if (firstNum == 0){
						firstNum = 1;
					}
					if (secNum == 0){
						secNum =1;
					}
					
					
					//choosing which operator to use
					while (arr[operator] == null){
						operator = random.nextInt(4);
					}
					
					
					//making sure the addition doesn't carry over. 
					if (carryOver.isSelected() == false && arr[operator].equals("+")){
						//stands for first Number Right Most Digit
						int fNRMD = 0;
						//stands for second Number Right Most Digit
						int sNRMD = 0;
						
						
						if (firstNum < 100){
							fNRMD = firstNum % 10;
						}
						
						if (secNum < 100){
							sNRMD = firstNum % 10;
						}
						
						
						if (fNRMD + sNRMD > 10 || fNRMD + sNRMD == 0){
							fNRMD = fNRMD / 2;
						}
						
					}
					
					//making sure that there are no negative answers 
					if (negs.isSelected() == true && arr[operator].equals("-")){
						
						if(firstNum - secNum < 0){
							int temp;
							temp = firstNum;
							firstNum = secNum;
							secNum = temp;
							
							 
						}
					}
					
					
					//making sure that there is even division
					if (dividesEven.isSelected() == true && arr[operator].equals("/")){
						
						//with x / y, if x < y it will also have a remaineder
						if (secNum > firstNum) {
							int temp;
							temp = firstNum;
							firstNum = secNum;
							secNum = temp;
						}
						
						//while looped use to decrease secNum by 1, until an even divisible is found. 
						//Lowest possible case will be secNum = 1, where firstNum is a prime number. 
						while (firstNum % secNum != 0){
							secNum--;
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

