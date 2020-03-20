
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import service.dto.APIBusinessObjects;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

/**
 * @Author yuchl
 * @Date 2020/3/19 0019
 * @Description
 */
public class APIBusinessObjectsTest {

    @Test
    public void test() throws Exception {
        //读取Resource目录下的XML文件
        Resource resource = new ClassPathResource("导出的P6计划(XML版) .xml");;
        APIBusinessObjects cityList = (APIBusinessObjects) XmlBuilder.xmlToObject(APIBusinessObjects.class, resource.getInputStream());
       System.out.println(cityList);
        System.out.println(cityList.getProject().getCalendar().getStandardWorkWeek());
        System.out.println(cityList.getProject().getCalendar().getHolidayOrExceptions());
        System.out.println(cityList.getProject().getWbs());
        System.out.println(cityList.getProject().getActivity());
        String xml = XmlBuilder.objectToXML(cityList);
        System.out.println(xml);
    }

}
