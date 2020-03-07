package com.lagou.edu.controller;

import com.lagou.edu.pojo.Resume;
import com.lagou.edu.service.ResumeService;
import com.lagou.edu.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fgm
 * @description  测试Controller
 * @date 2020-03-04
 ***/
@Controller
@RequestMapping("/resume")
public class DemoController {

    @Autowired
    private ResumeService resumeService;

    @GetMapping("/list")
    @ResponseBody
    public Result<List<Resume>> list(){
       return Result.success(resumeService.findAll());
    }

    @PostMapping("/save")
    @ResponseBody
    public Result<Long> save(@RequestBody Resume resume){
        return  Result.success(resumeService.saveResume(resume));
    }
    @PostMapping("/remove/{id}")
    @ResponseBody
    public Result<Boolean> remove(@PathVariable(name = "id")Long id){
        return Result.success(resumeService.removeById(id));
    }




}
