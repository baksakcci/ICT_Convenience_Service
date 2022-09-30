package com.example.sinabro.dto.item;

import com.example.sinabro.entity.item.Used;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemRequestDto {
    @NotBlank(message = "아이템 이름을 작성해주세요")
    private String itemName;

    @NotBlank(message = "아이템 세부적인 이름 혹은 넘버를 작성해주세요")
    private String itemDetailName;

    @NotNull(message = "사용여부를 작성해주세요")
    private Used used;

}
