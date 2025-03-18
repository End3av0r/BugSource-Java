package org.huawei.com.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.huawei.com.DTO.VulnerabilityInfoRequestDTO;
import org.huawei.com.domain.vulnerability.model.aggregate.VulnerabilityAggregate;
import org.huawei.com.domain.vulnerability.model.entity.VulnerabilityEntity;
import org.huawei.com.domain.vulnerability.service.IVunerabilityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
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
        String startDateStr = "2024-01-30";
        String endDateStr = "2024-01-31";
        //req.setCnvdId("CNVD-2024-49053");
       // req.setCnTitle("SQL");
        // 将字符串日期转换为 Date 对象
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);
            List<VulnerabilityAggregate> vulnerabilityEntities = vunerabilityService.queryVulnByInfo(req,startDate,endDate,0,10);
            System.out.println("queryVulnByInfo:" + vulnerabilityEntities.size());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    public void test_queryDate() {
        VulnerabilityEntity req = new VulnerabilityEntity();
        // req.setCnvdId("CNVD-2024-49053");

        String startDateStr = "2024-01-30";
        String endDateStr = "2024-01-31";

        // 将字符串日期转换为 Date 对象
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            // 调用查询方法
            //List<VulnerabilityAggregate> vulnerabilityEntities = vunerabilityService.queryVulnByTimeRange(startDate, endDate);
            List<VulnerabilityAggregate> vulnerabilityEntities = vunerabilityService.queryVulnByInfo(req,startDate,endDate,0,10);
            // 输出查询结果
            System.out.println("queryVulnByInfo:" + vulnerabilityEntities.size());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
