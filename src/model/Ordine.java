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
@Entity
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @Column(nullable=false)
    private Date dataCreazione;
    private Date dataSpedizione;
    @OneToMany(fetch =FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "order")
    private List<RigaOrdine> righeordine;
    @Column(nullable=false)
    private Integer state;
    @Column(nullable=false)
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Cliente ordersClient;

    public Ordine() {
        this.state = 0;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
        this.state = 1;
        return true;
    }

    public Boolean validate(){
        this.state = 2;
        return true;
    }
}
