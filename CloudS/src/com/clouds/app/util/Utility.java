package com.clouds.app.util;

import android.text.TextUtils;

import com.clouds.app.db.CloudsDB;
import com.clouds.app.model.City;
import com.clouds.app.model.County;
import com.clouds.app.model.Province;

public class Utility {
    //����������ʡ������
    public synchronized static boolean handleProvincesResponse(CloudsDB cloudsDB, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0) {
                for (String p : allProvinces) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //���������������ݴ��浽Province��
                    cloudsDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    //�����������м�����
    public synchronized static boolean handleCitiesResponse(CloudsDB coolWeatherDB,
                                                            String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String c : allCities) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    //���������������ݴ��浽City��
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    //�����������ؼ�����
    public static boolean handleCountiesResponse(CloudsDB coolWeatherDB,
                                                 String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String c : allCounties) {
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    //���������������ݴ��浽Couty��
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }

}
