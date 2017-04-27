
package Enumerators;

public enum UbicationResults {
    SUCCESFULL(0),
    INVALIDLICENCESPLATE(1),
    INVALIDLATITUDEORLONGITUDE(2),
    INVALIDDATE(3),
    UNESPESIFICSQLERROR(4);
    private int numVal;
    private String message;
    
    public int getnumVal(){
        return this.numVal;
    }
    public String getMessage(){
        switch(this){
            case SUCCESFULL : this.message = "Succesfully Added"; break ;
            case INVALIDLICENCESPLATE : this.message = "Invalid Unit"; break ;
            case INVALIDLATITUDEORLONGITUDE : this.message = "invalid Coordenates"; break ;
            case  INVALIDDATE : this.message = "Invalid Date"; break ;
            case UNESPESIFICSQLERROR : this.message = "Unspecified SQL Error"; break ;
        }
        return this.message;
    }

    private UbicationResults (int numVal) {
        this.numVal = numVal;
        this.message = "";
    }
   
}
