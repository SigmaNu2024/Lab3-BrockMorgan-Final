import java.util.List;
import java.util.stream.Collectors;

public class OscarAwardFilter
{
    public List<OscarAward> filterByCategory(List<OscarAward> awards, String category)
    {
        return awards.stream()
                .filter(award -> award.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<OscarAward> filterByYear(List<OscarAward> awards, int screenYear) {
        return awards.stream()
                .filter(award -> award.getYearFilm() == screenYear)
                .collect(Collectors.toList());
    }

    public List<OscarAward> filterByCeremonyYear(List<OscarAward> awards, int ceremonyYear) {
        return awards.stream()
                .filter(award -> award.getYearCeremony() == ceremonyYear)
                .collect(Collectors.toList());
    }


}
