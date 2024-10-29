import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsPanel extends JPanel {
    private final DefaultTableModel statsTableModel;

    public StatsPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Statistics"));

        String[] columnNames = {"Statistic", "Value"};
        statsTableModel = new DefaultTableModel(columnNames, 1);
        JTable statsTable = new JTable(statsTableModel);

        add(new JScrollPane(statsTable), BorderLayout.CENTER);
    }

    public void updateStats(List<OscarAward> awardsData) {
        if (awardsData == null || awardsData.isEmpty()) return;

        int totalAwards = awardsData.size();
        long totalWinners = awardsData.stream().filter(OscarAward::isWinner).count();

        // Clear the existing data
        statsTableModel.setRowCount(0);

        // Add new data
        statsTableModel.addRow(new Object[]{"Total Awards", totalAwards});
        statsTableModel.addRow(new Object[]{"Total Winners", totalWinners});

        // Calculate awards by category
        Map<String, Long> awardsByCategory = new HashMap<>();
        awardsData.stream()
                .map(OscarAward::getCategory)
                .forEach(category -> awardsByCategory.put(category, awardsByCategory.getOrDefault(category, 0L) + 1));

        StringBuilder categoryStats = new StringBuilder();
        awardsByCategory.forEach((category, count) -> categoryStats.append(category).append(" (").append(count).append("), "));

        if (!categoryStats.isEmpty()) {
            categoryStats.setLength(categoryStats.length() - 2); // Remove trailing comma
        }

        statsTableModel.addRow(new Object[]{"Awards by Category", categoryStats.toString()});
    }
}

