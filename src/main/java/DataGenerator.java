import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import entity.KlientEntity;
import entity.PaczkaEntity;
import region.Wojewodztwa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.SecureRandom;
import java.util.*;

public class DataGenerator {
    Faker faker = new Faker();
    FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-GB"), new RandomService());
    private static final SecureRandom random = new SecureRandom();
    public static final int KLIENT_ROW_NUMBER = 1500;
    public static final int PACZKA_ROW_NUMBER = 1500;
    HashSet<String> pinSet = new HashSet<String>();

    void generateData() {


        csvFileFromLis(generateKlientData(KLIENT_ROW_NUMBER), "klient.csv");
        csvFileFromLis(generatePaczkaData(PACZKA_ROW_NUMBER), "paczka.csv");


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

    <T extends Object> void jsonFromList(List<T> arrayList) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("jsonOutputFile\\klient.json"), arrayList);
        } catch (IOException e) {
            System.out.println("Exception  casting  arrayList to json file");
        }


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

    ArrayList<PaczkaEntity> generatePaczkaData(int numberOfRecords){
        ArrayList<PaczkaEntity> paczkaEntities = new ArrayList<PaczkaEntity>();
        for(int i =0; i < numberOfRecords; i++){
            PaczkaEntity paczka = new PaczkaEntity();
            paczka.setId_przesylki(i);
            paczka.setDlugosc_paczki(getRandomNumberInRange(10, 200));
            paczka.setSzerokosc_paczki(getRandomNumberInRange(10,200));
            paczka.setWysokosc_paczki(getRandomNumberInRange(10, 200));
            paczka.setWaga(getRandomNumberInRange(50, 5000));
            paczkaEntities.add(paczka);
        }
        System.out.println("Wygenerowano " + paczkaEntities.size() + " paczek");
        return paczkaEntities;
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
}
