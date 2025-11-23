package sn.esmt.isi.demospringmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sn.esmt.isi.demospringmvc.model.Task;
import sn.esmt.isi.demospringmvc.service.TaskService;

/**
 * Contrôleur principal pour la gestion des tâches
 * Démontre tous les concepts clés de Spring MVC:
 * - @Controller : marque la classe comme un contrôleur Spring MVC
 * - @RequestMapping : définit le chemin de base pour toutes les méthodes
 * - @GetMapping / @PostMapping : gère les requêtes HTTP GET et POST
 * - Model : transmet des données du contrôleur vers la vue
 * - @PathVariable : extrait des variables de l'URL
 * - @ModelAttribute : lie les paramètres de formulaire à un objet
 */
@Controller
@RequestMapping("/tasks")
public class TaskController {
    
    private final TaskService taskService;
    
    /**
     * Injection de dépendance via le constructeur
     * @param taskService le service de gestion des tâches
     */
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    /**
     * Affiche la liste de toutes les tâches
     * URL: GET /tasks
     */
    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("pageTitle", "Liste des tâches");
        return "tasks/list";
    }
    
    /**
     * Affiche les détails d'une tâche spécifique
     * URL: GET /tasks/{id}
     */
    @GetMapping("/{id}")
    public String viewTask(@PathVariable Long id, Model model) {
        return taskService.getTaskById(id)
                .map(task -> {
                    model.addAttribute("task", task);
                    return "tasks/view";
                })
                .orElse("redirect:/tasks");
    }
    
    /**
     * Affiche le formulaire de création d'une nouvelle tâche
     * URL: GET /tasks/new
     */
    @GetMapping("/new")
    public String newTaskForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("pageTitle", "Nouvelle tâche");
        model.addAttribute("isEdit", false);
        return "tasks/form";
    }
    
    /**
     * Traite la soumission du formulaire de création d'une tâche
     * URL: POST /tasks
     */
    @PostMapping
    public String createTask(@ModelAttribute Task task, RedirectAttributes redirectAttributes) {
        Task createdTask = taskService.createTask(task);
        redirectAttributes.addFlashAttribute("successMessage", 
                "Tâche créée avec succès: " + createdTask.getTitle());
        return "redirect:/tasks";
    }
    
    /**
     * Affiche le formulaire de modification d'une tâche existante
     * URL: GET /tasks/{id}/edit
     */
    @GetMapping("/{id}/edit")
    public String editTaskForm(@PathVariable Long id, Model model) {
        return taskService.getTaskById(id)
                .map(task -> {
                    model.addAttribute("task", task);
                    model.addAttribute("pageTitle", "Modifier la tâche");
                    model.addAttribute("isEdit", true);
                    return "tasks/form";
                })
                .orElse("redirect:/tasks");
    }
    
    /**
     * Traite la soumission du formulaire de modification d'une tâche
     * URL: POST /tasks/{id}
     */
    @PostMapping("/{id}")
    public String updateTask(@PathVariable Long id, 
                            @ModelAttribute Task task, 
                            RedirectAttributes redirectAttributes) {
        try {
            Task updatedTask = taskService.updateTask(id, task);
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Tâche mise à jour avec succès: " + updatedTask.getTitle());
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/tasks";
    }
    
    /**
     * Supprime une tâche
     * URL: POST /tasks/{id}/delete
     */
    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        taskService.deleteTask(id);
        redirectAttributes.addFlashAttribute("successMessage", "Tâche supprimée avec succès");
        return "redirect:/tasks";
    }
    
    /**
     * Change le statut de complétion d'une tâche
     * URL: POST /tasks/{id}/toggle
     */
    @PostMapping("/{id}/toggle")
    public String toggleTaskCompletion(@PathVariable Long id, 
                                      @RequestParam boolean completed,
                                      RedirectAttributes redirectAttributes) {
        try {
            taskService.toggleTaskCompletion(id, completed);
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Statut de la tâche mis à jour");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/tasks";
    }
}
