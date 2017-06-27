package com.qainfotech.tap.training.resourceio;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsJsonReader {

	/**
	 * get a list of individual objects from db json file
	 * 
	 * @return
	 */
	/** variable individualList Contain list of individuals that are members of
	 Individual */
	List<Individual> individualList;


	/**
	 * 
	 * @return
	 */
	public List<Individual> getListOfIndividuals() {
		// throw new UnsupportedOperationException("Not implemented.");

		JSONParser parser;
		JSONObject jsonObject = null;

		try {
			parser = new JSONParser();

			// Read db.json file from main Resources
			Object obj = parser.parse(new FileReader(new File(
					"C:\\Users\\somilbansal\\Desktop\\Eclipse_Workspace\\assignment-resource-io-master1\\src\\test\\resources\\db.json")));
			jsonObject = (JSONObject) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		individualList = new ArrayList<>();

		// Converting .json file data into a Map
		JSONArray individualArray = (JSONArray) jsonObject.get("individuals");

		for (int index = 0; index < individualArray.size(); index++) {
			JSONObject ob = (JSONObject) individualArray.get(index);

			// Fetch id,name and active data from .json file
			Integer id = ((Long) ob.get("id")).intValue();
			String name = ob.get("name").toString();
			Boolean active = (Boolean) ob.get("active");

			// putting data into map
			Map<String, Object> map = new HashMap();
			map.put("id", id);
			map.put("name", name);
			map.put("active", active);

			Individual individual = new Individual(map);
			// add data into list
			individualList.add(individual);
		}
		return individualList;
	}

	/**
	 * get individual object by id
	 * 
	 * @param id
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 */
	public Individual getIndividualById(Integer id) throws ObjectNotFoundException {
		// throw new UnsupportedOperationException("Not implemented.");

		// get a list of individuals
		individualList = getListOfIndividuals();

		/**
		 * idFlag is 1 if variable id data matched with any individual id data,
		 * otherwise 0
		 */
		int idFlag = 0;

		Iterator<Individual> iterate = individualList.iterator();
		Individual individual = null;
		while (iterate.hasNext()) {
			individual = iterate.next();
			if (individual.getId() == id.intValue()) {
				idFlag = 1;
				break;
			}
		}
		if (idFlag == 1)
			return individual;
		else {
			throw new ObjectNotFoundException("Individual", "id", id.toString());
		}
	}

	/**
	 * get individual object by name
	 * 
	 * @param name
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 */
	public Individual getIndividualByName(String name) throws ObjectNotFoundException {
		// throw new UnsupportedOperationException("Not implemented.");

		// get a list of individuals
		individualList = getListOfIndividuals();

		/**
		 * nameFlag is 1 if variable name data matched with any individual name
		 * data, otherwise 0
		 */

		int nameFlag = 0;

		Iterator<Individual> iterate = individualList.iterator();
		Individual individual = null;
		while (iterate.hasNext()) {
			individual = iterate.next();
			if (individual.getName().equalsIgnoreCase(name)) {
				nameFlag = 1;
				break;
			}
		}
		if (nameFlag == 1)

			return individual;
		else {
			throw new ObjectNotFoundException("Individual", "name", name);
		}

	}

	/**
	 * get a list of individual objects who are not active
	 * 
	 * @return List of inactive individuals object
	 */
	public List<Individual> getListOfInactiveIndividuals() {
		// throw new UnsupportedOperationException("Not implemented.");

		// get a list of individuals
		individualList = getListOfIndividuals();

		/**
		 * variable inactiveMembers Contain list of individuals that are
		 * inactive members of Individual
		 */
		List<Individual> inactiveMembers = new ArrayList<>();

		Iterator<Individual> iterate = individualList.iterator();

		Individual individual = null;

		while (iterate.hasNext()) {
			individual = iterate.next();
			if (!(individual.isActive())) {
				inactiveMembers.add(individual);
			}
		}
		return inactiveMembers;
	}

	/**
	 * get a list of individual objects who are active
	 * 
	 * @return List of active individuals object
	 */
	public List<Individual> getListOfActiveIndividuals() {
		// throw new UnsupportedOperationException("Not implemented.");
		individualList = getListOfIndividuals();
		/**
		 * variable activeMembers Contain list of individuals that are active
		 * members of Individual
		 */
		List<Individual> activeMembers = new ArrayList<>();

		Iterator<Individual> iterate = individualList.iterator();
		Individual individual = null;
		while (iterate.hasNext()) {
			individual = iterate.next();
			if ((individual.isActive()))
				activeMembers.add(individual);
		}
		return activeMembers;
	}

	/**
	 * get a list of team objects from db json
	 * 
	 * @return
	 */
	
	public List<Team> getListOfTeams() {
		// throw new UnsupportedOperationException("Not implemented.");

		JSONParser parser;
		JSONObject jsonObject = null;

		// Read db.json file from main Resources
		try {
			parser = new JSONParser();
			Object obj = parser.parse(new FileReader(new File(
					"C:\\Users\\somilbansal\\Desktop\\Eclipse_Workspace\\assignment-resource-io-master1\\src\\test\\resources\\db.json")));
			jsonObject = (JSONObject) obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * variable teamList Contain list of Team data
		 */
		List<Team> teamList = new ArrayList<>();
		// Converting .json file data into a Map
		JSONArray teams = (JSONArray) jsonObject.get("teams");

		for (int i = 0; i < teams.size(); i++) {
			JSONObject ob = (JSONObject) teams.get(i);
			// Fetch id,name and mamber  data of team from .json file
			Integer id = ((Long) ob.get("id")).intValue();
			String name = ob.get("name").toString();

		
			JSONArray mem = (JSONArray) ob.get("members");
            
			
			// variable list contain list of Team Members data
			List<Individual> list = new ArrayList();

			for (int j = 0; j < mem.size(); j++) {
				Integer id1 = ((Long) mem.get(j)).intValue();

				Individual individual;
				try {
					individual = getIndividualById(id1);
					list.add(individual);
				} catch (ObjectNotFoundException e) {
					
					e.printStackTrace();
				}

			}
			
			// putting data into map
			Map<String, Object> map = new HashMap();
			map.put("id", id);
			map.put("name", name);
			map.put("members", list);

			Team team = new Team(map);
			teamList.add(team);
		}

		return teamList;

	}
}
