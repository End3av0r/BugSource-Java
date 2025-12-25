package org.huawei.com.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagUpdateRequestDTO {
    private Integer id;
    private String tagName;
    private String description;
}
