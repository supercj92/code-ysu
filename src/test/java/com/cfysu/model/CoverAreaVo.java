package com.cfysu.model;

import java.io.Serializable;

/**
 * Created by zhangrenhua1 on 2017/11/1.
 */
public class CoverAreaVo implements Serializable {
    private String provinceId;
    private String[] cityIds;
    private String provinceName;
    private String cityNames;

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String[] getCityIds() {
        return cityIds;
    }

    public void setCityIds(String[] cityIds) {
        this.cityIds = cityIds;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityNames() {
        return cityNames;
    }

    public void setCityNames(String cityNames) {
        this.cityNames = cityNames;
    }

}
