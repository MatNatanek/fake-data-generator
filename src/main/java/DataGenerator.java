import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import entity.*;
import region.StatusRealizacji;
import region.Wojewodztwa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class DataGenerator {
    Faker faker = new Faker();
    FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-GB"), new RandomService());
    private static final SecureRandom random = new SecureRandom();
    public static final int KLIENT_ROW_NUMBER = 1500;
    public static final int PACZKA_ROW_NUMBER = 1500;
    public static final int UMOWA_ROW_NUMBER = 1500;
    public static final int SAMOCHOD_ROW_NUMBER = 1500;
    public static final int MAGAZYN_ROW_NUMBER = 1500;
    public static final int KURIER_ROW_NUMBER = 1500;
    public static final int ZLECENIE_ROW_NUMBER = 1500;

    HashSet<String> pinSet = new HashSet<String>();

    void generateData() {

        csvFileFromLis(generateKlientData(KLIENT_ROW_NUMBER), "klient.csv");
        csvFileFromLis(generatePaczkaData(PACZKA_ROW_NUMBER), "paczka.csv");
        csvFileFromLis(generateUmowaData(UMOWA_ROW_NUMBER), "umowa.csv");
        csvFileFromLis(generateSamochodData(SAMOCHOD_ROW_NUMBER), " samochod.csv");
        csvFileFromLis(generateMagazynData(MAGAZYN_ROW_NUMBER), "magazyn.csv");
        csvFileFromLis(generateKurierData(KURIER_ROW_NUMBER), "kurier.csv");
        csvFileFromLis(generateZlecenieData(ZLECENIE_ROW_NUMBER), "zlecenie.csv");

    }


    <T extends Object> void csvFileFromLis(List<T> list, String fileName) {
        try {
            Writer writer = new FileWriter(fileName);
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).withApplyQuotesToAll(false).build();
            beanToCsv.write(list);
            writer.close();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }

    }



    ArrayList<SamochodEntity> generateSamochodData(int numberOfRecords) {

        ArrayList<SamochodEntity> samochodEntityArrayList = new ArrayList<SamochodEntity>();
        for (int i = 0; i < numberOfRecords; i++) {
            SamochodEntity samochod = new SamochodEntity();
            samochod.setId_samochodu(i);
            samochod.setNr_rejestracyjny(faker.code().ean8());
            samochod.setPojemnosc(getRandomNumberInRange(10, 100));
            samochod.setMarka(faker.animal().name());
            samochodEntityArrayList.add(samochod);
        }
        System.out.println("Wygenerowano " + pinSet.size() + " peseli");
        return samochodEntityArrayList;
    }

    ArrayList<ZlecenieEntity> generateZlecenieData(int numberOfRecords) {

        ArrayList<ZlecenieEntity> zlecenieEntityArrayList = new ArrayList<ZlecenieEntity>();
        for (int i = 0; i < numberOfRecords; i++) {
            ZlecenieEntity zlecenieEntity = new ZlecenieEntity();
            zlecenieEntity.setId_zlecenia(i);
            zlecenieEntity.setKoszt(getRandomNumberInRange(5, 500));
            zlecenieEntity.setWojewodztwo_dostawy(randomEnum(Wojewodztwa.class).label);
            zlecenieEntity.setGmina_dostawy(faker.address().cityName());
            zlecenieEntity.setUlica_dostawy(faker.address().streetAddress());
            zlecenieEntity.setNr_budynku_dostawy(faker.address().streetAddressNumber());
            zlecenieEntity.setWojewodztwo_wyslania(randomEnum(Wojewodztwa.class).label);
            zlecenieEntity.setGmina_wyslania(faker.address().cityName());
            zlecenieEntity.setNr_budynku_wyslania(faker.address().streetAddressNumber());
            zlecenieEntity.setFk_id_klienta(getRandomNumberInRange(0, KLIENT_ROW_NUMBER));
            zlecenieEntity.setFk_id_kuriera(getRandomNumberInRange(0, KURIER_ROW_NUMBER));
            zlecenieEntity.setFk_id_przesylki(getRandomNumberInRange(0, PACZKA_ROW_NUMBER));
            zlecenieEntity.setStatus_realizacji(randomEnum(StatusRealizacji.class).label);
            zlecenieEntity.setFk_magazyn_poczatkowy(getRandomNumberInRange(0, MAGAZYN_ROW_NUMBER));
            zlecenieEntity.setFk_magazyn_koncowy(getRandomNumberInRange(0, MAGAZYN_ROW_NUMBER));
            //daty
            Date date1 = new Date(2015, 1, 1);
            Date date2 = new Date( 2019, 8, 1);
            Date dataNadania = faker.date().birthday(0, 5);

            zlecenieEntity.setData_nadania(convertToLocalDate(dataNadania));
            zlecenieEntity.setPlanowany_czas_realizacji(convertToLocalDate(dataNadania).plusDays(7));
            LocalDate dmp = convertToLocalDate(dataNadania).plusDays(getRandomNumberInRange(0,1));
            zlecenieEntity.setData_dostarczenia_na_magazyn_poczatkowy(dmp);
            LocalDate omp = dmp.plusDays(getRandomNumberInRange(0,1));
            zlecenieEntity.setData_opuszczenia_magazynu_poczatkowego(omp);
            LocalDate dmk = omp.plusDays(getRandomNumberInRange(0,1));
            zlecenieEntity.setData_dostarczenia_na_magazyn_koncowy(dmk);
            LocalDate omk = dmk.plusDays(getRandomNumberInRange(0,2));
            zlecenieEntity.setData_opuszczenia_magazynu_koncowego(omk);
            LocalDate realizacja = omk.plusDays(getRandomNumberInRange(1, 9));
            zlecenieEntity.setData_dostarczenia(realizacja);

            zlecenieEntityArrayList.add(zlecenieEntity);

        }
        System.out.println("Wygenerowano " + pinSet.size() + " kurierow");
        return zlecenieEntityArrayList;
    }

    ArrayList<KurierEntity> generateKurierData(int numberOfRecords) {

        ArrayList<KurierEntity> kurierEntityArrayList = new ArrayList<KurierEntity>();
        for (int i = 0; i < numberOfRecords; i++) {
            KurierEntity kurierEntity = new KurierEntity();
            kurierEntity.setId_kuriera(i);
            kurierEntity.setImie(faker.name().firstName());
            kurierEntity.setNazwisko(faker.name().lastName());
            kurierEntity.setPesel(getRandomPersonalIdNumber());
            kurierEntity.setFk_id_umowy(i);
            kurierEntity.setFk_id_samochodu(i);
            kurierEntity.setFk_id_magazynu(i);
            kurierEntity.setWojewodztwo(randomEnum(Wojewodztwa.class).label);
            kurierEntity.setGmina(faker.address().cityName());
            kurierEntity.setUlica(faker.address().streetAddress());
            kurierEntity.setObslugiwane_wojewodztwo(randomEnum(Wojewodztwa.class).label);
            kurierEntity.setObslugiwana_gmina(faker.address().cityName());
            kurierEntity.setNumer_telefonu(faker.phoneNumber().cellPhone());
            kurierEntity.setNr_budynku(faker.address().streetAddressNumber());
            kurierEntityArrayList.add(kurierEntity);
        }
        System.out.println("Wygenerowano " + pinSet.size() + " kurierow");
        return kurierEntityArrayList;
    }

    ArrayList<MagazynEntity> generateMagazynData(int numberOfRecords) {

        ArrayList<MagazynEntity> magazynEntityArrayList = new ArrayList<MagazynEntity>();
        for (int i = 0; i < numberOfRecords; i++) {
           MagazynEntity magazyn = new MagazynEntity();
           magazyn.setId_magazynu(i);
           magazyn.setId_magazynu(getRandomNumberInRange(1,20));
           magazyn.setGmina(faker.address().cityName());
           magazyn.setIlosc_pracownikow(getRandomNumberInRange(2,100));
           magazyn.setUlica(faker.address().streetAddress());
           magazyn.setNr_budynku(faker.address().streetAddressNumber());
           magazyn.setWojewodztwo(randomEnum(Wojewodztwa.class).label);
           magazynEntityArrayList.add(magazyn);
        }
        System.out.println("Wygenerowano " + pinSet.size() + " peseli");
        return magazynEntityArrayList;
    }

    ArrayList<KlientEntity> generateKlientData(int numberOfRecords) {

        ArrayList<KlientEntity> klientEntityArrayList = new ArrayList<KlientEntity>();
        for (int i = 0; i < numberOfRecords; i++) {
            KlientEntity klientEntity = new KlientEntity();
            klientEntity.setId_klienta(i);
            klientEntity.setImie(faker.name().firstName());
            klientEntity.setNazwisko(faker.name().lastName());
            klientEntity.setPesel(getRandomPersonalIdNumber());
            klientEntity.setNumer_telefonu(Integer.toString(getRandomNumberNdigit(9)));
            klientEntity.setWojewodztwo(randomEnum(Wojewodztwa.class).label);
            klientEntity.setGmina(faker.address().cityName());
            klientEntity.setUlica(faker.address().streetName());
            klientEntity.setNr_budynku(faker.address().streetAddressNumber());
            klientEntityArrayList.add(klientEntity);
        }
        System.out.println("Wygenerowano " + pinSet.size() + " peseli");
        return klientEntityArrayList;
    }

    ArrayList<PaczkaEntity> generatePaczkaData(int numberOfRecords) {
        ArrayList<PaczkaEntity> paczkaEntities = new ArrayList<PaczkaEntity>();
        for (int i = 0; i < numberOfRecords; i++) {
            PaczkaEntity paczka = new PaczkaEntity();
            paczka.setId_przesylki(i);
            paczka.setDlugosc_paczki(getRandomNumberInRange(10, 200));
            paczka.setSzerokosc_paczki(getRandomNumberInRange(10, 200));
            paczka.setWysokosc_paczki(getRandomNumberInRange(10, 200));
            paczka.setWaga(getRandomNumberInRange(50, 5000));
            paczkaEntities.add(paczka);
        }
        System.out.println("Wygenerowano " + paczkaEntities.size() + " paczek");
        return paczkaEntities;
    }

    ArrayList<UmowaEntity> generateUmowaData(int numberOfRecords) {

        ArrayList<UmowaEntity> umowaList = new ArrayList<UmowaEntity>();
        for (int i = 0; i < numberOfRecords; i++) {
            Date dateRoz = faker.date().birthday(1, 14);
            Date dateZak = faker.date().birthday(1, 14);

            UmowaEntity umowaEntity = new UmowaEntity();
            umowaEntity.setId_umowy(i);
            umowaEntity.setTyp(faker.leagueOfLegends().champion());
            umowaEntity.setData_rozpoczecia(convertToLocalDate(dateRoz));
            umowaEntity.setData_zakonczenia(convertToLocalDate(dateZak));
            umowaEntity.setPensja(getRandomNumberInRange(1500, 5000));
            umowaList.add(umowaEntity);
        }
        System.out.println("Wygenerowano " + pinSet.size() + " peseli");
        return umowaList;
    }

    //Metody pomocnicze
    Integer getRandomNumberNdigit(int n) {
        int m = (int) Math.pow(10, n - 1);
        return m + new Random().nextInt(9 * m);
    }

    String getRandomPersonalIdNumber() {
        String pin = fakeValuesService.numerify("###########");
        while (pinSet.contains(pin)) {
            pin = fakeValuesService.numerify("###########");
        }
        pinSet.add(pin);
        return pin;
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    <T extends Object> void jsonFromList(List<T> arrayList) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("jsonOutputFile\\klient.json"), arrayList);
        } catch (IOException e) {
            System.out.println("Exception  casting  arrayList to json file");
        }

    }

}
