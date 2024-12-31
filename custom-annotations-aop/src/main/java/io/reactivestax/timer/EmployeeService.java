package io.reactivestax.timer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private static final Logger log = LogManager.getLogger(EmployeeService.class);

    @Timer
    public void performTask(){
        log.info("Performing Employee tasks");
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

}
