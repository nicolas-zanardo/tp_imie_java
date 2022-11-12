package fr.imie.fomation.api.controller;


import fr.imie.fomation.api.model.Status;
import fr.imie.fomation.api.service.ClasseService;
import fr.imie.fomation.api.service.StatusService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author raouf
 */

@RestController
@RequestMapping(path = "/api" )
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping("/status")
    public  Iterable<Status> getStatus() {
        return  statusService.getStatus();
    }
    @PostMapping("/add-status")
    public Status createStatus(@RequestBody Status status){
        return  statusService.saveStatus(status);

    }

    @GetMapping("/status/{id}")
    public Status getStatus (@PathVariable("id") final Long id) {
        Optional<Status> status = statusService.getStatus(id);
        return status.orElse( null);
    }

    @PutMapping("/update-status/{id}")
    public Status updateStatus(@PathVariable("id") final Long id, @RequestBody Status status){
        Optional<Status>  st = statusService.getStatus(id);
        if(st.isPresent()){
            Status currentStatus = st.get();
            String name = status.getName();
            if( name!= null ){
                currentStatus.setName(name);
            }

            currentStatus.setName(status.getName());
            statusService.saveStatus(currentStatus);
            return currentStatus;

        }else{
            return null;

        }
    }


}
