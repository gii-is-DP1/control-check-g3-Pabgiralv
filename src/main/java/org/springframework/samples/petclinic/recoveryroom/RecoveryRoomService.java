package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecoveryRoomService {
	
	
	private RecoveryRoomRepository rrRepo;
	
	@Autowired
	public RecoveryRoomService(RecoveryRoomRepository rrRepo) {
		super();
		this.rrRepo = rrRepo;
	}

	
    public List<RecoveryRoom> getAll(){
        return rrRepo.findAll();
    }

    public List<RecoveryRoomType> getAllRecoveryRoomTypes(){
        return rrRepo.findAllRecoveryRoomTypes();
    }

    public RecoveryRoomType getRecoveryRoomType(String typeName) {
        return rrRepo.getRecoveryRoomType(typeName);
    }

    public List<RecoveryRoom> getRecoveryRoomsBiggerThan(double size) {
        return rrRepo.findBySizeMoreThan(size);
    }

    @Transactional
    public RecoveryRoom save(RecoveryRoom p) {
        return rrRepo.save(p);       
    }

    
}
