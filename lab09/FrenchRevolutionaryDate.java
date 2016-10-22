public class FrenchRevolutionaryDate extends Date {

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }

    public FrenchRevolutionaryDate nextDate(){
        int date = super.dayOfMonth();
        int month = super.month();
        int year = super.year();
        if(month==13){
            if(date==5){
                month = 1;
                date =1;
            }
        }
        else{
            if(date==30){
                month++;
                date =1;
            }
            else{
                date++;
            }
        }
        FrenchRevolutionaryDate a = new FrenchRevolutionaryDate(year,month,date);
        return a;
    }

}
