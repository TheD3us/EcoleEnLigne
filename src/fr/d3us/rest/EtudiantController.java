package fr.d3us.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.d3us.bll.EtudiantBll;

import fr.d3us.bo.Etudiant;

@RestController
@RequestMapping("/etudiant")
public class EtudiantController {
	@Autowired
	private EtudiantBll bll;
	
	@GetMapping
	public ResponseEntity<List<Etudiant>> findAll() {
		return new ResponseEntity<List<Etudiant>>(bll.selectAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Etudiant> findById(@PathVariable("id") int id) {
		if (bll.exist(id)) {
			return new ResponseEntity<Etudiant>(bll.selectById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<Etudiant>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Etudiant> insert(@RequestBody Etudiant e) {
		try {
			bll.insert(e);
			return new ResponseEntity<Etudiant>(e, HttpStatus.OK);
		} catch (Exception e1) {
			return new ResponseEntity<Etudiant>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Etudiant> update(
											@PathVariable("id") int id,
											@RequestBody Etudiant e) {
		try {
			// Je recupere l'instance telle qu'elle existe en bdd
			Etudiant originalBDD = bll.selectById(id);
			
			originalBDD.setNom(e.getNom());
			originalBDD.setPrenom(e.getPrenom());

			
			bll.update(originalBDD);
			return new ResponseEntity<Etudiant>(e, HttpStatus.OK);
		} catch (Exception e1) {
			return new ResponseEntity<Etudiant>(HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		try {
			bll.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
}
