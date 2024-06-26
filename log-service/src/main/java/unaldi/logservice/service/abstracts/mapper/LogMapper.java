package unaldi.logservice.service.abstracts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import unaldi.logservice.model.Log;
import unaldi.logservice.model.dto.LogDTO;
import unaldi.logservice.model.request.LogSaveRequest;
import unaldi.logservice.model.request.LogUpdateRequest;
import unaldi.logservice.utils.RabbitMQ.response.LogResponse;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogMapper {
    LogMapper INSTANCE = Mappers.getMapper(LogMapper.class);

    Log convertToSaveLog(LogSaveRequest logSaveRequest);
    Log convertToUpdateLog(LogUpdateRequest logUpdateRequest);
    LogDTO convertToLogDTO(Log log);
    Log convertToLog(LogResponse logResponse);
    List<LogDTO> convertLogDTOs(List<Log> logList);
}
