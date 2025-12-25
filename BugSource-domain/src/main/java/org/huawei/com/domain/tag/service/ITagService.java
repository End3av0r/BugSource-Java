package org.huawei.com.domain.tag.service;

import org.huawei.com.domain.tag.model.entity.TagEntity;

import java.util.List;

public interface ITagService {

    int createTag(TagEntity tagEntity);

    int updateTag(TagEntity tagEntity);

    int deleteTag(Integer id);

    TagEntity queryTagById(Integer id);

    TagEntity queryTagByName(String tagName);

    List<TagEntity> queryAllTags();

    List<TagEntity> queryActiveTagsOnly();
}
