package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecoveryRoomRepository extends CrudRepository<RecoveryRoom, Integer>{
   
	List<RecoveryRoom> findAll();
    
    @Query("SELECT RecoveryRoomType FROM RecoveryRoomType RecoveryRoomType")
    List<RecoveryRoomType> findAllRecoveryRoomTypes();
    
    @Query("SELECT RecoveryRoomType FROM RecoveryRoomType RecoveryRoomType WHERE name = :name")
    RecoveryRoomType getRecoveryRoomType(String name);
    
    Optional<RecoveryRoom> findById(int id);
        
    @Query("SELECT RecoveryRoom FROM RecoveryRoom RecoveryRoom WHERE size > :size")
    List<RecoveryRoom> findBySizeMoreThan(double size);
}
