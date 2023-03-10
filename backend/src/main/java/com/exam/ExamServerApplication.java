package com.exam;

/*import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;*/

@SpringBootApplication
public class ExamServerApplication{
	/* @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserService userService; */
	public static void main(String[] args) {
		SpringApplication.run(ExamServerApplication.class, args);
	}
	/*@Override
	public void run(String... args) throws Exception{

		User user=new User();
		user.setEmail("nikhil.softelevation@gmail.com");
		user.setPassword(bCryptPasswordEncoder.encode("password"));
		user.setFirstName("Name");
		user.setLastName("Last name");
		user.setProfile("nikhil.png");
		user.setPhone("8353800043");
		user.setUsername("Nikhil_0718");

		Role role=new Role();
		role.setRoleName("ADMIN");

		UserRole userRole=new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);

		Set<UserRole> userRoles=new HashSet<>();
		userRoles.add(userRole);

        User user1 = userService.createUser(user,userRoles);
		System.out.println(user1.getUsername());
	}*/
}