package service.dto;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(namespace = "http://xmlns.oracle.com/Primavera/P6Professional/V8.3/API/BusinessObjects",
        name = "APIBusinessObjects")
@XmlAccessorType(XmlAccessType.FIELD)
public class APIBusinessObjects {
    @XmlElement(name = "UDFType")
    private List<UDFType> udfType;

    @XmlElement(name = "OBS")
    private OBS obs;

    @XmlElement(name = "Project")
    private Project project;
}