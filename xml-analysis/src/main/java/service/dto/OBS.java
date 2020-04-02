package service.dto;

import lombok.Data;

import javax.xml.bind.annotation.*;

/**
 * @Author yuchl
 * @Date 2020/3/19 0019
 * @Description
 */
@Data
@XmlRootElement(namespace = "http://xmlns.oracle.com/Primavera/P6Professional/V8.3/API/BusinessObjects",
        name = "OBS")
@XmlAccessorType(XmlAccessType.FIELD)
public class OBS {

    @XmlElement(name = "Description")
    private String description;

    @XmlElement(name = "GUID")
    private String guId;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "ObjectId")
    private String objectId;

    @XmlElement( name = "ParentObjectId")
    private String parentObjectId;

}