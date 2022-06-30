package Model;
import tagpkg.TagDBHandler;

public class tag {
private int id, bqty;
private String Mmid,Mtid,prodname,mfgdate,bno,expdate,brand,rf,rfid;

/*Getters and Setters methods*/
public int getId() {  
    return id;  
}  
public void setId(int id) {  
    this.id = id;  
}
/*This method concatenates '-' for product name and brand if length of string is less than 25*/
public String getrf() {
    int i,j;
    String randomNo = TagDBHandler.checkid();
    String prod = prodname;
    String br=brand;
    for(i=prod.length();i<25;i++){
        prod += "-";
    }
    for(i=br.length();i<25;i++){
        br += "-";
    }
    rf = "R" + prod + mfgdate + br + expdate + randomNo;
    return rf;  
}  
public void setrf(String rf) {
    this.rf = rf;  
}
public String getrfid() {
    rfid = Mmid+Mtid+rf;
    return rfid;  
}  
public void setrfid(String rfid) {
    this.rfid = rfid;  
}
public String getMmid() {
    //tid = ProdDBHandler.checktid();
    return Mmid;  
}  
public void setMmid(String Mmid) {
    this.Mmid = Mmid;  
}
public String getMtid() {
    //tid = ProdDBHandler.checktid();
    return Mtid;  
}  
public void setMtid(String Mtid) {
    this.Mtid = Mtid;  
}
public String getprodname() {  
    return prodname;  
}  
public void setprodname(String prodname) {  
    this.prodname = prodname;  
}  
public String getmfgdate() {  
    return mfgdate;  
}  
public void setmfgdate(String mfgdate) {  
    this.mfgdate = mfgdate;  
}  
public String getbno() {  
    return bno;  
}  
public void setbno(String bno) {  
    this.bno = bno;  
}
public int getbqty() {  
    return bqty;  
}  
public void setbqty(int bqty) {  
    this.bqty = bqty;  
}
public String getbrand() {  
    return brand;  
}  
public void setbrand(String brand) {  
    this.brand = brand;  
}     
public String getexpdate() {  
    return expdate;  
}  
public void setexpdate(String expdate) {  
    this.expdate = expdate;  
}    
}
