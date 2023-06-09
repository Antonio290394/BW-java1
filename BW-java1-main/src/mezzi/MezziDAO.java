package mezzi;

import JPA_util.JpaUtil;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import main.Main;
import tratte.Tratta;
import tratte.TratteDAO;

public class MezziDAO {

    static EntityManager em = JpaUtil.getEmf().createEntityManager();

    public static void main(String[] args) {
        try {
            List<ParcoMezzi> listaMezzi;

            Tratta T1 = new Tratta();
            T1.setNome_tratta("Ro-Mi");
            T1.setPartenza("Roma");
            T1.setCapolinea("Milano");
            // TratteDAO.addTratta(T1);

            ParcoMezzi P1 = new ParcoMezzi();
            P1.setTipo_mezzo(TipoMezzo.TRAM);
            P1.setNome("Tram1");
            P1.setIn_servizio(true);
            P1.setCapienza(100);
            P1.setTratta(T1);
            // addMezzo(P1);

            ParcoMezzi P2 = new ParcoMezzi();
            P2.setTipo_mezzo(TipoMezzo.BUS);
            P2.setNome("Bus1");
            P2.setIn_servizio(true);
            P2.setCapienza(100);
            P2.setTratta(T1);
            // addMezzo(P2);

            //creaNuovoMezzo();

            listaMezzi = findAllMezzi();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    /*public static void creaNuovoMezzo() {
    	System.out.println("Quale mezzo vuoi creare?");
    	System.out.println("1 - per aggiungere un BUS");
    	System.out.println("2 - per aggiungere un TRAM");
    	int scelta = Main.scanner.nextInt();
    	TipoMezzo tipoMezzo;
    	switch (scelta) {
    		case 1:
    			tipoMezzo = TipoMezzo.BUS;
    			System.out.println("Inserisci il nome o il numero del mezzo");
    			String nomeMezzo = Main.scanner.next();
    			System.out.println("Inserisci la capienza");
    			int capienza = Main.scanner.nextInt();
    			System.out.println("Il mezzo è in servizio?");
    			System.out.println("1 - in servizio");
    			System.out.println("2 - in manutenzione");
    			int sceltaServizio = Main.scanner.nextInt();
    			switch (sceltaServizio) {
    				case 1:
    					ParcoMezzi mezzo1 = new ParcoMezzi(tipoMezzo, nomeMezzo, true, capienza);
    					addMezzo(mezzo1);
    					break;
    				case 2:
    					System.out.println("Inserisci la data di inizio manutenzione");
    					System.out.println("Giorno");
    					int giorno = Main.scanner.nextInt();
    					System.out.println("Mese");
    					int mese = Main.scanner.nextInt();
    					System.out.println("Anno");
    					int anno = Main.scanner.nextInt();
    					LocalDate dataManutenzione = LocalDate.of(anno, mese, giorno);
    					ParcoMezzi mezzo2 = new ParcoMezzi(tipoMezzo, nomeMezzo, false, dataManutenzione, capienza);
    					mezzo2.setIn_manutenzione(true);
    					addMezzo(mezzo2);
    					break;
    				default:
    					System.out.println("La scelta non è corretta");
    					break;
    			}
    			break;
    		case 2:
    			tipoMezzo = TipoMezzo.TRAM;
    			System.out.println("Inserisci il nome o il numero del mezzo");
    			nomeMezzo = Main.scanner.next();
    			System.out.println("Inserisci la capienza");
    			capienza = Main.scanner.nextInt();
    			System.out.println("Il mezzo è in servizio?");
    			System.out.println("1 - in servizio");
    			System.out.println("2 - in manutenzione");
    			sceltaServizio = Main.scanner.nextInt();
    			switch (sceltaServizio) {
    				case 1:
    					ParcoMezzi mezzo1 = new ParcoMezzi(tipoMezzo, nomeMezzo, true, capienza);
    					addMezzo(mezzo1);
    					break;
    				case 2:
    					System.out.println("Inserisci la data di inizio manutenzione");
    					System.out.println("Giorno");
    					int giorno = Main.scanner.nextInt();
    					System.out.println("Mese");
    					int mese = Main.scanner.nextInt();
    					System.out.println("Anno");
    					int anno = Main.scanner.nextInt();
    					LocalDate dataManutenzione = LocalDate.of(anno, mese, giorno);
    					ParcoMezzi mezzo2 = new ParcoMezzi(tipoMezzo, nomeMezzo, false, dataManutenzione, capienza);
    					mezzo2.setIn_manutenzione(true);
    					addMezzo(mezzo2);
    					break;
    				default:
    					System.out.println("La scelta non è corretta");
    					break;
    			}
    			break;
    		default:
    			System.out.println("La scelta non è corretta");
    			break;
    	}
    }*/


    // Metodo per aggiungere un nuovo mezzo al parco mezzi
    public static void addMezzo(ParcoMezzi t) {
        EntityManager em = JpaUtil.getEmf().createEntityManager(); 
        try {
            em.getTransaction().begin(); 
            em.persist(t); 
            em.getTransaction().commit(); 
            System.out.println("Ben fatto! Aggiunto nuovo mezzo"); 
        } catch (Exception e) {
            em.getTransaction().rollback(); 
            e.printStackTrace();
        } finally {
            em.close(); 
        }
    }

    // Metodo per eliminare un mezzo dal parco mezzi
    public static void dellMezzo(ParcoMezzi t) {
        em.getTransaction().begin(); 
        em.remove(t); 
        em.getTransaction().commit(); 
        System.out.println("Mezzo eliminato"); 
    }

    // Metodo per trovare un mezzo dato l'ID
    public static ParcoMezzi findMezziByID(Long id) {
        Query q = em.createNamedQuery("findMezziById"); 
        q.setParameter("id", id); 
        return (ParcoMezzi) q.getSingleResult(); 
    }

    // Metodo per trovare tutti i mezzi nel parco mezzi
    public static List<ParcoMezzi> findAllMezzi() {
        Query q = em.createNamedQuery("parco_mezzi.findAll"); 
        return q.getResultList(); 
    }
}
