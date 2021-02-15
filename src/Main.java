class Main {
    public static void main(String[] args) {
        if(args.length > 0) {
            var number = new InputNumber(args[0]);
            if(number.verifyInput()) {
                System.out.println("Input verified");
            } else {
                System.out.println("Input has issues");                
            }
            
        }
    }
}