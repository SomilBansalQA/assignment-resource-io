package com.qainfotech.tap.training.resourceio;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsYamlReader {

	/**
	 * get a list of individual objects from db yaml file
	 * 
	 * @return
	 */

	String fileName = "db.yaml";
	ClassLoader classLoader = this.getClass().getClassLoader();

	List<Individual> individualList;

	public List<Individual> getListOfIndividuals() throws FileNotFoundException {

		individualList = new ArrayList<>();
		InputStream input = classLoader.getResourceAsStream(fileName);
		Yaml yaml = new Yaml();
		Map<String, Object> result = (Map<String, Object>) yaml.load(input);

		Map<String, Object> map = new HashMap<String, Object>();

		ArrayList individualArray = (ArrayList) result.get("individuals");
	
		for (int index = 0; index < individualArray.size(); index++) {
			map = (Map<String, Object>) individualArray.get(index);

			Individual individual = new Individual(map);
			individualList.add(individual);
		}

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

		try {
			individualList = getListOfIndividuals();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		try {
			individualList = getListOfIndividuals();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

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
		if (nameFlag == 1) {

			return individual;
		} else {
			throw new ObjectNotFoundException("Individual", "Name", name);
		}

	}

	/**
	 * get a list of individual objects who are not active
	 * 
	 * @return List of inactive individuals object
	 */
	public List<Individual> getListOfInactiveIndividuals() {
		try {
			individualList = getListOfIndividuals();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		try {
			individualList = getListOfIndividuals();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 * get a list of team objects from db yaml
	 * 
	 * @return
	 */
	public List<Team> getListOfTeams() {

		List<Team> teamList = new ArrayList<>();

		Map<String, Object> map1 = new HashMap<String, Object>();

		InputStream input = classLoader.getResourceAsStream(fileName);
		Yaml yaml = new Yaml();
		Map<String, Object> result = (Map<String, Object>) yaml.load(input);

		Map<String, Object> map = new HashMap<String, Object>();

		ArrayList teamArray = (ArrayList) result.get("teams");

		for (int index = 0; index < teamArray.size(); index++) {
			List<Individual> individualList = new ArrayList<>();
			map = (Map<String, Object>) teamArray.get(index);
			map1.put("name", map.get("name"));
			map1.put("id", map.get("id"));
			List<Individual> list = new ArrayList();
			List members = (List) map.get("members");
			for (int i = 0; i < members.size(); i++) {

				Integer id1 = (Integer) members.get(i);
				
				try {
					Individual	individual = getIndividualById(id1);
					list.add(individual);
				} catch (ObjectNotFoundException e) {
					e.printStackTrace();
				}
			}

			map1.put("members", list);

			Team team = new Team(map1);
			teamList.add(team);

		}
		return teamList;
	}

	

}
