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
    private Boolean isClosed;

    public Ordine(Long id) {
        this.id = id;
        this.isClosed = false;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }

    public LocalGregorianCalendar.Date getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalGregorianCalendar.Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public Boolean close(){
        this.isClosed = true;
        return true;
    }
}
