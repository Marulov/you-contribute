package com.marulov.youcontribute.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulerService {

    private final ProjectService projectService;

    private final ImportService importService;

    @Scheduled(fixedRateString = "${application.import-frequency}", initialDelay = 60000)
    public void importIssuesScheduler() {
        log.info("Import scheduler started.");
        projectService.getAll().forEach(importService::importIssues);
        log.info("Import scheduler finished.");
    }
}