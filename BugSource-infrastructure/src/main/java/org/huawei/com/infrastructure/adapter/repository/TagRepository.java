package org.huawei.com.infrastructure.adapter.repository;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.huawei.com.domain.tag.model.entity.TagEntity;
import org.huawei.com.domain.tag.repository.ITagRepository;
import org.huawei.com.infrastructure.dao.ITagDao;
import org.huawei.com.infrastructure.dao.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class TagRepository implements ITagRepository {

    @Resource
    private ITagDao tagDao;

    @Override
    public int createTag(TagEntity tagEntity) {
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagEntity, tag);
        return tagDao.insertTag(tag);
    }

    @Override
    public int updateTag(TagEntity tagEntity) {
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagEntity, tag);
        return tagDao.updateTag(tag);
    }

    @Override
    public int deleteTag(Integer id) {
        return tagDao.deleteTag(id);
    }

    @Override
    public TagEntity queryTagById(Integer id) {
        Tag tag = tagDao.queryTagById(id);
        if (tag == null) {
            return null;
        }
        TagEntity tagEntity = new TagEntity();
        BeanUtils.copyProperties(tag, tagEntity);
        return tagEntity;
    }

    @Override
    public TagEntity queryTagByName(String tagName) {
        Tag tag = tagDao.queryTagByName(tagName);
        if (tag == null) {
            return null;
        }
        TagEntity tagEntity = new TagEntity();
        BeanUtils.copyProperties(tag, tagEntity);
        return tagEntity;
    }

    @Override
    public List<TagEntity> queryAllTags() {
        List<Tag> tags = tagDao.queryAllTags();
        List<TagEntity> tagEntities = new ArrayList<>();
        for (Tag tag : tags) {
            TagEntity tagEntity = new TagEntity();
            BeanUtils.copyProperties(tag, tagEntity);
            tagEntities.add(tagEntity);
        }
        return tagEntities;
    }

    @Override
    public List<TagEntity> queryActiveTagsOnly() {
        List<Tag> tags = tagDao.queryActiveTagsOnly();
        List<TagEntity> tagEntities = new ArrayList<>();
        for (Tag tag : tags) {
            TagEntity tagEntity = new TagEntity();
            BeanUtils.copyProperties(tag, tagEntity);
            tagEntities.add(tagEntity);
        }
        return tagEntities;
    }
}
