package com.qainfotech.tap.training.resourceio.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class Team {

	private final String name;
	private final Integer id;
	private final List<Individual> members;

	/**
	 * 
	 * @param teamMap
	 */

	// Constructor
	public Team(Map<String, Object> teamMap) {
		// throw new UnsupportedOperationException("Not implemented.");
		this.id = Integer.parseInt(teamMap.get("id").toString());
		this.name = teamMap.get("name").toString();
		this.members = (List<Individual>) teamMap.get("members");
	}

	/**
	 * get team name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * get team id
	 * 
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * get list of individuals that are members of this team
	 * 
	 * @return
	 */
	public List<Individual> getMembers() {
		return members;
	}

	/**
	 * get a list of individuals that are members of this team and are also
	 * active
	 * 
	 * @return
	 */
	public List<Individual> getActiveMembers() {
		// throw new UnsupportedOperationException("Not implemented.");

		/**
		 * variable activeMember has a list of individuals that are members of
		 * this team and are also active
		 */
		List<Individual> activeMember = new ArrayList<Individual>();

		for (int index = 0; index < members.size(); index++) {
			Individual individual = members.get(index);
			if (individual.isActive()) {
				activeMember.add(individual);
			}
		}
		return activeMember;
	}

	/**
	 * get a list of individuals that are members of this team but are inactive
	 * 
	 * @return
	 */
	public List<Individual> getInactiveMembers() {
		// throw new UnsupportedOperationException("Not implemented.");

		/**
		 * variable inactiveMember has a list of individuals that are members of
		 * this team and are also active
		 */
		List<Individual> inactiveMember = new ArrayList<Individual>();

		for (int index = 0; index < members.size(); index++) {
			Individual individual = members.get(index);
			if (!(individual.isActive())) {
				inactiveMember.add(individual);
			}
		}
		return inactiveMember;

	}
}
