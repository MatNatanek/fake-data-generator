package region;

public enum Wojewodztwa {
    DOL("dolnoslaskie"),
    KUJ("kujawsko-pomorskie"),
    LUB("lubelskie"),
    LUE("lubuskie"),
    LDE("lodzkie"),
    MAL("malopolskie"),
    MAZ("mazowieckie"),
    OPO("opolskie"),
    POD("podkarpackie"),
    POE("podlaskie"),
    POM("pomorskie"),
    SLE("slaskie"),
    SWE("swietrokrzyskie"),
    WMM("warminsko-mazurskie"),
    WPL("wielkopolskie"),
    ZPM("zachodniopomorskie");

    public final String label;

    private Wojewodztwa(String label) {
        this.label = label;
    }
}
