package org.huawei.com;

import org.huawei.com.DTO.VulnerabilityInfoRequestDTO;
import org.huawei.com.DTO.VulnerabilityInfoResponseDTO;
import org.huawei.com.types.model.Response;

import java.util.List;

public interface IVunerabilityInfo {
    Response<List<VulnerabilityInfoResponseDTO>> queryLatestInfo(int limit, int offset);

    Response<List<VulnerabilityInfoResponseDTO>> queryVulnByName(VulnerabilityInfoRequestDTO requestDTO);
}
