package com.luckyframe.project.testmanagmt.cicd.mapper;

import com.luckyframe.project.testmanagmt.cicd.domain.CiCdKubernetes;
import java.util.List;

/**
 * Kubernetes集群管理Mapper接口
 * 
 * @author luckyframe
 * @date 2024-09-05
 */
public interface CiCdKubernetesMapper 
{
    /**
     * 查询Kubernetes集群管理
     * 
     * @param kubernetesId Kubernetes集群管理ID
     * @return Kubernetes集群管理
     */
    public CiCdKubernetes selectCiCdKubernetesById(Integer kubernetesId);

    /**
     * 查询Kubernetes集群管理列表
     * 
     * @param ciCdKubernetes Kubernetes集群管理
     * @return Kubernetes集群管理集合
     */
    public List<CiCdKubernetes> selectCiCdKubernetesList(CiCdKubernetes ciCdKubernetes);

    /**
     * 新增Kubernetes集群管理
     * 
     * @param ciCdKubernetes Kubernetes集群管理
     * @return 结果
     */
    public int insertCiCdKubernetes(CiCdKubernetes ciCdKubernetes);

    /**
     * 修改Kubernetes集群管理
     * 
     * @param ciCdKubernetes Kubernetes集群管理
     * @return 结果
     */
    public int updateCiCdKubernetes(CiCdKubernetes ciCdKubernetes);

    /**
     * 删除Kubernetes集群管理
     * 
     * @param kubernetesId Kubernetes集群管理ID
     * @return 结果
     */
    public int deleteCiCdKubernetesById(Integer kubernetesId);

    /**
     * 批量删除Kubernetes集群管理
     * 
     * @param kubernetesIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCiCdKubernetesByIds(String[] kubernetesIds);
}
