package mx.com.televisa.landamark.client.email.types.local;

public class EmailDestinationAddress {
    private String lsNameTo;
    private String lsAddressTo;

    public void setLsNameTo(String lsNameTo) {
        this.lsNameTo = lsNameTo;
    }

    public String getLsNameTo() {
        return lsNameTo;
    }

    public void setLsAddressTo(String lsAddressTo) {
        this.lsAddressTo = lsAddressTo;
    }

    public String getLsAddressTo() {
        return lsAddressTo;
    }
}
