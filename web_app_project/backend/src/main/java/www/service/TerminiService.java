package www.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import www.models.Instruktor;
import www.models.Predmet;
import www.models.Termin;
import www.repository.InstruktorRepository;
import www.repository.PredmetRepository;
import www.repository.TerminRepository;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TerminiService {

    @Autowired
    InstruktorRepository instruktorRepository;
    @Autowired
    TerminRepository terminRepository;

    public List<Termin> getInstructorsTerminiById(String id){
        Instruktor instruktor = instruktorRepository.findById(Long.parseLong(id)).get();
        return instruktor.getSlobodniTermini();
    }

    public List<Termin> getInstructorsTerminiByEmail(String email){
        Instruktor instruktor = instruktorRepository.findByEmail(email).get();
        return instruktor.getSlobodniTermini();
    }

    public boolean addNoviTermin(String year, String month, String day,
                                    String startHour, String startMinute, String endHour, String endMinute, String email) {

        Instruktor instruktor = instruktorRepository.findByEmail(email).get();

        try {
            int godina = Integer.parseInt(year);
            int mjesec = Integer.parseInt(month);
            int dan = Integer.parseInt(day);
            int pocSat = Integer.parseInt(startHour);
            int pocMin = Integer.parseInt(startMinute);
            int krajSat = Integer.parseInt(endHour);
            int krajMin = Integer.parseInt(endMinute);

            if(mjesec < 1 || mjesec > 12) return false;
            if(dan < 1 || dan > 31) return false;
            if(pocSat < 0 || pocSat > 22) return false;
            if(pocMin < 0 || pocMin > 59) return false;
            if(krajSat < 0 || krajSat > 24) return false;
            if(krajMin < 0 || krajMin > 59) return false;

            Date poc = new Date(godina - 1900, mjesec - 1, dan, pocSat, pocMin );
            Date kraj = new Date(godina - 1900, mjesec - 1, dan, krajSat, krajMin );
            List<Termin> existing = instruktor.getSlobodniTermini();
            for(Termin single: existing){
                if(single.getStartTime().compareTo(poc) < 0 && single.getEndTime().compareTo(poc) > 0){
                    return false;
                }
                if(single.getStartTime().compareTo(kraj) < 0 && single.getEndTime().compareTo(kraj) > 0){
                    return false;
                }

                if(single.getStartTime().compareTo(poc) == 0 && single.getEndTime().compareTo(kraj) == 0){
                    return false;
                }
            }
            Termin noviTermin = new Termin(poc, kraj, instruktor);
            terminRepository.save(noviTermin);
        } catch (Exception e) {
            return false;
        }
        return true;
    }



}
