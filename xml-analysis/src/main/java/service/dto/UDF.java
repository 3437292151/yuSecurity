package service.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(namespace = "http://xmlns.oracle.com/Primavera/P6Professional/V8.3/API/BusinessObjects",
        name = "UDF")
@XmlAccessorType(XmlAccessType.FIELD)
public class UDF {

    @XmlElement(name = "typeObjectId")
    private String TypeObjectId;

    @XmlElement(name = "TextValue")
    private String textValue;
}
