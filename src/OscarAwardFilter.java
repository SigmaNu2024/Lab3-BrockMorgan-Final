import java.util.List;
import java.util.stream.Collectors;

public class OscarAwardFilter {
    public List<OscarAward> filterByCategory(List<OscarAward> awards, String category) {
        awards.stream()
                .filter(award -> award.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
        return awards;
    }
}
