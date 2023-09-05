package preprocessor;

public class PreProcessorToUpperImpl implements PreProcessor{
    public PreProcessorToUpperImpl() {

    }

    @Override
    public String toCase(String str) {
        return str.toUpperCase();
    }
}
