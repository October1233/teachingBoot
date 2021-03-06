package com.shiyue.springboot.domain;

public class CertificatePrivacy implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -5927579510864228268L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** 主键Id */
    private Long id;

    /** 用户id */
    private Integer userId;

    /** 车辆vin码 */
    private Integer carVinEnum;

    /** 车牌号码 */
    private Integer carNumEnum;

    /** 车辆品牌 */
    private Integer carBrandEnum;

    /** 车辆型号 */
    private Integer carModelEnum;

    /** 发动机号 */
    private Integer engineNumEnum;

    /** 生产日期 */
    private Integer productDateEnum;

    /** 权属类型 */
    private Integer ownershipTypeEnum;

    /** 权属主体 */
    private Integer ownershipSubjectEnum;

    /** 证件号码 */
    private Integer idCardNumEnum;

    /** 证明类型 */
    private Integer proveTypeEnum;

    /** 登记时间 */
    private Integer registerDateEnum;

    /** 合同截止时间 */
    private Integer contractCutOffDateEnum;

    /* This code was generated by TableGo tools, mark 1 end. */

    /* This code was generated by TableGo tools, mark 2 begin. */

    /**
     * 获取主键Id
     * 
     * @return 主键Id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置主键Id
     * 
     * @param id
     *          主键Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户id
     * 
     * @return 用户id
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 设置用户id
     * 
     * @param userId
     *          用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取车辆vin码
     * 
     * @return 车辆vin码
     */
    public Integer getCarVinEnum() {
        return this.carVinEnum;
    }

    /**
     * 设置车辆vin码
     * 
     * @param carVinEnum
     *          车辆vin码
     */
    public void setCarVinEnum(Integer carVinEnum) {
        this.carVinEnum = carVinEnum;
    }

    /**
     * 获取车牌号码
     * 
     * @return 车牌号码
     */
    public Integer getCarNumEnum() {
        return this.carNumEnum;
    }

    /**
     * 设置车牌号码
     * 
     * @param carNumEnum
     *          车牌号码
     */
    public void setCarNumEnum(Integer carNumEnum) {
        this.carNumEnum = carNumEnum;
    }

    /**
     * 获取车辆品牌
     * 
     * @return 车辆品牌
     */
    public Integer getCarBrandEnum() {
        return this.carBrandEnum;
    }

    /**
     * 设置车辆品牌
     * 
     * @param carBrandEnum
     *          车辆品牌
     */
    public void setCarBrandEnum(Integer carBrandEnum) {
        this.carBrandEnum = carBrandEnum;
    }

    /**
     * 获取车辆型号
     * 
     * @return 车辆型号
     */
    public Integer getCarModelEnum() {
        return this.carModelEnum;
    }

    /**
     * 设置车辆型号
     * 
     * @param carModelEnum
     *          车辆型号
     */
    public void setCarModelEnum(Integer carModelEnum) {
        this.carModelEnum = carModelEnum;
    }

    /**
     * 获取发动机号
     * 
     * @return 发动机号
     */
    public Integer getEngineNumEnum() {
        return this.engineNumEnum;
    }

    /**
     * 设置发动机号
     * 
     * @param engineNumEnum
     *          发动机号
     */
    public void setEngineNumEnum(Integer engineNumEnum) {
        this.engineNumEnum = engineNumEnum;
    }

    /**
     * 获取生产日期
     * 
     * @return 生产日期
     */
    public Integer getProductDateEnum() {
        return this.productDateEnum;
    }

    /**
     * 设置生产日期
     * 
     * @param productDateEnum
     *          生产日期
     */
    public void setProductDateEnum(Integer productDateEnum) {
        this.productDateEnum = productDateEnum;
    }

    /**
     * 获取权属类型
     * 
     * @return 权属类型
     */
    public Integer getOwnershipTypeEnum() {
        return this.ownershipTypeEnum;
    }

    /**
     * 设置权属类型
     * 
     * @param ownershipTypeEnum
     *          权属类型
     */
    public void setOwnershipTypeEnum(Integer ownershipTypeEnum) {
        this.ownershipTypeEnum = ownershipTypeEnum;
    }

    /**
     * 获取权属主体
     * 
     * @return 权属主体
     */
    public Integer getOwnershipSubjectEnum() {
        return this.ownershipSubjectEnum;
    }

    /**
     * 设置权属主体
     * 
     * @param ownershipSubjectEnum
     *          权属主体
     */
    public void setOwnershipSubjectEnum(Integer ownershipSubjectEnum) {
        this.ownershipSubjectEnum = ownershipSubjectEnum;
    }

    /**
     * 获取证件号码
     * 
     * @return 证件号码
     */
    public Integer getIdCardNumEnum() {
        return this.idCardNumEnum;
    }

    /**
     * 设置证件号码
     * 
     * @param idCardNumEnum
     *          证件号码
     */
    public void setIdCardNumEnum(Integer idCardNumEnum) {
        this.idCardNumEnum = idCardNumEnum;
    }

    /**
     * 获取证明类型
     * 
     * @return 证明类型
     */
    public Integer getProveTypeEnum() {
        return this.proveTypeEnum;
    }

    /**
     * 设置证明类型
     * 
     * @param proveTypeEnum
     *          证明类型
     */
    public void setProveTypeEnum(Integer proveTypeEnum) {
        this.proveTypeEnum = proveTypeEnum;
    }

    /**
     * 获取登记时间
     * 
     * @return 登记时间
     */
    public Integer getRegisterDateEnum() {
        return this.registerDateEnum;
    }

    /**
     * 设置登记时间
     * 
     * @param registerDateEnum
     *          登记时间
     */
    public void setRegisterDateEnum(Integer registerDateEnum) {
        this.registerDateEnum = registerDateEnum;
    }

    /**
     * 获取合同截止时间
     * 
     * @return 合同截止时间
     */
    public Integer getContractCutOffDateEnum() {
        return this.contractCutOffDateEnum;
    }

    /**
     * 设置合同截止时间
     * 
     * @param contractCutOffDateEnum
     *          合同截止时间
     */
    public void setContractCutOffDateEnum(Integer contractCutOffDateEnum) {
        this.contractCutOffDateEnum = contractCutOffDateEnum;
    }

    @Override
    public String toString() {
        return "CertificatePrivacy{" +
                "id=" + id +
                ", userId=" + userId +
                ", carVinEnum=" + carVinEnum +
                ", carNumEnum=" + carNumEnum +
                ", carBrandEnum=" + carBrandEnum +
                ", carModelEnum=" + carModelEnum +
                ", engineNumEnum=" + engineNumEnum +
                ", productDateEnum=" + productDateEnum +
                ", ownershipTypeEnum=" + ownershipTypeEnum +
                ", ownershipSubjectEnum=" + ownershipSubjectEnum +
                ", idCardNumEnum=" + idCardNumEnum +
                ", proveTypeEnum=" + proveTypeEnum +
                ", registerDateEnum=" + registerDateEnum +
                ", contractCutOffDateEnum=" + contractCutOffDateEnum +
                '}';
    }


    /* This code was generated by TableGo tools, mark 2 end. */
}