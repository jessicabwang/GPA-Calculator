import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

// class that keeps track of a list of courses and calculates GPA
public class GPACalculator extends JFrame {

	private static JLabel courseList;
	private static JLabel currentGPA;
	private static JLabel targetGPALabel;
	private static JLabel requiredGPALabel;
	private static JTextField inputCredits;
	private static JTextField inputTitle;
	private static JTextField inputGrade;
	private static JTextField inputRemoveCourse;
	private static JTextField inputTargetGPA;
	private static JButton addCourse;
	private static JButton removeCourse;
	private static JButton removeAll;
	private static JButton calculateCurrentGPA; 
	private static JButton addTargetGPA;
	private static ArrayList<Course> coursesArrayList = new ArrayList<>();
	static double totalGradePoints = 0;
	static double totalCredits = 0;
	static double computedGPA = 0;
	static double targetGPA = 0;
	static double requiredGPA = 0;

	public ArrayList<Course> getCoursesArrayList() {
		return coursesArrayList;
	}

	public void setCoursesArrayList(ArrayList<Course> coursesArrayList) {
		this.coursesArrayList = coursesArrayList;
	}

	/** Constructor for GPACalculator class. Sets Title, default close operation, and sets it visible. */
	GPACalculator() {
		setTitle("GPA Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/** method to add the components to the pane **/
	public void addComponentsToPane(Container pane) {
		/** panel 1: header panel with instructions */
		JPanel panel1 = new JPanel();
		String instructions = "<html>WELCOME TO THE UVA GPA CALCULATOR! Please follow the instructions below for optimal performance. <br/><br/> 1.) Enter the credits for your course and click Add Course. (Optional: add the Course Title and Grades). <br/> 2.) Click Compute Current GPA. <br/> 3.) Input your Target GPA and click Add Target GPA. <br/> 4.) The GPA Calculator will add suggested grades for courses with no entered grades to help you reach your target GPA.";
		instructions += "</html>";
		panel1.setBackground(Color.white);
		JLabel instructions1 = new JLabel(instructions);
		instructions1.setFont(new Font("Sans-serif", Font.BOLD, 12)); // for font, I used this resource: https://stackoverflow.com/questions/2715118/how-to-change-the-size-of-the-font-of-a-jlabel-to-take-the-maximum-size
		panel1.add(instructions1);

		/** panel 2: input panel. This panel contains the area where
		 * the user can enter their credits, title, and grades. */
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(5, 6, 2, 2)); // used this website: https://docs.oracle.com/javase/10/docs/api/java/awt/GridLayout.html
		panel2.setBackground(new Color(229, 114, 0)); // used this website to get color: https://stackoverflow.com/questions/29127041/change-background-color-of-jpanel-using-rgb-values
		JLabel creditsLabel = new JLabel("Course Credits (Required)");
		creditsLabel.setFont(new Font("Sans-serif", Font.BOLD, 12));
		inputCredits = new JTextField();
		inputCredits.setFont(new Font("Sans-serif", Font.BOLD, 12));
		JLabel titleLabel = new JLabel("Course Title (Optional)");
		titleLabel.setFont(new Font("Sans-serif", Font.BOLD, 12));
		inputTitle = new JTextField();
		inputTitle.setFont(new Font("Sans-serif", Font.BOLD, 12));
		JLabel courseGrade = new JLabel("Course Grade (Optional)");
		courseGrade.setFont(new Font("Sans-serif", Font.BOLD, 12));
		inputGrade = new JTextField();
		inputGrade.setFont(new Font("Sans-serif", Font.BOLD, 12));
		addCourse = new JButton("Add to course list: ");
		addCourse.setFont(new Font("Sans-serif", Font.BOLD, 12));

		/** adding components to panel 2 **/
		panel2.add(creditsLabel);
		panel2.add(inputCredits);
		panel2.add(titleLabel);
		panel2.add(inputTitle);
		panel2.add(courseGrade);
		panel2.add(inputGrade);
		panel2.add(addCourse);

		/** panel 3: summary section. This section 
		 * allows the user to calculate the GPA and target GPA. */
		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.white);
		panel3.setLayout(new GridLayout(4, 3, 2, 2));

		/** currentGPA label */
		currentGPA = new JLabel("Current GPA:");
		currentGPA.setFont(new Font("Sans-serif", Font.BOLD, 12));

		/** JButton to calculate GPA */
		calculateCurrentGPA = new JButton("Compute Current GPA");
		calculateCurrentGPA.setFont(new Font("Sans-serif", Font.BOLD, 12));

		/** JTextField and Button to remove specific course # or remove all */
		inputRemoveCourse = new JTextField("Enter the number of the Course to Remove");
		inputRemoveCourse.setFont(new Font("Sans-serif", Font.BOLD, 12));
		removeCourse = new JButton("Remove Course");
		removeCourse.setFont(new Font("Sans-serif", Font.BOLD, 12));
		removeAll = new JButton("Remove All Courses");
		removeAll.setFont(new Font("Sans-serif", Font.BOLD, 12));

		/** targetGPA JTextField and Button, with required GPA */
		inputTargetGPA = new JTextField("Enter the letter grade of your target GPA");
		inputTargetGPA.setFont(new Font("Sans-serif", Font.BOLD, 12));
		addTargetGPA = new JButton("Add Target GPA");
		addTargetGPA.setFont(new Font("Sans-serif", Font.BOLD, 12));
		targetGPALabel = new JLabel("Target GPA:");
		targetGPALabel.setFont(new Font("Sans-serif", Font.BOLD, 12));
		requiredGPALabel = new JLabel("Required GPA:");
		requiredGPALabel.setFont(new Font("Sans-serif", Font.BOLD, 12));

		/** adding components to panel */
		panel3.add(calculateCurrentGPA);
		panel3.add(currentGPA);
		panel3.add(new JLabel());
		panel3.add(inputRemoveCourse);
		panel3.add(removeCourse);
		panel3.add(removeAll);
		panel3.add(inputTargetGPA);
		panel3.add(addTargetGPA);
		panel3.add(new JLabel());
		panel3.add(targetGPALabel);
		panel3.add(requiredGPALabel);


		/** panel 4: list of courses. This section contains a visual list of courses. */
		JPanel panel4 = new JPanel();
		panel4.setBackground(new Color(35, 45, 75));
		courseList = new JLabel("List of Courses: ");
		courseList.setFont(new Font("Sans-serif", Font.BOLD, 12));
		courseList.setForeground(Color.white); // used this resource: https://stackoverflow.com/questions/2966334/how-do-i-set-the-colour-of-a-label-coloured-text-in-java
		panel4.add(courseList);

		/** Setting the window to be visible */
		pane.add(panel1, BorderLayout.NORTH);
		pane.add(panel2, BorderLayout.CENTER);
		pane.add(panel3, BorderLayout.SOUTH);
		pane.add(panel4, BorderLayout.EAST);
	}

