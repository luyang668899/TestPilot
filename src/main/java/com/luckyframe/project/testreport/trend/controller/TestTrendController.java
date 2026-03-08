package com.luckyframe.project.testreport.trend.controller;

import com.luckyframe.common.utils.poi.ExcelUtil;
import com.luckyframe.framework.aspectj.lang.annotation.Log;
import com.luckyframe.framework.aspectj.lang.enums.BusinessType;
import com.luckyframe.framework.web.controller.BaseController;
import com.luckyframe.framework.web.domain.AjaxResult;
import com.luckyframe.framework.web.page.TableDataInfo;
import com.luckyframe.project.testreport.trend.domain.TestTrend;
import com.luckyframe.project.testreport.trend.service.ITestTrendService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试趋势分析控制器
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/testreport/trend")
public class TestTrendController extends BaseController
{
    private String prefix = "testreport/trend";

    @Autowired
    private ITestTrendService testTrendService;

    @RequiresPermissions("testreport:trend:view")
    @GetMapping()
    public String trend()
    {
        return prefix + "/trend";
    }

    /**
     * 查询测试趋势列表
     */
    @RequiresPermissions("testreport:trend:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TestTrend testTrend)
    {
        startPage();
        List<TestTrend> list = testTrendService.selectTestTrendList(testTrend);
        return getDataTable(list);
    }

    /**
     * 导出测试趋势列表
     */
    @RequiresPermissions("testreport:trend:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TestTrend testTrend)
    {
        List<TestTrend> list = testTrendService.selectTestTrendList(testTrend);
        ExcelUtil<TestTrend> util = new ExcelUtil<TestTrend>(TestTrend.class);
        return util.exportExcel(list, "测试趋势数据");
    }

    /**
     * 查看测试趋势详情
     */
    @RequiresPermissions("testreport:trend:detail")
    @GetMapping("/detail/{trendId}")
    public String detail(@PathVariable("trendId") Long trendId, ModelMap mmap)
    {
        TestTrend testTrend = testTrendService.selectTestTrendById(trendId);
        mmap.put("testTrend", testTrend);
        return prefix + "/detail";
    }

    /**
     * 生成趋势数据
     */
    @RequiresPermissions("testreport:trend:generate")
    @PostMapping("/generate")
    @ResponseBody
    public AjaxResult generateTrendData(@RequestParam("projectId") Long projectId)
    {
        testTrendService.generateTestTrendData(projectId, 1);
        return success("趋势数据生成成功");
    }

    /**
     * 预测趋势
     */
    @RequiresPermissions("testreport:trend:predict")
    @PostMapping("/predict")
    @ResponseBody
    public AjaxResult predictTrend(@RequestParam("projectId") Long projectId, @RequestParam("days") Integer days)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("projectId", projectId);
        params.put("days", days);
        Map<String, Object> prediction = testTrendService.predictTestTrend(params);
        return success().put("prediction", prediction);
    }

    /**
     * 删除测试趋势
     */
    @RequiresPermissions("testreport:trend:remove")
    @Log(title = "测试趋势", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(testTrendService.deleteTestTrendByIds(ids.split(",")));
    }
}