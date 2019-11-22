package region;

public enum Wojewodztwa {
    DOL("dolnośląskie"),
    KUJ("kujawsko-pomorskie"),
    LUB("lubelskie"),
    LUE("lubuskie"),
    LDE("łódzkie"),
    MAL("małopolskie"),
    MAZ("mazowieckie"),
    OPO("opolskie"),
    POD("podkarpackie"),
    POE("podlaskie"),
    POM("pomorskie"),
    SLE("śląskie"),
    SWE("świętrokrzyskie"),
    WMM("warmińsko-mazurskie"),
    WPL("wielkopolskie"),
    ZPM("zachodniopomorskie");

    public final String label;

    private Wojewodztwa(String label) {
        this.label = label;
    }
}
