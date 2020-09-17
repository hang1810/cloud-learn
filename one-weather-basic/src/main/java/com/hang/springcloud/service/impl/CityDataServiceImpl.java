package com.hang.springcloud.service.impl;

import com.hang.springcloud.entiey.City;
import com.hang.springcloud.entiey.CityList;
import com.hang.springcloud.service.CityDataService;
import com.hang.springcloud.utils.XmlBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author Hang
 * @date 2020-07-23 14:52
 */
@Service
public class CityDataServiceImpl implements CityDataService {

    @Override
    public List<City> listCity()  throws Exception {
        //读取xml文件
        Resource resource=new ClassPathResource("citylist.xml");
        BufferedReader br=new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
        StringBuffer buffer=new StringBuffer();
        String line="";

        while((line=br.readLine())!=null) {
            buffer.append(line);
        }

        br.close();
        //xml转为java对象
        CityList cityList=(CityList) XmlBuilder.xmlStrToOject(CityList.class, buffer.toString());

        return cityList.getCityList();
    }

}
