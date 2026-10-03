/* Online Voting System (Core Java)
Scenario:
A college election where students vote digitally. Each student can vote only once.

Modules:

Register/Login (store users in HashMap)
Candidate list
Vote casting
View Total Votes
Result display - Finalist automatically
Display Runner-Up
Logic Design:

Map<String, Boolean> → track if user voted
Map<String, Integer> → candidate votes
Concepts Covered:

OOP (User, Candidate classes)
Collections (HashMap)
Exception handling */

package projettss;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Userclass {
	private String username;
	private String password;
	
	public Userclass(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
}

class Candidateclass{
    private String id;
    private String name;
    
    public Candidateclass(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}

public class onlineVotingSys {
	
    // Store registered users
    static Map<String, Userclass> users = new HashMap<>();

    // Track whether user has voted
    static Map<String, Boolean> userVoted = new HashMap<>();

    // Store candidates
    static Map<String, Candidateclass> candidates = new HashMap<>();

    // Store candidate votes
    static Map<String, Integer> candidateVotes = new HashMap<>();

    static Scanner sc = new Scanner(System.in);
    
    static void register() {
        System.out.print("Enter Username: ");
        String username = sc.next();
        
        if (users.containsKey(username)) {
            System.out.println("Username already exists!");
            return;
        }
        
        System.out.print("Enter Password: ");
        String password = sc.next();
        
        Userclass user = new Userclass(username, password);

        users.put(username, user);
        userVoted.put(username, false);

        System.out.println("Registration successful!");
    }
    
    // login
    
    static Userclass login() {

        System.out.print("Enter Username: ");
        String username = sc.next();

        System.out.print("Enter Password: ");
        String password = sc.next();

        if (users.containsKey(username)) {

            Userclass user = users.get(username);

            if (user.getPassword().equals(password)) {
                System.out.println("Login successful!");
                return user;
            }
        }

        System.out.println("Invalid username or password!");
        return null;
    }
    
    // DISPLAY CANDIDATES
    static void displayCandidates() {

        System.out.println("\n----- CANDIDATE LIST -----");

        for (Candidateclass candidate : candidates.values()) {

            int votes = candidateVotes.get(candidate.getId());

            System.out.println(
                    candidate.getId() + " - "
                    + candidate.getName()
                    + " | Votes: " + votes
            );
        }
    }
    
    // CAST VOTE
    static void castVote(Userclass user) {

        String username = user.getUsername();

        // Check whether user already voted
        if (userVoted.get(username)) {
            System.out.println("You have already voted!");
            return;
        }
        
        displayCandidates();

        System.out.print("\nEnter Candidate ID: ");
        String candidateId = sc.next();

        // Check candidate exists
        if (!candidates.containsKey(candidateId)) {
            System.out.println("Invalid Candidate ID!");
            return;
        }

        // Increase candidate vote count
        int currentVotes = candidateVotes.get(candidateId);

        candidateVotes.put(candidateId, currentVotes + 1);

        // Mark user as voted
        userVoted.put(username, true);

        System.out.println("Vote cast successfully!");
    }
    
    //  TOTAL VOTES
    static void displayTotalVotes() {

        int totalVotes = 0;

        for (int votes : candidateVotes.values()) {
            totalVotes += votes;
        }

        System.out.println("\nTotal Votes: " + totalVotes);
    }
    
    // RESULT
    static void displayResult() {

        if (candidateVotes.isEmpty()) {
            System.out.println("No candidates available.");
            return;
        }
        
        String winnerId = null;
        String runnerUpId = null;

        int highestVotes = -1;
        int secondHighestVotes = -1;
        
        for (String candidateId : candidateVotes.keySet()) {

            int votes = candidateVotes.get(candidateId);

            // Find highest
            if (votes > highestVotes) {

                secondHighestVotes = highestVotes;
                runnerUpId = winnerId;

                highestVotes = votes;
                winnerId = candidateId;

            }
            // Find second highest
            else if (votes > secondHighestVotes) {

                secondHighestVotes = votes;
                runnerUpId = candidateId;
            }
        }
        System.out.println("\n========== ELECTION RESULT ==========");

        Candidateclass winner = candidates.get(winnerId);

        System.out.println(
                "FINALIST / WINNER : "
                + winner.getName()
                + " (" + highestVotes + " votes)"
        );

        if (runnerUpId != null) {

            Candidateclass runnerUp = candidates.get(runnerUpId);

            System.out.println(
                    "RUNNER-UP         : "
                    + runnerUp.getName()
                    + " (" + secondHighestVotes + " votes)"
            );
        }
        System.out.println("=====================================");
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        // Add candidates
        candidates.put("C1", new Candidateclass("C1", "Rahul"));
        candidates.put("C2", new Candidateclass("C2", "Priya"));
        candidates.put("C3", new Candidateclass("C3", "Arjun"));

        // Initialize votes
        candidateVotes.put("C1", 0);
        candidateVotes.put("C2", 0);
        candidateVotes.put("C3", 0);
        
        while (true) {

            try {

                System.out.println("\n========== ONLINE VOTING SYSTEM ==========");
                System.out.println("1. Register");
                System.out.println("2. Login & Vote");
                System.out.println("3. Candidate List");
                System.out.println("4. Total Votes");
                System.out.println("5. Election Result");
                System.out.println("6. Exit");

                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                
                switch (choice) {

                case 1:
                    register();
                    break;

                case 2:

                    Userclass user = login();

                    if (user != null) {
                        castVote(user);
                    }

                    break;

                case 3:
                    displayCandidates();
                    break;
                    
                case 4:
                    displayTotalVotes();
                    break;

                case 5:
                    displayResult();
                    break;

                case 6:
                    System.out.println("Thank you for using Online Voting System!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }

        }
            catch (Exception e) {

                System.out.println("Invalid input! Please enter a valid value.");

                sc.nextLine();
            }
        }  


	}

}
