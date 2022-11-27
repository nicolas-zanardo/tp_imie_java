package fr.imie.fomation.api.controller;


import fr.imie.fomation.api.model.Status;
import fr.imie.fomation.api.service.StatusService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/statuses")
    public  Iterable<Status> getStatus() {
        return  statusService.getStatuses();
    }

    @GetMapping("/status/{id}")
    public Status getStatus (@PathVariable("id") final Long id) {
        Optional<Status> status = statusService.getStatusById(id);
        return status.orElse( null);
    }
    @PostMapping("/add-status")
    public Status createStatus(@RequestBody Status status){
        return  statusService.saveStatus(status);
    }
    @PutMapping("/update-status/{id}")
    public Status updateStatus(@PathVariable("id") final Long id, @RequestBody Status status){
        Optional<Status>  st = statusService.getStatusById(id);
        if(st.isPresent()){
            Status currentStatus = st.get();
            String name = status.getName();
            if( name!= null ){
                currentStatus.setName(name.trim().toLowerCase());
            }

            //currentStatus.setName(status.getName());
            statusService.saveStatus(currentStatus);
            return currentStatus;

        }else{
            return null;

        }
    }

    /**
     * @author json
     * @param id Long
     */
    @DeleteMapping("/delete-status/{id}")
    public void deleteSalle(@PathVariable("id") final Long id) {
        statusService.deleteStatus(id);
    }
}
