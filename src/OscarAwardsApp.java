import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OscarAwardsApp extends JFrame {
    private DefaultTableModel tableModel;
    private final OscarAwardData datas = new OscarAwardData();
    private final OscarAwardFilter awardFilter = new OscarAwardFilter();
    private List<OscarAward> awardsData;
    private StatsPanel statsPanel;
    private DetailsPanel detailsPanel;

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

        //Initialize filters
        JButton filterButton = new JButton("Filter by Category");
        filterButton.addActionListener(_ -> showFilterDialog());

        JButton filterByYearButton = new JButton("Filter by Screening Year");
        filterByYearButton.addActionListener(_ -> showYearFilterDialog());

        JButton filterByCeremonyYearButton = new JButton("Filter by Ceremony Year");
        filterByCeremonyYearButton.addActionListener(_ -> showCeremonyYearFilterDialog());

        JButton clearFilterButton = new JButton("Clear Filter");
        clearFilterButton.addActionListener(_ -> loadData());

        JPanel panel = new JPanel();
        panel.add(filterButton);
        panel.add(filterByYearButton);
        panel.add(filterByCeremonyYearButton);
        panel.add(clearFilterButton);

        statsPanel = new StatsPanel();
        detailsPanel = new DetailsPanel();

        awardTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = awardTable.getSelectedRow();
                if (selectedRow != -1) {
                    OscarAward selectedAward = awardsData.get(selectedRow);

                    //Update Details Panel
                    detailsPanel.updateDetails(selectedAward);
                } else {
                    detailsPanel.updateDetails(null); // Clear details if no selection
                }
            }
        });

        //Panel Design
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.EAST);
        add(detailsPanel, BorderLayout.SOUTH);

    }


    private void loadData()
    {
        //Load Data From CSV
        awardsData = datas.readDataFromCSV("the_oscar_award.csv");
        refreshTable(awardsData);
        statsPanel.updateStats(awardsData);
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
                //Update Stats Panel for filtered results
                statsPanel.updateStats(filteredAwards);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid category");
        }
    }

    //Outputs number of items removed from filter
    private void showYearFilterDialog() {
        String yearString = JOptionPane.showInputDialog(this, "Enter the screen year you want to filter:");

        if ((yearString != null) && !yearString.trim().isEmpty()) {
            try {
                int year = Integer.parseInt(yearString.trim());
                List<OscarAward> filteredAwards = awardFilter.filterByYear(awardsData, year);

                System.out.println("Number of filtered awards by year: " + filteredAwards.size());
                if (filteredAwards.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No Awards Found for the specified year");
                } else {
                    refreshTable(filteredAwards);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid year");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid year");
        }
    }

    private void showCeremonyYearFilterDialog() {
        String ceremonyYearString = JOptionPane.showInputDialog(this, "Enter the ceremony year you want to filter:");

        if ((ceremonyYearString != null) && !ceremonyYearString.trim().isEmpty()) {
            try {
                int ceremonyYear = Integer.parseInt(ceremonyYearString.trim());
                List<OscarAward> filteredAwards = awardFilter.filterByCeremonyYear(awardsData, ceremonyYear);

                System.out.println("Number of filtered awards by ceremony year: " + filteredAwards.size());
                if (filteredAwards.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No Awards Found for the specified ceremony year");
                } else {
                    refreshTable(filteredAwards);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid ceremony year");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid ceremony year");
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
