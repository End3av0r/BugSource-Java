package org.huawei.com.trigger.http;

import lombok.extern.slf4j.Slf4j;
import org.huawei.com.DTO.VulnTagModifyRequestDTO;
import org.huawei.com.DTO.VulnerabilityInfoRequestDTO;
import org.huawei.com.DTO.VulnerabilityInfoResponseDTO;
import org.huawei.com.IVunerabilityInfo;
import org.huawei.com.domain.vulnerability.model.aggregate.VulnerabilityAggregate;
import org.huawei.com.domain.vulnerability.model.entity.VulnerabilityEntity;
import org.huawei.com.domain.vulnerability.service.IVunerabilityService;
import org.huawei.com.types.enums.ResponseCode;
import org.huawei.com.types.model.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
@RestController()
@RequestMapping("/api/vuln")
public class VunerabilityController implements IVunerabilityInfo {

    @Resource
    private IVunerabilityService vunerabilityService;

    @Override
    @RequestMapping(value = "latest",method = RequestMethod.GET)
    public Response<List<VulnerabilityInfoResponseDTO>> queryLatestInfo(int limit, int offset) {
        if(limit < 0 || offset < 0){
            return Response.<List<VulnerabilityInfoResponseDTO>>builder()
                    .code(ResponseCode.ILLEGAL_PARAMETER.getCode())
                    .info(ResponseCode.ILLEGAL_PARAMETER.getInfo())
                    .build();
        }
        try {
            List<VulnerabilityEntity> vulnerabilityEntities = vunerabilityService.queryLatestVuln(limit, offset);
            List<VulnerabilityInfoResponseDTO> res = new ArrayList<>();
            for (VulnerabilityEntity vulnerabilityEntity : vulnerabilityEntities) {
                VulnerabilityInfoResponseDTO infoResponseDTO = new VulnerabilityInfoResponseDTO();
                BeanUtils.copyProperties(vulnerabilityEntity, infoResponseDTO);
                res.add(infoResponseDTO);
            }
            return Response.<List<VulnerabilityInfoResponseDTO>>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(res)
                    .build();
        }catch (Exception e){
            log.error("queryLatestInfo error:{}",e.getMessage(),e);
            return Response.<List<VulnerabilityInfoResponseDTO>>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @Override
    @RequestMapping(value = "query",method = RequestMethod.POST)
    public Response<List<VulnerabilityInfoResponseDTO>> queryVulnByName(@RequestBody VulnerabilityInfoRequestDTO requestDTO) {
        int limit = requestDTO.getLimit();
        int offset = requestDTO.getOffset();
        if(limit < 0 || offset < 0){
            return Response.<List<VulnerabilityInfoResponseDTO>>builder()
                    .code(ResponseCode.ILLEGAL_PARAMETER.getCode())
                    .info(ResponseCode.ILLEGAL_PARAMETER.getInfo())
                    .build();
        }
        Date startDate = requestDTO.getStartDate();
        Date endDate = requestDTO.getEndDate();
        try {
            VulnerabilityEntity vulnReq = new VulnerabilityEntity();
            BeanUtils.copyProperties(requestDTO, vulnReq);
            List<VulnerabilityAggregate> vulnerabilityAggregates = vunerabilityService.queryVulnByInfo(vulnReq, startDate,endDate,limit, offset);
            List<VulnerabilityInfoResponseDTO> res = new ArrayList<>();
            for (VulnerabilityAggregate vulnerabilityAggregate : vulnerabilityAggregates) {
                VulnerabilityInfoResponseDTO infoResponseDTO = new VulnerabilityInfoResponseDTO();
                BeanUtils.copyProperties(vulnerabilityAggregate, infoResponseDTO);
                res.add(infoResponseDTO);
            }
            return Response.<List<VulnerabilityInfoResponseDTO>>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(res)
                    .build();
        }catch (Exception e){
            log.error("queryLatestInfo error:{}",e.getMessage(),e);
            return Response.<List<VulnerabilityInfoResponseDTO>>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @Override
    @RequestMapping(value = "queryDate",method = RequestMethod.POST)
    public Response<List<VulnerabilityInfoResponseDTO>> queryVulnByTimeRange(@RequestBody VulnerabilityInfoRequestDTO requestDTO) {
        Date startDate = requestDTO.getStartDate();
        Date endDate = requestDTO.getEndDate();

        try {
            VulnerabilityEntity vulnReq = new VulnerabilityEntity();
            BeanUtils.copyProperties(requestDTO, vulnReq);
            List<VulnerabilityAggregate> vulnerabilityAggregates = vunerabilityService.queryVulnByTimeRange(startDate,endDate);
            List<VulnerabilityInfoResponseDTO> res = new ArrayList<>();
            for (VulnerabilityAggregate vulnerabilityAggregate : vulnerabilityAggregates) {
                VulnerabilityInfoResponseDTO infoResponseDTO = new VulnerabilityInfoResponseDTO();
                BeanUtils.copyProperties(vulnerabilityAggregate, infoResponseDTO);
                res.add(infoResponseDTO);
            }
            return Response.<List<VulnerabilityInfoResponseDTO>>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(res)
                    .build();
        }catch (Exception e){
            log.error("queryLatestInfo error:{}",e.getMessage(),e);
            return Response.<List<VulnerabilityInfoResponseDTO>>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @Override
    @RequestMapping(value = "/modify_tag", method = RequestMethod.POST)
    public Response<String> modifyVulnTag(@RequestBody VulnTagModifyRequestDTO vulnTagModifyRequestDTO) {
        try {
            vunerabilityService.modifyVulnTag(vulnTagModifyRequestDTO.getVulnId(), vulnTagModifyRequestDTO.getTag());
            return Response.<String>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .build();
        } catch (Exception e) {
            log.error("queryLatestInfo error:{}",e.getMessage(),e);
            return Response.<String>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }
}
