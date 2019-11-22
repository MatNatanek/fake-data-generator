package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KlientEntity {
    Integer id_klienta;
    String imie;
    String nazwisko;
    String pesel;
    String numer_telefonu;
    String województwo;
    String gmina;
    String ulica;
    String nr_budynku;
}
