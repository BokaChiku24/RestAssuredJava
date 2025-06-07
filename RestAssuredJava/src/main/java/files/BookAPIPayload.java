package files;

public class BookAPIPayload {
	
    public static String addBookJSON(String isbn, String aisle){
        return "{\n" +
                "\n" +
                "\"name\":\"iOS\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"Steve Jobs\"\n" +
                "}\n";
    }

}
