package org.huawei.com.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.huawei.com.infrastructure.dao.po.Tag;

import java.util.List;

@Mapper
public interface ITagDao {

    int insertTag(Tag tag);

    int updateTag(Tag tag);

    int deleteTag(Integer id);

    Tag queryTagById(Integer id);

    Tag queryTagByName(String tagName);

    List<Tag> queryAllTags();

    List<Tag> queryActiveTagsOnly();

    /**
     * 统计每个标签的漏洞数量
     * 通过关联表 vulnerability_tag_relation 统计
     *
     * @return 标签统计列表，每个Map包含tag和count字段
     */
    List<java.util.Map<String, Object>> countVulnByTag();
}