	/** method for converting course grades into GPA points */
	public static double convertGradesIntoPoints(String grades) {
		if (grades.equals("A") || grades.equals("A+")) {
			return 4.0;
		}
		else if (grades.equals("A-")){
			return 3.67;
		}
		else if (grades.equals("B+")) {
			return 3.33;
		}
		else if (grades.equals("B")) {
			return 3.00;
		}
		else if (grades.equals("B-")) {
			return 2.67;
		}
		else if (grades.equals("C+")) {
			return 2.33;
		}
		else if (grades.equals("C")) {
			return 2.00;
		}
		else if (grades.equals("D")) {
			return 1.00;
		}
		else if (grades.equals("F")) {
			return 0.00;
		}
		else {
			return 0.0;
		}
	}

	/** method for converting GPA points into course grades */
	public static String convertPointsIntoGrades(double grades) {
		if (grades >= 4.0) {
			return "A";
		}
		else if (3.67 <= grades && grades < 4.0){
			return "A";
		}
		else if (3.33 <= grades && grades < 3.67) {
			return "B+";
		}
		else if (3.00 <= grades && grades < 3.33) {
			return "B";
		}
		else if (2.67 <= grades && grades < 3.00) {
			return "B-";
		}
		else if (2.33 <= grades && grades < 2.67) {
			return "C+";
		}
		else if (2.00 <= grades && grades < 2.33) {
			return "C";
		}
		else if (1.00 <= grades && grades < 2.00) {
			return "D";
		}
		else if (0.00 < grades && grades < 1.00) {
			return "F";
		}
		else {
			return "";
		}
	}

