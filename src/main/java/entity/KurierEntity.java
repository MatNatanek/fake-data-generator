package entity;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class KurierEntity {
    @CsvBindByPosition(position = 0)
    Integer id_kuriera;
    @CsvBindByPosition(position = 1)
    String imie;
    @CsvBindByPosition(position = 2)
    String nazwisko;
    @CsvBindByPosition(position = 13)
    String pesel;
    @CsvBindByPosition(position = 3)
    Integer fk_id_umowy;
    @CsvBindByPosition(position = 4)
    Integer fk_id_samochodu;
    @CsvBindByPosition(position = 5)
    Integer fk_id_magazynu;
    @CsvBindByPosition(position = 6)
    String wojewodztwo;
    @CsvBindByPosition(position = 7)
    String gmina;
    @CsvBindByPosition(position = 8)
    String ulica;
    @CsvBindByPosition(position = 9)
    String obslugiwane_wojewodztwo;
    @CsvBindByPosition(position = 10)
    String obslugiwana_gmina;
    @CsvBindByPosition(position = 11)
    String numer_telefonu;
    @CsvBindByPosition(position = 12)
    String nr_budynku;
}
