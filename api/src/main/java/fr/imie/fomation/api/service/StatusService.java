package fr.imie.fomation.api.service;

import fr.imie.fomation.api.model.Status;
import fr.imie.fomation.api.repository.StatusRepository;
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
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public Optional<Status> getStatus(final Long id){
        return statusRepository.findById(id);
    }
    public Iterable<Status> getStatus() {
        return statusRepository.findAll();
    }

    public void deleteStatus(final Long id) {
        statusRepository.deleteById(id);
    }
    public Status saveStatus(Status status){
        return statusRepository.save(status);
    }

}
