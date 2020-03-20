package service.dto;

import config.LocalDateTimeXmlAdapter;
import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.List;

@Data
@XmlRootElement(namespace = "http://xmlns.oracle.com/Primavera/P6Professional/V8.3/API/BusinessObjects",
        name = "Project")
@XmlType(name = "Project",propOrder ={
        "activityDefaultActivityType",
        "activityDefaultCalendarObjectId",
        "activityDefaultCostAccountObjectId",
        "activityDefaultDurationType",
        "activityDefaultPercentCompleteType",
        "activityDefaultPricePerUnit",
        "activityIdBasedOnSelectedActivity",
        "activityIdIncrement",
        "activityIdPrefix",
        "activityIdSuffix",
        "activityPercentCompleteBasedOnActivitySteps",
        "addActualToRemaining",
        "addedBy",
        "allowNegativeActualUnitsFlag",
        "annualDiscountRate",
        "anticipatedFinishDate",
        "anticipatedStartDate",
        "assignmentDefaultDrivingFlag",
        "assignmentDefaultRateType",
        "checkOutStatus",
        "costQuantityRecalculateFlag",
        "criticalActivityFloatLimit",
        "criticalActivityPathType",
        "currentBaselineProjectObjectId",
        "dataDate",
        "dateAdded",
        "defaultPriceTimeUnits",
        "description",
        "discountApplicationPeriod",
        "earnedValueComputeType",
        "earnedValueETCComputeType",
        "earnedValueETCUserValue",
        "earnedValueUserPercent",
        "enableSummarization",
        "fiscalYearStartMonth",
        "guId",
        "id",
        "independentETCLaborUnits",
        "independentETCTotalCost",
        "integratedType",
        "lastFinancialPeriodObjectId",
        "levelingPriority",
        "linkActualToActualThisPeriod",
        "linkPercentCompleteWithActual",
        "linkPlannedAndAtCompletionFlag",
        "mustFinishByDate",
        "name",
        "obSObjectId",
        "objectId",
        "originalBudget",
        "ownerResourceObjectId",
        "parentEPSObjectId",
        "plannedStartDate",
        "primaryResourcesCanMarkActivitiesAsCompleted",
        "projectForecastStartDate",
        "resetPlannedToRemainingFlag",
        "resourceCanBeAssignedToSameActivityMoreThanOnce",
        "resourcesCanAssignThemselvesToActivities",
        "resourcesCanEditAssignmentPercentComplete",
        "resourcesCanMarkAssignmentAsCompleted",
        "resourcesCanViewInactiveActivities",
        "riskLevel",
        "scheduledFinishDate",
        "status",
        "strategicPriority",
        "summarizeToWBSLevel",
        "summarizedDataDate",
        "summaryLevel",
        "useProjectBaselineForEarnedValue",
        "wbSCodeSeparator",
        "wbSObjectId",
        "webSiteRootDirectory",
        "webSiteURL",
        "calendar",
        "wbs",
        "activity"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class Project {

    @XmlElement(name = "ActivityDefaultActivityType")
    private String activityDefaultActivityType;

    @XmlElement(name = "ActivityDefaultCalendarObjectId")
    private String activityDefaultCalendarObjectId;

    @XmlElement(name = "ActivityDefaultCostAccountObjectId")
    private String activityDefaultCostAccountObjectId;

    @XmlElement(name = "ActivityDefaultDurationType")
    private String activityDefaultDurationType;

    @XmlElement(name = "ActivityDefaultPercentCompleteType")
    private String activityDefaultPercentCompleteType;

    @XmlElement(name = "ActivityDefaultPricePerUnit")
    private String activityDefaultPricePerUnit;

    @XmlElement(name = "ActivityIdBasedOnSelectedActivity")
    private String activityIdBasedOnSelectedActivity;

    @XmlElement(name = "ActivityIdIncrement")
    private String activityIdIncrement;

    @XmlElement(name = "ActivityIdPrefix")
    private String activityIdPrefix;

    @XmlElement(name = "ActivityIdSuffix")
    private String activityIdSuffix;

    @XmlElement(name = "ActivityPercentCompleteBasedOnActivitySteps")
    private String activityPercentCompleteBasedOnActivitySteps;

    @XmlElement(name = "AddActualToRemaining")
    private String addActualToRemaining;

    @XmlElement(name = "AddedBy")
    private String addedBy;

    @XmlElement(name = "AllowNegativeActualUnitsFlag")
    private String allowNegativeActualUnitsFlag;

    @XmlElement(name = "AnnualDiscountRate")
    private String annualDiscountRate;

    @XmlElement(name = "AnticipatedFinishDate")
    private String anticipatedFinishDate;

    @XmlElement(name = "AnticipatedStartDate")
    private String anticipatedStartDate;

    @XmlElement(name = "AssignmentDefaultDrivingFlag")
    private String assignmentDefaultDrivingFlag;

    @XmlElement(name = "AssignmentDefaultRateType")
    private String assignmentDefaultRateType;

    @XmlElement(name = "CheckOutStatus")
    private String checkOutStatus;

    @XmlElement(name = "CostQuantityRecalculateFlag")
    private String costQuantityRecalculateFlag;

    @XmlElement(name = "CriticalActivityFloatLimit")
    private String criticalActivityFloatLimit;

    @XmlElement(name = "CriticalActivityPathType")
    private String criticalActivityPathType;

    @XmlElement(name = "CurrentBaselineProjectObjectId")
    private String currentBaselineProjectObjectId;

    @XmlElement(name = "DataDate")
    @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
    private LocalDateTime dataDate;

    @XmlElement(name = "DateAdded")
    @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
    private LocalDateTime dateAdded;

    @XmlElement(name = "DefaultPriceTimeUnits")
    private String defaultPriceTimeUnits;

    @XmlElement(name = "Description")
    private String description;

    @XmlElement(name = "DiscountApplicationPeriod")
    private String discountApplicationPeriod;

    @XmlElement(name = "EarnedValueComputeType")
    private String earnedValueComputeType;

    @XmlElement(name = "EarnedValueETCComputeType")
    private String earnedValueETCComputeType;

    @XmlElement(name = "EarnedValueETCUserValue")
    private String earnedValueETCUserValue;

    @XmlElement(name = "EarnedValueUserPercent")
    private String earnedValueUserPercent;

    @XmlElement(name = "EnableSummarization")
    private String enableSummarization;

    @XmlElement(name = "FiscalYearStartMonth")
    private String fiscalYearStartMonth;

    @XmlElement(name = "GUID")
    private String guId;

    @XmlElement(name = "Id")
    private String id;

    @XmlElement(name = "IndependentETCLaborUnits")
    private String independentETCLaborUnits;

    @XmlElement(name = "IndependentETCTotalCost")
    private String independentETCTotalCost;

    @XmlElement(name = "IntegratedType")
    private String integratedType;

    @XmlElement(name = "LastFinancialPeriodObjectId")
    private String lastFinancialPeriodObjectId;

    @XmlElement(name = "LevelingPriority")
    private String levelingPriority;

    @XmlElement(name = "LinkActualToActualThisPeriod")
    private String linkActualToActualThisPeriod;

    @XmlElement(name = "LinkPercentCompleteWithActual")
    private String linkPercentCompleteWithActual;

    @XmlElement(name = "LinkPlannedAndAtCompletionFlag")
    private String linkPlannedAndAtCompletionFlag;

    @XmlElement(name = "MustFinishByDate")
    @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
    private LocalDateTime mustFinishByDate;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "OBSObjectId")
    private String obSObjectId;

    @XmlElement(name = "ObjectId")
    private String objectId;

    @XmlElement(name = "OriginalBudget")
    private String originalBudget;

    @XmlElement(name = "OwnerResourceObjectId")
    private String ownerResourceObjectId;

    @XmlElement(name = "ParentEPSObjectId")
    private String parentEPSObjectId;

    @XmlElement(name = "PlannedStartDate")
    private String plannedStartDate;

    @XmlElement(name = "PrimaryResourcesCanMarkActivitiesAsCompleted")
    private String primaryResourcesCanMarkActivitiesAsCompleted;

    @XmlElement(name = "ProjectForecastStartDate")
    private String projectForecastStartDate;

    @XmlElement(name = "ResetPlannedToRemainingFlag")
    private String resetPlannedToRemainingFlag;

    @XmlElement(name = "ResourceCanBeAssignedToSameActivityMoreThanOnce")
    private String resourceCanBeAssignedToSameActivityMoreThanOnce;

    @XmlElement(name = "ResourcesCanAssignThemselvesToActivities")
    private String resourcesCanAssignThemselvesToActivities;

    @XmlElement(name = "ResourcesCanEditAssignmentPercentComplete")
    private String resourcesCanEditAssignmentPercentComplete;

    @XmlElement(name = "ResourcesCanMarkAssignmentAsCompleted")
    private String resourcesCanMarkAssignmentAsCompleted;

    @XmlElement(name = "ResourcesCanViewInactiveActivities")
    private String resourcesCanViewInactiveActivities;

    @XmlElement(name = "RiskLevel")
    private String riskLevel;

    @XmlElement(name = "ScheduledFinishDate")
    private String scheduledFinishDate;

    @XmlElement(name = "Status")
    private String status;

    @XmlElement(name = "StrategicPriority")
    private String strategicPriority;

    @XmlElement(name = "SummarizeToWBSLevel")
    private String summarizeToWBSLevel;

    @XmlElement(name = "SummarizedDataDate")
    private String summarizedDataDate;

    @XmlElement(name = "SummaryLevel")
    private String summaryLevel;

    @XmlElement(name = "UseProjectBaselineForEarnedValue")
    private String useProjectBaselineForEarnedValue;

    @XmlElement(name = "WBSCodeSeparator")
    private String wbSCodeSeparator;

    @XmlElement(name = "WBSObjectId")
    private String wbSObjectId;

    @XmlElement(name = "WebSiteRootDirectory")
    private String webSiteRootDirectory;

    @XmlElement(name = "WebSiteURL")
    private String webSiteURL;

    @XmlElement(name = "Calendar")
    private Calendar calendar;

    @XmlElement(name = "WBS")
    private List<WBS> wbs;

    @XmlElement(name = "Activity")
    private Activity activity;
}
