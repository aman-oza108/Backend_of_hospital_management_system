package com.HMSAPP.Hospital.Management.System.doclogin.controller;

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
import com.HMSAPP.Hospital.Management.System.doclogin.entity.Medicine;
import com.HMSAPP.Hospital.Management.System.doclogin.repository.MedicineRepository;
import com.HMSAPP.Hospital.Management.System.entity.Patient;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/three")


public class Medicinecontroller {

	
	MedicineRepository repo;

	public Medicinecontroller(MedicineRepository repo) {
		super();
		this.repo = repo;
	}
	
	@PostMapping("/insert")
	public Medicine createmedicine(@RequestBody Medicine medicine)
	{
		return repo.save(medicine);
	}
	
	@GetMapping
	public List<Medicine>getallmedicines()
	{
		return repo.findAll();
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteAppointment(@PathVariable long id) {
		Medicine patientmedi = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
		repo.delete(patientmedi);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);

	}
	@GetMapping("/medicine/{id}")
	public ResponseEntity<Medicine>getmedicinebyid(@PathVariable long id)
	{
		Medicine medicine=repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("medicine not found with id " + id));
		
		return ResponseEntity.ok(medicine);
	}
	
	

	@PutMapping ("/updated/{id}")
	public ResponseEntity<Medicine>updatemedicinebyid(@PathVariable long id, @RequestBody Medicine medicinedetails)
	{
		Medicine medicine = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("medicine not found with id " + id));
		medicine.setDrugName(medicinedetails.getDrugName());
		medicine.setStock(medicinedetails.getStock());
		
	
		
		Medicine savemedicine=repo.save(medicine);
		
		return ResponseEntity.ok(savemedicine);
	}
			
			
}
