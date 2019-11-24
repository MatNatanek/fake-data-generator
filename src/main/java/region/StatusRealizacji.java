package region;

public enum StatusRealizacji {

    ZR("Zrealizowano"),
    RE("Reklamacja");


    public final String label;

    private StatusRealizacji(String label) {
        this.label = label;
    }



}
