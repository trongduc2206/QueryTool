import java.io.IOException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class csvReader {
    public static void main(String[] args) throws IOException {
        Map parentIdDistrict = new HashMap<String, Long>();
        Map parentIdWard = new HashMap<String, Long>();
        String temp = "";
        String temp2 = "";
        String temp3 = "";
        long cnt = -1;
        long cnt2 = -1;
        Reader reader = new FileReader("src/main/java/DATA_LOCATION.csv");
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        Iterable<CSVRecord> csvRecords = csvParser.getRecords();
        for (CSVRecord csvRecord : csvRecords) {

            if (csvRecord.get(0).equals(temp)) continue;
            else {
                temp = csvRecord.get(0);
                cnt++;
                parentIdDistrict.put(csvRecord.get(2), cnt);
            }
            // insert city
//            System.out.println("insert into data_location(name,code,type) values('" + csvRecord.get(0) +
//                    "', '" + csvRecord.get(2) + "', 1);");
        }
        // insert district
        for (CSVRecord csvRecord : csvRecords) {
            if (csvRecord.get(3).equals(temp2)) continue;
            else {
                temp2 = csvRecord.get(3);
                cnt++;
                parentIdWard.put(csvRecord.get(5), cnt - 1);
            }
//            System.out.println("insert into data_location(parent_id,name,code,type) values("
//                    + parentIdDistrict.get(csvRecord.get(2)) + ", '" + csvRecord.get(3) + "', '"
//                    + csvRecord.get(5)+"',2);");
        }
        for (CSVRecord csvRecord : csvRecords) {
            if (csvRecord.get(6).equals(temp3)) continue;
            else {
                temp3 = csvRecord.get(6);
                cnt++;
            }
            System.out.println("insert into data_location(parent_id,name,code,type) values("
                    + parentIdWard.get(csvRecord.get(5)) + ",'" + csvRecord.get(6) + "', '"
                    +csvRecord.get(8)+"' ,3);"
            );
        }
        System.out.println(parentIdWard.get("148"));
    }
}
