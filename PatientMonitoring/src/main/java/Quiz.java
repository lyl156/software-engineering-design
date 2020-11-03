import java.io.BufferedReader;
import java.io.FileReader;

//java Quiz inputFile
public class Quiz {
    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String name = "";
//        while (!name.equals("stop")) {
//            System.out.println("Enter data");
//            name = br.readLine();
//            System.out.println("data is " + name);
//        }
//        BufferedReader brr = new BufferedReader(new InputStreamReader(System.in));
//        String name = "";
//        name = brr.readLine();


        String path = System.getProperty("user.dir");

//        System.out.println("Working Directory = " + path);
        //https://dotblogs.com.tw/cylcode/2018/09/21/170510
        FileReader fr = new FileReader(path + "/" + args[0]);
        BufferedReader br = new BufferedReader(fr);

        handle(br);
//        while (br.ready()) {
//            System.out.println(br.readLine());
//        }
        br.close();
        fr.close();


    }

    private static void handle(BufferedReader br) throws Exception {
        //input should be number, e.g 3000
        String inputLine = br.readLine();
        Long readSec = Long.parseLong(inputLine);

        PatientDataPrinter patientDataPrinter = new PatientDataPrinter(readSec);
        Patient curPatient = null;

        while ((inputLine = br.readLine()) != null) {
            String[] inputs = inputLine.trim().split(" ");
            String command = inputs[0];
            // input should be : patient Mark 600
            if ("patient".equals(command)) {
                //add the last one patient
                if (curPatient != null && !(curPatient.getSensors().isEmpty()))
                    patientDataPrinter.addPatient(curPatient);

                String name = inputs[1];
                int fre = Integer.parseInt(inputs[2]);
                curPatient = new Patient(name, fre);
                //add this patient to the factorMonitor in the last
            } else if ("PulseSensor".equals(command)) {
                String sensorName = inputs[1];
                //ClassLoader.getSystemClassLoader() get "AppClassLoader"
                String fileName = ClassLoader.getSystemClassLoader().getResource(inputs[2]).getPath();
                double lowerBound = Double.parseDouble(inputs[3]);
                double upperBound = Double.parseDouble(inputs[4]);

                Sensor sensor = new PulseSensor(sensorName, fileName);
                SafeRange safeRange = new PulseSafeRange(lowerBound, upperBound);
                curPatient.attachSensor(sensor, safeRange);
            } else if ("BloodPressureSensor".equals(command)) {
                String sensorName = inputs[1];
                String fileName = ClassLoader.getSystemClassLoader().getResource(inputs[2]).getPath();
                double lowerBound = Double.parseDouble(inputs[3]);
                double upperBound = Double.parseDouble(inputs[4]);
                Sensor sensor = new BloodPressureSensor(sensorName, fileName);
                SafeRange safeRange = new BloodPressureSafeRange(lowerBound, upperBound);
                curPatient.attachSensor(sensor, safeRange);
            } else if ("TemperatureSensor".equals(command)) {
                String sensorName = inputs[2];
                String fileName = ClassLoader.getSystemClassLoader().getResource(inputs[2]).getPath();
                double lowerBound = Double.parseDouble(inputs[3]);
                double upperBound = Double.parseDouble(inputs[4]);
                Sensor sensor = new TemperatureSensor(sensorName, fileName);
                SafeRange safeRange = new TemperatureSafeRange(lowerBound, upperBound);
                curPatient.attachSensor(sensor, safeRange);
            } else {
                throw new Exception("wrong input format");
            }
        }
        if (curPatient != null && !(curPatient.getSensors().isEmpty())) {
            patientDataPrinter.addPatient(curPatient);
        }
        patientDataPrinter.monitor();

//        System.out.println("--------------------print DB--------------------------------");
        patientDataPrinter.printToDB();
    }
}
