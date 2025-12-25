package org.huawei.com;

import org.huawei.com.DTO.VulnTagModifyRequestDTO;
import org.huawei.com.DTO.UploadMarkdownDTO;
import org.huawei.com.DTO.VulnerabilityInfoRequestDTO;
import org.huawei.com.DTO.VulnerabilityInfoResponseDTO;
import org.huawei.com.types.model.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

public interface IVunerabilityInfo {
    Response<List<VulnerabilityInfoResponseDTO>> queryLatestInfo(int limit, int offset);

    Response<Map<String, Object>> queryVulnByName(@RequestBody VulnerabilityInfoRequestDTO requestDTO);

    Response<String> deleteVulnTag(VulnTagModifyRequestDTO vulnTagModifyRequestDTO);

    Response<String> insertVulnTag(VulnTagModifyRequestDTO vulnTagModifyRequestDTO);

    /**
     * 查询所有不重复的标签
     * 
     * @return 所有标签列表
     */
    Response<List<String>> queryAllDistinctTags();

    /**
     * 根据标签查询漏洞列表
     * 
     * @param tag    标签名称
     * @param offset 偏移量
     * @param limit  限制数量
     * @return 漏洞列表
     */
    Response<List<VulnerabilityInfoResponseDTO>> queryVulnsByTag(String tag, int offset, int limit);

    Response<VulnerabilityInfoResponseDTO> queryVulnByName(int id);

    @RequestMapping(value = "query", method = RequestMethod.POST)
    Response<List<VulnerabilityInfoResponseDTO>> queryVulnByTimeRange(
            @RequestBody VulnerabilityInfoRequestDTO requestDTO);

    @RequestMapping(value = "upload_markdown", method = RequestMethod.POST)
    Response<VulnerabilityInfoResponseDTO> uploadMarkdown(@RequestBody UploadMarkdownDTO dto);

    /**
     * 仅解析Markdown，不保存到数据库
     *
     * @param dto Markdown内容
     * @return 解析后的漏洞信息
     */
    @RequestMapping(value = "parse_markdown", method = RequestMethod.POST)
    Response<VulnerabilityInfoResponseDTO> parseMarkdownOnly(@RequestBody UploadMarkdownDTO dto);

    /**
     * 保存漏洞信息到数据库
     *
     * @param dto 漏洞信息
     * @return 保存结果
     */
    @RequestMapping(value = "save_vulnerability", method = RequestMethod.POST)
    Response<VulnerabilityInfoResponseDTO> saveVulnerability(@RequestBody VulnerabilityInfoResponseDTO dto);
}
