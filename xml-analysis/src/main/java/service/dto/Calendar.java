package service.dto;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(namespace = "http://xmlns.oracle.com/Primavera/P6Professional/V8.3/API/BusinessObjects",
        name = "Calendar")
@XmlAccessorType(XmlAccessType.FIELD)
public class Calendar {

    @XmlElement(name = "BaseCalendarObjectId")
    private String baseCalendarObjectId;

    @XmlElement(name = "HoursPerDay")
    private String hoursPerDay;

    @XmlElement(name = "HoursPerMonth")
    private String hoursPerMonth;

    @XmlElement(name = "HoursPerWeek")
    private String hoursPerWeek;

    @XmlElement(name = "HoursPerYear")
    private String hoursPerYear;

    @XmlElement(name = "IsDefault")
    private String isDefault;

    @XmlElement(name = "IsPersonal")
    private String isPersonal;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "ObjectId")
    private String objectId;

    @XmlElement( name = "ParentObjectId")
    private String parentObjectId;

    @XmlElement(name = "Type")
    private String type;

    @XmlElement(name = "StandardWorkWeek")
    private StandardWorkWeek standardWorkWeek;

    @XmlElement(name = "HolidayOrExceptions")
    private HolidayOrExceptions holidayOrExceptions;

}
