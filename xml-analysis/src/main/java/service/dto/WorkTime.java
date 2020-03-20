package service.dto;

import config.LocalTimeXmlAdapter;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalTime;

@Data
@XmlRootElement(namespace = "http://xmlns.oracle.com/Primavera/P6Professional/V8.3/API/BusinessObjects",
        name = "WorkTime")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkTime {

    @XmlElement(name = "Start")
    @XmlJavaTypeAdapter(LocalTimeXmlAdapter.class)
    private LocalTime start;

    @XmlElement(name = "Finish")
    @XmlJavaTypeAdapter(LocalTimeXmlAdapter.class)
    private LocalTime finish;
}
