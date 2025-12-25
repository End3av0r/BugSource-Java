package org.huawei.com.domain.tag.repository;

import org.huawei.com.domain.tag.model.entity.TagEntity;

import java.util.List;

public interface ITagRepository {

    int createTag(TagEntity tagEntity);

    int updateTag(TagEntity tagEntity);

    int deleteTag(Integer id);

    TagEntity queryTagById(Integer id);

    TagEntity queryTagByName(String tagName);

    List<TagEntity> queryAllTags();

    List<TagEntity> queryActiveTagsOnly();
}
