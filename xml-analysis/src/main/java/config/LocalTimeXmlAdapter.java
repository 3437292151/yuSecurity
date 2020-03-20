package config;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeXmlAdapter extends XmlAdapter<String, LocalTime> {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    @Override
    public LocalTime unmarshal(String valueType) throws Exception {
        return LocalTime.parse(valueType, dtf);
    }

    @Override
    public String marshal(LocalTime boundType) throws Exception {
        return dtf.format(boundType);
    }
}
