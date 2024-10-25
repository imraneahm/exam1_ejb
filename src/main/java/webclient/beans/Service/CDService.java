package webclient.beans.Service;

import webclient.entites.CD;
import webclient.entites.Emprunt;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.Date;
import java.util.List;

@Stateless
public class CDService {

    @PersistenceContext
    private EntityManager em;

    // Liste tous les CDs disponibles
    public List<CD> listAvailableCDs() {
        TypedQuery<CD> query = em.createQuery("SELECT c FROM CD c WHERE c.status = 'disponible'", CD.class);
        return query.getResultList();
    }

    // Emprunte un CD pour un utilisateur donné
    public boolean borrowCD(Long cdId, Long userId) {
        CD cd = em.find(CD.class, cdId);

        if (cd != null && "disponible".equals(cd.getStatus())) {
            // Met à jour le statut du CD
            cd.setStatus("emprunté");
            em.merge(cd);

            // Crée un nouvel emprunt pour l'utilisateur
            Emprunt emprunt = new Emprunt(userId, cd);
            em.persist(emprunt);

            return true;  // Emprunt réussi
        }

        return false;  // Emprunt échoué
    }

    // Liste les emprunts en cours d'un utilisateur
    public List<Emprunt> getUserEmprunts(Long userId) {
        TypedQuery<Emprunt> query = em.createQuery(
                "SELECT e FROM Emprunt e WHERE e.userId = :userId AND e.returnDate IS NULL", Emprunt.class
        );
        query.setParameter("userId", userId);

        return query.getResultList();
    }

    // Retourne un CD emprunté en fonction de son ID
    public boolean returnCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);

        if (cd != null && "emprunté".equals(cd.getStatus())) {
            // Met à jour le statut du CD
            cd.setStatus("disponible");
            em.merge(cd);

            // Récupère l'emprunt en cours pour ce CD
            TypedQuery<Emprunt> query = em.createQuery(
                    "SELECT e FROM Emprunt e WHERE e.cd.id = :cdId AND e.returnDate IS NULL", Emprunt.class
            );
            query.setParameter("cdId", cdId);

            try {
                Emprunt emprunt = query.getSingleResult();
                emprunt.setReturnDate(new Date());  // Définit la date de retour
                em.merge(emprunt);

                return true;  // Retour réussi
            } catch (Exception e) {
                // Gestion d'une erreur si aucun emprunt en cours n'est trouvé
                e.printStackTrace();
            }
        }

        return false;  // Retour échoué
    }

    public void addCD(CD cd) {
        em.persist(cd);
    }

    public void updateCD(CD cd) {
        em.merge(cd);
    }

    public void deleteCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null) {
            em.remove(cd);
        }
    }
}
