package fr.imie.fomation.api.controller;


import fr.imie.fomation.api.model.RoomStatus;
import fr.imie.fomation.api.service.RoomStatusService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author raouf
 */

@RestController
@RequestMapping(path = "/api" )
public class RoomStatusController {

    @Autowired
    private RoomStatusService statusService;

    @GetMapping("/statuses")
    public  Iterable<RoomStatus> getStatus() {
        return  statusService.getStatuses();
    }

    @GetMapping("/status/{id}")
    public RoomStatus getStatus (@PathVariable("id") final Long id) {
        Optional<RoomStatus> status = statusService.getStatusById(id);
        return status.orElse( null);
    }
    @PostMapping("/add-status")
    public RoomStatus createStatus(@RequestBody RoomStatus status){
        return  statusService.saveStatus(status);
    }
    @PutMapping("/update-status/{id}")
    public RoomStatus updateStatus(@PathVariable("id") final Long id, @RequestBody RoomStatus status){
        Optional<RoomStatus>  st = statusService.getStatusById(id);
        if(st.isPresent()){
            RoomStatus currentStatus = st.get();
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
