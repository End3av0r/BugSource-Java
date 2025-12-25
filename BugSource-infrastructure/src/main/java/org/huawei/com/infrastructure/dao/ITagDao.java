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
}
