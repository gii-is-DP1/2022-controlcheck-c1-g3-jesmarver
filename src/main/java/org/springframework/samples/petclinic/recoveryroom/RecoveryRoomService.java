package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecoveryRoomService {

    public final RecoveryRoomRepository repo;


    @Autowired
    public RecoveryRoomService(RecoveryRoomRepository repository){
        this.repo = repository;

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

    public RecoveryRoom save(RecoveryRoom p) throws DuplicatedRoomNameException {
        // List<RecoveryRoom> rooms = repo.findAll();
        // for(RecoveryRoom room: rooms){
        //     if(p.name == room.getName()){
        //         throw new Exception("");
        //     }

        // }
        
        return repo.save(p);       
    }

    
}
