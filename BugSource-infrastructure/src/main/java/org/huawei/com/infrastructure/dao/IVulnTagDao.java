package org.huawei.com.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.huawei.com.infrastructure.dao.po.VulnTag;

import java.util.List;

/**
 * @author: ExC1ammm
 * @description:
 * @create: 2025-03-17 16:52
 */
@Mapper
public interface IVulnTagDao {
    void insertVulnTag(VulnTag vulnTag);

    void deleteVulnTag(VulnTag vulnTag);

    void updateVulnTag(VulnTag vulnTag);

    List<VulnTag> queryTagsByVulnId(int id);

    /**
     * 查询所有不重复的标签
     * @return 所有标签列表
     */
    List<String> queryAllDistinctTags();
}
