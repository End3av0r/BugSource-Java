package org.huawei.com.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private Integer id;
    private String tagName;
    private String description;
    private Integer isDeleted;
    private Date createdTime;
    private Date updatedTime;
}
