package config;

import org.apache.commons.lang.StringUtils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeXmlAdapter extends XmlAdapter<String, LocalDateTime> {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    public LocalDateTime unmarshal(String valueType) throws Exception {
        valueType = StringUtils.replace(valueType, "T", " ");
        return LocalDateTime.parse(valueType, dtf);
    }

    @Override
    public String marshal(LocalDateTime boundType) throws Exception {
        return dtf.format(boundType);
    }
}
