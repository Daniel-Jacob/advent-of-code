public class NumberElement implements Element {

     int value;
    public NumberElement(String integerNumber) {
        try {
        value = Integer.parseInt(integerNumber);
        } catch (Exception e) {
            System.out.println("FUCK!! ");
        }
    }
}
