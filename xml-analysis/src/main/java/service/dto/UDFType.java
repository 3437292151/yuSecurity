package service.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Author yuchl
 * @Date 2020/3/19 0019
 * @Description
 */
@Data
@XmlRootElement(namespace = "http://xmlns.oracle.com/Primavera/P6Professional/V8.3/API/BusinessObjects",
        name = "UDFType")
@XmlAccessorType(XmlAccessType.FIELD)
public class UDFType {

    @XmlElement(name = "DataType")
    private String DataType;

    @XmlElement(name = "IsSecureCode")
    private String isSecureCode;

    @XmlElement(name = "ObjectId")
    private String objectId;

    @XmlElement(name = "SubjectArea")
    private String subjectArea;

    @XmlElement(name = "Title")
    private String title;
}