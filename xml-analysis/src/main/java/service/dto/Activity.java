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
        name = "Activity")
@XmlAccessorType(XmlAccessType.FIELD)
public class Activity {

    @XmlElement(name = "Type")
    private String type;

    @XmlElement(name = "Id")
    private String id;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "ObjectId")
    private String objectId;

    @XmlElement(name = "PlannedStartDate")
    @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
    private LocalDateTime plannedStartDate;

    @XmlElement(name = "PlannedDuration")
    private String plannedDuration;

    @XmlElement(name = "PlannedFinishDate")
    @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
    private LocalDateTime plannedFinishDate;

    @XmlElement(name = "WBSObjectId")
    private String wbSObjectId;

    @XmlElement(name = "Status")
    private String status;

    @XmlElement(name = "CalendarObjectId")
    private String calendarObjectId;

    @XmlElement(name = "UDF")
    private UDF udf;

}
