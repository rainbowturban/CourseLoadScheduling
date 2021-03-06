package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.SectionController;
import org.dselent.scheduling.server.dto.AddSectionDto;
import org.dselent.scheduling.server.dto.EditSectionDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.section.Add;
import org.dselent.scheduling.server.requests.section.Edit;
import org.dselent.scheduling.server.requests.section.Remove;
import org.dselent.scheduling.server.requests.section.View;
import org.dselent.scheduling.server.requests.section.ViewAllInfo;
import org.dselent.scheduling.server.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller for handling requests related to the user such as logging in or registering.
 * Controller methods are the first methods reached by the request server-side (with special exception).
 * They parse the request and then call the appropriate service method to execute the business logic.
 * Any results or data is then sent back to the client.
 * 
 * @author dselent
 */
@Controller
public class SectionControllerImpl implements SectionController
{
	@Autowired
	private SectionService sectionService;

	/**
	 * 
	 * @param request The body of the request expected to contain a map of String key-value pairs
	 * @return A ResponseEntity for the response object(s) and the status code
	 * @throws Exception 
	 */
	@Override
	public ResponseEntity<String> addSection(@RequestBody Map<String, String> request) throws Exception 
	{
		// Print is for testing purposes
		System.out.println("controller reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();

		//Add functions
		String name = request.get(Add.getBodyName(Add.BodyKey.NAME));
		//Integer crn = Integer.parseInt(request.get(Add.getBodyName(Add.BodyKey.CRN)));
		Integer termsID = Integer.parseInt(request.get(Add.getBodyName(Add.BodyKey.TERMS_ID)));
		Integer sectionTypeID = Integer.parseInt(request.get(Add.getBodyName(Add.BodyKey.SECTION_TYPE_ID)));
		Integer daysID = Integer.parseInt(request.get(Add.getBodyName(Add.BodyKey.DAYS_ID)));
		Integer coursesID = Integer.parseInt(request.get(Add.getBodyName(Add.BodyKey.COURSES_ID)));
		Integer startID = Integer.parseInt(request.get(Add.getBodyName(Add.BodyKey.START_ID)));
		Integer endID = Integer.parseInt(request.get(Add.getBodyName(Add.BodyKey.END_ID)));

		//Add DTO
		AddSectionDto.Builder builder = AddSectionDto.builder();
		AddSectionDto addSectionDto = builder.withName(name)
				//.withCrn(crn)
				.withTermID(termsID)
				.withSectionTypeID(sectionTypeID)
				.withDaysID(daysID)
				.withCoursesID(coursesID)
				.withStartID(startID)
				.withEndID(endID)
				.build();

		sectionService.addSection(addSectionDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> editSection(@RequestBody Map<String, String> request) throws Exception
	{
		// Print is for testing purposes
		System.out.println("controller reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();

		//Edit functions

		System.out.println(Edit.getBodyName(Edit.BodyKey.ID) + ", term id: " + Edit.getBodyName(Edit.BodyKey.TERMS_ID));

		String editName = request.get(Edit.getBodyName(Edit.BodyKey.NAME));
		Integer editID = Integer.parseInt(request.get(Edit.getBodyName(Edit.BodyKey.ID)));
		Integer editTermID = Integer.parseInt(request.get(Edit.getBodyName(Edit.BodyKey.TERMS_ID)));
		Integer editSectionTypeID = Integer.parseInt(request.get(Edit.getBodyName(Edit.BodyKey.SECTION_TYPE_ID)));
		Integer editDaysID = Integer.parseInt(request.get(Edit.getBodyName(Edit.BodyKey.DAYS_ID)));
		Integer editCoursesID = Integer.parseInt(request.get(Edit.getBodyName(Edit.BodyKey.COURSES_ID)));
		Integer editStartID = Integer.parseInt(request.get(Edit.getBodyName(Edit.BodyKey.START_ID)));
		Integer editEndID = Integer.parseInt(request.get(Edit.getBodyName(Edit.BodyKey.END_ID)));

		//Edit DTO
		EditSectionDto.Builder builder = EditSectionDto.builder();
		EditSectionDto editSectionDto = builder.withName(editName)
				.withId(editID)
				.withTermID(editTermID)
				.withSectionTypeID(editSectionTypeID)
				.withDaysID(editDaysID)
				.withCoursesID(editCoursesID)
				.withStartID(editStartID)
				.withEndID(editEndID)
				.build();

		success.add(sectionService.editSection(editSectionDto));
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> removeSection(@RequestBody Map<String, String> request) throws Exception
	{
		// Print is for testing purposes
		System.out.println("controller reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();

		//Remove functions
		Integer id = Integer.parseInt(request.get(Remove.getBodyName(Remove.BodyKey.SECTION_ID)));

		sectionService.removeSection(id);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> viewSection(@RequestBody Map<String, String> request) throws Exception
	{
		// Print is for testing purposes
		System.out.println("controller (section/view) reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();

		System.out.println("request: "+request.toString());
		
		//Get the sections if a courseId was passed in
		if(request.get(View.getBodyName(View.BodyKey.COURSE_ID)) != null) {		
			Integer courseId = Integer.parseInt(request.get(View.getBodyName(View.BodyKey.COURSE_ID)));
			success.add(sectionService.viewSection(courseId));
		}

		//send response
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> viewAllInfo(@RequestBody Map<String, String> request) throws Exception {
		// Print is for testing purposes
		System.out.println("controller (section/view/one) reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		
		success.add(sectionService.viewAllInfo());
		//Get the sections if a courseId was passed in
		Integer facultyId = 0;
		Integer termsId = 0;
		if( request.get(ViewAllInfo.getBodyName(ViewAllInfo.BodyKey.FACULTY_ID)) != null) {		
			facultyId = Integer.parseInt(request.get(ViewAllInfo.getBodyName(ViewAllInfo.BodyKey.FACULTY_ID)));
		}
		if( request.get(ViewAllInfo.getBodyName(ViewAllInfo.BodyKey.TERMS_ID)) != null) {		
			termsId = Integer.parseInt(request.get(ViewAllInfo.getBodyName(ViewAllInfo.BodyKey.TERMS_ID)));
		}
		success.add(sectionService.viewOneFaculty(facultyId, termsId));

		//send response
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}