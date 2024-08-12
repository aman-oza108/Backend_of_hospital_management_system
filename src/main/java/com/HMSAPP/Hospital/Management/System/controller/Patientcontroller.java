package com.HMSAPP.Hospital.Management.System.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HMSAPP.Hospital.Management.System.doclogin.controller.exception.ResourceNotFoundException;
import com.HMSAPP.Hospital.Management.System.doclogin.entity.Appointment;
import com.HMSAPP.Hospital.Management.System.entity.Patient;
import com.HMSAPP.Hospital.Management.System.repository.PatientRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class Patientcontroller {

	//@Autowired
	//private PatientRepository repo;
	
	//constructor dependency injection
	private PatientRepository repo;

	public Patientcontroller(PatientRepository repo) {
		super();
		this.repo = repo;
	}
	
	@PostMapping("/insert")
	public Patient createpatient( @RequestBody  Patient patient)
	{
		return repo.save(patient);
	}
	
	@GetMapping
	public List<Patient>getallpaitents()
	{
		return repo.findAll();
	}
	
	@GetMapping("/patients/{id}")
	public ResponseEntity<Patient>getpatientbyid(@PathVariable long id)
	{
		Patient patient=repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
		
		return ResponseEntity.ok(patient);
		
	}
	
	
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteAppointment(@PathVariable long id) {
		Patient patient = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
		repo.delete(patient);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);

	}
	
	@PutMapping ("/updated/{id}")
	public ResponseEntity<Patient>updatepatientbyid(@PathVariable long id, @RequestBody Patient patientdetails)
	{
		Patient patient = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
		patient.setAge(patientdetails.getAge());
		patient.setName(patientdetails.getName());
		patient.setBlood(patientdetails.getBlood());
		patient.setDose(patientdetails.getDose());
		patient.setFees(patientdetails.getFees());
		patient.setPrescription(patientdetails.getPrescription());
		patient.setUrgency(patientdetails.getUrgency());
	
		
		Patient savepatient=repo.save(patient);
		
		return ResponseEntity.ok(savepatient);
	}
	
	
	
	
	
	
}
