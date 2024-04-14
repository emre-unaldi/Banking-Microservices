package unaldi.logservice.service.abstracts;

import unaldi.logservice.model.dto.LogDTO;
import unaldi.logservice.model.request.LogSaveRequest;
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
    DataResult<LogDTO> save(LogSaveRequest logSaveRequest);
    Result deleteById(String logId);
    DataResult<LogDTO> findById(String logId);
    DataResult<List<LogDTO>> findAll();
}
