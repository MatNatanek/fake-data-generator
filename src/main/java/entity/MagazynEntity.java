package entity;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MagazynEntity {

    @CsvBindByPosition(position = 0)
    Integer id_magazynu;
    @CsvBindByPosition(position = 1)
    Integer ilosc_pracownikow;
    @CsvBindByPosition(position = 2)
    Integer ilosc_bram;
    @CsvBindByPosition(position = 3)
    String wojewodztwo;
    @CsvBindByPosition(position = 4)
    String gmina;
    @CsvBindByPosition(position = 5)
    String ulica;
    @CsvBindByPosition(position = 6)
    String nr_budynku;
}
