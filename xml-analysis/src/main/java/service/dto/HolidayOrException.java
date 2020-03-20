package service.dto;

import config.LocalDateTimeXmlAdapter;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@Data
@XmlRootElement(namespace = "http://xmlns.oracle.com/Primavera/P6Professional/V8.3/API/BusinessObjects",
        name = "HolidayOrException")
@XmlAccessorType(XmlAccessType.FIELD)
public class HolidayOrException {

    @XmlElement(name = "Date")
    @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
    private LocalDateTime date;

    @XmlElement(name = "WorkTime")
    private String WorkTime;
}
