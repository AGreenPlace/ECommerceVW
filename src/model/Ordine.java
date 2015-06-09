package model;

import sun.util.calendar.LocalGregorianCalendar;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Luca on 29/05/2015.
 */
public class Ordine {
    private Long id;
    private String name;
    private LocalGregorianCalendar.Date dataCreazione;
    private List<RigaOrdine> righeordine;


    public Boolean addProduct(Prodotto prodotto, Integer quantity) {
        if (this.righeordine == null)
            this.righeordine = new LinkedList<>();
        RigaOrdine rigaOrdine = new RigaOrdine(prodotto, quantity);
        this.righeordine.add(rigaOrdine);
        return true;
    }
    public List<RigaOrdine> getRigheordine() {
        return righeordine;
    }

    public void setRigheordine(List<RigaOrdine> righeordine) {
        this.righeordine = righeordine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
