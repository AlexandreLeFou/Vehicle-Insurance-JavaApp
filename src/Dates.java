import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Dates {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    Date date1;

    public Dates() throws ParseException {

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); //timeStamp h shmerini imerominia
        Date date1 = sdf.parse(timeStamp);
        this.date1 = date1;
    }

    public boolean Date2afterDate1(String lastDay) throws ParseException {

        String finishDate = lastDay;  //finishDate last insurance day
        Date date2 = sdf.parse(lastDay);
        if (date2.after(date1)) {
            return true;
        }
        else
        return false;
    }

    public long daysBetween (String lastDay) throws ParseException {

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); //timeStamp h shmerini imerominia
        LocalDate ldA = LocalDate.parse(timeStamp); // ldA current Date
        String finishDate = lastDay;  //finishDate Last insurance day
        LocalDate ldB = LocalDate.parse(finishDate); //finishDate Last insurance day convert to ldB

        long daysBetween = ChronoUnit.DAYS.between(ldA, ldB);
        return daysBetween;
    }
}




