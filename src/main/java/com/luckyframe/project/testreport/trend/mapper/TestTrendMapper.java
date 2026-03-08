package com.luckyframe.project.testreport.trend.mapper;

import com.luckyframe.project.testreport.trend.domain.TestTrend;

import java.util.List;
import java.util.Map;

/**
 * 测试趋势分析Mapper接口
 * 
 * @author luckyframe
 * @date 2026-03-08
 */
public interface TestTrendMapper 
{
    /**
     * 查询测试趋势分析
     * 
     * @param trendId 测试趋势分析ID
     * @return 测试趋势分析
     */
    public TestTrend selectTestTrendById(Long trendId);

    /**
     * 查询测试趋势分析列表
     * 
     * @param testTrend 测试趋势分析
     * @return 测试趋势分析集合
     */
    public List<TestTrend> selectTestTrendList(TestTrend testTrend);

    /**
     * 新增测试趋势分析
     * 
     * @param testTrend 测试趋势分析
     * @return 结果
     */
    public int insertTestTrend(TestTrend testTrend);

    /**
     * 修改测试趋势分析
     * 
     * @param testTrend 测试趋势分析
     * @return 结果
     */
    public int updateTestTrend(TestTrend testTrend);

    /**
     * 删除测试趋势分析
     * 
     * @param trendId 测试趋势分析ID
     * @return 结果
     */
    public int deleteTestTrendById(Long trendId);

    /**
     * 批量删除测试趋势分析
     * 
     * @param trendIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTestTrendByIds(String[] trendIds);

    /**
     * 查询项目测试趋势数据
     * 
     * @param params 查询参数
     * @return 测试趋势数据集合
     */
    public List<TestTrend> selectProjectTestTrend(Map<String, Object> params);

    /**
     * 查询测试趋势统计数据
     * 
     * @param params 查询参数
     * @return 统计数据
     */
    public Map<String, Object> selectTestTrendStatistics(Map<String, Object> params);
}