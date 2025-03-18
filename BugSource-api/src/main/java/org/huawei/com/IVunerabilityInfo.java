package org.huawei.com;

import org.huawei.com.DTO.VulnTagModifyRequestDTO;
import org.huawei.com.DTO.VulnerabilityInfoRequestDTO;
import org.huawei.com.DTO.VulnerabilityInfoResponseDTO;
import org.huawei.com.types.model.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface IVunerabilityInfo {
    Response<List<VulnerabilityInfoResponseDTO>> queryLatestInfo(int limit, int offset);

    Response<List<VulnerabilityInfoResponseDTO>> queryVulnByName(VulnerabilityInfoRequestDTO requestDTO);
    Response<String> modifyVulnTag(VulnTagModifyRequestDTO vulnTagModifyRequestDTO);
    @RequestMapping(value = "query",method = RequestMethod.POST)
    Response<List<VulnerabilityInfoResponseDTO>> queryVulnByTimeRange(@RequestBody VulnerabilityInfoRequestDTO requestDTO);
}
