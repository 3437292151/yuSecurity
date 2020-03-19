
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
        //利用输入流获取XML文件内容
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }
        br.close();
        //XML转为JAVA对象
        APIBusinessObjects cityList = (APIBusinessObjects) XmlBuilder.xmlStrToObject(APIBusinessObjects.class, buffer.toString());
       System.out.println(cityList);
    }

}
