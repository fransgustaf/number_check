import java.time.LocalDate;

public class LenientDate {
        private int year;
        private int month;
        private int day;

        LenientDate(String date) {
            year = Integer.parseInt(date.substring(0, 4));
            month = Integer.parseInt(date.substring(4, 6));
            day = Integer.parseInt(date.substring(6, 8));
        }
        
        public boolean checkStrictDate() {
            try {
                LocalDate.of(year, month, day);
            } catch (Exception e) {
                System.out.println("Invalid date");
                return false;
            }
            return true;
        }
        // Getter
        public int getDay() {
          return day;
        }

        public int getMonth() {
            return month;
        }

        public int getYear() {
            return year;
        }
}
