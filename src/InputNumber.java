import java.time.LocalDate;

class InputNumber {
    private enum Type {
        PERSON_NUMMER, SAMORDNINGS_NUMMER, ORGANISATIONS_NUMMER
    }

    private String input;
    private LenientDate date;
    private Type type = Type.PERSON_NUMMER;

    InputNumber(String orginalInput) {
        this.input = orginalInput;
        System.out.println("Input: " + this.input);

        String divider = "-";
        if(this.input.indexOf("+") > -1) {
            divider = "+";
        }
        this.input = this.input.replaceAll("[+-]", "");

        if(!sanityCheck()) {
            return;
        }

        if(this.input.length() < 12) {
            
            addPrefix(divider);
        }

        System.out.println("Input after formatting: " + input);

        date = new LenientDate(input.substring(0, 8));

        this.setInputType();
        System.out.println("Type is: " + type);
    }

    private void addPrefix(String divider) {
        // If we have + and short year is later than today's year, we know the person is born in the 18th hundreds
        LocalDate dateNow = LocalDate.now();  
        //(this.input.indexOf("+") > -1 && 1921 < 82 + 1900)
        if( divider.equals("+") && dateNow.getYear()-100 < Integer.parseInt(input.substring(0, 2)) + 1900) {
           this.input = "18" + this.input;
        } else {
            this.input = "19" + this.input;
        }
    }

    private String getControlString() {
        return input.substring(2, 11);
    }

    private int getControlDigit() {
        return Integer.parseInt(input.substring(input.length()-1)); 
    }

    private void setInputType() {
        if(date.getDay() >= 61 && date.getDay()<= 91) {
            type = Type.SAMORDNINGS_NUMMER;
        }

        if(date.getMonth() >= 20) {
            type = Type.ORGANISATIONS_NUMMER;
        }
    }

    private boolean sanityCheck() {

        try {
            Double.parseDouble(this.input);
        } catch (Exception e) {
            System.out.println("Input contains illegal characters, can't continue.");
            return false;
        }

        if(!(this.input.length() == 10 || this.input.length() == 12)) {
            return false;
        }

        return true;
    }

    public boolean verifyInput() {
        boolean dateCheck = true;
        if(type == Type.PERSON_NUMMER) {
            dateCheck = date.checkStrictDate();
        }
        return dateCheck && verifyControlDigit();
    }

    private boolean verifyControlDigit() {
        int sum = 0;
        for (int i = 0; i < getControlString().length(); i++) {            
            int multiplier = (i % 2 == 0 ? 2 : 1);

            int current = Integer.parseInt(String.valueOf(getControlString().charAt(i)));
            
            int currentSum = current * multiplier;
            if(currentSum >= 10) {
                sum += 1 + currentSum % 10;
            } else {
                sum += currentSum;
            }
        }

        int control = (10 - (sum % 10)) % 10;
        
        boolean result = control == getControlDigit();
        System.out.println("Control digit check result: " + result);
        return result;
    }
}