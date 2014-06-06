package com.adesis.cursoTDD.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.adesis.cursoTDD.parser.Language;
import com.adesis.cursoTDD.parser.Parser;
import com.adesis.cursoTDD.parser.SpanishLanguage;
import com.adesis.cursoTDD.user.exceptions.UserRegistrationException;

public class RegistrationServiceTest {

	private static final String DEFAULT_USERNAME = "emiliano";
	private static final String DEFAULT_PROFILE = "un bomberó toreros.";

	private static final String DEFAULT_PLACE = "MADRID";
	private static final String DEFAULT_PARSED_PROFILE = "BOMBERO TORERO";
	private RegistrationService registrationService = new RegistrationService();

	private boolean hasBeenCalled;
	private User userExpected;
	private Parser parser;

	private User userMadridBombero = new User();
	private User userMadridTorero = new User();
	private User userMadridToreroBombero = new User();

	@Before
	public void setUp() {
		userExpected = new User();
		hasBeenCalled = false;
		Language language = new SpanishLanguage();
		parser = new Parser(language);
		registrationService.setParser(parser);
	}

	@Test
	public void register_new_user() {
		UserRepository userRepositorySpy = new UserRepository() {

			@Override
			public List<User> findUsersByPlace(String place) {
				return null;
			}

			@Override
			public void createNewUser(User user) {
				hasBeenCalled = true;
				userExpected.place = user.place;
				userExpected.profile = user.profile;
				userExpected.username = user.username;
			}
		};
		registrationService.setUserRepository(userRepositorySpy);
		registrationService.registerNewUser(DEFAULT_USERNAME, DEFAULT_PLACE, DEFAULT_PROFILE);
		assertThat(userExpected.place).isEqualTo(DEFAULT_PLACE);
		assertThat(userExpected.profile).isEqualTo(DEFAULT_PARSED_PROFILE);
		assertThat(userExpected.username).isEqualTo(DEFAULT_USERNAME);
		assertThat(hasBeenCalled).isTrue();
	}

	@Test(expected = UserRegistrationException.class)
	public void register_new_user_throws_exception() {
		UserRepository userRepositoryStub = new UserRepository() {

			@Override
			public List<User> findUsersByPlace(String place) {
				return null;
			}

			@Override
			public void createNewUser(User user) {
				throw new IllegalArgumentException();
			}
		};
		registrationService.setUserRepository(userRepositoryStub);
		registrationService.registerNewUser(DEFAULT_USERNAME, DEFAULT_PLACE, DEFAULT_PROFILE);
	}

	@Test
	public void users_are_not_found_with_wrong_place() {
		UserRepository userRepositoryStub = new UserRepository() {

			@Override
			public List<User> findUsersByPlace(String place) {
				return new ArrayList<User>();
			}

			@Override
			public void createNewUser(User user) {
			}
		};
		registrationService.setUserRepository(userRepositoryStub);
		assertThat(registrationService.findUserBy("", "")).isEmpty();
		assertThat(registrationService.findUserBy("toreros", "")).isEmpty();
		assertThat(registrationService.findUserBy("bomberos", "El pilar")).isEmpty();
	}

	@Test
	public void users_are_found_in_right_place() {
		UserRepository userRepositoryStub = new UserRepository() {

			@Override
			public List<User> findUsersByPlace(String place) {
				ArrayList<User> arrayList = new ArrayList<User>();
				userMadridTorero.place = "Madrid";
				userMadridTorero.profile = "TORERO";
				userMadridTorero.username = "Rafa";
				arrayList.add(userMadridTorero);
				return arrayList;
			}

			@Override
			public void createNewUser(User user) {
			}
		};
		registrationService.setUserRepository(userRepositoryStub);
		assertThat(registrationService.findUserBy("", "Madrid")).contains(userMadridTorero);
	}

	@Test
	public void users_are_not_found_with_wrong_profile() {
		UserRepository userRepositoryStub = new UserRepository() {

			@Override
			public List<User> findUsersByPlace(String place) {
				ArrayList<User> arrayList = new ArrayList<User>();
				userMadridTorero.place = "Madrid";
				userMadridTorero.profile = "TORERO";
				userMadridTorero.username = "Rafa";
				arrayList.add(userMadridTorero);
				return arrayList;
			}

			@Override
			public void createNewUser(User user) {
			}
		};
		registrationService.setUserRepository(userRepositoryStub);
		assertThat(registrationService.findUserBy("madrileños", "Madrid")).isEmpty();
	}

	@Test
	public void users_are_found() {
		UserRepository userRepositoryStub = new UserRepository() {

			@Override
			public List<User> findUsersByPlace(String place) {
				ArrayList<User> arrayList = new ArrayList<User>();
				userMadridTorero.place = "Madrid";
				userMadridTorero.profile = "TORERO";
				userMadridTorero.username = "Rafa";
				arrayList.add(userMadridTorero);
				userMadridBombero.place = "Madrid";
				userMadridBombero.profile = "BOMBERO";
				userMadridBombero.username = "Rafa";
				arrayList.add(userMadridBombero);
				return arrayList;
			}

			@Override
			public void createNewUser(User user) {
			}
		};
		registrationService.setUserRepository(userRepositoryStub);
		assertThat(registrationService.findUserBy("torero", "Madrid")).contains(userMadridTorero);
	}

	@Test
	public void users_are_found_profile_with_some_words() {
		UserRepository userRepositoryStub = new UserRepository() {

			@Override
			public List<User> findUsersByPlace(String place) {
				ArrayList<User> arrayList = new ArrayList<User>();
				userMadridTorero.place = "Madrid";
				userMadridTorero.profile = "TORERO";
				userMadridTorero.username = "Rafa";
				arrayList.add(userMadridTorero);
				userMadridBombero.place = "Madrid";
				userMadridBombero.profile = "BOMBERO";
				userMadridBombero.username = "Rafa";
				arrayList.add(userMadridToreroBombero);
				userMadridToreroBombero.place = "Madrid";
				userMadridToreroBombero.profile = "TORERO BOMBERO";
				userMadridToreroBombero.username = "Rafa";
				arrayList.add(userMadridToreroBombero);
				return arrayList;
			}

			@Override
			public void createNewUser(User user) {
			}
		};
		registrationService.setUserRepository(userRepositoryStub);
		assertThat(registrationService.findUserBy("torero bombero", "Madrid")).contains(
				userMadridToreroBombero);
	}
}
