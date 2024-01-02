package com.moodledemo.controller;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
public class MoodleService {
	
	OkHttpClient client = new OkHttpClient();

    public String run(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
 
    final String authToken = "f68564417772adf8ea29739cfc65c920";
    
    final String HOST_NAME = "http://localhost:8888/moodle400/webservice/rest/server.php?";
        
    final String requestFormat = "json";
    
    final String GET_COURSES = "core_course_get_courses";
    
    final String ENROL_USERS = "enrol_manual_enrol_users";
    
    final String GET_QUIZ_BY_COURSE= "mod_quiz_get_quizzes_by_courses";
    
    final String DELETE_USERS = "core_user_delete_users";
    
    final String GET_COURSE_GRADE = "core_completion_get_course_completion_status";
    
    final String CREATE_USERS = "core_user_create_users";
    
	@PostMapping("create-user")
	public String createUser(@RequestBody Map<String, String> data) throws IOException {
		String url = HOST_NAME + "wstoken=" + authToken + "&moodlewsrestformat=" + requestFormat + "&wsfunction=" + CREATE_USERS + "&users[0][username]=" + MapUtils.getString(data, "userName") + "&users[0][password]=" + MapUtils.getString(data, "password") + "&users[0][firstname]=" + MapUtils.getString(data, "firstName") + "&users[0][lastname]=" + MapUtils.getString(data, "lastName") + "&users[0][email]=" + MapUtils.getString(data, "email");
		String response = run(url);
		return response;
	}
	
	@GetMapping("get-courses")
	public String getCourses() throws IOException {
		String url = HOST_NAME + "wstoken=" + authToken + "&moodlewsrestformat=" + requestFormat + "&wsfunction=" + GET_COURSES;
        String response = run(url);
		return response;
	}
	
	@PostMapping("enrol-user-to-course")
	public String enrolUserToCourse(@RequestBody Map<String, String> data) throws IOException {
		String url = HOST_NAME + "wstoken=" + authToken + "&moodlewsrestformat=" + requestFormat + "&wsfunction=" + ENROL_USERS +"&enrolments[0][roleid]=" + MapUtils.getString(data, "roleId") + "&enrolments[0][userid]="+ MapUtils.getString(data, "userId") + "&enrolments[0][courseid]=" + MapUtils.getString(data, "courseId");
		String response = run(url);
		return response;
	}
	
	@PostMapping("get-quiz-by-courseid")
	public String getQuizByCourseId(@RequestBody Map<String, String> data) throws IOException {
		String url = HOST_NAME + "wstoken=" + authToken + "&moodlewsrestformat=" + requestFormat + "&wsfunction=" + GET_QUIZ_BY_COURSE + "&courseids[0]=" + MapUtils.getString(data, "courseId");
		String response = run(url);
		return response;

	}
	
	@PostMapping("delete-user")
	public String deleteUser(@RequestBody Map<String, String> data) throws IOException {
		String url = HOST_NAME + "wstoken=" + authToken + "&moodlewsrestformat=" + requestFormat + "&wsfunction=" + DELETE_USERS + "&userids[0]=" + MapUtils.getString(data, "userId");
		String response = run(url);
		return response;

	}
	
	@PostMapping("get-course-grade-by-user")
	public String getCourseGradeByUser(@RequestBody Map<String, String> data) throws IOException {
		String url = HOST_NAME + "wstoken=" + authToken + "&moodlewsrestformat=" + requestFormat + "&wsfunction=" + GET_COURSE_GRADE + "&courseid=" + MapUtils.getString(data, "courseId") + "&userid="+ MapUtils.getString(data, "userId");
		return run(url);
	}
	
}
