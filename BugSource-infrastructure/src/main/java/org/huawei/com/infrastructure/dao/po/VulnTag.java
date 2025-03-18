package org.huawei.com.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: ExC1ammm
 * @description:
 * @create: 2025-03-17 16:48
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VulnTag {
    /**
     * 自增ID
     */
    private Long id;
    /**
     * 漏洞 id
     */
    private Long vulnId;
    /**
     * 漏洞标签
     */
    private String tag;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date updatedTime;
}
