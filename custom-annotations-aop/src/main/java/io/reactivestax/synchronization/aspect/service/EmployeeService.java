package io.reactivestax.synchronization.aspect.service;

import io.reactivestax.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private static final Logger log = LogManager.getLogger(EmployeeService.class);


public void synchronizedSection(){
    log.info("Executing the thread safe manner: {}" , Thread.currentThread());
}

}
