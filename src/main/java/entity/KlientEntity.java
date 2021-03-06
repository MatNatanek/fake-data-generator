package entity;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KlientEntity {
    @CsvBindByPosition(position = 0)
    Integer id_klienta;
    @CsvBindByPosition(position = 1)
    String imie;
    @CsvBindByPosition(position = 2)
    String nazwisko;
    @CsvBindByPosition(position = 3)
    String wojewodztwo;
    @CsvBindByPosition(position = 4)
    String gmina;
    @CsvBindByPosition(position = 5)
    String ulica;
    @CsvBindByPosition(position = 6)
    String nr_budynku;
    @CsvBindByPosition(position = 7)
    String pesel;
    @CsvBindByPosition(position = 8)
    String numer_telefonu;
}
