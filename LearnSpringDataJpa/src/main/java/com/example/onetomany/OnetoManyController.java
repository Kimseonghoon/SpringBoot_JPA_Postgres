package com.example.onetomany;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnetoManyController {
	@Autowired UniversityRepository repository;
	@RequestMapping("/onetomany")
	public String onetomanyinit() {
		
		University university = new University();
		university.setId(1L);
		university.setName("kimseonghoon");
		
		List<Student> students = new ArrayList<Student>();
		
		for (int i = 0; i < 300; i++) {
			students.add(new Student("kimseonghoon"+i, "010-"+i, university));
		}
		university.setStudents(students);
		repository.save(university);
		
		System.out.println("저장 완료");
		System.out.println("===========");
		return "one to many initialization complete. <br>"
				+ "<a href=\"/university\">university check</a><br>"
				+ "<a href=\"univandstudents\">univandstudents check</a>";
	}

	@RequestMapping("/university") 
	public String university() { 
		University getUniversity = repository.getOne(1L); 
		return getUniversity.toString(); 
	} 
		 
	@RequestMapping("/univandstudents") 
	public String univandstudents() { 
		University getUniversity = repository.getOne(1L);
		return getUniversity.toStringPlusStudents();
	}
}
