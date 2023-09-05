package preprocessor;

public class PreProcessorToLowerImpl implements PreProcessor{
    public PreProcessorToLowerImpl() {

    }

    @Override
    public String toCase(String str) {
        return str.toLowerCase();
    }
}
