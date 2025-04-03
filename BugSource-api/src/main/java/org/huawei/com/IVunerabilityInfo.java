package org.huawei.com;

import org.huawei.com.DTO.VulnTagModifyRequestDTO;
import org.huawei.com.DTO.VulnerabilityInfoRequestDTO;
import org.huawei.com.DTO.VulnerabilityInfoResponseDTO;
import org.huawei.com.types.model.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IVunerabilityInfo {
    Response<List<VulnerabilityInfoResponseDTO>> queryLatestInfo(int limit, int offset);

    Response<Map<String,Object>> queryVulnByName(@RequestBody VulnerabilityInfoRequestDTO requestDTO);
    Response<String> deleteVulnTag(VulnTagModifyRequestDTO vulnTagModifyRequestDTO);

    Response<String> insertVulnTag(VulnTagModifyRequestDTO vulnTagModifyRequestDTO);

    Response<VulnerabilityInfoResponseDTO> queryVulnByName(int id);

    @RequestMapping(value = "query",method = RequestMethod.POST)
    Response<List<VulnerabilityInfoResponseDTO>> queryVulnByTimeRange(@RequestBody VulnerabilityInfoRequestDTO requestDTO);
}
