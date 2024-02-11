package data;

public class ArgumentValidations {
    private static ArgumentValidations instance;
    
    private int[] validWidths;
    private int[] validHeights;

    private ArgumentValidations() {}

    public synchronized static ArgumentValidations getInstance() {
        if (instance == null) {
            instance = new ArgumentValidations();
        }

        return instance;
    }

    public void setValidWidths(int[] validWidths) {
        this.validWidths = validWidths;
    }
    
    public int[] getValidWidths() {
        return validWidths;
    }
    
    public void setValidHeights(int[] validHeights) {
        this.validHeights = validHeights;
    }
    
    public int[] getValidHeights() {
        return validHeights;
    }
}
