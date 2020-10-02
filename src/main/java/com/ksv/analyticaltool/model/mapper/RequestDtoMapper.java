package com.ksv.analyticaltool.model.mapper;

import com.ksv.analyticaltool.model.RequestType;
import com.ksv.analyticaltool.model.ResponseType;
import com.ksv.analyticaltool.model.dto.RequestDto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class RequestDtoMapper {
    private static final Pattern DATE_PATTERN = Pattern
            .compile("^\\s*(3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2})\\s*$");
    private static final Pattern ID_PATTERN = Pattern.compile("^\\d+(\\.\\d+)*$");
    private static final Pattern TIME_PATTERN = Pattern.compile("\\d+");
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String LINE_SPLITTER = " ";
    private static final String DATE_SPLITTER = "-";
    private static final String MATCH_ALL = "*";
    private static final int NUMBER_OF_WAITING_TIME_COMPONENTS = 6;
    private static final int NUMBER_OF_QUERY_COMPONENTS = 5;
    private static final int REQUEST_TYPE = 0;
    private static final int SERVICE = 1;
    private static final int QUESTION_TYPE = 2;
    private static final int RESPONSE_TYPE = 3;
    private static final int DATE = 4;
    private static final int TIME = 5;

    public RequestDto getRequestDto(String line) throws IllegalArgumentException {
        var components = line.split(LINE_SPLITTER);
        if (!isCorrectLineFormat(components)) {
            throw new IllegalArgumentException();
        }
        var dates = components[DATE].split(DATE_SPLITTER);
        return new RequestDto()
                .setRequestType(RequestType.valueOf(components[REQUEST_TYPE]))
                .setServiceId(components[SERVICE])
                .setQuestionTypeId(components[QUESTION_TYPE])
                .setResponseType(ResponseType.valueOf(components[RESPONSE_TYPE]))
                .setDate(getCorrectDate(dates[0]))
                .setDateFrom(getCorrectDate(dates[0]))
                .setDateTo(dates.length == 2
                        ? getCorrectDate(dates[1])
                        : null)
                .setTime(components.length == NUMBER_OF_WAITING_TIME_COMPONENTS
                        ? Integer.parseInt(components[TIME])
                        : null);
    }

    private LocalDate getCorrectDate(String date) {
        if (date.length() < DATE_FORMAT.length()) {
            date = "0" + date;
        }
        var formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(date, formatter);
    }

    private boolean isCorrectLineFormat(String[] components) {
        return components[REQUEST_TYPE].equals(RequestType.C.toString())
                ? isCorrectWaitingTimeline(components)
                : components[REQUEST_TYPE].equals(RequestType.D.toString())
                && isCorrectQueryLine(components);
    }

    private boolean isCorrectQueryLine(String[] components) {
        return components.length == NUMBER_OF_QUERY_COMPONENTS
                && isCorrectId(components[SERVICE])
                && isCorrectId(components[QUESTION_TYPE])
                && isCorrectResponseType(components[RESPONSE_TYPE])
                && Arrays.stream(components[DATE].split(DATE_SPLITTER))
                .allMatch(this::isCorrectDateFormat);
    }

    private boolean isCorrectWaitingTimeline(String[] components) {
        return components.length == NUMBER_OF_WAITING_TIME_COMPONENTS
                && isCorrectId(components[SERVICE])
                && isCorrectId(components[QUESTION_TYPE])
                && isCorrectResponseType(components[RESPONSE_TYPE])
                && isCorrectTimeFormat(components[TIME])
                && isCorrectDateFormat(components[DATE]);
    }

    private boolean isCorrectTimeFormat(String time) {
        return TIME_PATTERN.matcher(time).matches();
    }

    private boolean isCorrectDateFormat(String date) {
        return DATE_PATTERN.matcher(date).matches();
    }

    private boolean isCorrectResponseType(String responseType) {
        return responseType.equals(ResponseType.P.toString())
                || responseType.equals(ResponseType.N.toString());
    }

    private boolean isCorrectId(String id) {
        return ID_PATTERN.matcher(id).matches() || id.equals(MATCH_ALL);
    }
}
