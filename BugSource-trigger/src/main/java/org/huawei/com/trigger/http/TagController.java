package org.huawei.com.trigger.http;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.huawei.com.DTO.TagRequestDTO;
import org.huawei.com.DTO.TagResponseDTO;
import org.huawei.com.DTO.TagUpdateRequestDTO;
import org.huawei.com.domain.tag.model.entity.TagEntity;
import org.huawei.com.domain.tag.service.ITagService;
import org.huawei.com.types.enums.ResponseCode;
import org.huawei.com.types.model.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api/tag")
public class TagController {

    @Resource
    private ITagService tagService;

    @PostMapping("/create")
    public Response<TagResponseDTO> createTag(@RequestBody TagRequestDTO requestDTO) {
        try {
            if (requestDTO.getTagName() == null || requestDTO.getTagName().trim().isEmpty()) {
                return Response.<TagResponseDTO>builder()
                        .code(ResponseCode.ILLEGAL_PARAMETER.getCode())
                        .info("标签名称不能为空")
                        .build();
            }

            TagEntity tagEntity = new TagEntity();
            BeanUtils.copyProperties(requestDTO, tagEntity);

            int result = tagService.createTag(tagEntity);

            if (result > 0) {
                TagResponseDTO responseDTO = new TagResponseDTO();
                BeanUtils.copyProperties(tagEntity, responseDTO);
                return Response.<TagResponseDTO>builder()
                        .code(ResponseCode.SUCCESS.getCode())
                        .info(ResponseCode.SUCCESS.getInfo())
                        .data(responseDTO)
                        .build();
            } else {
                return Response.<TagResponseDTO>builder()
                        .code(ResponseCode.UN_ERROR.getCode())
                        .info("创建标签失败")
                        .build();
            }
        } catch (Exception e) {
            log.error("创建标签失败: {}", e.getMessage(), e);
            return Response.<TagResponseDTO>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(e.getMessage())
                    .build();
        }
    }

    @PostMapping("/update")
    public Response<String> updateTag(@RequestBody TagUpdateRequestDTO requestDTO) {
        try {
            if (requestDTO.getId() == null) {
                return Response.<String>builder()
                        .code(ResponseCode.ILLEGAL_PARAMETER.getCode())
                        .info("标签ID不能为空")
                        .build();
            }

            TagEntity tagEntity = new TagEntity();
            BeanUtils.copyProperties(requestDTO, tagEntity);

            int result = tagService.updateTag(tagEntity);

            if (result > 0) {
                return Response.<String>builder()
                        .code(ResponseCode.SUCCESS.getCode())
                        .info("更新标签成功")
                        .build();
            } else {
                return Response.<String>builder()
                        .code(ResponseCode.UN_ERROR.getCode())
                        .info("更新标签失败")
                        .build();
            }
        } catch (Exception e) {
            log.error("更新标签失败: {}", e.getMessage(), e);
            return Response.<String>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(e.getMessage())
                    .build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public Response<String> deleteTag(@PathVariable Integer id) {
        try {
            if (id == null || id <= 0) {
                return Response.<String>builder()
                        .code(ResponseCode.ILLEGAL_PARAMETER.getCode())
                        .info("标签ID无效")
                        .build();
            }

            int result = tagService.deleteTag(id);

            if (result > 0) {
                return Response.<String>builder()
                        .code(ResponseCode.SUCCESS.getCode())
                        .info("删除标签成功")
                        .build();
            } else {
                return Response.<String>builder()
                        .code(ResponseCode.UN_ERROR.getCode())
                        .info("删除标签失败")
                        .build();
            }
        } catch (Exception e) {
            log.error("删除标签失败: {}", e.getMessage(), e);
            return Response.<String>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(e.getMessage())
                    .build();
        }
    }

    @GetMapping("/query/{id}")
    public Response<TagResponseDTO> queryTagById(@PathVariable Integer id) {
        try {
            if (id == null || id <= 0) {
                return Response.<TagResponseDTO>builder()
                        .code(ResponseCode.ILLEGAL_PARAMETER.getCode())
                        .info("标签ID无效")
                        .build();
            }

            TagEntity tagEntity = tagService.queryTagById(id);

            if (tagEntity != null) {
                TagResponseDTO responseDTO = new TagResponseDTO();
                BeanUtils.copyProperties(tagEntity, responseDTO);
                return Response.<TagResponseDTO>builder()
                        .code(ResponseCode.SUCCESS.getCode())
                        .info(ResponseCode.SUCCESS.getInfo())
                        .data(responseDTO)
                        .build();
            } else {
                return Response.<TagResponseDTO>builder()
                        .code(ResponseCode.UN_ERROR.getCode())
                        .info("标签不存在")
                        .build();
            }
        } catch (Exception e) {
            log.error("查询标签失败: {}", e.getMessage(), e);
            return Response.<TagResponseDTO>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(e.getMessage())
                    .build();
        }
    }

    @GetMapping("/list")
    public Response<List<TagResponseDTO>> queryAllTags() {
        try {
            List<TagEntity> tagEntities = tagService.queryActiveTagsOnly();
            List<TagResponseDTO> responseDTOs = new ArrayList<>();

            for (TagEntity tagEntity : tagEntities) {
                TagResponseDTO responseDTO = new TagResponseDTO();
                BeanUtils.copyProperties(tagEntity, responseDTO);
                responseDTOs.add(responseDTO);
            }

            return Response.<List<TagResponseDTO>>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(responseDTOs)
                    .build();
        } catch (Exception e) {
            log.error("查询标签列表失败: {}", e.getMessage(), e);
            return Response.<List<TagResponseDTO>>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(e.getMessage())
                    .build();
        }
    }

    @GetMapping("/queryByName")
    public Response<TagResponseDTO> queryTagByName(@RequestParam String tagName) {
        try {
            if (tagName == null || tagName.trim().isEmpty()) {
                return Response.<TagResponseDTO>builder()
                        .code(ResponseCode.ILLEGAL_PARAMETER.getCode())
                        .info("标签名称不能为空")
                        .build();
            }

            TagEntity tagEntity = tagService.queryTagByName(tagName);

            if (tagEntity != null) {
                TagResponseDTO responseDTO = new TagResponseDTO();
                BeanUtils.copyProperties(tagEntity, responseDTO);
                return Response.<TagResponseDTO>builder()
                        .code(ResponseCode.SUCCESS.getCode())
                        .info(ResponseCode.SUCCESS.getInfo())
                        .data(responseDTO)
                        .build();
            } else {
                return Response.<TagResponseDTO>builder()
                        .code(ResponseCode.UN_ERROR.getCode())
                        .info("标签不存在")
                        .build();
            }
        } catch (Exception e) {
            log.error("查询标签失败: {}", e.getMessage(), e);
            return Response.<TagResponseDTO>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(e.getMessage())
                    .build();
        }
    }
}
