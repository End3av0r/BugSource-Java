package org.huawei.com.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.huawei.com.DTO.VulnerabilityInfoRequestDTO;
import org.huawei.com.domain.vulnerability.model.entity.VulnerabilityEntity;
import org.huawei.com.domain.vulnerability.service.IVunerabilityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Resource
    IVunerabilityService vunerabilityService;
    @Test
    public void test_latest() {
        List<VulnerabilityEntity> vulnerabilityEntities = vunerabilityService.queryLatestVuln(0, 10);
        log.info("queryLatestInfo:{}", JSON.toJSONString(vulnerabilityEntities));
    }

    @Test
    public void test_query() {
        VulnerabilityEntity req = new VulnerabilityEntity();
        // req.setCnvdId("CNVD-2024-49053");
        req.setCnTitle("SQL");
        List<VulnerabilityEntity> vulnerabilityEntities = vunerabilityService.queryVulnByInfo(req,0,10);
        System.out.println("queryVulnByInfo:" + vulnerabilityEntities.size());
    }

}
