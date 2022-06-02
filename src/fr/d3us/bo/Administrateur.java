package fr.d3us.bo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@DiscriminatorValue(value = "A")
@Entity
public class Administrateur extends Personne {

}
