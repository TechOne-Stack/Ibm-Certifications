package ibm.java.academy.cerfiticationsapp.model;

// import java.util.Collection;
// import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
// import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
// @Builder
public class User{ 
// implements UserDetails{

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String name;
	private String surname;
	private String email;
	private String password;

	@JsonIgnoreProperties("user")
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	// @JsonManagedReference
	private List<Voucher> voucher;

	public User(String name, String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	//adding role to user
	// @Builder.Default
	// private UserRole userRole = UserRole.USER;

	// enum UserRole {

	// 	ADMIN, USER
	// }

	// @Builder.Default
	// private Boolean locked = false;

	// @Builder.Default
	// private Boolean enabled = false;

	// @Override
	// public Collection<? extends GrantedAuthority> getAuthorities() {
	// 	final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole.name());
	// 	return Collections.singletonList(simpleGrantedAuthority);
	// }

	// @Override
	// public String getUsername() {
	// 	return this.email;
	// }

	// @Override
	// public String getPassword() {
	// 	return password;
	// }

	// @Override
	// public boolean isAccountNonExpired() {
	// 	return true;
	// }

	// @Override
	// public boolean isAccountNonLocked() {
	// 	return !locked;
	// }

	// @Override
	// public boolean isCredentialsNonExpired() {
	// 	return true;
	// }

	// @Override
	// public boolean isEnabled() {
	// 	return enabled;
	// }
}
