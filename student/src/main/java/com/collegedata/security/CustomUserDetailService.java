package com.collegedata.security;



import com.collegedata.entities.StudentRecord;
import com.collegedata.repository.StudentRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private StudentRecordRepo studentRecordRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		StudentRecord c = this.studentRecordRepo.findByEmail(username);
		return c;
	}
}
