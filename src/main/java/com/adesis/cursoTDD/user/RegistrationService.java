package com.adesis.cursoTDD.user;

import java.util.ArrayList;
import java.util.List;

import com.adesis.cursoTDD.parser.Parser;
import com.adesis.cursoTDD.user.exceptions.UserRegistrationException;

public class RegistrationService {

	private static final String BLANK_CHAR = " ";
	private static final String DEFAULT_REGISTRATION_ERROR = "Error during user registration";
	private UserRepository userRepository;
	private Parser parser;

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> findUserBy(String profile, String place) {
		List<User> usersFoundByPlace = userRepository.findUsersByPlace(place);
		return filterUsersByProfile(formatProfile(profile), usersFoundByPlace);
	}

	private List<User> filterUsersByProfile(String profile, List<User> findUsersByPlace) {
		List<User> filteredUsers = new ArrayList<User>();
		if (validProfiles(profile)) {
			for (User user : findUsersByPlace) {
				if (profilesAreEquals(profile, user)) {
					filteredUsers.add(user);
				}
			}
		} else {
			filteredUsers = findUsersByPlace;
		}
		return filteredUsers;
	}

	private boolean profilesAreEquals(String profile, User user) {
		return user.profile.equals(profile);
	}

	private boolean validProfiles(String profile) {
		return !profile.equals("");
	}

	public void registerNewUser(String username, String place, String profile) {
		User user = new User();
		user.username = username;
		user.place = place;
		user.profile = formatProfile(profile);

		try {
			userRepository.createNewUser(user);
		} catch (IllegalArgumentException e) {
			throw new UserRegistrationException(DEFAULT_REGISTRATION_ERROR);
		}
	}

	private String formatProfile(String profile) {
		List<String> parsedProfile = parser.parse(profile);
		String concatParsedProfile = "";
		for (String word : parsedProfile) {
			concatParsedProfile = concatParsedProfile.concat(word).concat(BLANK_CHAR);
		}
		return concatParsedProfile.substring(0, concatParsedProfile.length() - 1);
	}

	public void setParser(Parser parser) {
		this.parser = parser;
	}
}
