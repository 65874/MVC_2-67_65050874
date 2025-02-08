import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Model {
    Controller controller ;
    CSVHandler csv ;
    List<String[]> FreshEXPList ;
    List<String[]> PickledEXPList ;
    List<String[]> CannedEXPList  ;
    List<String[]> UnexpiredList  ;

    Model(Controller controller){
        this.controller = controller ;
        this.csv = CSVHandler.getInstance("data.csv");
        FreshEXPList = new ArrayList<>() ;
        PickledEXPList = new ArrayList<>() ;
        CannedEXPList = new ArrayList<>() ;
        UnexpiredList = new ArrayList<>() ;
    }

    //ค้นหาว่ามี อาหารนี้ ใน database หรือไม่ด้วย รหัส
    public boolean findCode(String code){
        List<String[]> allData = csv.readData();
        for (String[] row : allData) {
            if (row.length > 0 && row[0].equals(code)) {  
                return true; 
            }
        }
        return false; 
    }

    //ค้นหาว่าเป็นอาหารชนิดไหนและส่งข้อมูลไปยัง controller
    public String[] findTypeByCode(String code){

        List<String[]> allData = csv.readData();
        for (String[] row : allData) {
            if (row.length >= 2 && row[0].equals(code)) { 
                return row;  
            }
        }
        return null;
    }

    // 3 Method สำหรับคำนวน วันหมดอายุแต่ละแบบ

    public String FreshEXP(String[] date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            Date inputDate = dateFormat.parse(date[2]);
            Date currentDate = new Date();
            if(inputDate.before(currentDate)) {
                FreshEXPList.add(date);
                return "this Fresh food is Expired" ;
            }
            UnexpiredList.add(date);
            return "this Fresh food is Unexpired";
        } catch (ParseException e) {
            return "Error: Invalid date format";  
        }
    }

    public String CannedEXP(String[] date){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
    
            try {
                Date inputDate = dateFormat.parse(date[2]);
    
                // เพิ่มวันหมดอายุไปอีก 9 เดือน
                Calendar expirationCalendar = Calendar.getInstance();
                expirationCalendar.setTime(inputDate);
                expirationCalendar.add(Calendar.MONTH, 9);  // บวก 9 เดือน
                Date extendedExpirationDate = expirationCalendar.getTime();
    
                // วันที่ปัจจุบัน
                Date currentDate = new Date();
    
                // ตรวจสอบวันหมดอายุ (ถ้าวันปัจจุบันเลยวันที่บวก 9 เดือนถือว่าหมดอายุ)
                if (currentDate.before(extendedExpirationDate)) {
                    UnexpiredList.add(date);  
                    return "This Canned food is Unexpired";
                } else {
                    CannedEXPList.add(date);
                    return "This Canned food is Expired";
                }
    
            } catch (ParseException e) {
                return "Error: Invalid date format";
            }
        
    }

    public String PickledEXP(String[] date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);  

        try {
            Date inputDate = dateFormat.parse(date[2]);

            Calendar expirationCalendar = Calendar.getInstance();
            expirationCalendar.setTime(inputDate);
            expirationCalendar.set(Calendar.DAY_OF_MONTH, 1);
            expirationCalendar.add(Calendar.MONTH, 1); 
            Date extendedExpirationDate = expirationCalendar.getTime();

            Date currentDate = new Date();
            
            // ตรวจสอบวันหมดอายุ (ก่อนวันที่ 1 ของเดือนถัดไปจากวันหมดอายุ เทียบกับวันที่ปัจจุบัน)
            if (extendedExpirationDate.before(currentDate)) {
                PickledEXPList.add(date);
                return "This Pickled food is expired"; 
            } else {
                UnexpiredList.add(date);
                return "This Pickled food is unexpired";
            }

        } catch (ParseException e) {
            return "Error: Invalid date format";
        }
    
    }

    // 2 Method ด้านล่างจัดการข้อมูลที่จะส่งกลับไปแสดงผลการค้นหาทั้งหมด

    public String getAllList() {
        StringBuilder allData = new StringBuilder();  // ใช้ StringBuilder สำหรับการรวมข้อความ

        allData.append("=== Fresh Food ===\n");
        allData.append(formatList(FreshEXPList));  // แสดงข้อมูล Fresh

        allData.append("\n=== Pickled Food ===\n");
        allData.append(formatList(PickledEXPList));  // แสดงข้อมูล Pickled

        allData.append("\n=== Canned Food ===\n");
        allData.append(formatList(CannedEXPList));  // แสดงข้อมูล Canned

        allData.append("\n=== Unexpired Food ===\n");
        allData.append(formatList(UnexpiredList));  // แสดงข้อมูลที่ยังไม่หมดอายุ

        return allData.toString();
    }

    private String formatList(List<String[]> list) {
        StringBuilder formattedData = new StringBuilder();

        for (String[] data : list) {
            formattedData.append("Code: ").append(data[0])
                         .append(", Type: ").append(data[1])
                         .append(", Expired : ").append(data[2])
                         .append("\n");
        }

        if (list.isEmpty()) {
            formattedData.append("no check history \n");
        }

        return formattedData.toString();
    }

}
