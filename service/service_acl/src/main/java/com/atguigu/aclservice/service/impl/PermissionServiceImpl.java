package com.atguigu.aclservice.service.impl;

import com.atguigu.aclservice.entity.Permission;
import com.atguigu.aclservice.entity.RolePermission;
import com.atguigu.aclservice.entity.User;
import com.atguigu.aclservice.mapper.PermissionMapper;
import com.atguigu.aclservice.service.PermissionService;
import com.atguigu.aclservice.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
@Slf4j
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {


    @Autowired
    PermissionService permissionService;

    @Autowired
    UserService userService;



    @Override
    public List<String> selectPermissionValueByUserId(String id) {
        log.info("根据userid获取权限");
        List<String> selectPermissionValueList = null;
        if(this.isSysAdmin(id)) {
            //如果是超级管理员，获取所有权限
            selectPermissionValueList = baseMapper.selectAllPermissionValue();
        } else {
            selectPermissionValueList = baseMapper.selectPermissionValueByUserId(id);
        }
        return selectPermissionValueList;
    }

    private boolean isSysAdmin(String userId) {
        User user = userService.getById(userId);
        if(null != user && "admin".equals(user.getUsername())) {
            log.info("是超级管理员");
            return true;
        }
        return false;
    }


    @Override
    public List<String> selectPermissionListByUserId(String id) {
        return permissionService.selectPermissionValueByUserId(id);
    }
    //递归查询所有菜单
    public List<Permission> queryAllMenuGuli2() {
        QueryWrapper<Permission> permissionQueryWrapper = new QueryWrapper<>();
        permissionQueryWrapper.orderByDesc("id");
        List<Permission> result = new ArrayList<Permission>();
        List<Permission> list = permissionService.list(permissionQueryWrapper);
        for (Permission permission : list) {
//            如果是一级菜单
            if(permission.getId().equals("0")){
                result.add(permission);
                permission.setChildren(findChildrenPermission(permission,list));
            }
        }
        return result;
    }

     public List<Permission>  findChildrenPermission(Permission permission,List<Permission> PermissionList){
        ArrayList<Permission> list = new ArrayList<>();
        for (Permission permission1 : PermissionList) {
            if(permission1.getPid().equals(permission.getId())){
                list.add(permission1);
                permission1.setChildren(findChildrenPermission(permission1,PermissionList));
            }
        }
        return list;
    }


    public List<Permission> queryAllMenuGuli() {
        ArrayList<Permission> result = new ArrayList<>();
        QueryWrapper<Permission> permissionQueryWrapper = new QueryWrapper<>();
        permissionQueryWrapper.orderByDesc("id");
        List<Permission> list = permissionService.list(permissionQueryWrapper);
        for (Permission permission : list) {
            if(permission.getPid().equals(0)){
                permission.setLevel(1);
                result.add(selectChildren(permission,list));
            }
        }
        return result;
    }

    private static Permission selectChildren(Permission permissionNode, List<Permission> permissionList) {
        ArrayList<Permission> children = new ArrayList<>();
        for (Permission permission : permissionList) {
            if(permission.getPid().equals(permissionNode.getId())){
                permission.setLevel(permissionNode.getLevel()+1);
                permissionNode.getChildren().add(selectChildren(permission,permissionList));
            }
        }
        return permissionNode;
    }
    @Override
//    递归删除所有子节点
    public void removeChildById(String id) {
        Permission permission = permissionService.getById(id);
        List<Permission> permissionList = permissionService.list();
        ArrayList<String> list = new ArrayList<>();
        selectChildListById(permission.getId(),list);
        list.add(id);
        baseMapper.deleteBatchIds(list);
    }

//   树形结构下  递归获取所有子节点的id数
    private void selectChildListById(String pid, List<String> permissionIdList) {
        QueryWrapper<Permission> permissionQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Permission> wrapper = permissionQueryWrapper.eq("id", pid);
        List<Permission> list = permissionService.list(wrapper);
        for (Permission permission : list) {
            if(permission.getId().equals("pid")){
                permissionIdList.add(permission.getId());
                selectChildListById(permission.getPid(),permissionIdList);
            }
        }
    }






}
