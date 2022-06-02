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

import fr.d3us.bll.AdministrateurBll;
import fr.d3us.bo.Administrateur;


@RestController
@RequestMapping("/administrateur")
public class AdministrateurController {
	@Autowired
	private AdministrateurBll bll;
	
	@GetMapping
	public ResponseEntity<List<Administrateur>> findAll() {
		return new ResponseEntity<List<Administrateur>>(bll.selectAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Administrateur> findById(@PathVariable("id") int id) {
		if (bll.exist(id)) {
			return new ResponseEntity<Administrateur>(bll.selectById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<Administrateur>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Administrateur> insert(@RequestBody Administrateur a) {
		try {
			bll.insert(a);
			return new ResponseEntity<Administrateur>(a, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Administrateur>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Administrateur> update(
											@PathVariable("id") int id,
											@RequestBody Administrateur a) {
		try {
			// Je recupere l'instance telle qu'elle existe en bdd
			Administrateur originalBDD = bll.selectById(id);
			
			originalBDD.setNom(a.getNom());
			originalBDD.setPrenom(a.getPrenom());

			
			bll.update(originalBDD);
			return new ResponseEntity<Administrateur>(a, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Administrateur>(HttpStatus.CONFLICT);
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
