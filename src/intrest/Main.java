package intrest;

import java.util.Date;

public class Main {
    private static final DateCalculator calc
            = new DateCalculator(DateCalculator.GMT);

    public static void main(String[] args) {
        double amount = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        Date startDate = calc.parseDate(args[2]);
        Date date = calc.parseDate(args[3]);
        double trate = 1.0;
        while (true) {
            Date next = calc.addYears(startDate, 1);
            if (next.after(date)) {
                break;
            }
            trate *= (1+rate);
            startDate = next;
        }
        trate *= Math.pow(1+rate,
                (double)DateCalculator.daysBetween(startDate, date)
                        /(double)calc.yearOf(startDate));
        System.out.println("Rate: " + trate);
        System.out.println("Amount: " + Math.round(20*amount*trate)/20.);
    }
}
