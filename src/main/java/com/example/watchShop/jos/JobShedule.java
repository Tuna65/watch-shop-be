package com.example.watchShop.jos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobShedule {

  private static final Logger log = LoggerFactory.getLogger(JobShedule.class);

  private final ExecutorService executorService = Executors.newSingleThreadExecutor();

  @Scheduled(cron = "0 15 18 * * ?")
  public void name() {
  }
}
