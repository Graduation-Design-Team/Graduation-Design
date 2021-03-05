package com.portal.service;

import com.portal.mapper.DocumentEntityMapper;
import com.portal.pojo.DocumentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

//保证事务的一致性
@Transactional
@Service
public class DocumentEntityService {

    @Autowired
    private DocumentEntityMapper documentEntityMapper;

    /**
     * 获取文档列表
     * @return
     */
    public List<DocumentEntity> getDocumentEntity() {
        Specification<DocumentEntity> spec = (Root<DocumentEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            Path<Object> isDel = root.get("isDel");
            Path<Object> isPass = root.get("isPass");
            // 0 没删除 1 删除
            Predicate p1 = criteriaBuilder.equal(isDel, 0);
            // 0 通过审核 1审核中
            Predicate p2 = criteriaBuilder.equal(isPass, 0);
            return criteriaBuilder.and(p1,p2);
        };
        List<DocumentEntity> list = null;
        try {
            list = documentEntityMapper.findAll(spec);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败");
        }
        return list;
    }

    /**
     * 添加文档
     * @param documentEntity
     */
    public void addDocumentEntity(DocumentEntity documentEntity) {
        try {
            documentEntity.setDocCreatedTime(documentEntity.newDateTime());
            documentEntityMapper.save(documentEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败");
        }
    }


    /**
     * 更新文档
     * @param documentEntity
     */
    public void updateDocumentEntity(DocumentEntity documentEntity) {
        try {
            documentEntity.setDocCreatedTime(documentEntity.newDateTime());
            int isDel = documentEntity.getIsDel();
            if(isDel != 0 && isDel != 1) {
                documentEntity.setIsDel((byte)0);
            }
            documentEntityMapper.save(documentEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("更新失败");
        }
    }


    /**
     * 删除文档
     * @param id
     */
    public void deleteDocumentEntity(Long id) {
        try {
            if(documentEntityMapper.existsById(id)) {
                documentEntityMapper.deleteDocumentEntityById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除失败");
        }
    }


    /**
     * 启用文档
     * @param id
     */
    public void openDocumentEntity(Long id) {
        try {
            if(documentEntityMapper.existsById(id)) {
                documentEntityMapper.openDocumentEntityById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("启用失败");
        }
    }


    /**
     * 审核不通过
     * @param id
     */
    public void notPassDocumentEntityById(Long id) {
        try {
            if(documentEntityMapper.existsById(id)) {
                documentEntityMapper.notPassDocumentEntityById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("审核不通过失败");
        }
    }

    /**
     * 审核通过
     * @param id
     */
    public void passDocumentEntityById(Long id) {
        try {
            if(documentEntityMapper.existsById(id)) {
                documentEntityMapper.passDocumentEntityById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("审核通过失败");
        }
    }
}
