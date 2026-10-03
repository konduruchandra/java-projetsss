package projettss;

import java.util.Scanner;


public class movieBookingSys {
	
	static Scanner sc = new Scanner(System.in);
	
	static boolean[][] seats = new boolean[5][6];
	
	static String movieName = "Arjun Reddy";
	
	static double ticketPrice = 200;
	
	//Seats arrangement
	
	static void showSeats() {
		System.out.println("\n ---------Seat Layout--------");
		
		for (int i = 0; i < seats.length; i ++) {
			System.out.print("Row"+ (i + 1)+":");
			
			for(int j =0; j<seats[i].length; j++) {
				
				if(seats[i][j]) {
					System.out.print("[X]");
				}else {
					System.out.print("["+(j+1)+"]");
				}
			}
			
			System.out.println();
		}
		System.out.println("X=Booked");
	}
	
	static void bookTickets() {
		showSeats();
        System.out.print("\nEnter row number: ");
        int row = sc.nextInt();

        System.out.print("Enter seat number: ");
        int seat = sc.nextInt();
        
        row--;
        seat--;
        
        if(row < 0 || row >= seats.length || seat<0 || seat >= seats[row].length) {
        	System.out.println("Invalid Seat.Please Try again!");
        	return;
        }
        
        seats[row][seat] = true;
        
        
        if(seats[row][seat]) {
        	System.out.println("Sorry! Seat is booked");
        	return;
        }
        
        
        
        double price = ticketPrice;
        
        // Weekend pricing
        System.out.print("Is today weekend? (yes/no): ");
        String weekend = sc.next();

        if (weekend.equalsIgnoreCase("yes")) {

            price = price + 50;

            System.out.println("Weekend charge: ₹50");
        }
        
        // Coupon
        System.out.print("Enter coupon code (or NONE): ");
        String coupon = sc.next();

        if (coupon.equalsIgnoreCase("MOVIE10")) {

            double discount = price * 0.10;
            price = price - discount;

            System.out.println("10% discount applied!");
            
        }else if (!coupon.equalsIgnoreCase("NONE")) {

            System.out.println("Invalid coupon!");
        }
        System.out.println("\n===== BOOKING SUCCESSFUL =====");
        System.out.println("Movie : " + movieName);
        System.out.println("Row   : " + (row + 1));
        System.out.println("Seat  : " + (seat + 1));
        System.out.println("Final Price : ₹" + price);
	}
	
    // Cancel ticket
    static void cancelTicket() {

        showSeats();

        System.out.print("\nEnter row number: ");
        int row = sc.nextInt();

        System.out.print("Enter seat number: ");
        int seat = sc.nextInt();
        
        row--;
        seat--;
        
        // Validate seat
        if (row < 0 || row >= seats.length ||
            seat < 0 || seat >= seats[row].length) {

            System.out.println("Invalid seat!");
            return;
        }
        
        // Check booking
        if (!seats[row][seat]) {

            System.out.println("This seat is not booked.");
            return;
        }
        // Cancel booking
        seats[row][seat] = false;

        System.out.println("Ticket cancelled successfully!");
        System.out.println("Row: " + (row + 1));
        System.out.println("Seat: " + (seat + 1));
    }
    
    // Movie rating
    static void movieRating() {

        System.out.println("\n===== MOVIE RATING =====");

        System.out.println("Movie: " + movieName);

        System.out.print("Enter rating (1 - 5): ");
        int rating = sc.nextInt();

        if (rating < 1 || rating > 5) {

            System.out.println("Invalid rating!");

        } else {

            System.out.println("Thank you for rating " + rating + "/5");
        }
    }



	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int choice;

        do {
        	System.out.println("================================");

            System.out.println("     MOVIE TICKET BOOKING"    );
            System.out.println("================================");

            System.out.println("Movie : " + movieName);
            System.out.println("Base Ticket Price : ₹" + ticketPrice);

            System.out.println("\n1. Show Available Seats");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Movie Rating");
            System.out.println("5. Exit");
            
            System.out.print("\nEnter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    showSeats();
                    break;

                case 2:
                    bookTickets();
                    break;

                case 3:
                    cancelTicket();
                    break;

                case 4:
                    movieRating();
                    break;
                    
                case 5:
                    System.out.println("Thank you! Visit again.");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();

	}

}
