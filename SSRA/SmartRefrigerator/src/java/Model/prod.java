package Model;
import prodpkg.ProdDBHandler;

public class prod {
private int id;
private String cmbCNC,cmbcat,cmbsubcat,tid;

/*Getters and Setters methods*/
public int getId() {  
    return id;  
}  
public void setId(int id) {  
    this.id = id;  
}
public String gettid() {
    tid = ProdDBHandler.checktid();
    return tid;  
}  
public void setmid(String tid) {
    this.tid = tid;  
}
public String getcmbCNC() {  
    return cmbCNC;  
}  
public void setcmbCNC(String cmbCNC) {  
    this.cmbCNC = cmbCNC;  
}  
public String getcmbcat() {  
    return cmbcat;  
}  
public void setcmbcat(String cmbcat) {  
    this.cmbcat = cmbcat;  
}  
public String getcmbsubcat() {  
    return cmbsubcat;  
}  
public void setcmbsubcat(String cmbsubcat) {  
    this.cmbsubcat = cmbsubcat;  
}  
}
