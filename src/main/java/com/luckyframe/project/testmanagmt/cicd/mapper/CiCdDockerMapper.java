package com.luckyframe.project.testmanagmt.cicd.mapper;

import com.luckyframe.project.testmanagmt.cicd.domain.CiCdDocker;
import java.util.List;

/**
 * Docker容器管理Mapper接口
 * 
 * @author luckyframe
 * @date 2024-09-05
 */
public interface CiCdDockerMapper 
{
    /**
     * 查询Docker容器管理
     * 
     * @param dockerId Docker容器管理ID
     * @return Docker容器管理
     */
    public CiCdDocker selectCiCdDockerById(Integer dockerId);

    /**
     * 查询Docker容器管理列表
     * 
     * @param ciCdDocker Docker容器管理
     * @return Docker容器管理集合
     */
    public List<CiCdDocker> selectCiCdDockerList(CiCdDocker ciCdDocker);

    /**
     * 新增Docker容器管理
     * 
     * @param ciCdDocker Docker容器管理
     * @return 结果
     */
    public int insertCiCdDocker(CiCdDocker ciCdDocker);

    /**
     * 修改Docker容器管理
     * 
     * @param ciCdDocker Docker容器管理
     * @return 结果
     */
    public int updateCiCdDocker(CiCdDocker ciCdDocker);

    /**
     * 删除Docker容器管理
     * 
     * @param dockerId Docker容器管理ID
     * @return 结果
     */
    public int deleteCiCdDockerById(Integer dockerId);

    /**
     * 批量删除Docker容器管理
     * 
     * @param dockerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCiCdDockerByIds(String[] dockerIds);
}
