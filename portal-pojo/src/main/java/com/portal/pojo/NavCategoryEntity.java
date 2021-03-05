package com.portal.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ApiModel("导航栏实体类")
@Entity
@Table(name = "nav_category")
public class NavCategoryEntity {

    @ApiModelProperty("分类id")
    @Id
    @Column(name = "nav_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long navId;  //分类id

    @ApiModelProperty("分类名字")
    @Column(name = "nav_name")
    private String navName; //分类名字

    @ApiModelProperty("父分类id")
    @Column(name = "nav_parent_id")
    private Integer navParentId; //

    @ApiModelProperty("是否父分类")
    @Column(name = "nav_is_parent")
    private Byte navIsParent; //是否父分类

    @ApiModelProperty("排序")
    @Column(name = "nav_sort")
    private Integer navSort;  //排序

    @ApiModelProperty("是否删除 0没删除 1删除")
    @Column(name = "nav_is_del")
    private Byte navIsDel;    //是否删除 0没删除 1删除


    @OneToMany(mappedBy = "navCategoryEntity", cascade = CascadeType.ALL)
    private Set<NavPictureEntity> pics = new HashSet<>();

    @OneToMany(mappedBy = "navCategoryEntity", cascade = CascadeType.ALL)
    private Set<NavVedioEntity> vedio = new HashSet<>();
    public Long getNavId() {
        return navId;
    }

    public void setNavId(Long navId) {
        this.navId = navId;
    }

    public String getNavName() {
        return navName;
    }

    public void setNavName(String navName) {
        this.navName = navName;
    }

    public Integer getNavParentId() {
        return navParentId;
    }

    public void setNavParentId(Integer navParentId) {
        this.navParentId = navParentId;
    }

    public Byte getNavIsParent() {
        return navIsParent;
    }

    public void setNavIsParent(Byte navIsParent) {
        this.navIsParent = navIsParent;
    }

    public Integer getNavSort() {
        return navSort;
    }

    public void setNavSort(Integer navSort) {
        this.navSort = navSort;
    }

    public Byte getNavIsDel() {
        return navIsDel;
    }

    public void setNavIsDel(Byte navIsDel) {
        this.navIsDel = navIsDel;
    }

    public Set<NavPictureEntity> getPics() {
        return pics;
    }

    public void setPics(Set<NavPictureEntity> pics) {
        this.pics = pics;
    }

    public Set<NavVedioEntity> getVedio() {
        return vedio;
    }

    public void setVedio(Set<NavVedioEntity> vedio) {
        this.vedio = vedio;
    }
}
