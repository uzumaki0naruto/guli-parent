package com.atguigu.aclservice.helper;

import com.atguigu.aclservice.entity.Permission;
import com.atguigu.commontuils.R;

import java.util.ArrayList;
import java.util.List;

public class p2 {

    public R build(List<Permission> permissionList){

        ArrayList<Permission> List = new ArrayList<>();

        for (Permission permission : permissionList) {
            if(permission.getPid().equals("0")){
                permission.setLevel(1);
               List.add(findChildren(permission,permissionList));
            }

        }

        return R.ok();
    }

    private Permission findChildren(Permission permission,List<Permission> permissionList) {

        ArrayList<Permission> list = new ArrayList<>();

        permission.setChildren(list);

        for (Permission permission1 : permissionList) {
            if(permission1.getPid().equals(permission.getPid())){
                permission1.setLevel(permission.getLevel()+1);


            permission.getChildren().add(findChildren(permission1,permissionList));



            }
        }
        return permission;




    }


}
