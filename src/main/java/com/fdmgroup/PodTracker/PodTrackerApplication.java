package com.fdmgroup.PodTracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.fdmgroup.PodTracker.model.*;
import com.fdmgroup.PodTracker.service.*;
import com.fdmgroup.PodTracker.security.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class PodTrackerApplication implements CommandLineRunner {
	private final UserService US;
	private final GoalService GS;
	private final UpskillingService USS;
	private final UpskillingGoalService UGS;
	private final AccountManagerService AMS;
	private final DiscussionGoalService DGS;
	private final OpportunityDiscussionService ODS;
	private final OpportunityService OS;
	private final PodProjectGoalService PPGS;
	private final PodProjectService PPS;
	private final PodService PS;
	private final ClientService CS;
	private final InterviewService IS;
	private final InterviewPrepService IPS;

	public PodTrackerApplication(UserService uS, GoalService gS, UpskillingService uSS, UpskillingGoalService uGS,
			AccountManagerService aMS, DiscussionGoalService dGS, OpportunityDiscussionService oDS,
			OpportunityService oS, PodProjectGoalService pPGS, PodProjectService pPS, PodService pS, ClientService cS,
			InterviewService iS, InterviewPrepService iPS) {

		super();
		US = uS;
		GS = gS;
		USS = uSS;
		UGS = uGS;
		AMS = aMS;
		DGS = dGS;
		ODS = oDS;
		OS = oS;
		PPGS = pPGS;
		PPS = pPS;
		PS = pS;
		CS = cS;
		IS = iS;
		IPS = iPS;
	}

	public static void main(String[] args) {
		SpringApplication.run(PodTrackerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {	
		//dummy data for account managers
		AccountManager ac1 = new AccountManager("James McCarthy", FDMLocation.NSW_Sydney);
		AccountManager ac2 = new AccountManager("Nicholas Lloyd", FDMLocation.NSW_Sydney);
		AccountManager ac3 = new AccountManager("Ed Bristow", FDMLocation.VIC_Melbourne);
		List<AccountManager> acs = Arrays.asList(ac1, ac2, ac3);
		for(AccountManager ac : acs) {
			AMS.save(ac);
		}
	
		//dummy data for clients
		Client client1 = new Client("CBA", State.NSW, "Sydney");
		Client client2 = new Client("ANZ", State.VIC, "Melbourne");
		Client client3 = new Client("KPMG Australia", State.VIC, "Melbourne");
		List<Client> clients = Arrays.asList(client1, client2, client3);
		for (Client client : clients) {
			CS.save(client);
		}

		Admin admin1 = new Admin("adminUsername1", "adminPassword1", "adminName1");
		Admin admin2 = new Admin("adminUsername2", "adminPassword2", "adminName2");
		Admin admin3 = new Admin("adminUsername3", "adminPassword3", "adminName3");
		Admin frank = new Admin("frank", "frank1", "Frank Reynolds");
		Trainer trainer1 = new Trainer("trainerUsername1", "trainerPassword1", "trainerName1");
		Trainer trainer2 = new Trainer("trainerUsername2", "trainerPassword2", "trainerName2");
		Trainer trainer3 = new Trainer("trainerUsername3", "trainerPassword3", "trainerName3");
		LocalDate date1 = LocalDate.of(2023, 9, 23);
		LocalDate date2 = LocalDate.of(2023, 9, 24);
		LocalDate date3 = LocalDate.of(2023, 9, 25);
		Trainee trainee1 = new Trainee("traineeUsername1", "traineePassword1", "traineeName1", date1, "Dev");
		Trainee trainee2 = new Trainee("traineeUsername2", "traineePassword2", "traineeName2", date2, "BA");
		Trainee trainee3 = new Trainee("traineeUsername3", "traineePassword3", "traineeName3", date3, "Dev");
		List<User> users = Arrays.asList(admin1, admin2, admin3, frank, trainer1, trainer2, trainer3, trainee1, trainee2,
				trainee3);

		for (User user : users) {
			US.registerUser(user);
		}

		// dummy data for Pods
		Pod pod1 = new Pod();
		pod1.setPodName("ToyStory");
		Pod pod2 = new Pod();
		pod2.setPodName("SimpsonsPod");
		Pod pod3 = new Pod();
		pod3.setPodName("Avatar");
		Pod pod4 = new Pod();
		pod4.setPodName("SpongeBobPod");
		Pod pod5 = new Pod();
		pod5.setPodName("ShrekPod");

		List<User> podUsers = new ArrayList<>();
		List<LocalDate> dates = Arrays.asList(date1, date2, date3);
		
		Trainer andy = new Trainer("Andy Davis", "davis1", "Andy Davis");
		podUsers.add(andy);
		pod1.setPrimaryTrainerId(andy);
		Trainer sid = new Trainer("Sid Phillips", "philip1", "Sid Phillips");
		podUsers.add(sid);
		pod1.setSecondaryTrainerId(sid);
		Trainer ned = new Trainer("Ned Flanders", "ned1", "Ned Flanders");
		podUsers.add(ned);
		pod2.setPrimaryTrainerId(ned);
		Trainer burns = new Trainer("Mr. Burns", "burns1", "Mr. Burns");
		podUsers.add(burns);
		pod2.setSecondaryTrainerId(burns);
		Trainer ozai = new Trainer("Fire Lord Ozai", "ozai1", "Fire Lord Ozai");
		podUsers.add(ozai);
		pod3.setPrimaryTrainerId(ozai);
		Trainer iroh = new Trainer("Uncle Iroh", "iroh1", "Uncle Iroh");
		podUsers.add(iroh);
		pod3.setSecondaryTrainerId(iroh);
		Trainer krabs = new Trainer("Mr. Krabs", "krabs1", "Mr. Krabs");
		podUsers.add(krabs);
		pod4.setPrimaryTrainerId(krabs);
		Trainer sandy = new Trainer("Sandy Cheeks", "sandy1", "Sandy Cheeks");
		podUsers.add(sandy);
		pod4.setSecondaryTrainerId(sandy);
		Trainer farquaad = new Trainer("Lord Farquaad", "farquaad1", "Lord Farquaad");
		podUsers.add(farquaad);
		pod5.setPrimaryTrainerId(farquaad);
		Trainer dragon = new Trainer("Dragon", "dragon1", "Dragon");
		podUsers.add(dragon);
		pod5.setSecondaryTrainerId(dragon);

		Trainee woody = new Trainee("Woody", "woody1", "Woody", randomDate(dates), "Dev");
		podUsers.add(woody);
		pod1.getTrainees().add(woody);
		//woody.setPod(pod1);
		Trainee buzz = new Trainee("Buzz Lightyear", "buzz1", "Buzz Lightyear", randomDate(dates), "BA");
		podUsers.add(buzz);
		pod1.getTrainees().add(buzz);
		//buzz.setPod(pod1);
		Trainee jessie = new Trainee("Jessie", "jessie1", "Jessie", randomDate(dates), "Dev");
		podUsers.add(jessie);
		pod1.getTrainees().add(jessie);
		//jessie.setPod(pod1);
		Trainee homer = new Trainee("Homer Simpson", "homer1", "Homer Simpson", randomDate(dates), "Dev");
		podUsers.add(homer);
		pod2.getTrainees().add(homer);
		//homer.setPod(pod2);
		Trainee marge = new Trainee("Marge Simpson", "marge1", "Marge Simpson", randomDate(dates), "BA");
		podUsers.add(marge);
		pod2.getTrainees().add(marge);
		//marge.setPod(pod2);
		Trainee bart = new Trainee("Bart Simpson", "bart1", "Bart Simpson", randomDate(dates), "Dev");
		podUsers.add(bart);
		pod2.getTrainees().add(bart);
		//bart.setPod(pod2);
		Trainee aang = new Trainee("Aang", "aang1", "Aang", randomDate(dates), "Dev");
		podUsers.add(aang);
		pod3.getTrainees().add(aang);
		//aang.setPod(pod3);
		Trainee katara = new Trainee("Katara", "katara1", "Katara", randomDate(dates), "Dev");
		podUsers.add(katara);
		pod3.getTrainees().add(katara);
		//aang.setPod(pod3);
		Trainee sokka = new Trainee("Sokka", "sokka1", "Sokka", randomDate(dates), "Dev");
		podUsers.add(sokka);
		pod3.getTrainees().add(sokka);
		//sokka.setPod(pod3);
		Trainee spongeBob = new Trainee("SpongeBob SquarePants", "spongebob1", "SpongeBob SquarePants", randomDate(dates), "Dev");
		podUsers.add(spongeBob);
		pod4.getTrainees().add(spongeBob);
		//spongeBob.setPod(pod4);
		Trainee patrick = new Trainee("Patrick Star", "patrick1", "Patrick Star", randomDate(dates), "Dev");
		podUsers.add(patrick);
		pod4.getTrainees().add(patrick);
		//spongeBob.setPod(pod4);
		Trainee squidward = new Trainee("Squidward Tentacles", "squidward1", "Squidward Tentacles", randomDate(dates), "Dev");
		podUsers.add(squidward);
		pod4.getTrainees().add(squidward);
		//squidward.setPod(pod4);
		Trainee shrek = new Trainee("Shrek", "shrek1", "Shrek", randomDate(dates), "Dev");
		podUsers.add(shrek);
		pod5.getTrainees().add(shrek);
		//shrek.setPod(pod5);
		Trainee donkey = new Trainee("Donkey", "donkey1", "Donkey", randomDate(dates), "Dev");
		podUsers.add(donkey);
		pod5.getTrainees().add(donkey);
		//donkey.setPod(pod5);
		Trainee fiona = new Trainee("Princess Fiona", "fiona1", "Princess Fiona", randomDate(dates), "Dev");
		podUsers.add(fiona);
		pod5.getTrainees().add(fiona);
		//fiona.setPod(pod5);
		
		LocalDate startDate1 = LocalDate.of(2023, 10, 9);
		LocalDate startDate2 = LocalDate.of(2023, 10, 12);
		LocalDate startDate3 = LocalDate.of(2023, 10, 10);
		LocalDate startDate4 = LocalDate.of(2023, 10, 06);
		
		LocalDate completionDate1 = LocalDate.of(2023, 10, 10);
		LocalDate completionDate2 = LocalDate.of(2023, 11, 11);
		LocalDate completionDate3 = LocalDate.of(2023, 12, 9);
		
		List<LocalDate> startDates = Arrays.asList(startDate1, startDate2, startDate3, startDate4);
		List<LocalDate> completionDates = Arrays.asList(completionDate1, completionDate2, completionDate3, null);

		// dummy data for goals
		Goal goal1 = new Goal(date1, false);
		Goal goal2 = new Goal(date2, false);
		Goal goal3 = new Goal(date3, false);
		List<Goal> goals = Arrays.asList(goal1, goal2, goal3);
		for (Goal goal : goals) {

			GS.save(goal);
		}

		// dummy data for projects
		PodProject project1 = new PodProject();
		PodProject project2 = new PodProject();
		PodProject project3 = new PodProject();
		PodProject project4 = new PodProject();
		PodProject project5 = new PodProject();
		project1.setName("Toy Story");
		project2.setName("Simpsons");
		project3.setName("Avatar");
		project4.setName("SpongeBob");
		project5.setName("Shrek");
		
		project1.setDescription("A Project about toys coming to life");
		project1.setStartDate(randomDate(startDates));
		project1.setCompletionDate(randomDate(completionDates));
		
		project2.setDescription("A project about a yellow family in Springfield");
		project2.setStartDate(randomDate(startDates));
		project2.setCompletionDate(randomDate(completionDates));
		
		project3.setDescription("A project about the last Airbender");
		project3.setStartDate(randomDate(startDates));
		project3.setCompletionDate(randomDate(completionDates));
		
		project4.setDescription("A project about underwater adventures");
		project4.setStartDate(randomDate(startDates));
		project4.setCompletionDate(randomDate(completionDates));
		
		project5.setDescription("A project about an ogre's adventures");
		project5.setStartDate(randomDate(startDates));
		project5.setStartDate(randomDate(startDates));
		
		pod1.setProject(project1);
		pod2.setProject(project2);
		pod3.setProject(project3);
		pod4.setProject(project4);
		pod5.setProject(project5);
		
		List<PodProject> projects = Arrays.asList(project1, project2, project3, project4, project5);
		for (PodProject project : projects) {
			PPS.save(project);
		}
		PodProjectGoal projectGoal1 = new PodProjectGoal(project1, goal1, 2);
		PodProjectGoal projectGoal2 = new PodProjectGoal(project1, goal2, 5);
		PodProjectGoal projectGoal3 = new PodProjectGoal(project2, goal3, 3);
		List<PodProjectGoal> projectGoals = Arrays.asList(projectGoal1, projectGoal2, projectGoal3);
		for (PodProjectGoal projectGoal : projectGoals) {

			PPGS.save(projectGoal);
		}

		for(User user: podUsers) {
			US.registerUser(user);
		}
		
		List<Pod> pods = Arrays.asList(pod1, pod2, pod3, pod4, pod5);
		for (Pod pod : pods) {
			Random random = new Random();
			pod.setCapacity(random.nextInt(8) + 2);
			PS.save(pod);
		}
		
		
		// dummy data for upskilling
		Upskilling skill1 = new Upskilling(trainee1, date1, LocalDate.now(), "React", "LinkedIn Learning",
				"React Project", TaskStatus.Planned);
		Upskilling skill2 = new Upskilling(trainee2, date2, LocalDate.now(), "Spring Boot", "LinkedIn Learning",
				"Spring Boot Project", TaskStatus.Planned);
		List<Upskilling> skills = Arrays.asList(skill1, skill2);
		for (Upskilling skill : skills) {

			USS.save(skill);
		}

		UpskillingGoal skillGoal1 = new UpskillingGoal(skill1, goal1, 1);
		UpskillingGoal skillGoal2 = new UpskillingGoal(skill1, goal2, 3);
		UpskillingGoal skillGoal3 = new UpskillingGoal(skill2, goal1, 5);
		List<UpskillingGoal> skillGoals = Arrays.asList(skillGoal1, skillGoal2, skillGoal3);

		for (UpskillingGoal skillGoal : skillGoals) {
			UGS.save(skillGoal);
		}

		// dummy data for opportunities
		Opportunity opportunity1 = new Opportunity(trainee1, "It's a Java engineer role", OpportunityStatus.Released, ac1, client1,
				goal1, "Java engineer");
		Opportunity opportunity2 = new Opportunity(trainee3, "It's a Data engineer role", OpportunityStatus.Disscussing, ac2,
				client2, goal2, "Data engineer");
		Opportunity opportunity3 = new Opportunity(trainee3, "QA", OpportunityStatus.Interviewing, ac3, client3, goal3, "It's a QA role");
		List<Opportunity> oppos = Arrays.asList(opportunity1, opportunity2, opportunity3);

		for (Opportunity oppo : oppos) {
			OS.save(oppo);
		}

		// dummy data for interviews
		Interview interview1 = new Interview(trainee1, ac1, client1);
		Interview interview2 = new Interview(trainee2, ac2, client2);
		Interview interview3 = new Interview(trainee3, ac2, client2);
		List<Interview> interviews = Arrays.asList(interview1, interview2, interview3);
		for (Interview interview : interviews) {
			IS.save(interview);
		}

		//dummy data for interview preps
		InterviewPrep ip1 = new InterviewPrep(trainee1, InterviewPrepType.Research, date1, goal1);
		InterviewPrep ip2 = new InterviewPrep(trainee2, InterviewPrepType.mock_interview, date2, goal2);
		InterviewPrep ip3 = new InterviewPrep(trainee1, InterviewPrepType.mock_interview, date3, goal1);

		List<InterviewPrep> ips = Arrays.asList(ip1, ip2, ip3);
		for(InterviewPrep ip : ips) {
			IPS.save(ip);
		}
		
		//dummy data for opportunity discussions
		OpportunityDiscussion discussion1 = new OpportunityDiscussion(opportunity1);
		OpportunityDiscussion discussion2 = new OpportunityDiscussion(opportunity2);
		OpportunityDiscussion discussion3 = new OpportunityDiscussion(opportunity3);
		
		List<OpportunityDiscussion> discussions = Arrays.asList(discussion1, discussion2, discussion3);
		for(OpportunityDiscussion discussion : discussions) {
			ODS.save(discussion);
		}

		DiscussionGoal dg1 = new DiscussionGoal(discussion1, goal1, 1);
		DiscussionGoal dg2 = new DiscussionGoal(discussion2, goal1, 1);
		DiscussionGoal dg3 = new DiscussionGoal(discussion3, goal3, 1);
		List<DiscussionGoal> dgs = Arrays.asList(dg1,dg2,dg3);
		for(DiscussionGoal dg : dgs) {
			DGS.save(dg);
		}
	}
	
	public static LocalDate randomDate(List<LocalDate> dates) {
        Random random = new Random();
        int index = random.nextInt(dates.size());
        return dates.get(index);
    }

}
	