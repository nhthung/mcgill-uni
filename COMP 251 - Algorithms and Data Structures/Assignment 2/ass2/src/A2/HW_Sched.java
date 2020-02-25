package A2;
import java.util.*;

class Assignment implements Comparator<Assignment>{
	int number;
	int weight;
	int deadline;
	
	
	protected Assignment() {
	}
	
	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}
	
	
	
	/**
	 * This method is used to sort to compare assignment objects for sorting. 
	 * The way you implement this method will define which order the assignments appear in when you sort.
	 * Return -1 if a1 should appear after a2
	 * Return 1 if a1 should appear before a2
	 * Return 0 if a1 and a2 are equivalent 
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {
		//YOUR CODE GOES HERE, DONT FORGET TO EDIT THE RETURN STATEMENT
		int a1Deadline = a1.deadline,
			a2Deadline = a2.deadline,
			a1Weight = a1.weight,
			a2Weight = a2.weight;
		
		if (a1Deadline == a2Deadline && a1Weight == a2Weight)
			return 0;
		
		 /* If better to do a1 first, then set a1 "smaller" than a2,
		  * because Collection.sort is in ascending order */
		else if (
				(a1Deadline == a2Deadline && a1Weight > a2Weight) ||
				(a1Deadline < a2Deadline))
			return -1;
		else
			return 1;
	}
}

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;
	
	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m =size;
	}
	
	
	/**
	 * 
	 * @return Array where output[i] corresponds to when assignment #i will be completed. output[i] is 0 if assignment #i is never completed.
	 * The homework you complete first will be given an output of 1, the second, 2, etc.
	 */
	public int[] SelectAssignments() {
		//Use the following command to sort your Assignments: 
		//Collections.sort(Assignments, new Assignment());
		//This will re-order your assignments. The resulting order will depend on how the compare function is implemented
		Collections.sort(Assignments, new Assignment());
		
		//Initializes the homeworkPlan, which you must fill out and output
		int[] homeworkPlan = new int[Assignments.size()];
		//YOUR CODE GOES HERE
		for (int i = 0, t = 1; i < this.m; i++) {
			Assignment homework = Assignments.get(i);
			
			if (t <= homework.deadline) {
				homeworkPlan[homework.number] = t;
				t++;
			}
			else
				homeworkPlan[homework.number] = 0;
		}
		
		return homeworkPlan;
	}
}
	



