/*
It's a global task class that describes main task functionality
This class is extended by others task classes
 */


package sample;

import java.lang.String;
import java.lang.Exception;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

// this exception throws if date format isn't good

class WrongFormatException extends Exception {
    public WrongFormatException() {
        super();
    }
    public WrongFormatException(String s) {
        super(s);
    }
}

// !____________________________________________________________________________!

// begin of abstract GlobalTask class

public abstract class GlobalTask {
    private int id; // use for database as primary key, possible to get
    private float importance; // the key-concept in task management, can be modified
    private String description;
    private String theme; // description
    private String deadline; // task ends
    private String start; // task begins
    private boolean done; // true if task is done
    private char digits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}; // digits array is used to check date format
    private long deadlineComparisonValue; // is used for task finish optimisation in TaskManager class

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

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
            return false;
        }
        for(int i=0; i < date.length(); i++) {
            switch (i) {
                case 2:
                case 5:
                    if(date.charAt(i) != '-') return false;
                case 10:
                    if(date.charAt(i) != ' ') return false;
                case 13:
                case 16:
                    if(date.charAt(i) != ':') return false;
                default:
                    if(! inDigits(date.charAt(i)) ) return false;
            }
        }
        return true;
    }

    //___________________________________________________________________


    // beginning of constructor

    public GlobalTask(int id, float importance, String theme, String description, String deadline) throws WrongFormatException {
        this.id = id;
        this.importance = importance;
        this.theme = theme;
        this.description = description;
        if(checkFormat(deadline)) this.deadline = deadline;
        else {
            throw new WrongFormatException("You use wrong format");
        }
        LocalDateTime nowInstant = LocalDateTime.now();
        this.start = nowInstant.format(this.formatter);
        this.done = false;
        try {
            this.deadlineComparisonValue = this.format.parse(deadline).getTime();
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

    public float getImportance() {
        return this.importance;
    }

    public void setImportance(float importanceValue) {
        this.importance = importanceValue;
    }

    //__________________________________

    //calculates time remaining

    public long[] timeRemaining() {
        LocalDateTime nowInstant = LocalDateTime.now();
        String dateNowString = nowInstant.format(this.formatter);
        Date dateNow = null;
        Date dateEnd = null;
        long Seconds = 0;
        long Minutes = 0;
        long Hours = 0;
        long Days = 0;
        try {
            dateNow = this.format.parse(dateNowString);
            dateEnd = this.format.parse(this.deadline);
            long remainingTime = dateEnd.getTime() - dateNow.getTime();
            if (remainingTime <= 0) {
                long[] timer = {0,0,0,0}; // returns 0 if task must be finished and hasn't been finished yet
                return timer;
            }
            Seconds = remainingTime / 1000 % 60;
            Minutes = remainingTime / (60 * 1000) % 60;
            Hours = remainingTime / (60 * 60 * 1000) % 24;
            Days = remainingTime / (24 * 60 * 60 * 1000);
        } catch(Exception e ) {
            e.printStackTrace();
        }
        long[] timer = {Days,Hours,Minutes,Seconds};
        return timer;
    }

    //__________________________________
}
