import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
//import java.awt.event.ActionListener;
import java.util.List;

public class OscarAwardsApp extends JFrame {
    private DefaultTableModel tableModel;
    private OscarAwardData datas = new OscarAwardData();
    private final OscarAwardFilter awardFilter = new OscarAwardFilter();
    private List<OscarAward> awardsData;

    public OscarAwardsApp() {
        setTitle("Oscar Awards Data 1927-2018");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeComponents();
        loadData();
    }

    private void initializeComponents() {
        tableModel = new DefaultTableModel(new String[]{"Screen Year", "Ceremony Year", "Category", "Nominee","Film Title", "Winner"}, 0);
        JTable awardTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(awardTable);

        JButton filterButton = new JButton("Filter by Category");
        filterButton.addActionListener(_ -> showFilterDialog());

        JPanel panel = new JPanel();
        panel.add(filterButton);
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.NORTH);
    }

    private void loadData() {
        awardsData = datas.readDataFromCSV("the_oscar_award.csv");
        refreshTable(awardsData);
    }

    private void refreshTable(List<OscarAward> awards) {
        tableModel.setRowCount(0);
        awards.forEach(award -> tableModel.addRow(new Object[]{
                award.getYearFilm(),
                award.getYearCeremony(),
                award.getCategory(),
                award.getNominee(),
                award.getFilmTitle(),
                award.isWinner()
        }));

        awardsData.revalidate();

    }

    private void showFilterDialog() {
        String category = JOptionPane.showInputDialog(this, "Enter the category you want to filter: ");

        if ((category != null) && !category.trim().isEmpty()) {
            List<OscarAward> filteredAwards = awardFilter.filterByCategory(awardsData, category.trim());

            System.out.println("Number of filtered awards: " + filteredAwards.size());
            if (filteredAwards.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No Awards Found");
            } else {
                refreshTable(filteredAwards);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid category");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OscarAwardsApp app;
            app = new OscarAwardsApp();
            app.setVisible(true);
        });
    }


}
