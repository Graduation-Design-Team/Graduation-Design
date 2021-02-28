package com.portal.controller;

import com.portal.pojo.Resume;
import com.portal.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @RequestMapping(value = "resume/insertByIdSelective",method = RequestMethod.POST)
    public Boolean insertByIdSelective(Resume resume){
        return resumeService.insertByIdSelective(resume);
    }

    @RequestMapping(value = "resume/insertByIdAll",method = RequestMethod.PUT)
    public Boolean insertByIdAll(Resume resume){
        return resumeService.insertByIdAll(resume);
    }

    @RequestMapping(value = "resume/deleteById",method = RequestMethod.DELETE)
    public Boolean deleteById(@PathVariable("resume_id") Long id){
        return resumeService.deleteById(id);
    }

    @RequestMapping(value = "resume/updateByIdSelective",method = RequestMethod.POST)
    public Boolean updateByIdSelective(Resume resume){
        return resumeService.updateByIdSelective(resume);
    }

    @RequestMapping(value = "resume/updateByIdAll",method = RequestMethod.PUT)
    public Boolean updateByIdAll(Resume resume){
        return resumeService.updateByIdAll(resume);
    }

    @RequestMapping(value = "resume/queryResumeSelective",method = RequestMethod.GET)
    public Resume queryResumeSelective(@PathVariable("resume_id") Long id){
        return resumeService.queryResumeSelective(id);
    }

    @RequestMapping(value = "resume/quertResumeAll",method = RequestMethod.GET)
    public List<Resume> quertResumeAll(){
        return resumeService.quertResumeAll();
    }

}
