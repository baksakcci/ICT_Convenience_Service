package com.example.sinabro.entity.union;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "room")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Union {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Convert(converter=BooleanToYNConverter.class)
    private boolean isOpen;

    public Union(boolean isOpen) {
        this.isOpen = isOpen;
    }
    public void setIsOpen(boolean b) {
        this.isOpen = b;
    }

}

@Converter
class BooleanToYNConverter implements AttributeConverter<Boolean, String>{
    @Override
    public String convertToDatabaseColumn(Boolean attribute){
        return (attribute != null && attribute) ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData){
        return "Y".equals(dbData);
    }
}