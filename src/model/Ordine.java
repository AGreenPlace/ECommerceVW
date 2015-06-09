package model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Luca on 29/05/2015.
 */
public class Ordine {
    private List<RigaOrdine> righeordine;

    public List<RigaOrdine> getRigheordine() {
        return righeordine;
    }

    public void setRigheordine(List<RigaOrdine> righeordine) {
        this.righeordine = righeordine;
    }

    public Boolean addProduct(Prodotto prodotto, Integer quantity) {
        if (this.righeordine == null)
            this.righeordine = new LinkedList<>();
        RigaOrdine rigaOrdine = new RigaOrdine(prodotto, quantity);
        this.righeordine.add(rigaOrdine);
        return true;

    }
}
