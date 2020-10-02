package com.ksv.analyticaltool.service.impl;

import com.ksv.analyticaltool.model.RequestType;
import com.ksv.analyticaltool.model.ResponseType;
import com.ksv.analyticaltool.model.dto.QueryLineDto;
import com.ksv.analyticaltool.model.dto.RequestDto;
import com.ksv.analyticaltool.model.dto.ResponseDto;
import com.ksv.analyticaltool.model.dto.WaitingTimeLineDto;
import com.ksv.analyticaltool.model.mapper.QueryLineMapper;
import com.ksv.analyticaltool.model.mapper.WaitingTimeLineMapper;
import com.ksv.analyticaltool.service.QueryService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QueryServiceImpl implements QueryService {
    private static final String MATCH_ALL = "*";
    private static final String EMPTY = "-";
    private final QueryLineMapper queryLineMapper;
    private final WaitingTimeLineMapper waitingTimeLineMapper;

    @Override
    public List<ResponseDto> getAverageWaitingTime(List<RequestDto> requestDtoList) {
        List<ResponseDto> responseDtoList = new ArrayList<>();
        for (int i = 0; i < requestDtoList.size(); i++) {
            var requestDto = requestDtoList.get(i);
            if (requestDto.getRequestType().equals(RequestType.D)) {
                int totalTime = 0;
                int countTimeLines = 0;
                var queryLine = queryLineMapper.getQueryLineDto(requestDto);
                for (int j = 0; j < i; j++) {
                    var dto = requestDtoList.get(j);
                    if (dto.getRequestType().equals(RequestType.C)) {
                        var waitingTimeLine = waitingTimeLineMapper.getWaitingTimeLineDto(dto);
                        if (isMatch(queryLine, waitingTimeLine)) {
                            totalTime += waitingTimeLine.getTime();
                            countTimeLines++;
                        }
                    }
                }
                var averageWaitingTime = calculateAverageWaitingTime(totalTime, countTimeLines);
                var responseDto = new ResponseDto().setAverageWaitingTime(averageWaitingTime);
                responseDtoList.add(responseDto);
            }
        }
        return responseDtoList;
    }

    private String calculateAverageWaitingTime(int totalTime, int countTimeLines) {
        return countTimeLines > 0
                ? String.valueOf(totalTime / countTimeLines)
                : EMPTY;
    }

    private boolean isMatch(QueryLineDto queryLineDto, WaitingTimeLineDto waitingTimeLineDto) {
        return isServiceMatch(queryLineDto.getServiceId(), waitingTimeLineDto.getServiceId())
                && isQuestionTypeMatch(queryLineDto.getQuestionTypeId(),
                waitingTimeLineDto.getQuestionTypeId())
                && isResponseTypeMatch(queryLineDto.getResponseType(),
                waitingTimeLineDto.getResponseType())
                && isMatchDate(waitingTimeLineDto.getDate(),
                queryLineDto.getDateFrom(), queryLineDto.getDateTo());
    }

    private boolean isServiceMatch(String queryLineService, String waitingTimeLineService) {
        return queryLineService.equals(MATCH_ALL)
                || waitingTimeLineService.startsWith(queryLineService);
    }

    private boolean isQuestionTypeMatch(String queryLineQuestionType,
                                        String waitingTimeLineQuestionType) {
        return queryLineQuestionType.equals(MATCH_ALL)
                || waitingTimeLineQuestionType.startsWith(queryLineQuestionType);
    }

    private boolean isResponseTypeMatch(ResponseType queryResponseType,
                                        ResponseType waitingTimeLineResponseType) {
        return queryResponseType.equals(waitingTimeLineResponseType);
    }

    private boolean isMatchDate(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return dateTo == null
                ? date.equals(dateFrom)
                : dateFrom.isBefore(date) && dateTo.isAfter(date);
    }
}
