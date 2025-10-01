package org.huawei.com.trigger.http;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.huawei.com.DTO.VulnTagModifyRequestDTO;
import org.huawei.com.DTO.UploadMarkdownDTO;
import org.huawei.com.DTO.VulnerabilityInfoRequestDTO;
import org.huawei.com.DTO.VulnerabilityInfoResponseDTO;
import org.huawei.com.IVunerabilityInfo;
import org.huawei.com.domain.vulnerability.model.aggregate.VulnerabilityAggregate;
import org.huawei.com.domain.vulnerability.model.aggregate.VulnerabilityQueryResponse;
import org.huawei.com.domain.vulnerability.model.entity.VulnerabilityEntity;
import org.huawei.com.domain.vulnerability.service.IVunerabilityService;
import org.huawei.com.types.enums.ResponseCode;
import org.huawei.com.types.model.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/vuln")
public class VunerabilityController implements IVunerabilityInfo {

    @Resource
    private IVunerabilityService vunerabilityService;

    @Override
    @RequestMapping(value = "latest", method = RequestMethod.GET)
    public Response<List<VulnerabilityInfoResponseDTO>> queryLatestInfo(int limit, int offset) {
        if (limit < 0 || offset < 0) {
            return Response.<List<VulnerabilityInfoResponseDTO>>builder()
                    .code(ResponseCode.ILLEGAL_PARAMETER.getCode())
                    .info(ResponseCode.ILLEGAL_PARAMETER.getInfo())
                    .build();
        }
        try {
            List<VulnerabilityAggregate> vulnerabilityAggregates = vunerabilityService.queryLatestVuln(offset, limit);
            List<VulnerabilityInfoResponseDTO> res = new ArrayList<>();
            for (VulnerabilityAggregate vulnerabilityAggregate : vulnerabilityAggregates) {
                VulnerabilityInfoResponseDTO infoResponseDTO = new VulnerabilityInfoResponseDTO();
                BeanUtils.copyProperties(vulnerabilityAggregate.getVulnerabilityEntity(), infoResponseDTO);
                infoResponseDTO.setTag(vulnerabilityAggregate.getTags());
                res.add(infoResponseDTO);
            }
            return Response.<List<VulnerabilityInfoResponseDTO>>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(res)
                    .build();
        } catch (Exception e) {
            log.error("queryLatestInfo error:{}", e.getMessage(), e);
            return Response.<List<VulnerabilityInfoResponseDTO>>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @Override
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public Response<Map<String, Object>> queryVulnByName(@RequestBody VulnerabilityInfoRequestDTO requestDTO) {
        log.info("Received query request: {}", requestDTO);
        int limit = requestDTO.getLimit();
        int offset = requestDTO.getOffset();
        if (limit < 0 || offset < 0) {
            Response.<Map<String, Object>>builder()
                    .code(ResponseCode.ILLEGAL_PARAMETER.getCode())
                    .info(ResponseCode.ILLEGAL_PARAMETER.getInfo())
                    .build();
        }
        Date startDate = requestDTO.getStartDate();
        Date endDate = requestDTO.getEndDate();
        try {
            VulnerabilityEntity vulnReq = new VulnerabilityEntity();
            BeanUtils.copyProperties(requestDTO, vulnReq);
            log.info(vulnReq.getCnTitle());
            log.info("CNVD ID: {}", vulnReq.getCnvdId());
            log.info("CVE ID: {}", vulnReq.getCveId());

            VulnerabilityQueryResponse responseData = vunerabilityService.queryVulnByInfo(vulnReq, startDate, endDate,
                    limit, offset);
            List<VulnerabilityAggregate> vulnerabilityAggregates = responseData.getAggregateList();
            Map<String, Object> resData = new HashMap<>();
            List<VulnerabilityInfoResponseDTO> res = new ArrayList<>();
            for (VulnerabilityAggregate vulnerabilityAggregate : vulnerabilityAggregates) {
                VulnerabilityInfoResponseDTO infoResponseDTO = new VulnerabilityInfoResponseDTO();
                BeanUtils.copyProperties(vulnerabilityAggregate.getVulnerabilityEntity(), infoResponseDTO);
                infoResponseDTO.setTag(vulnerabilityAggregate.getTags());
                res.add(infoResponseDTO);
            }
            resData.put("data", res);
            resData.put("total", responseData.getTotal());
            log.info("Query result size: {}", vulnerabilityAggregates.size());
            return Response.<Map<String, Object>>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(resData)
                    .build();
        } catch (Exception e) {
            log.error("queryLatestInfo error:{}", e.getMessage(), e);
            Response.<Map<String, Object>>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
        return null;
    }

    @Override
    @RequestMapping(value = "queryId", method = RequestMethod.GET)
    public Response<VulnerabilityInfoResponseDTO> queryVulnByName(@RequestParam int id) {
        try {
            VulnerabilityAggregate vulnerabilityAggregate = vunerabilityService.queryVulnById(id);
            VulnerabilityInfoResponseDTO vulnerabilityInfoResponseDTO = new VulnerabilityInfoResponseDTO();
            VulnerabilityEntity vulnerabilityEntity = vulnerabilityAggregate.getVulnerabilityEntity();
            BeanUtils.copyProperties(vulnerabilityEntity, vulnerabilityInfoResponseDTO);
            vulnerabilityInfoResponseDTO.setTag(vulnerabilityAggregate.getTags());
            return Response.<VulnerabilityInfoResponseDTO>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(vulnerabilityInfoResponseDTO)
                    .build();
        } catch (Exception e) {
            log.error("queryLatestInfo error:{}", e.getMessage(), e);
            return Response.<VulnerabilityInfoResponseDTO>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @Override
    @RequestMapping(value = "queryDate", method = RequestMethod.POST)
    public Response<List<VulnerabilityInfoResponseDTO>> queryVulnByTimeRange(
            @RequestBody VulnerabilityInfoRequestDTO requestDTO) {
        Date startDate = requestDTO.getStartDate();
        Date endDate = requestDTO.getEndDate();

        try {
            VulnerabilityEntity vulnReq = new VulnerabilityEntity();
            BeanUtils.copyProperties(requestDTO, vulnReq);
            List<VulnerabilityAggregate> vulnerabilityAggregates = vunerabilityService.queryVulnByTimeRange(startDate,
                    endDate);
            List<VulnerabilityInfoResponseDTO> res = new ArrayList<>();
            for (VulnerabilityAggregate vulnerabilityAggregate : vulnerabilityAggregates) {
                VulnerabilityInfoResponseDTO infoResponseDTO = new VulnerabilityInfoResponseDTO();
                BeanUtils.copyProperties(vulnerabilityAggregate.getVulnerabilityEntity(), infoResponseDTO);
                res.add(infoResponseDTO);
            }
            return Response.<List<VulnerabilityInfoResponseDTO>>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(res)
                    .build();
        } catch (Exception e) {
            log.error("queryLatestInfo error:{}", e.getMessage(), e);
            return Response.<List<VulnerabilityInfoResponseDTO>>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @Override
    @RequestMapping(value = "/delete_tag", method = RequestMethod.POST)
    public Response<String> deleteVulnTag(@RequestBody VulnTagModifyRequestDTO vulnTagModifyRequestDTO) {
        try {
            vunerabilityService.deleteVulnTag(vulnTagModifyRequestDTO.getVulnId(), vulnTagModifyRequestDTO.getTag());
            log.info("删除标签 " + vulnTagModifyRequestDTO.getTag() + "成功");
            return Response.<String>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .build();
        } catch (Exception e) {
            log.error("queryLatestInfo error:{}", e.getMessage(), e);
            return Response.<String>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @Override
    @RequestMapping(value = "/add_tag", method = RequestMethod.POST)
    public Response<String> insertVulnTag(@RequestBody VulnTagModifyRequestDTO vulnTagModifyRequestDTO) {
        try {
            vunerabilityService.insertVulnTag(vulnTagModifyRequestDTO.getVulnId(), vulnTagModifyRequestDTO.getTag());
            log.info("添加标签 " + vulnTagModifyRequestDTO.getTag() + "成功");
            return Response.<String>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .build();
        } catch (Exception e) {
            log.error("queryLatestInfo error:{}", e.getMessage(), e);
            return Response.<String>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    /**
     * 每日数据统计
     * 
     * @param days
     * @return
     */
    @GetMapping("/daily_count")
    public Response<List<Map<String, Object>>> getDailyNewVuln(@RequestParam int days) {
        if (days <= 0) {
            return Response.<List<Map<String, Object>>>builder()
                    .code(ResponseCode.ILLEGAL_PARAMETER.getCode())
                    .info("参数days必须大于0")
                    .build();
        }
        try {
            List<Map<String, Object>> result = vunerabilityService.countDailyNewVuln(Math.min(days, 30));
            return Response.<List<Map<String, Object>>>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(result)
                    .build();
        } catch (Exception e) {
            log.error("getDailyNewVuln error:{}", e.getMessage(), e);
            return Response.<List<Map<String, Object>>>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    /**
     * 每月数据统计
     * 
     * @param months
     * @return
     */
    @GetMapping("/monthly_count")
    public Response<List<Map<String, Object>>> getMonthlyNewVuln(
            @RequestParam(required = false, defaultValue = "12") int months) {
        int limit = Math.min(months, 12);
        if (limit <= 0) {
            return Response.<List<Map<String, Object>>>builder()
                    .code(ResponseCode.ILLEGAL_PARAMETER.getCode())
                    .info("参数months必须大于0")
                    .build();
        }
        try {
            List<Map<String, Object>> result = vunerabilityService.countMonthlyNewVuln(limit);
            return Response.<List<Map<String, Object>>>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(result)
                    .build();
        } catch (Exception e) {
            log.error("getMonthlyNewVuln error:{}", e.getMessage(), e);
            return Response.<List<Map<String, Object>>>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    /**
     * 各类型漏洞数量统计
     */
    @GetMapping("/type_count")
    public Response<List<Map<String, Object>>> getTypeCount() {
        try {
            List<Map<String, Object>> result = vunerabilityService.countVulnByType();
            return Response.<List<Map<String, Object>>>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(result)
                    .build();
        } catch (Exception e) {
            log.error("getTypeCount error:{}", e.getMessage(), e);
            return Response.<List<Map<String, Object>>>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @Override
    @PostMapping("/upload_markdown")
    public Response<VulnerabilityInfoResponseDTO> uploadMarkdown(@RequestBody UploadMarkdownDTO dto) {
        try {
            String markdown = dto.getMarkdown();
            VulnerabilityEntity entity = vunerabilityService.parseMarkdown(markdown);
            int inserted = vunerabilityService.createVulnerability(entity);
            if (inserted > 0) {
                VulnerabilityInfoResponseDTO resp = new VulnerabilityInfoResponseDTO();
                BeanUtils.copyProperties(entity, resp);
                return Response.<VulnerabilityInfoResponseDTO>builder()
                        .code(ResponseCode.SUCCESS.getCode())
                        .info(ResponseCode.SUCCESS.getInfo())
                        .data(resp)
                        .build();
            }
            return Response.<VulnerabilityInfoResponseDTO>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info("Insert failed")
                    .build();
        } catch (Exception e) {
            log.error("uploadMarkdown error:{}", e.getMessage(), e);
            return Response.<VulnerabilityInfoResponseDTO>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    // 解析逻辑已迁移到 Spring AI 服务
}
