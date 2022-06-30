package Model;
import mfrpkg.MfrDBHandler;

public class Mfr {
private int id;
private String mname,maddr,memail,mphno, mid, midDB;

/*Getters and Setters methods*/
public int getId() {  
    return id;  
}  
public void setId(int id) {  
    this.id = id;  
}
public String getmid() {
    mid = MfrDBHandler.checkmid();
    return mid;  
}  
public void setmid(String mid) {
    this.mid = mid;  
}
public String getmname() {  
    return mname;  
}  
public void setmname(String mname) {  
    this.mname = mname;  
}  
public String getmaddr() {  
    return maddr;  
}  
public void setmaddr(String maddr) {  
    this.maddr = maddr;  
}  
public String getmemail() {  
    return memail;  
}  
public void setmemail(String memail) {  
    this.memail = memail;  
}  
public String getmphno() {  
    return mphno;  
}  
public void setmphno(String mphno) {  
    this.mphno = mphno;  
}
public void setMIDFromDB(String val){
    this.midDB = val;
}   
public String getMIDFromDB() {  
    return midDB;  
}
}
