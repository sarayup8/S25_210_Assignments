package assn05;

import java.util.ArrayList;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> _patients;

    public SimpleEmergencyRoom() {
        _patients = new ArrayList<>();
    }

    /**
     * TODO (Task 1): dequeue
     * @return return patient with the highest priority
     */
    public Patient dequeue() {
        // if the ArrayList is empty, return null
        if (_patients.isEmpty()) {
            return null;
        } else if (_patients.size() == 1) {
            return _patients.remove(0);
        } else {
            Patient highestPriorityPatient = _patients.get(0);
            int highestIndex = 0;

            for (int i = 1; i < _patients.size(); i++) {
                Patient current = _patients.get(i);
                if (current.compareTo(highestPriorityPatient) > 0) {
                    highestPriorityPatient = current;
                    highestIndex = i;
                }
            }
            return _patients.remove(highestIndex);
        }
    }


    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        _patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        _patients.add(patient);
    }

    public List getPatients() {
        return _patients;
    }

    public int size() {
        return _patients.size();
    }

}
