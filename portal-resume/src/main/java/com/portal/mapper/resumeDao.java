package com.portal.mapper;

import com.portal.pojo.Resume;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface resumeDao {

    Boolean insertByIdSelective(Resume resume);

    Boolean insertByIdAll(Resume resume);

    Boolean deleteById(Long id);

    Boolean updateByIdSelective(Resume resume);

    Boolean updateByIdAll(Resume resume);

    Resume queryResumeSelective(Long id);

    List<Resume> quertResumeAll();
}
