package unaldi.logservice.service.abstracts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import unaldi.logservice.model.Log;
import unaldi.logservice.model.dto.LogDTO;
import unaldi.logservice.model.request.LogRequest;
import unaldi.logservice.model.response.LogResponse;

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

    LogResponse convertToLogResponse(LogRequest logRequest);
    LogDTO convertToLogDTO(LogResponse logResponse);
    LogDTO convertToLogDTO(Log log);
    Log convertToLog(LogDTO logDTO);
    List<LogDTO> convertLogDTOs(List<Log> logList);
}
