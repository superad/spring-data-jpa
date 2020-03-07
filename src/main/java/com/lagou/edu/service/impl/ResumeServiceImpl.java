package com.lagou.edu.service.impl;

import com.lagou.edu.dao.ResumeDao;
import com.lagou.edu.pojo.Resume;
import com.lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fgm
 * @description
 * @date 2020-03-05
 ***/
@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeDao resumeDao;

    @Override
    public List<Resume> findAll() {
        return resumeDao.findAll();
    }

    @Override
    public Long saveResume(Resume resume) {
        Resume result=  resumeDao.save(resume);
        return result.getId();
    }

    @Override
    public Boolean removeById(Long id) {
        resumeDao.deleteById(id);
        return Boolean.TRUE;
    }
}
