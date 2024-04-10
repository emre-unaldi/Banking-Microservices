package unaldi.logservice.service.abstracts;

import unaldi.logservice.model.dto.LogDTO;
import unaldi.logservice.model.request.LogRequest;
import unaldi.logservice.model.response.LogResponse;
import unaldi.logservice.utils.result.DataResult;
import unaldi.logservice.utils.result.Result;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public interface LogService {
    DataResult<LogResponse> sendToLog(LogRequest logRequest);
    Result deleteById(String logId);
    DataResult<LogDTO> findById(String logId);
    DataResult<List<LogDTO>> findAll();
}
