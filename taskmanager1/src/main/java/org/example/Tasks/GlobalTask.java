/*
It's a global task class that describes main task functionality
This class is extended by others task classes
 */


package org.example.Tasks;

import java.lang.String;
import java.lang.Exception;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


// begin of abstract GlobalTask class

public abstract class GlobalTask {
    // this exception throws if date format isn't good

    public static class WrongFormatException extends Exception {
        public WrongFormatException() {
            super();
        }
        public WrongFormatException(String s) {
            super(s);
        }
    }

    // !____________________________________________________________________________!

    private final int id; // use for database as primary key, possible to get
    private double importance; // the key-concept in task management, can be modified
    private String description;
    private String theme; // description
    private String deadline; // task ends
    private String start; // task begins
    private boolean done; // true if task is done
    private char digits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}; // digits array is used to check date format
    private long deadlineComparisonValue; // is used for task finish optimisation in TaskManager class

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); // this formatter is used to format date
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // this format is used to parse date and get time

    // checks if symbol is in digits array

    protected boolean inDigits(char symbol) {
        for(int i=0; i<10; i++) {
            if(symbol == digits[i]) return true;
        }
        return false;
    }

    // ______________________________________________________

    // checks date format

    protected boolean checkFormat(String date) {
        if(date.length() != 19) {
            return false; // length must be of 19 characters
        }
        for(int i=0; i < date.length(); i++) {
            switch (i) { // it's important to use break, because if not cases are mixed and it doesn't work
                case 2:
                case 5:
                    if(date.charAt(i) != '-') return false; // - between date parameters
                    break;
                case 10:
                    if(date.charAt(i) != ' ') return false; // space between date and time
                    break;
                case 13:
                case 16:
                    if(date.charAt(i) != ':') return false; // : between time parameters
                    break;
                default:
                    if(!inDigits(date.charAt(i)) ) return false; // other characters must be digits
                    break;
            }
        }
        return true;
    }

    //___________________________________________________________________


    // beginning of constructor

    public GlobalTask(int id, double importance, String theme, String description, String deadline) throws WrongFormatException {
        this.id = id;
        this.importance = importance;
        this.theme = theme;
        this.description = description;
        if(checkFormat(deadline)) this.deadline = deadline;
        else {
            throw new WrongFormatException("You use wrong format " + deadline); // exception defined before
        }
        LocalDateTime nowInstant = LocalDateTime.now();
        this.start = nowInstant.format(this.formatter);
        this.done = false;
        try {
            this.deadlineComparisonValue = this.format.parse(deadline).getTime(); // is used for comparison in TaskManager
        } catch(ParseException e) {
            e.printStackTrace();
        }

    }

    // end of constructor _______________________________

    // true if finished

    public boolean isFinished() {
        return done;
    }

    //________________________________

    //finishes task

    public void finish() {
        this.done  = true;
    }

    //_________________________________


    // Getters ans setters

    public int getId() {
        return this.id;
    }

    public double getImportance() {
        return this.importance;
    }

    public void setImportance(float importanceValue) {
        this.importance = importanceValue;
    }
    public String getStart() {
        return this.start;
    }
    public void setStart(String start) {
        this.start = start;
    }
    public String getDeadline() { return this.deadline; }
    public long getDeadlineComparisonValue() { return this.deadlineComparisonValue; }

    //__________________________________

    //calculates time remaining

    public long[] timeRemaining() {
        LocalDateTime nowInstant = LocalDateTime.now(); // returns date and time in this instant
        String dateNowString = nowInstant.format(this.formatter); // string formatted and corresponding to this instant
        Date dateNow = null;
        Date dateEnd = null;
        long Seconds = 0;
        long Minutes = 0;
        long Hours = 0;
        long Days = 0;
        try {
            dateNow = this.format.parse(dateNowString);
            dateEnd = this.format.parse(this.deadline);
            long timeNow = dateNow.getTime();
            long timeEnd = dateEnd.getTime();
            if (timeEnd <= timeNow) {
                long[] timer = {0,0,0,0}; // returns 0 if task must be finished and hasn't been finished yet
                return timer;
            }
            long remainingTime = dateEnd.getTime() - dateNow.getTime(); // calculates time remaining using dateNow and dateEnd
            Seconds = remainingTime / 1000 % 60;
            Minutes = remainingTime / (60 * 1000) % 60;
            Hours = remainingTime / (60 * 60 * 1000) % 24;
            Days = remainingTime / (24 * 60 * 60 * 1000);
        } catch(Exception e ) {
            e.printStackTrace();
        }
        long[] timer = {Days,Hours,Minutes,Seconds};
        return timer; // returns Days, Hours, Minutes and Seconds remaining
    }

    //__________________________________
}
