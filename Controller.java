class Controller {
    Model model ;
    View view ;
    
    public Controller(){
        model = new Model(this) ;
        view = new View(this,"Q1 Check EXP ");
    }

    //รับ input จาก view 
    public void takeInput(String inputData) {
        if(!checkValidation(inputData)){
            view.updateOutputField("Unvalidation input try again.");
        } else if(model.findCode(inputData)){
            computeEXP(inputData) ;
        }else{
            view.updateOutputField("Not found in batabase try again.");
        }
         
    }

    //ตรวจสอบความถูกต้อง input
    public boolean checkValidation(String data){
        if (data.length() != 6) {
            return false;  // ถ้าความยาวไม่ตรงตามที่กำหนด ให้คืนค่า false
        }
        if (!data.matches("\\d{6}")) {
            return false;  // ถ้ามีตัวที่ไม่ใช่ตัวเลข ให้คืนค่า false
        }
        if (data.charAt(0) == '0') {
            return false;  // ถ้าตัวแรกเป็น 0 ให้คืนค่า false
        }

        return true ;
    }

    //เลือกส่งไปคำนวน แต่ละประเภท 
    public void computeEXP(String code){
        String[] info = model.findTypeByCode(code) ;
        switch (info[1]) {
            case "Fresh": 
                view.updateOutputField("Fresh Food information : " + String.join(", ", info) + " " + model.FreshEXP(info) + "\n" + model.getAllList()); 
                break;
            case "Canned": 
                view.updateOutputField("Canned Food information : " + String.join(", ", info) + " " + model.CannedEXP(info) + "\n" + model.getAllList());
                break;
            case "Pickled": 
                view.updateOutputField("Pickled Food information : " + String.join(", ", info) + " " + model.PickledEXP(info) + "\n" + model.getAllList());
                break;
            default: view.updateOutputField(code) ; break;
        }
        
    }

}
