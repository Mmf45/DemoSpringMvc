package sn.esmt.isi.demospringmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Contrôleur pour la page d'accueil
 * Démontre l'utilisation de @Controller et @GetMapping
 */
@Controller
public class HomeController {
    
    /**
     * Page d'accueil de l'application
     * @param model le modèle pour passer des données à la vue
     * @return le nom de la vue à afficher
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("welcomeMessage", "Bienvenue dans l'application de gestion de tâches");
        model.addAttribute("description", "Application de démonstration Spring MVC");
        return "home";
    }
}
