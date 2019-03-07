import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImportTxtToMysql {


    public static void main(String[] args) throws Exception {
        BufferedReader reader;

        MySQLAccess dao = new MySQLAccess();

        dao.initDB();
        dao.createDB();

        try {
            reader = new BufferedReader(new FileReader(
                    //这儿你的改成你的ip.txt的目录
                    "/home/crab179/Documents/ip.txt"));
            String line = reader.readLine();
            System.out.println("数据较多，请等待，结束时会有提示");
            while (line != null) {
                dao.insertIntoDatabase(line.substring(0,16), line.substring(16, 32), line.substring(32));
                line = reader.readLine();
            }
            reader.close();
            System.out.println("录入成功,现在可以查看了");
        } catch (IOException e) {
            e.printStackTrace();
        }

        dao.close();
    }
}
