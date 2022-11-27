package fr.imie.webapp.service;


import fr.imie.webapp.model.RoomStatus;
import fr.imie.webapp.repository.RoomStatusProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author raouf
 */
@Data
@Service
public class RoomStatusService {

    @Autowired
    private RoomStatusProxy statusProxy;


    public Iterable<RoomStatus> getStatuses() {return  statusProxy.getStatuses();}

    public RoomStatus getStatus (final int id){return statusProxy.getStatus(id);}

    public void saveStatus(RoomStatus status){
        RoomStatus savedStatus = null;
        if(status.getId() == null){
            savedStatus = statusProxy.createStatus(status);
        }else {
            savedStatus = statusProxy.updateStatus(status);

        }

    }

    public void deleteStatus(final int id){ statusProxy.deleteStatus(id);}
}
