public class GregorianDate extends Date {

    public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        int rtnValue = 0;
        for (int m = 0; m < month() - 1; m++) {
            rtnValue += monthLengths[m];
        }
        return rtnValue + dayOfMonth();
    }
    public GregorianDate nextDate(){
        int date = super.dayOfMonth();
        int month = super.month();
        int year = super.year();
        if(date==monthLengths[month-1]){
            date=1;
            if(month==12){
                month=1;
                year++;
            }
            else{
                month++;
            }
        }
        else{
            date++;
        }
        GregorianDate a = new GregorianDate(year,month,date);
        return a;
    }

}
