import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImportTxtToMysql {


    public static void main(String[] args) throws Exception {

        BufferedReader reader;


        MySQLAccess dao = new MySQLAccess();

        dao.createDB();



        try {
            reader = new BufferedReader(new FileReader(
                    "/home/crab179/Documents/ip.txt"));
            String line = reader.readLine();
            while (line != null) {


                dao.insertIntoDatabase(line.substring(0,16), line.substring(17, 32), line.substring(33));

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dao.close();
    }
}
