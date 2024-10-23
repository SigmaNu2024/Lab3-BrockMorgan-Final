import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class OscarAwardData {
    public List<OscarAward> readDataFromCSV(String fileName) {
        List<OscarAward> awards = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null){
                String[] data = line.split(",");
                OscarAward award = new OscarAward();
                award.setYearFilm(Integer.parseInt(data[0]));
                award.setYearCeremony(Integer.parseInt(data[1]));
                award.setCeremony(Integer.parseInt(data[2]));
                award.setCategory(data[3]);
                award.setNominee(data[4]);
                award.setFilmTitle(data[5]);
                award.setWinner(Boolean.parseBoolean(data[6]));
                awards.add(award);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return awards;
    }
}
