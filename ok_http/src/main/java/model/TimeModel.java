package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "week_number",
        "utc_offset",
        "unixtime",
        "timezone",
        "dst_until",
        "dst_from",
        "dst",
        "day_of_year",
        "day_of_week",
        "datetime",
        "abbreviation"
})
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeModel {
    @JsonProperty("week_number")
    private String weekNumber;
    @JsonProperty("utc_offset")
    private String utcOffset;
    @JsonProperty("unixtime")
    private String unixtime;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("dst_until")
    private Object dstUntil;
    @JsonProperty("dst_from")
    private Object dstFrom;
    @JsonProperty("dst")
    private Boolean dst;
    @JsonProperty("day_of_year")
    private Integer dayOfYear;
    @JsonProperty("day_of_week")
    private Integer dayOfWeek;
    @JsonProperty("datetime")
    private String datetime;
    @JsonProperty("abbreviation")
    private String abbreviation;
}