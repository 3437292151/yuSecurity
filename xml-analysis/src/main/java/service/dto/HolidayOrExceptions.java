package service.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(namespace = "http://xmlns.oracle.com/Primavera/P6Professional/V8.3/API/BusinessObjects",
        name = "HolidayOrExceptions")
@XmlAccessorType(XmlAccessType.FIELD)
public class HolidayOrExceptions {

    @XmlElement(name = "HolidayOrException")
    private List<HolidayOrException> holidayOrExceptionList;
}
