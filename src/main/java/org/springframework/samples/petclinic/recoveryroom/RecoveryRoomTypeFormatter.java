package org.springframework.samples.petclinic.recoveryroom;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class RecoveryRoomTypeFormatter implements Formatter<RecoveryRoomType>{

    RecoveryRoomService service;

    @Autowired
    public RecoveryRoomTypeFormatter(RecoveryRoomService rrservice){
        this.service = rrservice;
    }

    @Override
    public String print(RecoveryRoomType object, Locale locale) {
        return object.getName();
    }

    @Override
    public RecoveryRoomType parse(String text, Locale locale) throws ParseException {
        RecoveryRoomType rrtype = service.getRecoveryRoomType(text);
        if(rrtype == null){
            throw new ParseException("Recovery room typr not found: " + text, 0);
        }
        return rrtype;
    }
    
}
