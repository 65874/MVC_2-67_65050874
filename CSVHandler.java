import java.io.*;
import java.util.*;

public class CSVHandler {
    private static CSVHandler instance;  // ตัวแปรสำหรับเก็บอินสแตนซ์ Singleton
    private final String filePath;

    // Constructor เป็น private เพื่อป้องกันการสร้างอินสแตนซ์จากภายนอก
    // ถ้ายังไม่มีไฟล์จะสร้าง ใหม่พร้อม add setup data
    private CSVHandler(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        if (!file.exists()) {
            createFile();
            writeData(new String[]{"100001", "Fresh", "12/05/2025"});
            writeData(new String[]{"100002", "Fresh", "15/08/2027"});
            writeData(new String[]{"100003", "Fresh", "09/11/2023"});
            writeData(new String[]{"100004", "Fresh", "03/01/2028"});
            writeData(new String[]{"100005", "Fresh", "25/12/2029"});
            writeData(new String[]{"100006", "Fresh", "18/06/2024"});
            writeData(new String[]{"100007", "Fresh", "07/04/2030"});
            writeData(new String[]{"100008", "Fresh", "14/09/2022"});
            writeData(new String[]{"100009", "Fresh", "20/03/2026"});
            writeData(new String[]{"100010", "Fresh", "28/07/2023"});

            writeData(new String[]{"200001", "Pickled", "07/11/2022"});
            writeData(new String[]{"200002", "Pickled", "29/01/2017"});
            writeData(new String[]{"200003", "Pickled", "02/12/2029"});
            writeData(new String[]{"200004", "Pickled", "11/05/2024"});
            writeData(new String[]{"200005", "Pickled", "09/03/2021"});
            writeData(new String[]{"200006", "Pickled", "19/08/2028"});
            writeData(new String[]{"200007", "Pickled", "02/02/2025"});
            writeData(new String[]{"200008", "Pickled", "30/10/2020"});
            writeData(new String[]{"200009", "Pickled", "13/02/2027"});
            writeData(new String[]{"200010", "Pickled", "22/09/2019"});

            writeData(new String[]{"300001", "Canned", "23/08/2028"});
            writeData(new String[]{"300002", "Canned", "15/07/2019"});
            writeData(new String[]{"300003", "Canned", "14/09/2024"});
            writeData(new String[]{"300004", "Canned", "10/01/2023"});
            writeData(new String[]{"300005", "Canned", "05/10/2030"});
            writeData(new String[]{"300006", "Canned", "27/04/2026"});
            writeData(new String[]{"300007", "Canned", "03/06/2022"});
            writeData(new String[]{"300008", "Canned", "21/03/2018"});
            writeData(new String[]{"300009", "Canned", "17/12/2025"});
            writeData(new String[]{"300010", "Canned", "08/11/2015"});

            writeData(new String[]{"100011", "Fresh", "02/02/2021"});
            writeData(new String[]{"100012", "Fresh", "11/04/2029"});
            writeData(new String[]{"100013", "Fresh", "19/06/2018"});
            writeData(new String[]{"100014", "Fresh", "24/08/2027"});
            writeData(new String[]{"100015", "Fresh", "03/03/2016"});
            writeData(new String[]{"100016", "Fresh", "10/10/2024"});
            writeData(new String[]{"100017", "Fresh", "05/05/2025"});
            writeData(new String[]{"100018", "Fresh", "18/12/2023"});
            writeData(new String[]{"100019", "Fresh", "22/11/2027"});
            writeData(new String[]{"100020", "Fresh", "30/08/2029"});

            writeData(new String[]{"200011", "Pickled", "06/07/2019"});
            writeData(new String[]{"200012", "Pickled", "15/05/2028"});
            writeData(new String[]{"200013", "Pickled", "23/09/2025"});
            writeData(new String[]{"200014", "Pickled", "12/11/2023"});
            writeData(new String[]{"200015", "Pickled", "31/01/2020"});
            writeData(new String[]{"200016", "Pickled", "04/03/2022"});
            writeData(new String[]{"200017", "Pickled", "16/08/2024"});
            writeData(new String[]{"200018", "Pickled", "08/09/2017"});
            writeData(new String[]{"200019", "Pickled", "25/12/2018"});
            writeData(new String[]{"200020", "Pickled", "19/07/2026"});

            writeData(new String[]{"300011", "Canned", "04/04/2026"});
            writeData(new String[]{"300012", "Canned", "09/12/2017"});
            writeData(new String[]{"300013", "Canned", "18/08/2019"});
            writeData(new String[]{"300014", "Canned", "27/10/2024"});
            writeData(new String[]{"300015", "Canned", "22/06/2023"});
            writeData(new String[]{"300016", "Canned", "13/01/2020"});
            writeData(new String[]{"300017", "Canned", "17/03/2025"});
            writeData(new String[]{"300018", "Canned", "12/09/2028"});
            writeData(new String[]{"300019", "Canned", "05/11/2022"});
            writeData(new String[]{"300020", "Canned", "26/05/2030"});

        }
    }

    // ดึงอินสแตนซ์ของ Singleton 
    public static CSVHandler getInstance(String filePath) {
        if (instance == null) {
            instance = new CSVHandler(filePath);
        }
        return instance;
    }

    // สร้างไฟล์ใหม่
    private void createFile() {
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("Create new file: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // อ่านข้อมูลทั้งหมดจากไฟล์ CSV
    public List<String[]> readData() {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));  // แยกข้อมูลแต่ละคอลัมน์
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    // เขียนข้อมูล new row
    public void writeData(String[] values) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(String.join(",", values));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // แก้ไขข้อมูลใน row
    public void updateRow(int rowIndex, String[] newValues) {
        List<String[]> data = readData();
        if (rowIndex < 0 || rowIndex >= data.size()) {
            throw new IndexOutOfBoundsException("Row not found");
        }

        data.set(rowIndex, newValues);
        saveAllData(data);
    }

    // ลบบรรทัดข้อมูลที่กำหนด
    public void deleteRow(int rowIndex) {
        List<String[]> data = readData();
        if (rowIndex < 0 || rowIndex >= data.size()) {
            throw new IndexOutOfBoundsException("Row not found");
        }

        data.remove(rowIndex);
        saveAllData(data);
    }

    // เขียนข้อมูลทั้งหมดกลับไปในไฟล์
    private void saveAllData(List<String[]> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ลบข้อมูลทั้งหมดในไฟล์ CSV
    public void clearData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("");  // ลบข้อมูลทั้งหมด
            System.out.println("Delete all data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
