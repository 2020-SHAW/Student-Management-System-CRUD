package net.javaguides.sms.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class DateService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public String calculateAge(String dob) {
        if (dob == null || dob.isEmpty()) {
            return "N/A";
        }

        LocalDate dateOfBirth = LocalDate.parse(dob, DATE_FORMATTER);
        LocalDate now = LocalDate.now();
        Period period = Period.between(dateOfBirth, now);

        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        StringBuilder ageDescription = new StringBuilder();

        if (years > 0) {
            ageDescription.append(years).append(" year");
            if (years > 1) ageDescription.append("s");
        }

        if (months > 0) {
            if (ageDescription.length() > 0) {
                ageDescription.append(" , ");
            }
            ageDescription.append(months).append(" month");
            if (months > 1) ageDescription.append("s");
        }

        if (days > 0) {
            if (ageDescription.length() > 0) {
                ageDescription.append(" , ");
            }
            ageDescription.append(days).append(" day");
            if (days > 1) ageDescription.append("s");
        }

        if (ageDescription.length() == 0) {
            return "Less than a day";
        }

        return ageDescription.toString();
    }
}
