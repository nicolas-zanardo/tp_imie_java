package fr.imie.fomation.api.service;

import fr.imie.fomation.api.model.RoomStatus;
import fr.imie.fomation.api.repository.RoomStatusRepository;
import java.util.Optional;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author raouf
 */

@Data
@Service
public class RoomStatusService {

    @Autowired
    private RoomStatusRepository statusRepository;

   // GET ALL STATUSES
   public Iterable<RoomStatus> getStatuses() {return statusRepository.findAll();}

    public Optional<RoomStatus> getStatusById(final Long id){
        return statusRepository.findById(id);
    }
    public RoomStatus saveStatus(RoomStatus status){return statusRepository.save(status);}
    public void deleteStatus(final Long id) {
        statusRepository.deleteById(id);
    }

}
