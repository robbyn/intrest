package intrest;

import java.util.Date;

public class Main {
    private static final Dates CALC = new Dates(Dates.GMT);

    public static void main(String[] args) {
        double amount = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        Date startDate = CALC.parseDate(args[2]);
        Date endDate = CALC.parseDate(args[3]);
        double trate = totalRate(startDate, endDate, rate);
        System.out.println("Rate: " + trate);
        System.out.println("Amount: " + Math.round(20*amount*trate)/20.);
    }

    public static double totalRate(Date startDate, Date endDate,
            double annualRate) {
        double trate = 1.0;
        while (true) {
            Date next = CALC.addYears(startDate, 1);
            if (next.after(endDate)) {
                break;
            }
            trate *= (1+annualRate);
            startDate = next;
        }
        trate *= Math.pow(1+annualRate,
                (double)Dates.daysBetween(startDate, endDate)
                        /(double)Dates.daysInYear(CALC.yearOf(startDate)));
        return trate;
    }
}