	/** method to check if input is a string. Used this resource: https://www.geeksforgeeks.org/program-check-input-integer-string/ */
	static boolean isNumber(String s) 
	{ 
		for (int i = 0; i < s.length(); i++) 
			if (Character.isDigit(s.charAt(i))  
					== false) 
				return false; 
		return true; 
	} 
	
	/** method to check if input is a valid letter grade */
	static boolean isLetterGrade(String s) {
		if(s.equals("") || s.equals("A+") || s.equals("A") || s.equals("A-") || s.equals("B+") || s.equals("B") || s.equals("B-") || s.equals("C+") || s.equals("C") || s.equals("D") || s.equals("F")) {
			return true;
		}
		else {
			return false;
		}
		
	}

	public static void main(String[] args) {
		/** creating the frame that holds all the JPanels */
		GPACalculator frame = new GPACalculator();
		// frame.getContentPane().setLayout(new GridLayout(10, 5));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set up the content pane.
		frame.addComponentsToPane(frame.getContentPane());
		// Display the window.
		frame.pack();
		frame.setVisible(true);

		/** making a class that makes the addCourse button add to the course list 
		 * ASSUMPTIONS:
		 * -the user will not add more than 9 courses. If they add more, the interface will not show them but the current GPA methods will still work.*/
		class AddCourseListener implements ActionListener
		{
			int courseNumber = 0;
			public void actionPerformed(ActionEvent event) {
				if (addCourse.getActionCommand().equals("click"));
				if(isLetterGrade(inputGrade.getText()) == false) {
					JOptionPane.showMessageDialog(frame, "Please enter a valid letter grade and recalculate.");
				}
				else {
					int newCourseNumber = 0;
					// making sure the user enters a valid input
					try 
					{ 
						String insideCourseList = "<html>List of Courses: <br/>";
						double credits = Double.parseDouble(inputCredits.getText());
						String title = inputTitle.getText();
						String grade = inputGrade.getText();
						courseNumber ++;
						coursesArrayList.add(new Course(courseNumber, credits, title, grade));
						for (Course course: coursesArrayList) {
							newCourseNumber ++;
							course.setCourseNumber(newCourseNumber);
							insideCourseList += course.toString() + "<br/>";
						}
						insideCourseList += "</html>";
						courseList.setText(insideCourseList);

					}  
					catch (NumberFormatException e)  
					{ 
						JOptionPane.showMessageDialog(frame, "You did not enter a valid number for your credits. Try again and recalculate.");
					} 
				}
			}
		}
		addCourse.addActionListener(new AddCourseListener());

		/** creating a class for the calculate current GPA button 
		 * ASSUMPTIONS:
		 * -*/
		class AddCalculateGPAListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				if(calculateCurrentGPA.getActionCommand().equals("click"));
				double gradePoints = 0;
				double credits = 0;
				gradePoints = 0;
				totalCredits = 0;
				totalGradePoints = 0;
				for (Course course: coursesArrayList) {
					gradePoints = convertGradesIntoPoints(course.getCourseGrade());				
					credits = course.getCourseCredits();
					totalCredits += credits;
					gradePoints = gradePoints * credits;
					totalGradePoints += gradePoints;
					if(course.getCourseGrade().equals("")) {
						totalCredits -= credits;
					}
				}
				if (totalGradePoints == 0.0) {
					computedGPA = 0.0;
					currentGPA.setText("Current GPA: " + computedGPA);
				}
				else {
					computedGPA = (totalGradePoints) / totalCredits;
					currentGPA.setText("Current GPA: " + computedGPA);
				}
			}
		}
		calculateCurrentGPA.addActionListener(new AddCalculateGPAListener());

		/** making a class that removes a single course by the number 
		 * ASSUMPTIONS:
		 * -the user will select all the existing text and replace it with a number 
		 * -the user will not enter a course number that does not exist
		 * -the user will enter a number, not a letter or random symbol*/
		class removeSingleCourseListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				String insideCourseList = "<html>List of Courses: <br/>";
				if(removeCourse.getActionCommand().equals("click"));
				int count = 0;
				coursesArrayList.remove(Integer.parseInt(inputRemoveCourse.getText()) - 1);
				for (Course course: coursesArrayList) {
					count ++;
					course.setCourseNumber(count);
					insideCourseList += course.toString() + "<br/>";
				}
				insideCourseList += "</html>";
				courseList.setText(insideCourseList);
			}
		}
		removeCourse.addActionListener(new removeSingleCourseListener());

		/** making a class that removes all courses 
		 * */
		class removeAllListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				if(removeAll.getActionCommand().equals("click"));
				coursesArrayList.clear();
				courseList.setText("List of Courses: ");
			}
		}
		removeAll.addActionListener(new removeAllListener());

		/** making a class that adds target GPA */
		/** ASSUMPTIONS:
		 * -the user will press "Compute GPA" to get their current GPA before entering their Target GPA
		 * -after the GPA calculator has added suggested grades, the user will either rerun the program or add courses with no credits. 
		 * */
		class targetGPAListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				if(addTargetGPA.getActionCommand().equals("click"));
				if (isNumber(inputTargetGPA.getText())) {
					JOptionPane.showMessageDialog(frame, "Please enter a valid letter grade and try again.");
				}
				else {
					targetGPA = convertGradesIntoPoints(inputTargetGPA.getText());
					targetGPALabel.setText("Target GPA: " + inputTargetGPA.getText());
					if (computedGPA == 0) {
						requiredGPA = targetGPA;
					}
					else {
						requiredGPA = computedGPA + (targetGPA - computedGPA) * 2;
						if (requiredGPA > 4.0) {
							JOptionPane.showMessageDialog(frame, "Your required GPA exceeds 4.0. Please enter more credit hours and recalculate.");
						}
						if (requiredGPA < 2.0) {
							JOptionPane.showMessageDialog(frame, "Your required GPA is less than 2.0. You can take less credit hours if you wish.");
						}
					}
					requiredGPALabel.setText("Required GPA: " + requiredGPA);

					/** section to input required grade into the courses with empty credits */
					double currentGPAPoints = 0;
					double emptyCredits = 0;
					double goalGPAPoints = 0;
					double stillNeedGPAPoints =0;
					double pointsPerCredit = 0;
					double gradeGPAPoints = 0;
					double allCredits = 0;
					double fullCredits = 0;
					/** for loop that goes through the courses, calculating GPA points for courses with grades */
					for (Course course: coursesArrayList) {
						if(course.getCourseGrade().equals("") == false) {
							double singleGradePoints = convertGradesIntoPoints(course.getCourseGrade());
							currentGPAPoints += singleGradePoints;
							fullCredits += course.getCourseCredits();
						}
						else {
							emptyCredits += course.getCourseCredits();
						}
						allCredits += course.getCourseCredits();
					}
					goalGPAPoints = targetGPA * allCredits;
					stillNeedGPAPoints = goalGPAPoints - (computedGPA * fullCredits);
					pointsPerCredit = stillNeedGPAPoints / emptyCredits;
					for (Course course: coursesArrayList) {
						if(course.getCourseGrade().equals("")) {
							course.setCourseGrade(convertPointsIntoGrades(pointsPerCredit) + " or higher (Suggested)");
						}
					}
					String insideCourseList = "<html>List of Courses: <br/>";
					for (Course course: coursesArrayList) {
						insideCourseList += course.toString() + "<br/>";
					}
					insideCourseList += "</html>";
					courseList.setText(insideCourseList);
				}
			}
		}
		addTargetGPA.addActionListener(new targetGPAListener());


	}
}
