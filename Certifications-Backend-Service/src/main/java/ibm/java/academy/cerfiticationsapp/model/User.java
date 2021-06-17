package ibm.java.academy.cerfiticationsapp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private Long id;
	private String name;
	private String surname;
	private String email;

	@JsonIgnoreProperties("user")
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	// @JsonManagedReference
	private List<Voucher> voucher;

	public User(String name, String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	
}
