package com.portal.service.lmp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.mapper.resumeDao;
import com.portal.pojo.Resume;
import com.portal.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServicelmp implements ResumeService {

    @Autowired
    private resumeDao resumedao;


    @Override
    public Boolean insertByIdSelective(Resume resume) {
        return resumedao.insertByIdSelective(resume);
    }

    @Override
    public Boolean insertByIdAll(Resume resume) {
        return resumedao.insertByIdAll(resume);
    }

    @Override
    public Boolean deleteById(Integer id) {
        return resumedao.deleteById(id);
    }

    @Override
    public Boolean updateByIdSelective(Resume resume) {
        return resumedao.updateByIdSelective(resume);
    }

    @Override
    public Boolean updateByIdAll(Resume resume) {
        return resumedao.updateByIdAll(resume);
    }

    @Override
    public Resume queryResumeSelective(Resume resume) {
        return resumedao.queryResumeSelective(resume);
    }

    @Override
    public List<Resume> queryResumeAll() {
        PageHelper.startPage(1,6);
        List<Resume> mylist = resumedao.queryResumeAll();
        PageInfo<Resume> pageInfo = new PageInfo<>(mylist);
        return pageInfo.getList();
    }
}
