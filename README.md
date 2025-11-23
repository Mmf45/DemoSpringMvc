# Application To-Do - Démonstration Spring MVC

![Home Page]
<img width="1904" height="912" alt="image" src="https://github.com/user-attachments/assets/5d2025ce-c6fd-4065-a22e-7580503ab94a" />


## 📋 Description

Cette application de gestion de tâches (To-Do App) est une démonstration pédagogique complète de **Spring MVC**. Elle illustre tous les concepts fondamentaux de l'architecture MVC dans le contexte de Spring Boot.

### 🎯 Objectifs pédagogiques

Cette application démontre :

1. **Introduction à Spring MVC** - Architecture et principes de base
2. **Architecture MVC** - Séparation claire des responsabilités
3. **Contrôleurs** - Gestion des requêtes HTTP
4. **Vues** - Rendu avec Thymeleaf
5. **Modèles** - Représentation des données
6. **Configuration** - Configuration Spring Boot et MVC

## 🏗️ Architecture

L'application suit le pattern MVC (Model-View-Controller) :

```
┌─────────────┐
│  Vue (View) │  ← Templates Thymeleaf (HTML)
└──────┬──────┘
       │
┌──────▼──────────────┐
│ Contrôleur          │  ← @Controller, @GetMapping, @PostMapping
│ (Controller)        │
└──────┬──────────────┘
       │
┌──────▼──────────────┐
│ Service Layer       │  ← Logique métier
└──────┬──────────────┘
       │
┌──────▼──────────────┐
│ Modèle (Model)      │  ← Classes POJO (Task)
└─────────────────────┘
```

## 📁 Structure du projet

```
src/main/java/sn/esmt/isi/demospringmvc/
├── controller/           # Contrôleurs MVC
│   ├── HomeController.java
│   └── TaskController.java
├── model/               # Modèles de données
│   └── Task.java
├── service/             # Couche service (logique métier)
│   ├── TaskService.java
│   └── TaskServiceImpl.java
└── DemoSpringMvcApplication.java

src/main/resources/
├── templates/           # Vues Thymeleaf
│   ├── home.html
│   └── tasks/
│       ├── list.html
│       ├── form.html
│       └── view.html
└── static/
    └── css/
        └── style.css
```

## 🔑 Concepts Spring MVC démontrés

### 1. Contrôleurs (@Controller)

```java
@Controller
@RequestMapping("/tasks")
public class TaskController {
    // Gestion des requêtes HTTP
}
```

**Annotations utilisées :**
- `@Controller` : Marque la classe comme un contrôleur Spring MVC
- `@RequestMapping` : Définit le chemin de base pour toutes les méthodes
- `@GetMapping` : Traite les requêtes HTTP GET
- `@PostMapping` : Traite les requêtes HTTP POST
- `@PathVariable` : Extrait des variables de l'URL
- `@ModelAttribute` : Lie les paramètres de formulaire à un objet

### 2. Modèles (Model)

```java
public class Task {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // getters, setters...
}
```

### 3. Vues (Thymeleaf)

Templates HTML avec expressions Thymeleaf :
```html
<div th:each="task : ${tasks}">
    <h5 th:text="${task.title}">Titre</h5>
    <p th:text="${task.description}">Description</p>
</div>
```

### 4. Service Layer

```java
@Service
public class TaskServiceImpl implements TaskService {
    // Logique métier
}
```

### 5. Injection de dépendances

```java
@Autowired
public TaskController(TaskService taskService) {
    this.taskService = taskService;
}
```

## 🚀 Fonctionnalités

L'application offre les opérations CRUD complètes :

- ✅ **Créer** une nouvelle tâche
- 📖 **Lire** la liste des tâches et voir les détails
- ✏️ **Modifier** une tâche existante
- 🗑️ **Supprimer** une tâche
- ✔️ **Marquer** une tâche comme terminée/en cours

## 📸 Captures d'écran

### Page d'accueil
![Home Page]<img width="1902" height="914" alt="image" src="https://github.com/user-attachments/assets/0873a60d-ab8e-4654-9793-bc63301ad261" />


### Liste des tâches
![Tasks List]<img width="1919" height="916" alt="Capture d&#39;écran 2025-11-23 171425" src="https://github.com/user-attachments/assets/521c9bf3-66f7-43ec-b922-2889b0aac138" />


