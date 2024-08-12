package com.HMSAPP.Hospital.Management.System.doclogin.controller;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.HashMap;
import javax.management.AttributeNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HMSAPP.Hospital.Management.System.doclogin.controller.exception.ResourceNotFoundException;
import com.HMSAPP.Hospital.Management.System.doclogin.entity.Appointment;
import com.HMSAPP.Hospital.Management.System.doclogin.repository.AppointmentRepository;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/two")
public class Appointmentcontroller {

	AppointmentRepository repo;

	public Appointmentcontroller(AppointmentRepository repo) {
		super();
		this.repo = repo;
	}

	// @CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/insert")
	public Appointment createappointment(@RequestBody Appointment appointment) {
		return repo.save(appointment);
	}

	@GetMapping
	public List<Appointment> getallappointments() {
		return repo.findAll();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteAppointment(@PathVariable long id) {
		Appointment appointment = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id " + id));
		repo.delete(appointment);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);

	}
}
