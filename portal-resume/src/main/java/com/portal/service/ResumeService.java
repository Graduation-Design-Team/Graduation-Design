package com.portal.service;

import com.portal.pojo.Resume;

import java.util.List;

public interface ResumeService {

    Boolean insertByIdSelective(Resume resume);

    Boolean insertByIdAll(Resume resume);

    Boolean deleteById(Long id);

    Boolean updateByIdSelective(Resume resume);

    Boolean updateByIdAll(Resume resume);

    Resume queryResumeSelective(Long id);

    List<Resume> quertResumeAll();
}
