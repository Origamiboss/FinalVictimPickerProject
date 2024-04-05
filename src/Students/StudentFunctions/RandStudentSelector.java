package src.Students.StudentFunctions;

import src.Students.Victim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandStudentSelector {
    private static ArrayList<Victim> students;

    public static void addStudents(ArrayList<Victim> impStudents) throws ArrayEmptyException {
        if (impStudents.isEmpty()) {
            throw new ArrayEmptyException();
        }
        students = impStudents;
    }

    public static Victim getRandomStudent(ArrayList<Victim> impStudents, List<Victim> existingPlayers) {
        Victim pickedStudent = null;

        try {
            addStudents(impStudents);

            Random rand = new Random();
            double totalInfluence = 0;

            // Calculate total influence
            for (Victim student : students) {
                if (!existingPlayers.contains(student)) {
                    totalInfluence += student.getInfluenceScore();
                }
            }

            double randomValue = rand.nextDouble() * totalInfluence;

            List<Victim> candidates = new ArrayList<>();
            double sum = 0;

            for (Victim student : students) {
                if (!existingPlayers.contains(student)) {
                    sum += student.getInfluenceScore();
                    if (randomValue <= sum) {
                        candidates.add(student);
                        if (randomValue < sum) {
                            break;
                        }
                    }
                }
            }

            if (!candidates.isEmpty()) {
                int randomIndex = rand.nextInt(candidates.size());
                pickedStudent = candidates.get(randomIndex);
            }

        } catch (ArrayEmptyException e) {
            students = null;
            System.out.println("List empty");
            e.printStackTrace();
        }
        return pickedStudent;
    }

    private static class ArrayEmptyException extends Exception {
        public ArrayEmptyException() {
        }
    }
}