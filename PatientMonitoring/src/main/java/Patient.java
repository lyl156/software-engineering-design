import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Patient own sensors and corresponding safeRange
 * get safeRange by Map
 */

public class Patient {
    private String name;
    private List<Sensor> sensors = new ArrayList<>();
    private Map<Sensor, SafeRange> safeMap = new HashMap<>();
    private int frequency;

    public Patient(String name, int frequency) {
        this.name = name;
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public int getFrequency() {
        return frequency;
    }

    public SafeRange getSafeRange(Sensor sensor) {
        return safeMap.get(sensor);
    }

    /**
     * Add the new sensor and corresponding safeRange to the patient
     * @param sensor
     *        the specific sensor you intent to add
     * @param safeRange
     *        the specific safeRange corresponding to sensor
     */
    public void attachSensor(Sensor sensor, SafeRange safeRange) {
        sensors.add(sensor);
        safeMap.put(sensor, safeRange);
    }

}
