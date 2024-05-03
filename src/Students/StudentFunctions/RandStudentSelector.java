package Students.StudentFunctions;

import Students.Victim;
import java.util.ArrayList;
import java.util.Random;

public class RandStudentSelector {
    private static ArrayList<Victim> students;

    public static void addStudents(ArrayList<Victim> impStudents) throws ArrayEmptyException {
        if (impStudents.isEmpty()) {
            throw new ArrayEmptyException("The list of important students is empty.");
        }
        students = new ArrayList<>(impStudents);  // Create a new ArrayList from impStudents to avoid altering the original list
    }

    public static Victim getRandomStudent(ArrayList<Victim> impStudents, ArrayList<Victim> presentVics) throws ArrayEmptyException {
        if (impStudents.isEmpty()) {
            throw new ArrayEmptyException("The list of important students is empty.");
        }

        Random rand = new Random();
        ArrayList<Victim> availableStudents = new ArrayList<>(impStudents);
        availableStudents.removeAll(presentVics);  // Remove all present victims from the list of candidates

        if (availableStudents.isEmpty()) {
            throw new ArrayEmptyException("No available students after filtering.");
        }

        double totalInfluence = 0;
        for (Victim student : availableStudents) {
            totalInfluence += student.getInfluenceScore();
        }

        double randomValue = rand.nextDouble() * totalInfluence;
        double sum = 0;

        for (Victim student : availableStudents) {
            sum += student.getInfluenceScore();
            if (randomValue <= sum) {
                return student;
            }
        }

        return null;  // Fallback, shouldn't normally reach here if everything else is correct
    }

    public static class ArrayEmptyException extends Exception {
        public ArrayEmptyException(String message) {
            super(message);
        }
    }
}
