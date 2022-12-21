package org.springframework.samples.petclinic.recoveryroom;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class RecoveryRoomTypeFormatter implements Formatter<RecoveryRoomType>{

    private final RecoveryRoomService service;

    @Autowired
    public RecoveryRoomTypeFormatter(RecoveryRoomService s){
        this.service = s;
    }

    @Override
    public String print(RecoveryRoomType object, Locale locale) {
        return object.name;
    }

    @Override
    public RecoveryRoomType parse(String text, Locale locale) throws ParseException {
        RecoveryRoomType rrt = service.getRecoveryRoomType(text);

        if(rrt == null){
            throw new ParseException("Recovery room type not found: "+ text, 0);
        }
        return rrt;
    }
    
}
