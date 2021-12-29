package com.atguigu.service_acl.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.atguigu.service_acl.entity.Permission;
import com.atguigu.service_acl.entity.RolePermission;
import com.atguigu.service_acl.mapper.PermissionMapper;
import com.atguigu.service_acl.service.PermissionService;
import com.atguigu.service_acl.service.RolePermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService{



    @Autowired
    RolePermissionService rolePermissionService;


    @Override
    public List<Permission> queryAllMenu() {
        return null;
    }

    @Override
    public List<Permission> selectAllMenu(String roleId) {
        return null;
    }

    @Override
    public void saveRolePermissionRealtionShip(String roleId, String[] permissionId) {

    }

    @Override
    public List<String> selectPermissionValueByUserId(String id) {
        return null;
    }

    @Override
    public List<JSONObject> selectPermissionByUserId(String id) {
        return null;
    }


/*
获得所有菜单
 */
    @Override
    public List<Permission> queryAllMenuGuli() {
        List<Permission> finalPermissionList=new ArrayList<>();
        //获得所有的菜单列表
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");
        List<Permission> permissionList = this.list(wrapper);
        for (Permission permission : permissionList) {
//            所有pid为0的菜单都是一级菜单
            if("0".equals(permission.getPid())){
                finalPermissionList.add(permission);
                permission.setLevel(1);
//                每个一级菜单都有子菜单
                addPermission(permission,permissionList);
            }
        }
        System.out.println(finalPermissionList);
        return finalPermissionList;
    }
    private void addPermission(Permission permission, List<Permission> permissionList) {
        for (Permission pm : permissionList) {
            if(pm.getPid().equals(permission.getId())){
//                子菜单的level+1
               pm.setLevel(permission.getLevel()+1);
                //初始化,不然会有空指针异常
                if(permission.getChildren() == null) {
                    permission.setChildren(new ArrayList<Permission>());
                }
               permission.getChildren().add(pm);
                addPermission(pm,permissionList);
            }
        }


    }

    /*
    按id 递归删除一级菜单,二级菜单,三级菜单
     */
    @Override
    public void removeChildById(String id) {
        List<String> finalIdList = new ArrayList<>();
        finalIdList.add(id);
        getChildIdList(id,finalIdList);
        baseMapper.deleteBatchIds(finalIdList);


    }


    public void getChildIdList(String id,List<String> finalIdList) {
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("pid",id);
        wrapper.select("id");
        List<Permission> idList = this.list(wrapper);
        idList.stream().forEach(item->{
            finalIdList.add(item.getId());
            getChildIdList(item.getId(), finalIdList);

        });


    }


    @Override
    public void saveRolePermissionRealtionShipGuli(String roleId, String[] permissionId) {
        List<RolePermission> rolePermissions = new ArrayList<>();
        for (String perId : permissionId) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(perId);
            rolePermission.setRoleId(roleId);
            rolePermissions.add(rolePermission);
//            rolePermissionService.save(rolePermission);
        }
        rolePermissionService.saveBatch(rolePermissions);



    }
}
