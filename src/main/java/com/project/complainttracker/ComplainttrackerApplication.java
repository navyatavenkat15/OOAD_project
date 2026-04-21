// ================= APPLICATION ENTRY POINT =================
// This is the main class that bootstraps the Spring Boot application.
//
// Creational Patterns:
// - Singleton Pattern: Spring creates a single instance of this application context
// - Factory Pattern: Spring IoC container creates and manages all beans
//
// Structural Patterns:
// - Acts as the root configuration for MVC architecture
//
// GRASP:
// - High Cohesion: Responsible only for starting the application
//
// SOLID:
// - SRP (Single Responsibility Principle): Only initializes the application
// ==========================================================
package com.project.complainttracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ComplainttrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComplainttrackerApplication.class, args);
	}

}