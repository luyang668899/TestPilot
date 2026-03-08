package com.luckyframe.project.testmanagmt.projectCaseParams.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyframe.common.constant.ProjectCaseParamsConstants;
import com.luckyframe.common.exception.BusinessException;
import com.luckyframe.common.support.Convert;
import com.luckyframe.common.utils.StringUtils;
import com.luckyframe.common.utils.security.PermissionUtils;
import com.luckyframe.common.utils.security.ShiroUtils;
import com.luckyframe.project.testmanagmt.projectCaseParams.domain.ProjectCaseParams;
import com.luckyframe.project.testmanagmt.projectCaseParams.mapper.ProjectCaseParamsMapper;

/**
 * 用例公共参数 服务层实现
 * 
 * @author luckyframe
 * @date 2019-03-21
 */
@Service
public class ProjectCaseParamsServiceImpl implements IProjectCaseParamsService 
{
	@Autowired
	private ProjectCaseParamsMapper projectCaseParamsMapper;

	/**
     * 查询用例公共参数信息
     * 
     * @param paramsId 用例公共参数ID
     * @return 用例公共参数信息
     */
    @Override
	public ProjectCaseParams selectProjectCaseParamsById(Integer paramsId)
	{
	    return projectCaseParamsMapper.selectProjectCaseParamsById(paramsId);
	}
	
	/**
     * 查询用例公共参数列表
     * 
     * @param projectCaseParams 用例公共参数信息
     * @return 用例公共参数集合
     */
	@Override
	public List<ProjectCaseParams> selectProjectCaseParamsList(ProjectCaseParams projectCaseParams)
	{
	    return projectCaseParamsMapper.selectProjectCaseParamsList(projectCaseParams);
	}
	
	/**
	 * 根据项目ID查询所有项目中以及当前项目下的公共参数
	 * @param projectId 项目ID
	 * @author Seagull
	 * @date 2019年6月18日
	 */
	@Override
	public List<ProjectCaseParams> selectProjectCaseParamsListByProjectId(Integer projectId)
	{
	    return projectCaseParamsMapper.selectProjectCaseParamsListByProjectId(projectId);
	}

	/**
	 * 根据项目ID和环境查询所有项目中以及当前项目下的公共参数
	 * @param projectId 项目ID
	 * @param environmentId 环境ID
	 * @author jerelli
	 * @date 2020年6月18日
	 */
	@Override
	public List<ProjectCaseParams> selectProjectCaseParamsListByProjectIdAndEnvironmentId(Integer projectId, Integer environmentId)
	{
		return projectCaseParamsMapper.selectProjectCaseParamsListByProjectIdAndEnvironmentId(projectId, environmentId);
	}

	/**
	 * 根据项目ID和环境名称查询所有项目中以及当前项目下的公共参数
	 * @param projectId 项目ID
	 * @param envName 环境名称
	 * @author jerelli
	 * @date 2020年6月18日
	 */
	@Override
	public List<ProjectCaseParams> selectProjectCaseParamsListByProjectIdAndEnvName(Integer projectId, String envName)
	{
		// 这里需要通过环境名称查询环境ID，然后调用现有的方法
		// 暂时返回空列表，后续需要实现环境名称到环境ID的转换
		return projectCaseParamsMapper.selectProjectCaseParamsListByProjectId(projectId);
	}

	/**
	 * 根据项目ID查询该项目下所有的环境
	 * @param projectId 项目ID
	 * @return 环境列表
	 * @author jerelli
	 * @date 2020年6月18日
	 */
	@Override
	public List<String> selectProjectEnvListByProjectId(Integer projectId) {
		// 这里需要查询环境管理表获取环境列表
		// 暂时返回空列表，后续需要实现从环境管理表获取数据
		return java.util.Collections.emptyList();
	}

	/**
     * 新增用例公共参数
     * 
     * @param projectCaseParams 用例公共参数信息
     * @return 结果
     */
	@Override
	public int insertProjectCaseParams(ProjectCaseParams projectCaseParams)
	{
		projectCaseParams.setCreateBy(ShiroUtils.getLoginName());
		projectCaseParams.setCreateTime(new Date());
		projectCaseParams.setUpdateBy(ShiroUtils.getLoginName());
		projectCaseParams.setUpdateTime(new Date());
		
	    return projectCaseParamsMapper.insertProjectCaseParams(projectCaseParams);
	}
	
	/**
     * 修改用例公共参数
     * 
     * @param projectCaseParams 用例公共参数信息
     * @return 结果
     */
	@Override
	public int updateProjectCaseParams(ProjectCaseParams projectCaseParams)
	{
		projectCaseParams.setUpdateBy(ShiroUtils.getLoginName());
		projectCaseParams.setUpdateTime(new Date());
		
	    return projectCaseParamsMapper.updateProjectCaseParams(projectCaseParams);
	}

	/**
     * 删除用例公共参数对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteProjectCaseParamsByIds(String ids)
	{
		String[] paramsIds=Convert.toStrArray(ids);

		for(String paramsIdstr:paramsIds){
			Integer paramsId=Integer.valueOf(paramsIdstr);
			ProjectCaseParams projectCaseParams  = projectCaseParamsMapper.selectProjectCaseParamsById(paramsId);
			
			if(!PermissionUtils.isProjectPermsPassByProjectId(projectCaseParams.getProjectId())){
			  throw new BusinessException(String.format("用例参数【%1$s】没有项目删除权限", projectCaseParams.getParamsName()));
			}
		}

		
		return projectCaseParamsMapper.deleteProjectCaseParamsByIds(paramsIds);
	}
	
    /**
     * 校验用例参数名称是否唯一
     */
    @Override
    public String checkProjectCaseParamsNameUnique(ProjectCaseParams projectCaseParams)
    {
        long paramsId = StringUtils.isNull(projectCaseParams.getParamsId()) ? -1L : projectCaseParams.getParamsId();
        ProjectCaseParams info = projectCaseParamsMapper.checkProjectCaseParamsNameUnique(projectCaseParams);
        if (StringUtils.isNotNull(info) && info.getParamsId().longValue() != paramsId)
        {
            return ProjectCaseParamsConstants.PROJECTCASEPARAMS_NAME_NOT_UNIQUE;
        }
        return ProjectCaseParamsConstants.PROJECTCASEPARAMS_NAME_UNIQUE;
    }
}
