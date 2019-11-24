package entity;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZlecenieEntity {

    @CsvBindByPosition(position = 0)
    Integer id_zlecenia;
    @CsvBindByPosition(position = 1)
    Integer koszt;
    @CsvBindByPosition(position = 2)
    String wojewodztwo_dostawy;
    @CsvBindByPosition(position = 3)
    String gmina_dostawy;
    @CsvBindByPosition(position = 4)
    String ulica_dostawy;
    @CsvBindByPosition(position = 5)
    String nr_budynku_dostawy;
    @CsvBindByPosition(position = 6)
    String wojewodztwo_wyslania;
    @CsvBindByPosition(position = 7)
    String gmina_wyslania;
    @CsvBindByPosition(position = 8)
    String nr_budynku_wyslania;
    @CsvBindByPosition(position = 9)
    Integer fk_id_klienta;
    @CsvBindByPosition(position = 10)
    Integer fk_id_kuriera;
    @CsvBindByPosition(position = 11)
    Integer fk_id_przesylki;
    @CsvBindByPosition(position = 12)
    LocalDate data_nadania;
    @CsvBindByPosition(position = 13)
    LocalDate data_dostarczenia;
    @CsvBindByPosition(position = 14)
    LocalDate data_dostarczenia_na_magazyn_poczatkowy;
    @CsvBindByPosition(position = 15)
    LocalDate data_opuszczenia_magazynu_poczatkowego;
    @CsvBindByPosition(position = 16)
    LocalDate data_dostarczenia_na_magazyn_koncowy;
    @CsvBindByPosition(position = 17)
    LocalDate data_opuszczenia_magazynu_koncowego;
    @CsvBindByPosition(position = 18)
    LocalDate planowany_czas_realizacji;
    @CsvBindByPosition(position = 19)
    String status_realizacji;
    @CsvBindByPosition(position = 20)
    Integer fk_magazyn_poczatkowy;
    @CsvBindByPosition(position = 21)
    Integer fk_magazyn_koncowy;



}
