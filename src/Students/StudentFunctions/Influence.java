package src.Students.StudentFunctions;

public class Influence {
    private static final int POINTS_WEIGHT = 1;
    private static final int ABSENCES_WEIGHT = 10;
    private static final int PASSED_WEIGHT = 10;
    private static final int ANSWERED_WEIGHT = 5;
    private static final int TIMES_PICKED_WEIGHT = 1;
    public static double getInfluence(int points, int abscences, int passed, int answered, int timesPicked){
        return ((TIMES_PICKED_WEIGHT * points) - (ABSENCES_WEIGHT * abscences) - (PASSED_WEIGHT * passed) + (ANSWERED_WEIGHT * answered) + (TIMES_PICKED_WEIGHT * timesPicked));
    }
}
