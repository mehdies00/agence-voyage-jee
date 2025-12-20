package Model.entity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import Model.dao.DestinationDao;
import Model.dao.FeedbackDao;

public class Teste {
	
	public static void main(String[]args)throws Exception{
		/*String text = "The platform is incredibly intuitive and the results are on point. I highly recommend it for all your travel needs.";
		String dateString = "2025-05-10";
		LocalDate date = LocalDate.parse(dateString);
		Feedback feedback = new Feedback(1,text,date);
		FeedbackDao feedbackDao = new FeedbackDao();
		
		feedbackDao.save(feedback);*/
		/*
		DestinationDao destinationDao = new DestinationDao();

		Destination france = new Destination(
		    "France",
		    "The capital of France, famous for the Eiffel Tower, the Louvre, and Notre-Dame.",
		    "../asset/EiffelParis.jpg",
		    "Paris",
		    "2.1 million",
		    "French",
		    "Euro",
		    List.of("Eiffel Tower", "Louvre", "Notre-Dame", "Champs-Élysées"),
		    List.of("Gastronomy", "Shopping", "Seine River Cruise")
		);
		destinationDao.save(france);
*/
		DestinationDao  destinationDao = new  DestinationDao();
		Collection< Destination>  destinations  = destinationDao.findAll();
		
		for (Destination destination:destinations) {
			System.out.println(destination.getSize());
		}
	}
}
