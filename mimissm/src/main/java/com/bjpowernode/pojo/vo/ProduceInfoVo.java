package com.bjpowernode.pojo.vo;

/**
 * 查询条件的封装对象
 */
public class ProduceInfoVo {
//    商品名称
    private String pname;
//    商品类型
    private Integer typeId;
//    最低价格
    private Integer lprice;
//    最高价格
    private Integer hpirce;
//    设置页码
    private Integer page=1;

    public ProduceInfoVo() {
    }

    public ProduceInfoVo(String pname, Integer typeId, Integer lprice, Integer hpirce, Integer page) {
        this.pname = pname;
        this.typeId = typeId;
        this.lprice = lprice;
        this.hpirce = hpirce;
        this.page = page;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getLprice() {
        return lprice;
    }

    public void setLprice(Integer lprice) {
        this.lprice = lprice;
    }

    public Integer getHpirce() {
        return hpirce;
    }

    public void setHpirce(Integer hpirce) {
        this.hpirce = hpirce;
    }

    @Override
    public String toString() {
        return "ProduceInfoVo{" +
                "pname='" + pname + '\'' +
                ", typeId=" + typeId +
                ", lprice=" + lprice +
                ", hpirce=" + hpirce +
                ", page=" + page +
                '}';
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
