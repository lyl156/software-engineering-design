import java.util.*;

//in readSecond do the Printer
//Printer read every patient's all sensor's value
public class PatientDataPrinter {
    private final List<Patient> patients = new ArrayList<>();
    private final Long readSecond;
    private final Map<Sensor, List<Pair>> patientData = new HashMap<>();

    public PatientDataPrinter(Long readSecond) {
        this.readSecond = readSecond;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    /**
     *  Print all the fails input and danger value over time.
     */
    public void monitor() {
        //the period that patient use
        for (int time = 0; time <= readSecond; time++) {
            //traverse patients
            for (Patient patient : patients) {
                int fre = patient.getFrequency();
                if (time % fre == 0) {
                    for (Sensor sensor : patient.getSensors()) {
                        double value = sensor.read();
                        SafeRange safeRange = patient.getSafeRange(sensor);
                        if (value == -1) {
                            printAlarm("[" + time + "] " + sensor.getSensorName() + " fails");
                        } else if (value < safeRange.getLowerBound() || value > safeRange.getUpperBound()) {
                            printAlarm("[" + time + "] " + patient.getName() + " is in danger! Cause: " + sensor.getSensorName() + " " + value);
                        }
                        // save sensor , time,  value of this time
                        saveToDB(sensor, time, value);
                    }
                }
            }
        }
    }

    private void printAlarm(String s) {
        System.out.println(s);
    }

    private void saveToDB(Sensor sensor, int time, double data) {
        if (!patientData.containsKey(sensor)) {
            patientData.put(sensor, new ArrayList<Pair>());
        }
        List<Pair> sensorData = patientData.get(sensor);
        sensorData.add(new Pair(time, data));
    }

    /**
     *  Print every patient's sensor data in the order of each patient.
     */
    public void printToDB() {
        for (Patient patient : patients) {
            System.out.println("patient " + patient.getName());
            for (Sensor sensor : patient.getSensors()) {
                //print: sensor type ＋ sensor name
                System.out.println(sensor.getClass().getName() + " " + sensor.getSensorName());
                List<Pair> sensorData = patientData.get(sensor);
                for (Pair pair : sensorData) {
                    int time = pair.getTime();
                    double value = pair.getValue();
                    System.out.println("[" + time + "] " + value);
                }
            }
        }
    }
}
