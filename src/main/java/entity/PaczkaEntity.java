package entity;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaczkaEntity {

    @CsvBindByPosition(position = 0)
    Integer id_przesylki;
    @CsvBindByPosition(position = 1)
    Integer waga;
    @CsvBindByPosition(position = 2)
    Integer szerokosc_paczki;
    @CsvBindByPosition(position = 3)
    Integer wysokosc_paczki;
    @CsvBindByPosition(position = 4)
    Integer dlugosc_paczki;
}
