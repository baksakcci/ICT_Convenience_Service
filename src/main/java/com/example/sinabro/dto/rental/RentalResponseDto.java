package com.example.sinabro.dto.rental;

import com.example.sinabro.entity.item.Item;
import com.example.sinabro.entity.rental.Rental;
import com.example.sinabro.entity.rental.Rented;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalResponseDto {
    private String date;
    private Rented isRental;
    private Item item;

    public static RentalResponseDto toDto(Rental rental) {
        return new RentalResponseDto(rental.getDate(), rental.getIsRental(), rental.getItem());
    }
}
