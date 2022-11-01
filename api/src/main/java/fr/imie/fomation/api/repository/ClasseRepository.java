/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fr.imie.fomation.api.repository;

import fr.imie.fomation.api.model.Classe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jason
 */
@Repository
public interface ClasseRepository extends CrudRepository<Classe, Long> {
}
