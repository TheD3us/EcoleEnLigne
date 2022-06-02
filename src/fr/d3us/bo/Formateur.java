package fr.d3us.bo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@DiscriminatorValue(value = "F")
@Entity
public class Formateur extends Personne{
	
	 @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
	 @JoinColumn(name="id")
	 private List<Module> module;
}
