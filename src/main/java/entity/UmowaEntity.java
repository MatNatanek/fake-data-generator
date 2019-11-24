package entity;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UmowaEntity {
    @CsvBindByPosition(position = 0)
    Integer id_umowy;
    @CsvBindByPosition(position = 1)
    String typ;
    @CsvBindByPosition(position = 3)
    LocalDate data_rozpoczecia;
    @CsvBindByPosition(position = 4)
    LocalDate data_zakonczenia;
    @CsvBindByPosition(position = 2)
    Integer pensja;

}
