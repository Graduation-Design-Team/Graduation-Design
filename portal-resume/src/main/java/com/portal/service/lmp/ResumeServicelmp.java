package com.portal.service.lmp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.mapper.resumeDao;
import com.portal.pojo.Resume;
import com.portal.pojo.User;
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
    public Boolean deleteById(Long id) {
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
    public Resume queryResumeSelective(Long id) {
        return resumedao.queryResumeSelective(id);
    }

    @Override
    public List<Resume> quertResumeAll() {
        PageHelper.startPage(1,6);
        List<Resume> mylist = resumedao.quertResumeAll();
        PageInfo<Resume> pageInfo = new PageInfo<>(mylist);
        return pageInfo.getList();
    }
}
