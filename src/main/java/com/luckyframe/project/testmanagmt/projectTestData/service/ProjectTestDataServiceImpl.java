package com.luckyframe.project.testmanagmt.projectTestData.service;

import com.luckyframe.common.utils.DateUtils;
import com.luckyframe.common.utils.security.ShiroUtils;
import com.luckyframe.common.utils.StringUtils;
import com.luckyframe.project.testmanagmt.projectTestData.domain.ProjectTestData;
import com.luckyframe.project.testmanagmt.projectTestData.mapper.ProjectTestDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 测试数据管理Service实现
 * 
 * @author luckyframe
 * @date 2026-03-08
 */
@Service
public class ProjectTestDataServiceImpl implements IProjectTestDataService
{
    @Autowired
    private ProjectTestDataMapper projectTestDataMapper;

    /**
     * 查询测试数据管理
     */
    @Override
    public ProjectTestData selectProjectTestDataById(Long dataId)
    {
        return projectTestDataMapper.selectProjectTestDataById(dataId);
    }

    /**
     * 查询测试数据管理列表
     */
    @Override
    public List<ProjectTestData> selectProjectTestDataList(ProjectTestData projectTestData)
    {
        return projectTestDataMapper.selectProjectTestDataList(projectTestData);
    }

    /**
     * 新增测试数据管理
     */
    @Override
    public int insertProjectTestData(ProjectTestData projectTestData)
    {
        projectTestData.setCreateBy(ShiroUtils.getLoginName());
        projectTestData.setCreateTime(DateUtils.getNowDate());
        projectTestData.setUpdateBy(ShiroUtils.getLoginName());
        projectTestData.setUpdateTime(DateUtils.getNowDate());
        return projectTestDataMapper.insertProjectTestData(projectTestData);
    }

    /**
     * 修改测试数据管理
     */
    @Override
    public int updateProjectTestData(ProjectTestData projectTestData)
    {
        projectTestData.setUpdateBy(ShiroUtils.getLoginName());
        projectTestData.setUpdateTime(DateUtils.getNowDate());
        return projectTestDataMapper.updateProjectTestData(projectTestData);
    }

    /**
     * 删除测试数据管理
     */
    @Override
    public int deleteProjectTestDataById(Long dataId)
    {
        return projectTestDataMapper.deleteProjectTestDataById(dataId);
    }

    /**
     * 批量删除测试数据管理
     */
    @Override
    public int deleteProjectTestDataByIds(String[] dataIds)
    {
        return projectTestDataMapper.deleteProjectTestDataByIds(dataIds);
    }

    /**
     * 根据项目ID和环境ID查询测试数据
     */
    @Override
    public List<ProjectTestData> selectProjectTestDataByProjectAndEnvironment(Long projectId, Long environmentId)
    {
        return projectTestDataMapper.selectProjectTestDataByProjectAndEnvironment(projectId, environmentId);
    }

    /**
     * 根据项目ID查询测试数据
     */
    @Override
    public List<ProjectTestData> selectProjectTestDataByProjectId(Long projectId)
    {
        return projectTestDataMapper.selectProjectTestDataByProjectId(projectId);
    }

    /**
     * 生成测试数据
     */
    @Override
    public List<ProjectTestData> generateTestData(Long projectId, Long environmentId, String dataType, int count)
    {
        List<ProjectTestData> testDataList = new ArrayList<>();
        Random random = new Random();
        String username = ShiroUtils.getLoginName();
        
        for (int i = 0; i < count; i++) {
            ProjectTestData testData = new ProjectTestData();
            testData.setProjectId(projectId);
            testData.setEnvironmentId(environmentId);
            testData.setDataType(dataType);
            testData.setCreateBy(username);
            testData.setCreateTime(DateUtils.getNowDate());
            testData.setUpdateBy(username);
            testData.setUpdateTime(DateUtils.getNowDate());
            
            // 根据数据类型生成不同的测试数据
            switch (dataType) {
                case "string":
                    testData.setDataName("STRING_DATA_" + i);
                    testData.setDataValue(generateRandomString(10));
                    testData.setDescription("随机字符串测试数据");
                    break;
                case "number":
                    testData.setDataName("NUMBER_DATA_" + i);
                    testData.setDataValue(String.valueOf(random.nextInt(10000)));
                    testData.setDescription("随机数字测试数据");
                    break;
                case "boolean":
                    testData.setDataName("BOOLEAN_DATA_" + i);
                    testData.setDataValue(String.valueOf(random.nextBoolean()));
                    testData.setDescription("随机布尔值测试数据");
                    break;
                case "email":
                    testData.setDataName("EMAIL_DATA_" + i);
                    testData.setDataValue(generateRandomEmail());
                    testData.setDescription("随机邮箱测试数据");
                    break;
                case "phone":
                    testData.setDataName("PHONE_DATA_" + i);
                    testData.setDataValue(generateRandomPhone());
                    testData.setDescription("随机手机号测试数据");
                    break;
                default:
                    testData.setDataName("TEST_DATA_" + i);
                    testData.setDataValue(generateRandomString(20));
                    testData.setDescription("默认测试数据");
                    break;
            }
            
            projectTestDataMapper.insertProjectTestData(testData);
            testDataList.add(testData);
        }
        
        return testDataList;
    }

    /**
     * 生成随机字符串
     */
    private String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    /**
     * 生成随机邮箱
     */
    private String generateRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "example.com"};
        String username = generateRandomString(8);
        String domain = domains[new Random().nextInt(domains.length)];
        return username + "@" + domain;
    }

    /**
     * 生成随机手机号
     */
    private String generateRandomPhone() {
        String[] prefixes = {"130", "131", "132", "133", "134", "135", "136", "137", "138", "139", "150", "151", "152", "153", "155", "156", "157", "158", "159", "170", "171", "172", "173", "175", "176", "177", "178", "180", "181", "182", "183", "184", "185", "186", "187", "188", "189"};
        String prefix = prefixes[new Random().nextInt(prefixes.length)];
        StringBuilder suffix = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            suffix.append(new Random().nextInt(10));
        }
        return prefix + suffix.toString();
    }
}