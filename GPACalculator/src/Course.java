// class for courses. Used by the GPACalculator class
public class Course {
	private int courseNumber;
	private double courseCredits;
	private String courseTitle;
	private String courseGrade;
	
	public Course(int courseNumber, double courseCredits, String courseTitle, String courseGrade) {
		this.courseNumber = courseNumber;
		this.courseCredits = courseCredits;
		this.courseTitle = courseTitle;
		this.courseGrade = courseGrade;
	}

	public String toString() {
		return "Course #" + + this.getCourseNumber() + " Course name: " + this.getCourseTitle() + " : " + this.getCourseCredits() + " credits. Grade received: " + this.getCourseGrade();
	}
	
	public int getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}
	
	public double getCourseCredits() {
		return courseCredits;
	}

	public void setCourseCredits(double courseCredits) {
		this.courseCredits = courseCredits;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getCourseGrade() {
		return courseGrade;
	}

	public void setCourseGrade(String courseGrade) {
		this.courseGrade = courseGrade;
	}
}
