package org.huawei.com.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ExC1ammm
 * @description:
 * @create: 2025-03-17 17:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VulnTagModifyRequestDTO {
    private Long vulnId;
    private String tag;
}
