package service.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(namespace = "http://xmlns.oracle.com/Primavera/P6Professional/V8.3/API/BusinessObjects",
        name = "WBS")
@XmlAccessorType(XmlAccessType.FIELD)
public class WBS {

    @XmlElement(name = "Code")
    private String code;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "ObjectId")
    private String objectId;

    @XmlElement(name = "ParentObjectId")
    private String parentObjectId;
}
