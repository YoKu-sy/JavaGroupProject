import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// new 在这里的代码修改是为了 import the Function interface to pass the parsing behavior as a parameter
import java.util.function.Function;

class CsvReader {

    // new 在这里的代码修改是为了 make the method generic (<T>) and accept a custom parser function to handle different data types
    public static <T> List<T> ReadCsv(String filename, Function<String[], T> mapper){

        // new 在这里的代码修改是为了 change the list type from specific Candidate to generic T
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
                // new 在这里的代码修改是为了 apply the custom mapper function to dynamically convert the string array into a generic object
                allCandidate.add(mapper.apply(contentArray));
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.print("something goes wrong");//throw a Stack exception if something goes wrong in load csv file process
        }
        // new 在这里的代码修改是为了 return the List directly because creating a generic array (T[]) is not allowed in Java due to type erasure
        return allCandidate;//Used ArrayList just in case the CSV has extra or missing lines.It barely affects performance, and we just convert it back to an array at the end using toArray()
    }

}