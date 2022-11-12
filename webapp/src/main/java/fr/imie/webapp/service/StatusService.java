package fr.imie.webapp.service;


import fr.imie.webapp.model.Status;
import fr.imie.webapp.repository.StatusProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author raouf
 */
@Data
@Service
public class StatusService {

    @Autowired
    private StatusProxy statusProxy;


    public void saveStatus(Status status){
        Status savedStatus = null;
        if(status.getId() == null){
            savedStatus = statusProxy.createStatus(status);
        }else {
            savedStatus = statusProxy.updateStatus(status);

        }

    }


    public Iterable<Status> getStatuses() {return  statusProxy.getStatuses();}

    public Status getStatus (final int id){return statusProxy.getStatus(id);}

    public void deleteStatus(final int id){ statusProxy.deleteStatus(id);}
}
