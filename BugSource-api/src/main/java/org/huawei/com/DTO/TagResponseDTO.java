package org.huawei.com.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagResponseDTO {
    private Integer id;
    private String tagName;
    private String description;
    private Integer isDeleted;
    private Date createdTime;
    private Date updatedTime;
}
