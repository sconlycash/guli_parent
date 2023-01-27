package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.poi.ss.formula.functions.Rows;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-01-26
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    //查询讲师表所有数据
    @Resource
    private EduTeacherService teacherService;
    @GetMapping("findAll")
    public R findAllTeacher(){
        //调用service方法实现查询所有的操作
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }
    @DeleteMapping("{id}")
    public R removeTeacher(@PathVariable String id){
        boolean flag = teacherService.removeById(id);
        if(flag){
            return R.ok();
        }
        else {
            return R.error();
        }

    }
    //分页查询方法
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit){
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        //调用方法实现分页
        teacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//每页数据的list集合
//        Map map  = new HashMap();
//        map.put("total",total);
//        map.put("rows",records);
//        return R.ok().data(map);
        return R.ok().data("total",total).data("rows",records);
    }

}

