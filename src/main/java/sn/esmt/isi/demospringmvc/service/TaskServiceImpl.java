package sn.esmt.isi.demospringmvc.service;

import org.springframework.stereotype.Service;
import sn.esmt.isi.demospringmvc.model.Task;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Implémentation du service de gestion des tâches
 * Utilise une HashMap en mémoire pour stocker les tâches (pour la démo)
 * En production, on utiliserait une base de données
 */
@Service
public class TaskServiceImpl implements TaskService {
    
    // Stockage en mémoire des tâches
    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    
    // Générateur d'ID auto-incrémenté
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    /**
     * Constructeur - Initialise quelques tâches de démonstration
     */
    public TaskServiceImpl() {
        // Ajout de données de démonstration
        Task task1 = new Task(idGenerator.getAndIncrement(), 
                "Préparer l'exposé Spring MVC", 
                "Créer les slides et préparer la démo de l'application");
        
        Task task2 = new Task(idGenerator.getAndIncrement(), 
                "Étudier les contrôleurs Spring", 
                "Comprendre @Controller, @RequestMapping, @GetMapping, @PostMapping");
        
        Task task3 = new Task(idGenerator.getAndIncrement(), 
                "Apprendre Thymeleaf", 
                "Maîtriser les templates et l'intégration avec Spring MVC");
        task3.setCompleted(true);
        
        tasks.put(task1.getId(), task1);
        tasks.put(task2.getId(), task2);
        tasks.put(task3.getId(), task3);
    }
    
    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values())
                .stream()
                .sorted(Comparator.comparing(Task::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Task> getTaskById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }
    
    @Override
    public Task createTask(Task task) {
        task.setId(idGenerator.getAndIncrement());
        tasks.put(task.getId(), task);
        return task;
    }
    
    @Override
    public Task updateTask(Long id, Task task) {
        if (!tasks.containsKey(id)) {
            throw new IllegalArgumentException("Tâche non trouvée avec l'ID: " + id);
        }
        task.setId(id);
        tasks.put(id, task);
        return task;
    }
    
    @Override
    public void deleteTask(Long id) {
        tasks.remove(id);
    }
    
    @Override
    public Task toggleTaskCompletion(Long id, boolean completed) {
        Task task = tasks.get(id);
        if (task == null) {
            throw new IllegalArgumentException("Tâche non trouvée avec l'ID: " + id);
        }
        task.setCompleted(completed);
        return task;
    }
}
