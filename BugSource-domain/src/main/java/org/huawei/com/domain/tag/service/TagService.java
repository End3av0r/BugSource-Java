package org.huawei.com.domain.tag.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.huawei.com.domain.tag.model.entity.TagEntity;
import org.huawei.com.domain.tag.repository.ITagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TagService implements ITagService {

    @Resource
    private ITagRepository tagRepository;

    @Override
    public int createTag(TagEntity tagEntity) {
        TagEntity existingTag = tagRepository.queryTagByName(tagEntity.getTagName());
        if (existingTag != null) {
            log.warn("标签已存在: {}", tagEntity.getTagName());
            throw new RuntimeException("标签名称已存在");
        }
        return tagRepository.createTag(tagEntity);
    }

    @Override
    public int updateTag(TagEntity tagEntity) {
        return tagRepository.updateTag(tagEntity);
    }

    @Override
    public int deleteTag(Integer id) {
        return tagRepository.deleteTag(id);
    }

    @Override
    public TagEntity queryTagById(Integer id) {
        return tagRepository.queryTagById(id);
    }

    @Override
    public TagEntity queryTagByName(String tagName) {
        return tagRepository.queryTagByName(tagName);
    }

    @Override
    public List<TagEntity> queryAllTags() {
        return tagRepository.queryAllTags();
    }

    @Override
    public List<TagEntity> queryActiveTagsOnly() {
        return tagRepository.queryActiveTagsOnly();
    }
}
