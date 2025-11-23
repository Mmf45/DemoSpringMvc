package sn.esmt.isi.demospringmvc.service;

import sn.esmt.isi.demospringmvc.model.Task;
import java.util.List;
import java.util.Optional;

/**
 * Interface du service de gestion des tâches
 * Définit les opérations métier pour les tâches
 */
public interface TaskService {
    
    /**
     * Récupère toutes les tâches
     * @return liste de toutes les tâches
     */
    List<Task> getAllTasks();
    
    /**
     * Récupère une tâche par son ID
     * @param id l'identifiant de la tâche
     * @return Optional contenant la tâche si trouvée
     */
    Optional<Task> getTaskById(Long id);
    
    /**
     * Crée une nouvelle tâche
     * @param task la tâche à créer
     * @return la tâche créée avec son ID
     */
    Task createTask(Task task);
    
    /**
     * Met à jour une tâche existante
     * @param id l'identifiant de la tâche à mettre à jour
     * @param task les nouvelles données de la tâche
     * @return la tâche mise à jour
     */
    Task updateTask(Long id, Task task);
    
    /**
     * Supprime une tâche
     * @param id l'identifiant de la tâche à supprimer
     */
    void deleteTask(Long id);
    
    /**
     * Marque une tâche comme complétée ou non
     * @param id l'identifiant de la tâche
     * @param completed le statut de complétion
     * @return la tâche mise à jour
     */
    Task toggleTaskCompletion(Long id, boolean completed);
}
