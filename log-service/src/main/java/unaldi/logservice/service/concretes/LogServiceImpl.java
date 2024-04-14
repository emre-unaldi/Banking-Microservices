package unaldi.logservice.service.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unaldi.logservice.model.Log;
import unaldi.logservice.model.dto.LogDTO;
import unaldi.logservice.model.request.LogSaveRequest;
import unaldi.logservice.repository.LogRepository;
import unaldi.logservice.service.abstracts.LogService;
import unaldi.logservice.service.abstracts.mapper.LogMapper;
import unaldi.logservice.utils.constant.ExceptionMessages;
import unaldi.logservice.utils.constant.Messages;
import unaldi.logservice.utils.exception.customExceptions.LogNotFoundException;
import unaldi.logservice.utils.result.DataResult;
import unaldi.logservice.utils.result.Result;
import unaldi.logservice.utils.result.SuccessDataResult;
import unaldi.logservice.utils.result.SuccessResult;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Service
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public DataResult<LogDTO> save(LogSaveRequest logSaveRequest) {
        Log log = LogMapper.INSTANCE.convertToSaveLog(logSaveRequest);

        this.logRepository.save(log);

        return new SuccessDataResult<>(
                LogMapper.INSTANCE.convertToLogDTO(log),
                Messages.LOG_CREATED
        );
    }

    @Override
    public Result deleteById(String logId) {
        Log log = this.logRepository
                .findById(logId)
                .orElseThrow(() -> new LogNotFoundException(ExceptionMessages.LOG_NOT_FOUND));

        this.logRepository.deleteById(log.getId());

        return new SuccessResult(Messages.LOG_DELETED);
    }

    @Override
    public DataResult<LogDTO> findById(String logId) {
        LogDTO logDTO = this.logRepository
                .findById(logId)
                .map(LogMapper.INSTANCE::convertToLogDTO)
                .orElseThrow(() -> new LogNotFoundException(ExceptionMessages.LOG_NOT_FOUND));

        return new SuccessDataResult<>(
                logDTO,
                Messages.LOG_FOUND
        );
    }

    @Override
    public DataResult<List<LogDTO>> findAll() {
        List<Log> logList = this.logRepository.findAll();

        return new SuccessDataResult<>(
                LogMapper.INSTANCE.convertLogDTOs(logList),
                Messages.LOGS_LISTED
        );
    }
}