### Formulaire de création
![Create Task]<img width="1900" height="919" alt="Capture d&#39;écran 2025-11-23 171526" src="https://github.com/user-attachments/assets/afd69d06-cb0e-480e-98b0-1e58075fa78e" />


### Détails d'une tâche
![Task Details]<img width="1903" height="908" alt="Capture d&#39;écran 2025-11-23 171608" src="https://github.com/user-attachments/assets/a4044a21-e545-42a8-aee5-9b3e85dd7a87" />


## 🛠️ Technologies utilisées

- **Spring Boot 4.0.0** - Framework principal
- **Spring MVC** - Framework web MVC
- **Thymeleaf** - Moteur de templates pour les vues
- **Bootstrap 5.3** - Framework CSS pour le design
- **Bootstrap Icons** - Icônes
- **Maven** - Gestion des dépendances
- **Java 17** - Langage de programmation

## 📦 Installation et lancement

### Prérequis

- Java 17 ou supérieur
- Maven 3.6 ou supérieur

### Étapes

1. **Cloner le projet**
```bash
git clone <repository-url>
cd DemoSpringMvc
```

2. **Compiler le projet**
```bash
./mvnw clean compile
```

3. **Lancer l'application**
```bash
./mvnw spring-boot:run
```

4. **Accéder à l'application**
```
http://localhost:8080
```

### Exécuter les tests

```bash
./mvnw test
```

## 🎓 Points d'apprentissage pour l'exposé

### 1. Architecture MVC
- Séparation des préoccupations (Separation of Concerns)
- Chaque couche a une responsabilité unique et bien définie
- Facilite la maintenance et les tests

### 2. Contrôleurs Spring
- Point d'entrée de l'application
- Gèrent les requêtes HTTP et les réponses
- Orchestrent l'interaction entre le modèle et la vue

### 3. Mapping des URLs
- `@GetMapping("/tasks")` → Liste des tâches
- `@GetMapping("/tasks/{id}")` → Détails d'une tâche
- `@PostMapping("/tasks")` → Création d'une tâche
- `@PostMapping("/tasks/{id}")` → Mise à jour d'une tâche

### 4. Flux d'une requête

```
Navigateur → Contrôleur → Service → Modèle
                ↓
          Vue (Template)
                ↓
            Navigateur
```

### 5. Avantages de Spring MVC
- Configuration simplifiée avec Spring Boot
- Injection de dépendances automatique
- Testabilité facile
- Support de nombreux moteurs de templates
- Gestion des erreurs intégrée

## 📝 Exemples de code clés

### Créer une tâche (POST)

```java
@PostMapping
public String createTask(@ModelAttribute Task task, RedirectAttributes redirectAttributes) {
    Task createdTask = taskService.createTask(task);
    redirectAttributes.addFlashAttribute("successMessage", 
            "Tâche créée avec succès: " + createdTask.getTitle());
    return "redirect:/tasks";
}
```

### Afficher une liste (GET + Model)

```java
@GetMapping
public String listTasks(Model model) {
    model.addAttribute("tasks", taskService.getAllTasks());
    model.addAttribute("pageTitle", "Liste des tâches");
    return "tasks/list";
}
```

### Template Thymeleaf

```html
<div th:each="task : ${tasks}">
    <h5 th:text="${task.title}">Titre</h5>
    <span th:if="${task.completed}" class="badge bg-success">Terminée</span>
</div>
```

## 🔧 Configuration

L'application utilise les configurations par défaut de Spring Boot :
- Port : 8080
- Context path : /
- Thymeleaf cache : désactivé en développement (via DevTools)

## 🤝 Contribution

Cette application est à des fins pédagogiques. N'hésitez pas à la modifier et à l'améliorer pour vos besoins.

## 📄 Licence

Projet de démonstration pédagogique - Libre d'utilisation

## 👨‍💻 Auteur

Application de démonstration Spring MVC pour exposé pédagogique

---

**Note** : Cette application utilise un stockage en mémoire (HashMap). Les données sont perdues au redémarrage de l'application. Pour une application en production, il faudrait utiliser une base de données persistante (H2, PostgreSQL, MySQL, etc.).
