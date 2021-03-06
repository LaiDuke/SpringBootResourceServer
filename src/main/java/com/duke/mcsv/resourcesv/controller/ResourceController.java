package com.duke.mcsv.resourcesv.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duke.mcsv.resourcesv.model.CustomPrincipal;

@RestController
public class ResourceController {

	@GetMapping("/admins")
	@PreAuthorize("hasAuthority('role_admin')")
	public String context() {
		CustomPrincipal principal = (CustomPrincipal) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return principal.getUsername() + " " + principal.getEmail();
	}

	@GetMapping("/users")
	@PreAuthorize("hasAnyAuthority('role_admin','role_user')")
	public String secured(CustomPrincipal principal) {
		return principal.getUsername() + " " + principal.getEmail();
	}

	@GetMapping("/common")
	@PreAuthorize("isAuthenticated()")
	public String general() {
		return "common api success";
	}
	
}
