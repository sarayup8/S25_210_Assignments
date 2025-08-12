package assn05;

public class Main {

    /**
     * Write a series of tests to check the functionality of each task
     * @param args
     */
    public static void main(String[] args) {
//        testT1();
//        testT2();
//        testT3();
        testT4();
    }

    /**
     * Test Task 1 - Write some tests to convince yourself that your code for Task 1 is working
     */
    public static void testT1(){
        SimpleEmergencyRoom patients = new SimpleEmergencyRoom();
        patients.addPatient("Andy", 7);
        patients.addPatient("Ned", 3);
        patients.addPatient("Grace", 9);
        patients.addPatient("Amy", 1);

        System.out.println(patients.dequeue().getValue());
        System.out.println(patients.dequeue().getValue());
    }

    /**
     * Test Task 2 - Write some tests to convince yourself that your code for Task 2 (A & B) is working
     */
    public static void testT2(){
        MaxBinHeapER<String, Integer> patients = new MaxBinHeapER<>();
        patients.enqueue("Andy", 7);
        patients.enqueue("Ned");
        patients.enqueue("Grace", 9);
        patients.enqueue("Amy", 1);

        System.out.println(patients.size());

        System.out.println(patients.getMax());

        String largest = patients.dequeue();
        System.out.println(largest);

        patients.updatePriority("Amy", 15);
        System.out.println(patients.getMax());

        patients.updatePriority("Tamara", 60);
        System.out.println(patients.getMax());

        patients.updatePriority("Andy", -2);
        System.out.println(patients.size());
    }

    /**
     * Test Task 3 - This part can be used to test for task 3.
     */
    public static void testT3(){
        MaxBinHeapER transfer = new MaxBinHeapER(makePatients());
        Prioritized[] arr = transfer.getAsArray();
        for(int i = 0; i < transfer.size(); i++) {
            System.out.println("Value: " + arr[i].getValue()
                    + ", Priority: " + arr[i].getPriority());
        }
    }

    /**
     * Test Task 4 - Write some tests to convince yourself that your code for Task 4 is working
     * You can use some of the helper methods already given below.
     * An example is given below:
     */
    public static void testT4() {
        double[] res = compareRuntimes();
        System.out.println("SimpleER: total time for 100,000 dequeues: " + res[0] + " nanosec");
        System.out.println("SimpleER: time per dequeue: " + res[1] + " nanosec");
        System.out.println("Heap: total time for 100,000 dequeues: " + res[2] + " nanosec");
        System.out.println("Heap: time per dequeue: " + res[3] + " nanosec");
    }

    /**
     * fills up an Emergency Room based on a MaxBinHeapER
     * @param complexER an initially empty MaxBinHeapER
     */
    public static void fillER(MaxBinHeapER complexER) {
        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }

    /**
     * fills up an Emergency Room based on a SimpleEmergencyRoom (overloaded)
     * @param simpleER an initially empty SimpleEmergencyRoom
     */
    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    /**
     * Creates an array of patients
     * @return returns this array of patients
     */
    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }

    /**
     * TODO (Task 4): compareRuntimes
     * Compares the Run Times of the SimpleEmergencyRoom vs MaxBinHeapER
     * @return an array of results as follows:
     * index 0: total nanosec for simpleER dequeues
     * index 1: average nanosec for simpleER dequeues
     * index 2: total nanosec for maxHeapER dequeues
     * index 3: average nanosec for maxHeapER dequeues
     */
    public static double[] compareRuntimes() {
        // Array which you will populate as part of Task 4
        double[] results = new double[4];

        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);

        MaxBinHeapER binHeap = new MaxBinHeapER();
        fillER(binHeap);

        // Code for (Task 4.1) Here
        long startTime1 = System.nanoTime();

        while(simplePQ.size() > 0) {
            simplePQ.dequeue();
        }

        long endTime1 = System.nanoTime();
        long totalTime1 = endTime1 - startTime1;

        results[0] = totalTime1;
        results[1] = ((double) totalTime1 / 100000);

        // Code for (Task 4.2) Here
        long startTime2 = System.nanoTime();

        while(simplePQ.size() > 0) {
            simplePQ.dequeue();
        }

        long endTime2 = System.nanoTime();
        long totalTime2 = endTime2 - startTime2;

        results[2] = totalTime2;
        results[3] = ((double) totalTime2 / 100000);

        return results;
    }

}
