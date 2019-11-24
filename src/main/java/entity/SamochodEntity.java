package entity;


import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SamochodEntity {

    @CsvBindByPosition(position = 0)
    Integer id_samochodu;
    @CsvBindByPosition(position = 3)
    String marka;
    @CsvBindByPosition(position = 1)
    String nr_rejestracyjny;
    @CsvBindByPosition(position = 2)
    Integer pojemnosc;
}
