import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class FileImporter {

    private BufferedReader reader;

    public Mesh parseObj(String file) {

        try {
            reader = new BufferedReader(new FileReader(file));
            ArrayList<Vector3> vertices = new ArrayList<>();

            String[] flags;
            String line = reader.readLine();
            while (line != null) {
                flags = line.split(" ");

                if (flags[0].trim().toLowerCase().equals("v")) {

                    double x = Double.parseDouble(flags[1]);
                    double y = Double.parseDouble(flags[2]);
                    double z = Double.parseDouble(flags[3]);
                    Vector3 vertex = new Vector3(x, y, z);
                    vertices.add(vertex);
                }

                line = reader.readLine();
            }

            Vector3[] vertexArray = new Vector3[vertices.size()];

            for (int i = 0; i < vertexArray.length; i++) {
                vertexArray[i] = vertices.get(i);
            }

            return new Mesh(vertexArray);

        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            return null;
        }

    }

}
