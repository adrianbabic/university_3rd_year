package www.responses;

import java.util.ArrayList;
import java.util.List;

import www.models.Instruktor;
import www.models.Termin;

public class TerminiResponse {

    List<Termin> termini = new ArrayList<>();

    public TerminiResponse(List<Termin> termini) {
        this.termini = termini;
    }

    public List<Termin> getdataSource() {
        return termini;
    }


    /**
     * @return returns positive int if sucessfully added to list, otherwise -1
     */
    public int addTermin(Termin termin) {
        if(termin == null)
            throw new NullPointerException("termin ne moze biti null");

        if(termini.contains(termin)) return -1;

        termini.add(termin);
        return 1;
    }

}