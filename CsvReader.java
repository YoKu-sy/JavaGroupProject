import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

class CsvReader {

    public static <T> List<T> ReadCsv(String filename, Function<String[], T> mapper){

        List<T> allCandidate = new ArrayList<>();
        List<String> location_id = new ArrayList<>();
        List<Integer> priority_score = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            br.readLine(); //skip first line in csv, avoid type exception
            String content;
            //start loop, split location_id and priority_score to construct each candidate instance
            while (true){
                content = br.readLine();
                if(content == null){
                    break;
                } //break loop if no more content in csv
                String [] contentArray = content.split(",");
                allCandidate.add(mapper.apply(contentArray));
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.print("something goes wrong");//throw a Stack exception if something goes wrong in load csv file process
        }
        //Used ArrayList just in case the CSV has extra or missing lines.It barely affects performance, and we just convert it back to an array at the end using toArray()
        return allCandidate;
    }

}