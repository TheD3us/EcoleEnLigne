package fr.d3us.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.d3us.bo.Etudiant;
import fr.d3us.dal.EtudiantDAO;



@Service
public class EtudiantBll {
	@Autowired
	private EtudiantDAO dao;
	
	public List<Etudiant> selectAll() {
		return dao.findAll();
	}
	
	public Etudiant selectById(int id) {
		return dao.findById(id).get();
	}
	
	public void update(Etudiant e) {
		dao.save(e);
	}
	
	public void insert(Etudiant e) {
		dao.save(e);
	}
	
	public void delete(int id) {
		dao.deleteById(id);
	}
	
	public boolean exist(int id) {
		return dao.existsById(id);
	}
}

