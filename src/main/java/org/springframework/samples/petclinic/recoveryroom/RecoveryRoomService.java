package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecoveryRoomService {

    private final RecoveryRoomRepository repo;

    public RecoveryRoomService(RecoveryRoomRepository rep){
        this.repo = rep;
    }

    public List<RecoveryRoom> getAll(){
        return repo.findAll();
    }

    public List<RecoveryRoomType> getAllRecoveryRoomTypes(){
        return repo.findAllRecoveryRoomTypes();
    }

    public RecoveryRoomType getRecoveryRoomType(String typeName) {
        return repo.getRecoveryRoomType(typeName);
    }

    @Transactional(rollbackFor = DuplicatedRoomNameException.class)
    public RecoveryRoom save(RecoveryRoom p) throws DuplicatedRoomNameException {
        
        List<RecoveryRoom> recoveryRooms = repo.findAll();

        if(recoveryRooms.stream().anyMatch(t -> t.roomType.equals(p.roomType) && t.name.equals(p.name))){
            throw new DuplicatedRoomNameException();
            
        }else{
            repo.save(p);
        }

        return  p;
    }

    
}
