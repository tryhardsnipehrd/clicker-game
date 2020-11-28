import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    //Define everything for later, this is gonna get crazy

    private long currentClicks = 0;
    private int clickMultiplier = 1;
    private long lifetimeClicks = 0;
    private int shopMultiplier = 0;


    private JLabel cash;
    private JLabel lifetime;
    private JFrame frame;
    private JButton clicker;
    private JPanel panel;
    private JButton shop;

    public GUI() {
        // Now that everything is created, actually make them usable

        frame = new JFrame();
        shop = new JButton("Upgrade your clicks");
        clicker = new JButton("Click Me");
        lifetime = new JLabel("Lifetime clicks: "+lifetimeClicks, SwingConstants.CENTER);

        cash = new JLabel("Number of clicks: 0", SwingConstants.CENTER);
        panel = new JPanel();
        // This is where I found the answer for separate buttons
        // https://stackoverflow.com/questions/5911565/how-to-add-multiple-actionlisteners-for-multiple-buttons-in-java-swing/5911621
        clicker.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        currentClicks += 1 * clickMultiplier;
                        lifetimeClicks++;
                        cash.setText("Number of clicks: " + currentClicks);
                        lifetime.setText("lifetime clicks: " + lifetimeClicks);
                    }
                }
        );
        shop.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (currentClicks >= 100+(50*shopMultiplier)){
                            currentClicks -= 100 + (50*shopMultiplier);
                            cash.setText("Number of clicks: " + currentClicks);
                            shopMultiplier++;
                            clickMultiplier++;
                        }

                    }
                }
        );


        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(4, 1));
        panel.add(clicker);
        panel.add(shop);
        panel.add(cash);
        panel.add(lifetime);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Clicker Game");
        frame.pack();
        frame.setVisible(true);


    }

    public static void main(String[] args) {

        new GUI();

    }
}
