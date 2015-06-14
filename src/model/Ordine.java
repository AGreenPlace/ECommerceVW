package model;

import sun.util.calendar.LocalGregorianCalendar;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Luca on 29/05/2015.
 */
/*@Entity*/
public class Ordine {
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)*/
    private Long id;
    /*@Column(nullable=false)*/
    private String name;
    /*@Column(nullable=false)*/
    private Date dataCreazione;
    private Date dataSpedizione;
    /*@OneToMany*/
    private List<RigaOrdine> righeordine;
    /*@Column(nullable=false)*/
    private Boolean isClosed;
    /*@Column(nullable=false)
    @ManyToOne*/
    private Cliente ordersClient;

    public Ordine(Long id) {
        this.id = id;
        this.isClosed = false;
    }

    public Ordine() {
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

    public Date getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public Cliente getOrdersClient() {
        return ordersClient;
    }

    public void setOrdersClient(Cliente ordersClient) {
        this.ordersClient = ordersClient;
    }


    public Date getDataSpedizione() {
        return dataSpedizione;
    }

    public void setDataSpedizione() {
        this.dataSpedizione = Calendar.getInstance().getTime();
    }

    public Boolean close(){
        this.isClosed = true;
        return true;
    }
}
