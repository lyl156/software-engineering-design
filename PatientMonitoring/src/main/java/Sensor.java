public abstract class Sensor {
    private String sensorName;

    public Sensor(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSensorName() {
        return sensorName;
    }

    abstract public double read();
}
