package titoli;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import negozi.PuntiVendita;
import tessere.Tessera;

@Entity
public class Abbonamento extends Titolo {
    
    @Column
    private LocalDate data_inizio;
    
    @Column
    private LocalDate data_scadenza;
    
    @Enumerated(EnumType.STRING)
    @Column
    private TipoAbbonamento durata;
    
    @OneToOne
    @JoinColumn(name = "tessera_abbonamento")
    private Tessera tessera;
    
    public Abbonamento() {}

    public Abbonamento(LocalDate data_emissione, LocalDate data_inizio, TipoAbbonamento durata, PuntiVendita punto_vendita) {
        super(data_emissione, punto_vendita);
        this.data_inizio = data_inizio;
        this.durata = durata;
        setData_inizio(data_inizio, durata);
    }

    public LocalDate getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(LocalDate data, TipoAbbonamento durata) {
        this.data_inizio = data;

        LocalDate dataScadenza = null;

        if (durata == TipoAbbonamento.SETTIMANALE) {
            dataScadenza = data.plusWeeks(1);
        } else if (durata == TipoAbbonamento.MENSILE) {
            dataScadenza = data.plusMonths(1);
        }

        this.data_scadenza = dataScadenza;
    }

    public void setData_scadenza(LocalDate data_scadenza) {
        this.data_scadenza = data_scadenza;
    }

    public LocalDate getData_scadenza() {
        return data_scadenza;
    }

    public TipoAbbonamento getDurata() {
        return durata;
    }

    public void setDurata(TipoAbbonamento durata) {
        this.durata = durata;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    @Override
    public String toString() {
        return "Abbonamento [getData_emissione()=" + getData_emissione() + "getData_inizio()=" + getData_inizio() + ", getData_scadenza()=" + getData_scadenza()
                + ", getDurata()=" + getDurata() + "]";
    }
}

