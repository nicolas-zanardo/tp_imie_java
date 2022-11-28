package fr.imie.fomation.api.controller;


import fr.imie.fomation.api.model.RoomStatus;
import fr.imie.fomation.api.service.RoomStatusService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author raouf
 */

@RestController
@RequestMapping(path = "/api" )
public class RoomStatusController {

    @Autowired
    private RoomStatusService roomStatusService;

    @GetMapping("/statuses")
    public  Iterable<RoomStatus> getStatus() {
        return  roomStatusService.getStatuses();
    }

    @GetMapping("/status/{id}")
    public RoomStatus getStatus (@PathVariable("id") final Long id) {
        Optional<RoomStatus> status = roomStatusService.getStatusById(id);
        return status.orElse( null);
    }

    @PostMapping("/add-status")
    public RoomStatus createStatus(@RequestBody RoomStatus status){


        if(!status.getName().isEmpty()){
            status.setName(status.getName().toLowerCase().trim());
            Iterable<RoomStatus>  list = roomStatusService.getStatusRepository().findAll();

            for (RoomStatus li: list) {
                if(li.getName().equals(status.getName())){
                    roomStatusService.getStatusById(status.getId());
                }
            }
            roomStatusService.saveStatus(status);
        }

        return  roomStatusService.saveStatus(status);
    }
    @PutMapping("/update-status/{id}")
    public RoomStatus updateStatus(@PathVariable("id") final Long id, @RequestBody RoomStatus status){
        Optional<RoomStatus>  st = roomStatusService.getStatusById(id);
        if(st.isPresent()){
            RoomStatus currentStatus = st.get();
            String name = status.getName();
            if( name!= null ){
                currentStatus.setName(name.trim().toLowerCase());
            }


            roomStatusService.saveStatus(currentStatus);
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
        roomStatusService.deleteStatus(id);
    }
}
