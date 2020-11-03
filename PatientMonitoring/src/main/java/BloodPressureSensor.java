import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BloodPressureSensor extends Sensor {
    private BufferedReader valueReader;

    public BloodPressureSensor(String sensorName, String fileName) {
        super(sensorName);
        try {
            valueReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double read() {
        double value = -1;
        try {
            String input = valueReader.readLine();
            if (input != null) {
                value = Double.parseDouble(input.trim());
            }
        } catch (IOException e) {
            System.out.println("BloodPressureSensor read error");
            e.printStackTrace();
        }
        return value;
    }
}
