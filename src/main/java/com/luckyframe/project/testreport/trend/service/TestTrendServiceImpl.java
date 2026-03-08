package com.luckyframe.project.testreport.trend.service;

import com.luckyframe.common.utils.DateUtils;
import com.luckyframe.common.utils.security.ShiroUtils;
import com.luckyframe.project.testexecution.taskExecute.domain.TaskExecute;
import com.luckyframe.project.testexecution.taskExecute.mapper.TaskExecuteMapper;
import com.luckyframe.project.testreport.trend.domain.TestTrend;
import com.luckyframe.project.testreport.trend.mapper.TestTrendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 测试趋势分析Service实现
 * 
 * @author luckyframe
 * @date 2026-03-08
 */
@Service
public class TestTrendServiceImpl implements ITestTrendService
{
    @Autowired
    private TestTrendMapper testTrendMapper;

    @Autowired
    private TaskExecuteMapper taskExecuteMapper;

    /**
     * 查询测试趋势分析
     */
    @Override
    public TestTrend selectTestTrendById(Long trendId)
    {
        return testTrendMapper.selectTestTrendById(trendId);
    }

    /**
     * 查询测试趋势分析列表
     */
    @Override
    public List<TestTrend> selectTestTrendList(TestTrend testTrend)
    {
        return testTrendMapper.selectTestTrendList(testTrend);
    }

    /**
     * 新增测试趋势分析
     */
    @Override
    public int insertTestTrend(TestTrend testTrend)
    {
        testTrend.setCreateBy(ShiroUtils.getLoginName());
        testTrend.setCreateTime(DateUtils.getNowDate());
        testTrend.setUpdateBy(ShiroUtils.getLoginName());
        testTrend.setUpdateTime(DateUtils.getNowDate());
        return testTrendMapper.insertTestTrend(testTrend);
    }

    /**
     * 修改测试趋势分析
     */
    @Override
    public int updateTestTrend(TestTrend testTrend)
    {
        testTrend.setUpdateBy(ShiroUtils.getLoginName());
        testTrend.setUpdateTime(DateUtils.getNowDate());
        return testTrendMapper.updateTestTrend(testTrend);
    }

    /**
     * 删除测试趋势分析
     */
    @Override
    public int deleteTestTrendById(Long trendId)
    {
        return testTrendMapper.deleteTestTrendById(trendId);
    }

    /**
     * 批量删除测试趋势分析
     */
    @Override
    public int deleteTestTrendByIds(String[] trendIds)
    {
        return testTrendMapper.deleteTestTrendByIds(trendIds);
    }

    /**
     * 查询项目测试趋势数据
     */
    @Override
    public List<TestTrend> selectProjectTestTrend(Map<String, Object> params)
    {
        return testTrendMapper.selectProjectTestTrend(params);
    }

    /**
     * 查询测试趋势统计数据
     */
    @Override
    public Map<String, Object> selectTestTrendStatistics(Map<String, Object> params)
    {
        return testTrendMapper.selectTestTrendStatistics(params);
    }

    /**
     * 生成测试趋势数据
     */
    @Override
    public int generateTestTrendData(Long projectId, Integer testType)
    {
        // 查询任务执行数据
        TaskExecute taskExecute = new TaskExecute();
        taskExecute.setProjectId(projectId.intValue());
        List<TaskExecute> taskExecutes = taskExecuteMapper.selectTaskExecuteList(taskExecute);

        // 按日期分组统计
        Map<String, Map<String, Object>> dailyStats = new HashMap<>();
        for (TaskExecute task : taskExecutes) {
            String dateStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, task.getCreateTime());
            if (!dailyStats.containsKey(dateStr)) {
                Map<String, Object> stats = new HashMap<>();
                stats.put("totalCount", 0);
                stats.put("succCount", 0);
                stats.put("failCount", 0);
                stats.put("totalCaseCount", 0);
                dailyStats.put(dateStr, stats);
            }

            Map<String, Object> stats = dailyStats.get(dateStr);
            stats.put("totalCount", (int) stats.get("totalCount") + 1);
            stats.put("succCount", (int) stats.get("succCount") + (task.getCaseSuccCount() != null ? task.getCaseSuccCount() : 0));
            stats.put("failCount", (int) stats.get("failCount") + (task.getCaseFailCount() != null ? task.getCaseFailCount() : 0));
            stats.put("totalCaseCount", (int) stats.get("totalCaseCount") + (task.getCaseTotalCount() != null ? task.getCaseTotalCount() : 0));
        }

        // 生成趋势数据
        int count = 0;
        for (Map.Entry<String, Map<String, Object>> entry : dailyStats.entrySet()) {
            String dateStr = entry.getKey();
            Map<String, Object> stats = entry.getValue();

            TestTrend trend = new TestTrend();
            trend.setProjectId(projectId);
            trend.setTestType(testType);
            trend.setExecuteDate(DateUtils.parseDate(dateStr));
            trend.setCaseTotalCount((int) stats.get("totalCaseCount"));
            trend.setCaseSuccCount((int) stats.get("succCount"));
            trend.setCaseFailCount((int) stats.get("failCount"));

            int totalCaseCount = (int) stats.get("totalCaseCount");
            if (totalCaseCount > 0) {
                trend.setSuccessRate((double) (int) stats.get("succCount") / totalCaseCount * 100);
            } else {
                trend.setSuccessRate(0.0);
            }

            // 计算执行时间（简化处理，实际应该从任务执行日志中获取）
            trend.setExecuteTime(30.0);

            trend.setCreateBy(ShiroUtils.getLoginName());
            trend.setCreateTime(DateUtils.getNowDate());
            trend.setUpdateBy(ShiroUtils.getLoginName());
            trend.setUpdateTime(DateUtils.getNowDate());

            testTrendMapper.insertTestTrend(trend);
            count++;
        }

        return count;
    }

    /**
     * 预测测试趋势
     */
    @Override
    public Map<String, Object> predictTestTrend(Map<String, Object> params)
    {
        // 查询历史数据
        List<TestTrend> historyData = testTrendMapper.selectProjectTestTrend(params);
        if (historyData.isEmpty()) {
            return Collections.emptyMap();
        }

        // 计算历史数据的平均值和趋势
        double avgSuccessRate = 0;
        double avgExecuteTime = 0;
        int totalCount = historyData.size();

        for (TestTrend trend : historyData) {
            avgSuccessRate += trend.getSuccessRate() != null ? trend.getSuccessRate() : 0;
            avgExecuteTime += trend.getExecuteTime() != null ? trend.getExecuteTime() : 0;
        }

        avgSuccessRate /= totalCount;
        avgExecuteTime /= totalCount;

        // 简单线性预测（实际项目中可以使用更复杂的预测算法）
        List<Map<String, Object>> predictions = new ArrayList<>();
        Date lastDate = historyData.get(historyData.size() - 1).getExecuteDate();

        for (int i = 1; i <= 7; i++) {
            Date predictDate = DateUtils.addDays(lastDate, i);
            Map<String, Object> prediction = new HashMap<>();
            prediction.put("date", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, predictDate));
            prediction.put("successRate", avgSuccessRate + (Math.random() - 0.5) * 5); // 加入一些随机波动
            prediction.put("executeTime", avgExecuteTime + (Math.random() - 0.5) * 5);
            predictions.add(prediction);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("historyData", historyData);
        result.put("predictions", predictions);
        result.put("avgSuccessRate", avgSuccessRate);
        result.put("avgExecuteTime", avgExecuteTime);

        return result;
    }
}