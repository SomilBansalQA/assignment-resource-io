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

	List<Individual> individualList;
	List<Individual> inactiveMembers;
	List<Individual> activeMembers;
	List<Team> teamList;
	List<Team> members;

	public List<Individual> getListOfIndividuals() {
		// throw new UnsupportedOperationException("Not implemented.");

		JSONParser parser;
		JSONObject jsonObject = null;
		try {
			parser = new JSONParser();
			Object obj = parser.parse(new FileReader(new File(
					"C:\\Users\\somilbansal\\Desktop\\Eclipse_Workspace\\assignment-resource-io-master1\\src\\test\\resources\\db.json")));
			jsonObject = (JSONObject) obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		individualList = new ArrayList<>();
		JSONArray individual = (JSONArray) jsonObject.get("individuals");
		for (int i = 0; i < individual.size(); i++) {
			JSONObject ob = (JSONObject) individual.get(i);
			Integer id = ((Long) ob.get("id")).intValue();
			// Integer id = Integer.parseInt((String) ob.get("id"));
			String name = ob.get("name").toString();
			Boolean active = (Boolean) ob.get("active");
			Map<String, Object> map = new HashMap();
			map.put("id", id);
			map.put("name", name);
			map.put("active", active);

			Individual ind1 = new Individual(map);
			individualList.add(ind1);

		}
		// System.out.println("Ifsdf "+individualList+"\n\n\n\nn");
		return individualList;
	}

	/**
	 * get individual object by id
	 * 
	 * @param id
	 *            individual id
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 */
	public Individual getIndividualById(Integer id) throws ObjectNotFoundException {
		// throw new UnsupportedOperationException("Not implemented.");
		individualList = getListOfIndividuals();
		int flag = 0;
		Iterator<Individual> iterate = individualList.iterator();
		Individual newindividual = null;
		while (iterate.hasNext()) {
			newindividual = iterate.next();
			if (newindividual.getId() == id.intValue()) {
				flag = 1;
				break;
			}
		}
		if (flag == 1)
			return newindividual;
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

		individualList = getListOfIndividuals();
		int flag = 0;

		Iterator<Individual> iterate = individualList.iterator();
		Individual newindividual = null;
		while (iterate.hasNext()) {
			newindividual = iterate.next();
			if (newindividual.getName().equalsIgnoreCase(name)) {
				flag = 1;
				break;
			}
		}
		if (flag == 1)

			return newindividual;
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
		individualList = getListOfIndividuals();
		Iterator<Individual> iterate = individualList.iterator();
		inactiveMembers = new ArrayList<>();
		Individual newindividual = null;
		while (iterate.hasNext()) {
			newindividual = iterate.next();
			if (!(newindividual.isActive())) {
				inactiveMembers.add(newindividual);
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
		Iterator<Individual> iterate = individualList.iterator();
		activeMembers = new ArrayList<>();
		Individual newindividual = null;
		while (iterate.hasNext()) {
			newindividual = iterate.next();
			if ((newindividual.isActive()))
				activeMembers.add(newindividual);
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
		try {
			parser = new JSONParser();
			Object obj = parser.parse(new FileReader(new File(
					"C:\\Users\\somilbansal\\Desktop\\Eclipse_Workspace\\assignment-resource-io-master1\\src\\test\\resources\\db.json")));
			jsonObject = (JSONObject) obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		teamList = new ArrayList<>();
		JSONArray teams = (JSONArray) jsonObject.get("teams");

		for (int i = 0; i < teams.size(); i++) {
			JSONObject ob = (JSONObject) teams.get(i);
			Integer id = ((Long) ob.get("id")).intValue();
			// Integer id = Integer.parseInt((String) ob.get("id"));
			String name = ob.get("name").toString();

			JSONArray mem = (JSONArray) ob.get("members");

			List<Individual> list = new ArrayList();

			for (int j = 0; j < mem.size(); j++) {
				Integer id1 = ((Long) mem.get(j)).intValue();

				Individual ind;
				try {
					ind = getIndividualById(id1);
					list.add(ind);
				} catch (ObjectNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			Map<String, Object> map = new HashMap();

			map.put("id", id);
			map.put("name", name);
			map.put("members", list);
			// System.out.println("dsad "+list.get(0).getName());
			Team team1 = new Team(map);

			teamList.add(team1);
		}

		return teamList;

	}
}
