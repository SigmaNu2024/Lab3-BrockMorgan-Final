import javax.swing.*;
import java.awt.*;

public class DetailsPanel extends JPanel {
    private JLabel[] detailLabels;

    public DetailsPanel()
    {
        setLayout(new GridLayout(6, 1));
        setBorder(BorderFactory.createTitledBorder("Details"));

        // Initialize Oscar labels
        String[] labelNames = {
                "Screen Year: ",
                "Ceremony Year: ",
                "Category: ",
                "Nominee: ",
                "Film Title: ",
                "Winner: "
        };
        detailLabels = new JLabel[labelNames.length];

        for (int i = 0; i < labelNames.length; i++)
        {
            detailLabels[i] = new JLabel(labelNames[i]);
            add(detailLabels[i]);
        }
    }

    public void updateDetails(OscarAward award) {
        if (award != null)
        {
            detailLabels[0].setText("Screen Year: " + award.getYearFilm());
            detailLabels[1].setText("Ceremony Year: " + award.getYearCeremony());
            detailLabels[2].setText("Category: " + award.getCategory());
            detailLabels[3].setText("Nominee: " + award.getNominee());
            detailLabels[4].setText("Film Title: " + award.getFilmTitle());
            detailLabels[5].setText("Winner: " + (award.isWinner() ? "Yes" : "No"));
        } else {
            // Reset labels if no award is selected
            for (JLabel label : detailLabels) {
                label.setText(label.getText().split(":")[0] + ": ");
            }
        }
    }
}

